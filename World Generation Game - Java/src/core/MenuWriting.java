package core;

import edu.princeton.cs.algs4.StdDraw;

import static core.World.HEIGHT;
import static core.World.WIDTH;

public class MenuWriting {

    public static void setMenu(boolean portuguese, boolean forestTheme, boolean secondPlayer) {
        StdDraw.clear(StdDraw.BLACK);
        StdDraw.setPenColor(StdDraw.WHITE);
        if (portuguese && !forestTheme && !secondPlayer) {
            portugueseStandard();
        }
        if (portuguese && forestTheme && !secondPlayer) {
            portugueseForest();
        }
        if (!portuguese && !forestTheme && !secondPlayer) {
            englishStandard();
        }
        if (!portuguese && forestTheme && !secondPlayer) {
            englishForest();
        }
        if (portuguese && !forestTheme && secondPlayer) {
            portugueseStandard2();
        }
        if (portuguese && forestTheme && secondPlayer) {
            portugueseForest2();
        }
        if (!portuguese && !forestTheme && secondPlayer) {
            englishStandard2();
        }
        if (!portuguese && forestTheme && secondPlayer) {
            englishForest2();
        }
    }

    public static void setVictoryMenu(boolean portuguese, int player) {
        if (!portuguese && player == 0) {
            victoryMenu();
        }
        if (!portuguese && player == 1) {
            victoryMenu1();
        }
        if (!portuguese && player == 2) {
            victoryMenu2();
        }
        if (portuguese && player == 0) {
            portugueseVictoryMenu();
        }
        if (portuguese && player == 1) {
            portugueseVictoryMenu1();
        }
        if (portuguese && player == 2) {
            portugueseVictoryMenu2();
        }
    }

    public static void setRulesMenu(boolean portuguese) {
        StdDraw.clear(StdDraw.BLACK);
        StdDraw.setPenColor(StdDraw.WHITE);
        if (portuguese) {
            portugueseRules();
        }
        if (!portuguese) {
            englishRules();
        }
    }

    public static void portugueseRules() {
        StdDraw.text(0.5 * WIDTH, 0.7 * HEIGHT, "Bemvindo ao nosso jogo!");
        StdDraw.text(0.5 * WIDTH, 0.6 * HEIGHT, "Para um jogador, usa WASD. Para dois jogadores, jogador1 usa WASD and jogador2 usa IJKL");
        StdDraw.text(0.5 * WIDTH, 0.5 * HEIGHT, "Para um jogador, o objectivo è 10 moedas! Para dois jogadores, o primeiro que tem 5 moedas gana!");
        StdDraw.text(0.5 * WIDTH, 0.4 * HEIGHT, "(c) continuar ao jogo");
        StdDraw.text(0.5 * WIDTH, 0.3 * HEIGHT, "(q) fechar o jogo");
    }

    public static void englishRules() {
        StdDraw.text(0.5 * WIDTH, 0.7 * HEIGHT, "Welcome to our game!");
        StdDraw.text(0.5 * WIDTH, 0.6 * HEIGHT, "For single player, use WASD. For two players, player1 uses WASD and player2 uses IJKL");
        StdDraw.text(0.5 * WIDTH, 0.5 * HEIGHT, "For single player, the objective is 10 coins! For two players, it is the first to 5 coins!");
        StdDraw.text(0.5 * WIDTH, 0.4 * HEIGHT, "(c) continue to game");
        StdDraw.text(0.5 * WIDTH, 0.3 * HEIGHT, "(q) quit game");
    }

    public static void englishForest() {
        StdDraw.text(0.5 * WIDTH, 0.8 * HEIGHT, "Main Menu");
        StdDraw.text(0.5 * WIDTH, 0.7 * HEIGHT, "Karina and Rosette's amazing game");
        StdDraw.text(0.5 * WIDTH, 0.6 * HEIGHT, "(n) new game");
        StdDraw.text(0.5 * WIDTH, 0.5 * HEIGHT, "(l) load save file");
        StdDraw.text(0.5 * WIDTH, 0.4 * HEIGHT, "(c) change theme to standard");
        StdDraw.text(0.5 * WIDTH, 0.3 * HEIGHT, "(p) português");
        StdDraw.text(0.5 * WIDTH, 0.2 * HEIGHT, "(s) switch to two players");
        StdDraw.text(0.5 * WIDTH, 0.1 * HEIGHT, "(q) quit game");
        StdDraw.show();
    }

