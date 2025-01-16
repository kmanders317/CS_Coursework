package core;

import edu.princeton.cs.algs4.StdDraw;

public class SeedMenu {
    public String seed;

    public SeedMenu(boolean portuguese) {
        StringBuilder seed = new StringBuilder();
        if (!portuguese) {
            MenuWriting.seedMenuEnglish(seed);
        }
        if (portuguese) {
            MenuWriting.seedMenuPortuguese(seed);
        }

        String validKeys = "0123456789";

        char key;
        outer:
        while (true) {
            while (StdDraw.hasNextKeyTyped()) {
                key = StdDraw.nextKeyTyped();
                key = Character.toLowerCase(key);
                if (validKeys.indexOf(key) != -1) {
                    seed.append(key);
                    if (!portuguese) {
                        MenuWriting.seedMenuEnglish(seed);
                    }
                    if (portuguese) {
                        MenuWriting.seedMenuPortuguese(seed);
                    }
                }
                if (key == 's' && !seed.toString().isEmpty()) {
                    this.seed = seed.toString();
                    break outer;
                }
            }
        }
    }

}
