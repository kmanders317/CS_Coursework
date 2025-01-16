package core;

import edu.princeton.cs.algs4.StdDraw;
import tileengine.TETile;
import tileengine.Tileset;
import utils.NPCDialogue;

import java.util.*;

import static utils.FileUtils.writeFile;

public class World {
    public static final int WIDTH = 85;
    public static final int HEIGHT = 50;
    final int ROOMDIMENSION = 9;
    private TETile[][] world = new TETile[WIDTH][HEIGHT];
    int tilesUsed;
    List<Room> listOfRooms = new ArrayList<>();
    UnionFind roomConnections;
    protected int avatarX;
    protected int avatarY;
    Random r;
    String seed;
//    List<NPC> listOfNPCs = new ArrayList<>();
    boolean portuguese;
//    Map<String, String> dialogue = new NPCDialogue().dialogue();
    TETile floor = Tileset.FLOOR;
    TETile wall = Tileset.WALL;
    TETile avatar = Tileset.AVATAR;
    TETile blackFloor = Tileset.BLACKFLOOR;
    TETile blackWall = Tileset.BLACKWALL;
    TETile coin = Tileset.COIN;
    boolean fullView;
    boolean forestTheme;
    int numCoins = 0;
    int score = 0;
    int score2 = 0;
    boolean secondPlayer;
    TETile avatar2 = Tileset.AVATAR2;
    int avatar2X;
    int avatar2Y;
    List<Integer> listOfRemovedCoinCoordinates = new ArrayList<>();
    int removedCoins;


    public World(String seed, boolean portuguese, boolean forestTheme, boolean secondPlayer) {
        this.seed = seed;
        this.portuguese = portuguese;
        fullView = true;
        this.forestTheme = forestTheme;
        this.secondPlayer = secondPlayer;
        if (forestTheme) {
            this.floor = Tileset.FORESTFLOOR;
            this.wall = Tileset.TREE;
            this.coin = Tileset.FORESTCOIN;
            this.avatar = Tileset.FLOWER;
            if (secondPlayer) {
                this.avatar2 = Tileset.FLOWER2;
            }
        }

        //Set all tiles to nothing initially
        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < HEIGHT; y++) {
                world[x][y] = Tileset.NOTHING;
            }
        }
        r = new Random(Long.parseLong(seed));
        while ((double) tilesUsed / (WIDTH * HEIGHT) < 0.5) {
            int nextTileX = Math.abs(r.nextInt() % (WIDTH));
            int nextTileY = Math.abs(r.nextInt() % (HEIGHT));
            // check whether tile is already part of a room
            if (!isRoom(nextTileX, nextTileY)) {
                int roomH = Math.abs(r.nextInt() % 4) + ROOMDIMENSION;
                int roomW = Math.abs(r.nextInt() % 4) + ROOMDIMENSION;
                roomAlgorithm(roomW, roomH, nextTileX, nextTileY);
            }
        }
        //union find to show us all connected rooms
        roomConnections = new UnionFind(listOfRooms.size());
        for (Room r1: listOfRooms) {
            for (Room r2: listOfRooms) {
                if (!r1.equals(r2)) {
                    if (!roomConnections.connected(listOfRooms.indexOf(r1), listOfRooms.indexOf(r2))) {
                        r1.connectIfAdjoiningWalls(r2);
                    }
                }
            }
        }
        // iterate through all rooms in room connections to ensure that they are attached to something, and if not
        // connect them to the nearest room
        for (int i = 0; i < listOfRooms.size(); i++) {
            if (roomConnections.parent(i) == -1) {
                // get index of closest room and attach them
                listOfRooms.get(i).attachToClosestRoom(listOfRooms);
            }
        }
        while (!fullyConnected()) {
            joinSets();
        }
        placeAvatar();