    public static void englishStandard() {
        StdDraw.text(0.5 * WIDTH, 0.8 * HEIGHT, "Main Menu");
        StdDraw.text(0.5 * WIDTH, 0.7 * HEIGHT, "Karina and Rosette's amazing game");
        StdDraw.text(0.5 * WIDTH, 0.6 * HEIGHT, "(n) new game");
        StdDraw.text(0.5 * WIDTH, 0.5 * HEIGHT, "(l) load save file");
        StdDraw.text(0.5 * WIDTH, 0.4 * HEIGHT, "(c) change theme to forest");
        StdDraw.text(0.5 * WIDTH, 0.3 * HEIGHT, "(p) português");
        StdDraw.text(0.5 * WIDTH, 0.2 * HEIGHT, "(s) switch to two players");
        StdDraw.text(0.5 * WIDTH, 0.1 * HEIGHT, "(q) quit game");
        StdDraw.show();
    }

    public static void portugueseStandard() {
        StdDraw.text(0.5 * WIDTH, 0.8 * HEIGHT, "Menu principal");
        StdDraw.text(0.5 * WIDTH, 0.7 * HEIGHT, "O jogo maravilhoso de Karina e Rosette");
        StdDraw.text(0.5 * WIDTH, 0.6 * HEIGHT, "(n) nuovo jogo");
        StdDraw.text(0.5 * WIDTH, 0.5 * HEIGHT, "(l) carregar o jogo guardado");
        StdDraw.text(0.5 * WIDTH, 0.4 * HEIGHT, "(c) cambiar pra tema de floresta");
        StdDraw.text(0.5 * WIDTH, 0.3 * HEIGHT, "(p) english");
        StdDraw.text(0.5 * WIDTH, 0.2 * HEIGHT, "(s) joga com dois jogadores");
        StdDraw.text(0.5 * WIDTH, 0.1 * HEIGHT, "(q) fechar o jogo");
        StdDraw.show();
    }

    public static void portugueseForest() {
        StdDraw.text(0.5 * WIDTH, 0.8 * HEIGHT, "Menu principal");
        StdDraw.text(0.5 * WIDTH, 0.7 * HEIGHT, "O jogo maravilhoso de Karina e Rosette");
        StdDraw.text(0.5 * WIDTH, 0.6 * HEIGHT, "(n) nuovo jogo");
        StdDraw.text(0.5 * WIDTH, 0.5 * HEIGHT, "(l) carregar o jogo guardado");
        StdDraw.text(0.5 * WIDTH, 0.4 * HEIGHT, "(c) cambiar pra tema padrão");
        StdDraw.text(0.5 * WIDTH, 0.3 * HEIGHT, "(p) english");
        StdDraw.text(0.5 * WIDTH, 0.2 * HEIGHT, "(s) joga com dois jogadores");
        StdDraw.text(0.5 * WIDTH, 0.1 * HEIGHT, "(q) fechar o jogo");
        StdDraw.show();
    }

    public static void englishForest2() {
        StdDraw.text(0.5 * WIDTH, 0.8 * HEIGHT, "Main Menu");
        StdDraw.text(0.5 * WIDTH, 0.7 * HEIGHT, "Karina and Rosette's amazing game");
        StdDraw.text(0.5 * WIDTH, 0.6 * HEIGHT, "(n) new game");
        StdDraw.text(0.5 * WIDTH, 0.5 * HEIGHT, "(l) load save file");
        StdDraw.text(0.5 * WIDTH, 0.4 * HEIGHT, "(c) change theme to standard");
        StdDraw.text(0.5 * WIDTH, 0.3 * HEIGHT, "(p) português");
        StdDraw.text(0.5 * WIDTH, 0.2 * HEIGHT, "(s) switch to single player");
        StdDraw.text(0.5 * WIDTH, 0.1 * HEIGHT, "(q) quit game");
        StdDraw.show();
    }

