package core;

import edu.princeton.cs.algs4.StdDraw;

import java.util.ArrayList;
import java.util.List;

import static utils.FileUtils.fileExists;
import static utils.FileUtils.readFile;

public class MainMenu {
    public World thisWorld;
    private boolean portuguese = false;
    private boolean forestTheme = false;
    private boolean secondPlayer = false;

    public MainMenu() { }

    public void execute() {
        MenuWriting.englishStandard();

        // creating world with load or save
        char key;
        outer:
        while (true) {
            while (StdDraw.hasNextKeyTyped()) {
                key = StdDraw.nextKeyTyped();
                key = Character.toLowerCase(key);
                switch (key) {
                    // type seed
                    case 'n':
                        SeedMenu seedMenu = new SeedMenu(portuguese);
                        thisWorld = new World(seedMenu.seed, portuguese, forestTheme, secondPlayer);
                        break outer;
                    // load save file (if it exists)
                    case 'l':
                        if (!fileExists("savefile")) {
                            System.exit(0);
                        }
                        String saveFile = readFile("savefile");
                        String[] saveFileList = saveFile.split(",");
                        if (saveFileList[4].equals("true")) {
                            forestTheme = true;
                        }
                        if (saveFileList[1].equals("false")) {
                            portuguese = false;
                        }
                        if (saveFileList[1].equals("true")) {
                            portuguese = true;
                        }
                        if (saveFileList[6].equals("true")) {
                            secondPlayer = true;
                        }
                        thisWorld = new World(saveFileList[0], portuguese, forestTheme, secondPlayer);
                        thisWorld.moveAvatar(Integer.parseInt(saveFileList[2]), Integer.parseInt(saveFileList[3]));
                        thisWorld.score = Integer.parseInt(saveFileList[5]);
                        if (secondPlayer) {
                            thisWorld.moveAvatar2(Integer.parseInt(saveFileList[7]), Integer.parseInt(saveFileList[8]));
                            thisWorld.score2 = Integer.parseInt(saveFileList[9]);
                        }
                        if (Integer.parseInt(saveFileList[10]) > 0) {
                            List<Integer> coordinates = new ArrayList<>();
                            for (int i = 0; i < Integer.parseInt(saveFileList[10]); i++) {
                                coordinates.add(Integer.valueOf(saveFileList[11 + i * 2]));
                                coordinates.add(Integer.valueOf(saveFileList[12 + i * 2]));
                            }
                            thisWorld.listOfRemovedCoinCoordinates = coordinates;
                            thisWorld.removeCoins();
                        }
                        break outer;
                    case 'q':
                        System.exit(0);
                    case 'p':
                        portuguese = !portuguese;
                        MenuWriting.setMenu(portuguese, forestTheme, secondPlayer);
                        break;
                    case 'c':
                        forestTheme = !forestTheme;
                        MenuWriting.setMenu(portuguese, forestTheme, secondPlayer);
                        break;
                    case 's':
                        secondPlayer = !secondPlayer;
                        MenuWriting.setMenu(portuguese, forestTheme, secondPlayer);
                }
            }
        }
        MenuWriting.setRulesMenu(portuguese);
        outer2:
        while (true) {
            while (StdDraw.hasNextKeyTyped()) {
                key = StdDraw.nextKeyTyped();
                key = Character.toLowerCase(key);
                switch (key) {
                    case 'c':
                        break outer2;
                    case 'q':
                        System.exit(0);
                }
            }
        }
    }
}