//        for (int i = 0; i < 3; i++) {
//            NPC n = new NPC();
//            listOfNPCs.add(n);
//        }
        if (secondPlayer) {
            placeAvatar2();
        }
        for (int i = 0; i < 10; i++) {
            placeCoin();
            numCoins++;
        }
    }

    public boolean roomChecker(int startX, int startY, int width, int height) {
        if (startX < 0 || startY < 0 || startX + width > WIDTH - 1 || startY + height > HEIGHT - 1) {
            throw new IllegalArgumentException("The given room is outside of the bounds of the frame");
        }
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (isRoom(startX + i, startY + j)) {
                    return false;
                }
            }
        }
        return true;
    }

    public void roomAlgorithm(int roomW, int roomH, int nextTileX, int nextTileY) {
        // long and complicated, but the essential point is that it tries to create the largest possible room
        // with the start tile given, minimum room size 2x2 (4x4 with walls)
        int rightV = validSpaces(true, 1, roomW, nextTileX, nextTileY);
        int leftV = validSpaces(true, -1, roomW, nextTileX, nextTileY);
        if (rightV + leftV >= 4) {
            roomW = Math.min(roomW, rightV + leftV);
            int upV = validSpaces(false, 1, roomH, nextTileX, nextTileY);
            int downV = validSpaces(false, -1, roomH, nextTileX, nextTileY);
            if (upV + downV >= 4) {
                roomH = Math.min(roomH, upV + downV);
                boolean validRoom = false;
                while (!validRoom && roomW >= 4) {
                    int tempH = roomH;
                    while (!validRoom && tempH >= 4) {
                        outer:
                        for (int i = 0; i < (upV + downV) - tempH + 1; i++) {
                            for (int j = 0; j < (rightV + leftV) - roomH + 1; j++) {
                                int startX = nextTileX - (roomW - rightV) - i;
                                int startY = nextTileY - (tempH - upV) - j;
                                if (startX >= 0 && startY >= 0 && startX + roomW < WIDTH && startY + tempH < HEIGHT
                                        && roomChecker(startX, startY, roomW, tempH)) {
                                    // set dimensions then break
                                    Room room = new Room(startX, startY, roomW, tempH);
                                    listOfRooms.add(room);
                                    validRoom = true;
                                    break outer;
                                }
                            }
                        }
                        tempH--;
                    }
                    roomW--;
                }
            }
        }
    }

    public class Room {
        int startX;
        int startY;
        int width;
        int height;

        public Room(int startX, int startY, int width, int height) {
            this.startX = startX;
            this.startY = startY;
            this.width = width;
            this.height = height;

            for (int i = startX; i < startX + width; i++) {
                for (int j = startY; j < startY + height; j++) {
                    boolean isWall = i == startX || j == startY || i == startX + width - 1 || j == startY + height - 1;
                    if (isWall) {
                        world[i][j] = wall;
                    }
                    if (!isWall) {
                        world[i][j] = floor;
                    }
                    tilesUsed++;
                }
            }
        }

        public void connectIfAdjoiningWalls(Room room) {
            // if the rooms are touching on the right or left
            if (room.startX + room.width == this.startX || this.startX + this.width == room.startX) {
                Room rightRoom = room;
                if (room.startX + room.width == this.startX) {
                    rightRoom = this;
                }
                if (((this.startY <= room.startY) && (room.startY <= this.startY + this.height))
                        || ((room.startY <= this.startY) && (this.startY <= room.startY + room.height))) {
                    // finds lower of the top ends of the two rooms, and then subtracts the higher of the bottom ends
                    int adjTiles = Math.min(this.startY + this.height, room.startY + room.height)
                            - Math.max(this.startY, room.startY);
                    if (adjTiles >= 3) {
                        int startValue = Math.max(this.startY, room.startY) + Math.floorDiv(adjTiles, 2);
                        world[rightRoom.startX - 1][startValue] = floor;
                        world[rightRoom.startX][startValue] = floor;
                        roomConnections.union(listOfRooms.indexOf(this), listOfRooms.indexOf(room));
                    }
                }
            }
            // if the rooms are touching on the top or bottom
            if (this.startY + this.height == room.startY || room.startY + room.height == this.startY) {
                Room topRoom = room;
                if (room.startY + room.height == this.startY) {
                    topRoom = this;
                }
                if (((this.startX <= room.startX) && (room.startX <= this.startX + this.width))
                        || ((room.startX <= this.startX) && (this.startX <= room.startX + room.width))) {
                    int adjTiles = Math.min(this.startX + this.width, room.startX + room.width)
                            - Math.max(this.startX, room.startX);
                    if (adjTiles >= 3) {
                        int startValue = Math.max(this.startX, room.startX) + Math.floorDiv(adjTiles, 2);
                        world[startValue][topRoom.startY - 1] = floor;
                        world[startValue][topRoom.startY] = floor;
                        roomConnections.union(listOfRooms.indexOf(this), listOfRooms.indexOf(room));
                    }
                }
            }
        }

        public void attachToClosestRoom(List<Room> roomList) {
            // I am planning to reuse this function, and pass in a list of the rooms that are already connected to
            // each other through the quick union, and it will attach whichever room is the closest.
            double minDistance = WIDTH * HEIGHT;
            int indexOfDesiredRoom = 0;
            for (Room room : roomList) {
                if (!this.equals(room)) {
                    double thisDistance = Math.sqrt(Math.pow((this.startX - room.startX), 2) + Math.pow((this.startY - room.startY), 2));
                    if (thisDistance < minDistance) {
                        minDistance = thisDistance;
                        indexOfDesiredRoom = roomList.indexOf(room);
                    }
                }
            }
            // we need to attach the two rooms
            Room startRoom = this;
            Room endRoom = roomList.get(indexOfDesiredRoom);
            // start with right room and end with left
            // startRoom is always determined by the X value
            if (this.startX < roomList.get(indexOfDesiredRoom).startX) {
                startRoom = roomList.get(indexOfDesiredRoom);
                endRoom = this;
            }
            // makes sure that you actually need to move in the x direction
            if (this.startX != roomList.get(indexOfDesiredRoom).startX) {
                makeHallway(true, -1, startRoom.startX, startRoom.startY, startRoom.startX - endRoom.startX);
            }
            if (startRoom.startY != endRoom.startY) {
                if (startRoom.startY > endRoom.startY) {
                    makeHallway(false, 1, endRoom.startX, endRoom.startY, startRoom.startY - endRoom.startY);
                }
                if (startRoom.startY < endRoom.startY) {
                    makeHallway(false, -1, endRoom.startX, endRoom.startY, endRoom.startY - startRoom.startY);
                }
            }
        }
    }

    public double joinClosestRooms(List<Room> firstSet, List<Room> secondSet, boolean execute) {
        double minDistance = WIDTH * HEIGHT;
        List<Room> desiredRooms = new ArrayList<>();
        for (Room room : firstSet) {
            for (Room room2 : secondSet) {
                double thisDistance = Math.sqrt((room.startX - room2.startX) ^ 2 + (room.startY - room2.startY) ^ 2);
                if (thisDistance < minDistance) {
                    minDistance = thisDistance;
                    desiredRooms = new ArrayList<>();
                    desiredRooms.add(room);
                    desiredRooms.add(room2);
                }
            }
        }
        if (execute) {
            Room startRoom = desiredRooms.get(0);
            Room endRoom = desiredRooms.get(1);
            if (desiredRooms.get(0).startX < desiredRooms.get(1).startX) {
                startRoom = desiredRooms.get(1);
                endRoom = desiredRooms.get(0);
            }
            if (startRoom.startX != endRoom.startX) {
                makeHallway(true, -1, startRoom.startX, startRoom.startY, startRoom.startX - endRoom.startX);
            }
            if (startRoom.startY != endRoom.startY) {
                if (startRoom.startY > endRoom.startY) {
                    makeHallway(false, 1, endRoom.startX, endRoom.startY, startRoom.startY - endRoom.startY);
                }
                if (startRoom.startY < endRoom.startY) {
                    makeHallway(false, -1, endRoom.startX, endRoom.startY, endRoom.startY - startRoom.startY);
                }
            }
            roomConnections.union(listOfRooms.indexOf(startRoom), listOfRooms.indexOf(endRoom));
        }
        return minDistance;
    }

    public void joinSets() {
        List<List<Room>> listOfSets = new ArrayList<>();
        List<Room> tempList3 = new ArrayList<>();
        boolean added = false;
        for (int i = 0; i < listOfRooms.size(); i++) {
            if (i == 0) {
                tempList3.add(listOfRooms.get(i));
                listOfSets.add(tempList3);
            }
            if (i != 0) {
                added = false;
                for (int j = 0; j < listOfSets.size(); j++) {
                    if (roomConnections.connected(i, listOfRooms.indexOf(listOfSets.get(j).get(0)))) {
                        listOfSets.get(j).add(listOfRooms.get(i));
                        added = true;
                        break;
                    }
                }
                if (!added) {
                    List<Room> tempList4 = new ArrayList<>();
                    tempList4.add(listOfRooms.get(i));
                    listOfSets.add(tempList4);
                }
            }
        }
        if (listOfSets.size() == 2) {
            joinClosestRooms(listOfSets.get(0), listOfSets.get(1), true);
        }
        if (listOfSets.size() > 2) {
            double minDistance = WIDTH * HEIGHT;
            List<List<Room>> desiredLists = new ArrayList<>();
            for (List<Room> list : listOfSets) {
                for (List<Room> list2 : listOfSets) {
                    if (list != list2) {
                        double thisDistance = joinClosestRooms(list, list2, false);
                        if (thisDistance < minDistance) {
                            minDistance = thisDistance;
                            desiredLists = new ArrayList<>();
                            desiredLists.add(list);
                            desiredLists.add(list2);
                        }
                    }
                }
            }
            joinClosestRooms(desiredLists.get(0), desiredLists.get(1), true);
        }
    }

    public void makeHallway(boolean isX, int dir, int x, int y, int dimension) {
        // I used very similar logic to the room building. One small difference is that I didn't want to write
        // over existing floor tiles with walls, or write over the wall of a room when you're starting a y hallway.
        // for the dir variable, up is positive and right is positive
        for (int i = 0; i < dimension + 1; i++) {
            if (isX) {
                int nextX = x - i;
                if (world[nextX][y] != floor) {
                    world[nextX][y] = wall;
                }
                if (i != dimension) {
                    world[nextX][y + 1] = floor;
                }
                // finishing the hallway if it does not exist in a room
                if (i == dimension && world[nextX][y + 1] != floor) {
                    world[nextX][y + 1] = wall;
                }
                if (world[nextX][y + 2] != floor) {
                    world[nextX][y + 2] = wall;
                }
            }
            if (!isX) {
                if (dir == 1 || i != dimension) {
                    int nextY = y + (i * dir);
                    if (world[x][nextY] != floor) {
                        world[x][nextY] = wall;
                    }
                    if (dir != 1 || i != 0) {
                        world[x + 1][nextY] = floor;
                    }
                    if (world[x + 2][nextY] != floor) {
                        world[x + 2][nextY] = wall;
                    }
                }
            }
        }
    }

    public TETile[][] worldRep() {
        return world;
    }

    public boolean isRoom(int x, int y) {
        return world[x][y] == floor || world[x][y] == wall;
    }

    public int validSpaces(boolean x, int dir, int dimension, int xTile, int yTile) {
        // right and up are positive
        int variable = 0;
        for (int i = 0; i < dimension; i++) {
            if (x) {
                int next = xTile + (i * dir);
                if (next < 0 || next >= WIDTH || isRoom(next, yTile)) {
                    break;
                }
            }
            if (!x) {
                int next = yTile + (i * dir);
                if (next < 0 || next >= HEIGHT || isRoom(xTile, next)) {
                    break;
                }
            }
            variable++;
        }
        return variable;
    }

    public boolean fullyConnected() {
        return roomConnections.sizeOf(0) == listOfRooms.size();
    }

    public void placeAvatar() {
        boolean placed = false;
        while (!placed) {
            int x = Math.abs(r.nextInt() % (WIDTH));
            int y = Math.abs(r.nextInt() % (HEIGHT));
            if (world[x][y] == floor) {
                world[x][y] = avatar;
                avatarX = x;
                avatarY = y;
                placed = true;
            }
        }
    }

    public void placeAvatar2() {
        boolean placed = false;
        while (!placed) {
            int x = Math.abs(r.nextInt() % (WIDTH));
            int y = Math.abs(r.nextInt() % (HEIGHT));
            if (world[x][y] == floor) {
                world[x][y] = avatar2;
                avatar2X = x;
                avatar2Y = y;
                placed = true;
            }
        }
    }

    public void moveAvatar(int x, int y) {
        world[avatarX][avatarY] = floor;
        avatarX = x;
        avatarY = y;
        world[x][y] = avatar;
    }

    public void moveAvatar2(int x, int y) {
        world[avatar2X][avatar2Y] = floor;
        avatar2X = x;
        avatar2Y = y;
        world[x][y] = avatar2;
    }