    public static void englishStandard2() {
        StdDraw.text(0.5 * WIDTH, 0.8 * HEIGHT, "Main Menu");
        StdDraw.text(0.5 * WIDTH, 0.7 * HEIGHT, "Karina and Rosette's amazing game");
        StdDraw.text(0.5 * WIDTH, 0.6 * HEIGHT, "(n) new game");
        StdDraw.text(0.5 * WIDTH, 0.5 * HEIGHT, "(l) load save file");
        StdDraw.text(0.5 * WIDTH, 0.4 * HEIGHT, "(c) change theme to forest");
        StdDraw.text(0.5 * WIDTH, 0.3 * HEIGHT, "(p) português");
        StdDraw.text(0.5 * WIDTH, 0.2 * HEIGHT, "(s) switch to single player");
        StdDraw.text(0.5 * WIDTH, 0.1 * HEIGHT, "(q) quit game");
        StdDraw.show();
    }

    public static void portugueseStandard2() {
        StdDraw.text(0.5 * WIDTH, 0.8 * HEIGHT, "Menu principal");
        StdDraw.text(0.5 * WIDTH, 0.7 * HEIGHT, "O jogo maravilhoso de Karina e Rosette");
        StdDraw.text(0.5 * WIDTH, 0.6 * HEIGHT, "(n) nuovo jogo");
        StdDraw.text(0.5 * WIDTH, 0.5 * HEIGHT, "(l) carregar o jogo guardado");
        StdDraw.text(0.5 * WIDTH, 0.4 * HEIGHT, "(c) cambiar pra tema de floresta");
        StdDraw.text(0.5 * WIDTH, 0.3 * HEIGHT, "(p) english");
        StdDraw.text(0.5 * WIDTH, 0.2 * HEIGHT, "(s) joga com um jogador");
        StdDraw.text(0.5 * WIDTH, 0.1 * HEIGHT, "(q) fechar o jogo");
        StdDraw.show();
    }

    public static void portugueseForest2() {
        StdDraw.text(0.5 * WIDTH, 0.8 * HEIGHT, "Menu principal");
        StdDraw.text(0.5 * WIDTH, 0.7 * HEIGHT, "O jogo maravilhoso de Karina e Rosette");
        StdDraw.text(0.5 * WIDTH, 0.6 * HEIGHT, "(n) nuovo jogo");
        StdDraw.text(0.5 * WIDTH, 0.5 * HEIGHT, "(l) carregar o jogo guardado");
        StdDraw.text(0.5 * WIDTH, 0.4 * HEIGHT, "(c) cambiar pra tema padrão");
        StdDraw.text(0.5 * WIDTH, 0.3 * HEIGHT, "(p) english");
        StdDraw.text(0.5 * WIDTH, 0.2 * HEIGHT, "(s) joga com um jogador");
        StdDraw.text(0.5 * WIDTH, 0.1 * HEIGHT, "(q) fechar o jogo");
        StdDraw.show();
    }

    public static void seedMenuPortuguese(StringBuilder seed) {
        StdDraw.clear(StdDraw.BLACK);
        StdDraw.setPenColor(StdDraw.WHITE);
        StdDraw.text(0.5 * WIDTH, 0.7 * HEIGHT, "Menu Principal");
        StdDraw.text(0.5 * WIDTH, 0.6 * HEIGHT, "O jogo maravilhoso de Karina e Rosette");
        StdDraw.text(0.5 * WIDTH, 0.5 * HEIGHT, "Por favor entra um numero de semilla, depois pressione a letra s");
        if (!seed.isEmpty()) {
            StdDraw.text(0.5 * WIDTH, 0.4 * HEIGHT, seed.toString());
        }
        StdDraw.show();
    }

