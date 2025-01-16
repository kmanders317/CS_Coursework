package core;

import edu.princeton.cs.algs4.StdDraw;
import tileengine.TERenderer;

import static core.World.HEIGHT;
import static utils.FileUtils.*;

public class Main {
    public static void main(String[] args) {
        while (true) {
            // main menu
            StdDraw.setCanvasSize(World.WIDTH * 16, World.HEIGHT * 16);
            StdDraw.setXscale(0, World.WIDTH);
            StdDraw.setYscale(0, World.HEIGHT);
            StdDraw.clear(StdDraw.BLACK);
            StdDraw.setPenColor(StdDraw.WHITE);
            MainMenu mainMenu = new MainMenu();
            mainMenu.execute();
            World thisWorld = mainMenu.thisWorld;

            // while in world
            StdDraw.clear();
            TERenderer ter = new TERenderer();
            ter.initialize(World.WIDTH, World.HEIGHT);
            ter.renderFrame(thisWorld.worldRep());
            char c;
            outer2:
            while (true) {
                while (StdDraw.hasNextKeyTyped()) {
                    c = StdDraw.nextKeyTyped();
                    c = Character.toLowerCase(c);
                    String key = String.valueOf(c);
                    //toggle line of sight on and off
                    if (key.equalsIgnoreCase("v")) {
                        thisWorld.changeSight();
                    }
                    // move the character in one of four directions (or don't move if there's a wall)
                    if (key.equals("w") || key.equals("a") || key.equals("s") || key.equals("d")) {
                        thisWorld.movement(key);
                    }
                    if (thisWorld.secondPlayer) {
                        if (key.equals("i") || key.equals("j") || key.equals("k") || key.equals("l")) {
                            thisWorld.movement2(key);
                        }
                    }
                    // check if the player intends to quit
                    if (key.equals(":")) {
                        outer:
                        while (true) {
                            while (StdDraw.hasNextKeyTyped()) {
                                key = String.valueOf(StdDraw.nextKeyTyped());
                                if (key.equalsIgnoreCase("q")) {
                                    // save the current world so it can be replayed
                                    writeFile("savefile", thisWorld.saveInfo());
                                    System.exit(0);
                                }
                                if (key.equalsIgnoreCase("v")) {
                                    thisWorld.changeSight();
                                    break outer;
                                }
                                // move the character, ignoring the previous input
                                if (key.equalsIgnoreCase("w") || key.equalsIgnoreCase("a")
                                        || key.equalsIgnoreCase("s") || key.equalsIgnoreCase("d")) {
                                    thisWorld.movement(key);
                                    break outer;
                                }
                                if (thisWorld.secondPlayer) {
                                    if (key.equalsIgnoreCase("j") || key.equalsIgnoreCase("k")
                                            || key.equalsIgnoreCase("i") || key.equalsIgnoreCase("l")) {
                                        thisWorld.movement2(key);
                                        break outer;
                                    }
                                }
                            }
                        }
                    }
//                    if (key.equals("t")) {
//                        thisWorld.talkToNPC();
//                    }
                    // render frame after, in case any changes have been made
                    if (thisWorld.score == 10) {
                        MenuWriting.setVictoryMenu(thisWorld.portuguese, 0);
                        while (true) {
                            while (StdDraw.hasNextKeyTyped()) {
                                c = StdDraw.nextKeyTyped();
                                c = Character.toLowerCase(c);
                                switch (c) {
                                    case 'q':
                                        System.exit(0);
                                    case 'm':
                                        break outer2;
                                }
                            }
                        }
                    }
                    if (thisWorld.secondPlayer && (thisWorld.score == 5 || thisWorld.score2 == 5)) {
                        if (thisWorld.score == 5) {
                            MenuWriting.setVictoryMenu(thisWorld.portuguese, 1);
                        }
                        if (thisWorld.score2 == 5) {
                            MenuWriting.setVictoryMenu(thisWorld.portuguese, 2);
                        }
                        while (true) {
                            while (StdDraw.hasNextKeyTyped()) {
                                c = StdDraw.nextKeyTyped();
                                c = Character.toLowerCase(c);
                                switch (c) {
                                    case 'q':
                                        System.exit(0);
                                    case 'm':
                                        break outer2;
                                }
                            }
                        }
                    }
                    ter.renderFrame(thisWorld.worldRep());
                }
            }
        }
    }
}