//    public class NPC {
//        int x;
//        int y;
//
//        public NPC() {
//            boolean placed = false;
//            while (!placed) {
//                int x1 = Math.abs(r.nextInt() % (WIDTH));
//                int y1 = Math.abs(r.nextInt() % (HEIGHT));
//                // checks that tile is a valid floor tile and that the NPC is not spawning in a hallway (although I haven't yet covered the case of a hallway that runs in both directions
//                if (world[x1][y1] == floor && ((world[x1 + 1][y1] != wall || world[x1 - 1][y1] != wall) && (world[x1][y1 + 1] != wall || world[x1][y1 - 1] != wall))) {
//                    world[x1][y1] = Tileset.NPC;
//                    placed = true;
//                    this.x = x1;
//                    this.y = y1;
//                }
//            }
//        }
//    }

//    public void talkToNPC() {
//        int npcID = -1;
//        //find where npc is
//        if (world[avatarX + 1][avatarY] == Tileset.NPC) {
//            npcID = npcMatcher(avatarX + 1, avatarY);
//        }
//        if (world[avatarX][avatarY + 1] == Tileset.NPC) {
//            npcID = npcMatcher(avatarX, avatarY + 1);
//        }
//        if (world[avatarX - 1][avatarY] == Tileset.NPC) {
//            npcID = npcMatcher(avatarX - 1, avatarY);
//        }
//        if (world[avatarX][avatarY - 1] == Tileset.NPC) {
//            npcID = npcMatcher(avatarX, avatarY - 1);
//        }
//        if (npcID >= 0) {
//            StringBuilder response = new StringBuilder();
//            StringBuilder finalResponse = new StringBuilder();
//            if (portuguese) {
//                response.append("p");
//                finalResponse.append("p");
//            }
//            response.append("npc");
//            response.append(npcID);
//            finalResponse.append("npc");
//            finalResponse.append(npcID);
//            StdDraw.clear(StdDraw.BLACK);
//            StdDraw.setPenColor(StdDraw.WHITE);
//            StdDraw.text(WIDTH * 0.5, HEIGHT * 0.8, response.toString());
//            StdDraw.text(WIDTH * 0.5, HEIGHT * 0.7, "NPC" + npcID + " : " + dialogue.get(response.toString()));
//            StdDraw.text(WIDTH * 0.5, HEIGHT * 0.2, "(e) exit conversation");
//            StdDraw.show();
//            int dialogueLevel = 0;
//            outer:
//            while (true) {
//                char c;
//                while (StdDraw.hasNextKeyTyped()) {
//                    c = StdDraw.nextKeyTyped();
//                    switch (c) {
//                        case '0', '1', '2':
//                            if (dialogueLevel < 2) {
//                                response.append(c);
//                                StdDraw.text(WIDTH * 0.5, HEIGHT * (0.6 - (0.2 * dialogueLevel)), "YOU : " + dialogue.get(response.toString()));
//                                if (dialogueLevel == 0) {
//                                    response.append("r");
//                                    StdDraw.text(WIDTH * 0.5, HEIGHT * 0.5, "NPC" + npcID + " : " + dialogue.get(response.toString()));
//                                }
//                                if (dialogueLevel == 1) {
//                                    finalResponse.append("f");
//                                    StdDraw.text(WIDTH * 0.5, HEIGHT * 0.3, "NPC" + npcID + " : " + dialogue.get(finalResponse.toString()));
//                                }
//                                StdDraw.show();
//                                dialogueLevel++;
//                            }
//                            break;
//                            // remember to backslash dialogue !!
//                        case 'e':
//                            break outer;
//                        case ':':
//                            outer2:
//                            while (true) {
//                                while (StdDraw.hasNextKeyTyped()) {
//                                    c = StdDraw.nextKeyTyped();
//                                    if (c == 'q') {
//                                        // save the current world so it can be replayed
//                                        writeFile("savefile", saveInfo());
//                                        System.exit(0);
//                                    }
//                                    if (c == '0' || c == '1' || c == '2') {
//                                        if (dialogueLevel < 2) {
//                                            response.append(c);
//                                            StdDraw.text(WIDTH * 0.5, HEIGHT * (0.6 - (0.2 * dialogueLevel)), "YOU : " + dialogue.get(response.toString()));
//                                            if (dialogueLevel == 0) {
//                                                response.append("r");
//                                                StdDraw.text(WIDTH * 0.5, HEIGHT * 0.5, "NPC" + npcID + " : " + dialogue.get(response.toString()));
//                                            }
//                                            if (dialogueLevel == 1) {
//                                                finalResponse.append("f");
//                                                StdDraw.text(WIDTH * 0.5, HEIGHT * 0.3, "NPC" + npcID + " : " + dialogue.get(finalResponse.toString()));
//                                            }
//                                            StdDraw.show();
//                                            dialogueLevel++;
//                                        }
//                                        break outer2;
//                                    }
//                                }
//                            }
//                            break;
//                        default:
//                            break;
//                    }
//                }
//            }
//        }
//    }