    public static void seedMenuEnglish(StringBuilder seed) {
        StdDraw.clear(StdDraw.BLACK);
        StdDraw.setPenColor(StdDraw.WHITE);
        StdDraw.text(0.5 * WIDTH, 0.7 * HEIGHT, "Main Menu");
        StdDraw.text(0.5 * WIDTH, 0.6 * HEIGHT, "Karina and Rosette's amazing game");
        StdDraw.text(0.5 * WIDTH, 0.5 * HEIGHT, "Please enter a seed number, then press s");
        if (!seed.isEmpty()) {
            StdDraw.text(0.5 * WIDTH, 0.4 * HEIGHT, seed.toString());
        }
        StdDraw.show();
    }

    public static void victoryMenu() {
        StdDraw.clear(StdDraw.BLACK);
        StdDraw.setPenColor(StdDraw.WHITE);
        StdDraw.text(0.5 * WIDTH, HEIGHT * 0.7, "Congratulations, you won!");
        StdDraw.text(0.5 * WIDTH, HEIGHT * 0.6, "(m) return to main menu");
        StdDraw.text(0.5 * WIDTH, HEIGHT * 0.5, "(q) quit game");
        StdDraw.show();
    }

    public static void victoryMenu1() {
        StdDraw.clear(StdDraw.BLACK);
        StdDraw.setPenColor(StdDraw.WHITE);
        StdDraw.text(0.5 * WIDTH, HEIGHT * 0.7, "Congratulations player1, you won!");
        StdDraw.text(0.5 * WIDTH, HEIGHT * 0.6, "(m) return to main menu");
        StdDraw.text(0.5 * WIDTH, HEIGHT * 0.5, "(q) quit game");
        StdDraw.show();
    }

    public static void victoryMenu2() {
        StdDraw.clear(StdDraw.BLACK);
        StdDraw.setPenColor(StdDraw.WHITE);
        StdDraw.text(0.5 * WIDTH, HEIGHT * 0.7, "Congratulations player2, you won!");
        StdDraw.text(0.5 * WIDTH, HEIGHT * 0.6, "(m) return to main menu");
        StdDraw.text(0.5 * WIDTH, HEIGHT * 0.5, "(q) quit game");
        StdDraw.show();
    }

    public static void portugueseVictoryMenu() {
        StdDraw.clear(StdDraw.BLACK);
        StdDraw.setPenColor(StdDraw.WHITE);
        StdDraw.text(0.5 * WIDTH, HEIGHT * 0.7, "Felicitaçoes, você ganhou!");
        StdDraw.text(0.5 * WIDTH, HEIGHT * 0.6, "(m) voltar ao menu principal");
        StdDraw.text(0.5 * WIDTH, HEIGHT * 0.5, "(q) fechar o jogo");
        StdDraw.show();
    }

    public static void portugueseVictoryMenu1() {
        StdDraw.clear(StdDraw.BLACK);
        StdDraw.setPenColor(StdDraw.WHITE);
        StdDraw.text(0.5 * WIDTH, HEIGHT * 0.7, "Felicitaçoes jogador1, você ganhou!");
        StdDraw.text(0.5 * WIDTH, HEIGHT * 0.6, "(m) voltar ao menu principal");
        StdDraw.text(0.5 * WIDTH, HEIGHT * 0.5, "(q) fechar o jogo");
        StdDraw.show();
    }

    public static void portugueseVictoryMenu2() {
        StdDraw.clear(StdDraw.BLACK);
        StdDraw.setPenColor(StdDraw.WHITE);
        StdDraw.text(0.5 * WIDTH, HEIGHT * 0.7, "Felicitaçoes jogador2, você ganhou!");
        StdDraw.text(0.5 * WIDTH, HEIGHT * 0.6, "(m) voltar ao menu principal");
        StdDraw.text(0.5 * WIDTH, HEIGHT * 0.5, "(q) fechar o jogo");
        StdDraw.show();
    }

}
