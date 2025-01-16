package tileengine;

import java.awt.Color;

/**
 * Contains constant tile objects, to avoid having to remake the same tiles in different parts of
 * the code.
 *
 * You are free to (and encouraged to) create and add your own tiles to this file. This file will
 * be turned in with the rest of your code.
 *
 * Ex:
 *      world[x][y] = Tileset.FLOOR;
 *
 * The style checker may crash when you try to style check this file due to use of unicode
 * characters. This is OK.
 */

public class Tileset {
    public static final TETile AVATAR = new TETile('@', Color.white, Color.black, "you", 0);
    public static final TETile WALL = new TETile('#', new Color(216, 128, 128), Color.darkGray,
            "wall", 1);
    public static final TETile FLOOR = new TETile('·', new Color(128, 192, 128), Color.black, "floor", 2);
    public static final TETile NOTHING = new TETile(' ', Color.black, Color.black, "nothing", 3);
    public static final TETile GRASS = new TETile('"', Color.green, Color.black, "grass", 4);
    public static final TETile WATER = new TETile('≈', Color.blue, Color.black, "water", 5);
    public static final TETile FLOWER = new TETile('❀', Color.magenta, Color.pink, "flower", 6);
    public static final TETile LOCKED_DOOR = new TETile('█', Color.orange, Color.black,
            "locked door", 7);
    public static final TETile UNLOCKED_DOOR = new TETile('▢', Color.orange, Color.black,
            "unlocked door", 8);
    public static final TETile SAND = new TETile('▒', Color.yellow, Color.black, "sand", 9);
    public static final TETile MOUNTAIN = new TETile('▲', Color.gray, Color.black, "mountain", 10);
    public static final TETile TREE = new TETile('♠', Color.green, Color.black, "tree", 11);

    public static final TETile CELL = new TETile('█', Color.white, Color.black, "cell", 12);
    public static final TETile NPC = new TETile('+', Color.white, Color.black, "npc", 13);
    public static final TETile FORESTFLOOR = new TETile('^', Color.green, Color.green, "forest_floor", 14);
    public static final TETile BLACKFLOOR = new TETile(' ', Color.black, Color.black, "black floor", 15);
    public static final TETile BLACKWALL = new TETile(' ', Color.black, Color.black, "black wall", 16);
    public static final TETile BLUEWALL = new TETile('#', new Color(216, 128, 128), Color.darkGray,
            "blue wall", 17);
    public static final TETile BLUEFLOOR = new TETile('·', new Color(128, 192, 128), Color.black, "blue floor", 18);
    public static final TETile HIDDENNPC = new TETile('+', Color.black, Color.black, "hidden npc", 19);
    public static final TETile COIN = new TETile('●', Color.yellow, Color.black, "coin", 20);
    public static final TETile HIDDENCOIN = new TETile('+', Color.black, Color.black, "hidden coin", 21);
    public static final TETile FLOWER2 = new TETile('❀', Color.pink, Color.black, "flower2", 22);
    public static final TETile AVATAR2 = new TETile('@', Color.black, Color.white, "2nd player", 23);
    public static final TETile FORESTCOIN = new TETile('●', Color.yellow, Color.green, "coin", 24);

}