//    public int npcMatcher(int x, int y) {
//        for (NPC npc : listOfNPCs) {
//            if (x == npc.x && y == npc.y) {
//                return listOfNPCs.indexOf(npc);
//            }
//        }
//        return -1;
//    }

    public String saveInfo() {
        String initial = seed + "," + portuguese + "," + avatarX + "," + avatarY + "," + forestTheme + "," + score + "," + secondPlayer + "," + avatar2X + "," + avatar2Y + "," + score2 + "," + removedCoins;
        StringBuilder info = new StringBuilder();
        info.append(initial);
        for (Integer coinCoordinate : listOfRemovedCoinCoordinates) {
            info.append(",");
            info.append(coinCoordinate);
        }
        return info.toString();
    }

    public void removeCoins() {
        for (int i = 0; i < listOfRemovedCoinCoordinates.size() / 2; i++) {
            world[listOfRemovedCoinCoordinates.get(2 * i)][listOfRemovedCoinCoordinates.get(2 * i + 1)] = floor;
        }
    }

    public void lineOfSight() {
        //How many tiles in each direction we want to be visible
        int reach = 5;
        for (int i = 0; i < reach; i++) {
            int right = avatarX + i;
            int left = avatarX - i;
            int up = avatarY + i;
            int down = avatarY - i;
            //Set all floor, wall, and npc tiles out of range to black
            for (int x = 0; x < WIDTH; x++) {
                for (int y = 0; y < HEIGHT; y++) {
                    if (Math.sqrt(Math.pow(avatarX - x, 2) + Math.pow(avatarY - y, 2)) <= reach || (secondPlayer && Math.sqrt(Math.pow(avatar2X - x, 2) + Math.pow(avatar2Y - y, 2)) <= reach)) {
                        if (world[x][y] == blackFloor) {
                            world[x][y] = floor;
                        }
                        if (world[x][y] == blackWall) {
                            world[x][y] = wall;
                        }
                        if (world[x][y] == Tileset.HIDDENNPC) {
                            world[x][y] = Tileset.NPC;
                        }
                        if (world[x][y] == Tileset.HIDDENCOIN) {
                            world[x][y] = coin;
                        }
                    } else { //set all black floor and walls to regular floor and walls within range
                        if (world[x][y] == floor) {
                            world[x][y] = blackFloor;
                        }
                        if (world[x][y] == wall) {
                            world[x][y] = blackWall;
                        }
                        if (world[x][y] == Tileset.NPC) {
                            world[x][y] = Tileset.HIDDENNPC;
                        }
                        if (world[x][y] == coin) {
                            world[x][y] = Tileset.HIDDENCOIN;
                        }
                    }
                }
            }
        }



    }

    //Bring back full vision of map
    //Set all blackFloors to floors and all blackWalls to walls
    public void fullSight() {
        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < HEIGHT; y++) {
                if (world[x][y] == blackFloor) {
                    world[x][y] = floor;
                }
                if (world[x][y] == blackWall) {
                    world[x][y] = wall;
                }
                if (world[x][y] == Tileset.HIDDENNPC) {
                    world[x][y] = Tileset.NPC;
                }
                if (world[x][y] == Tileset.HIDDENCOIN) {
                    world[x][y] = coin;
                }
            }
        }
    }

    //Changes boolean fullView and calls corresponding methods for full view or limited sight
    public void changeSight() {
        fullView = !fullView;
        if (fullView) {
            fullSight();
        } else {
            lineOfSight();
        }
    }

    public void movement(String key) {
        switch (key) {
            case "w":
                if (world[avatarX][avatarY + 1] == floor) {
                    moveAvatar(avatarX, avatarY + 1);
                    if (!fullView) {
                        lineOfSight();
                    }
                }
                else if (world[avatarX][avatarY + 1] == coin){
                    listOfRemovedCoinCoordinates.add(avatarX);
                    listOfRemovedCoinCoordinates.add(avatarY + 1);
                    removedCoins++;
                    moveAvatar(avatarX, avatarY + 1);
                    if (!fullView) {
                        lineOfSight();
                    }
                    score++;
                }
                break;
            case "a":
                if (world[avatarX - 1][avatarY] == floor) {
                    moveAvatar(avatarX - 1, avatarY);
                    if (!fullView) {
                        lineOfSight();
                    }
                }
                else if (world[avatarX - 1][avatarY] == coin){
                    listOfRemovedCoinCoordinates.add(avatarX - 1);
                    listOfRemovedCoinCoordinates.add(avatarY);
                    removedCoins++;
                    moveAvatar(avatarX - 1, avatarY);
                    if (!fullView) {
                        lineOfSight();
                    }
                    score++;
                }
                break;
            case "s":
                if (world[avatarX][avatarY - 1] == floor) {
                    moveAvatar(avatarX, avatarY - 1);
                    if (!fullView) {
                        lineOfSight();
                    }
                }
                else if (world[avatarX][avatarY - 1] == coin){
                    listOfRemovedCoinCoordinates.add(avatarX);
                    listOfRemovedCoinCoordinates.add(avatarY - 1);
                    removedCoins++;
                    moveAvatar(avatarX, avatarY - 1);
                    if (!fullView) {
                        lineOfSight();
                    }
                    score++;
                }
                break;
            case "d":
                if (world[avatarX + 1][avatarY] == floor) {
                    moveAvatar(avatarX + 1, avatarY);
                    if (!fullView) {
                        lineOfSight();
                    }
                }
                else if (world[avatarX + 1][avatarY] == coin){
                    listOfRemovedCoinCoordinates.add(avatarX + 1);
                    listOfRemovedCoinCoordinates.add(avatarY);
                    removedCoins++;
                    moveAvatar(avatarX + 1, avatarY);
                    if (!fullView) {
                        lineOfSight();
                    }
                    score++;
                }
                break;
            default:
                break;
        }
    }

    public void movement2(String key) {
        switch (key) {
            case "i":
                if (world[avatar2X][avatar2Y + 1] == floor) {
                    moveAvatar2(avatar2X, avatar2Y + 1);
                    if (!fullView) {
                        lineOfSight();
                    }
                }
                else if (world[avatar2X][avatar2Y + 1] == coin){
                    listOfRemovedCoinCoordinates.add(avatar2X);
                    listOfRemovedCoinCoordinates.add(avatar2Y + 1);
                    removedCoins++;
                    moveAvatar2(avatar2X, avatar2Y + 1);
                    if (!fullView) {
                        lineOfSight();
                    }
                    score2++;
                }
                break;
            case "j":
                if (world[avatar2X - 1][avatar2Y] == floor) {
                    moveAvatar2(avatar2X - 1, avatar2Y);
                    if (!fullView) {
                        lineOfSight();
                    }
                }
                else if (world[avatar2X - 1][avatar2Y] == coin){
                    listOfRemovedCoinCoordinates.add(avatar2X - 1);
                    listOfRemovedCoinCoordinates.add(avatar2Y);
                    removedCoins++;
                    moveAvatar2(avatar2X - 1, avatar2Y);
                    if (!fullView) {
                        lineOfSight();
                    }
                    score2++;
                }
                break;
            case "k":
                if (world[avatar2X][avatar2Y - 1] == floor) {
                    moveAvatar2(avatar2X, avatar2Y - 1);
                    if (!fullView) {
                        lineOfSight();
                    }
                }
                else if (world[avatar2X][avatar2Y - 1] == coin){
                    listOfRemovedCoinCoordinates.add(avatar2X);
                    listOfRemovedCoinCoordinates.add(avatar2Y - 1);
                    removedCoins++;
                    moveAvatar2(avatar2X, avatar2Y - 1);
                    if (!fullView) {
                        lineOfSight();
                    }
                    score2++;
                }
                break;
            case "l":
                if (world[avatar2X + 1][avatar2Y] == floor) {
                    moveAvatar2(avatar2X + 1, avatar2Y);
                    if (!fullView) {
                        lineOfSight();
                    }
                }
                else if (world[avatar2X + 1][avatar2Y] == coin){
                    listOfRemovedCoinCoordinates.add(avatar2X + 1);
                    listOfRemovedCoinCoordinates.add(avatar2Y);
                    removedCoins++;
                    moveAvatar2(avatar2X + 1, avatar2Y);
                    if (!fullView) {
                        lineOfSight();
                    }
                    score2++;
                }
                break;
            default:
                break;
        }
    }

    public void placeCoin() {
        boolean placed = false;
        while (!placed) {
            int x1 = Math.abs(r.nextInt() % (WIDTH));
            int y1 = Math.abs(r.nextInt() % (HEIGHT));
            // checks that tile is a valid floor tile and that the coin is not spawning in a hallway (although I haven't yet covered the case of a hallway that runs in both directions
            if (world[x1][y1] == floor) {
                world[x1][y1] = coin;
                placed = true;
            }
        }
    }


}
