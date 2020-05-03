import java.util.Random;
import java.util.Scanner;

public class Logic {
    static int SIZE;
    static int DOTS_TO_WIN;

    static final char DOT_X = 'X';
    static final char DOT_O = 'O';
    static final char DOT_EMPTY = '.';

    static char[][] map;

    static Random random = new Random();
    static boolean gameFinished = false;
    static String winnerName = "";

    public static void go() {
        winnerName = "";
        gameFinished = true;
        printMap();
        if (checkWinLines(DOT_X)) {
            System.out.println("Ты Супер победитель!");
            winnerName = "Human";
            return;
        }

        if (isFull()) {
            System.out.println("Ничья...");
            winnerName = "Nobody";
            return;
        }

        aiTurn();
        printMap();
        if (checkWinLines(DOT_O)) {
            System.out.println("ИИ нынче очень развито, компьютер победил!");
            winnerName = "Computer";
            return;

        }
        if (isFull()) {
            System.out.println("Ничья...");
            winnerName = "Nobody";
            return;
        }
        gameFinished = false;
    }

    public static void initMap() {
        map = new char[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                map[i][j] = DOT_EMPTY;
            }
        }
    }

    public static void printMap() {
        System.out.print("  ");
        for (int i = 1; i <= SIZE; i++) {
            System.out.print(i + " ");
        }
        System.out.println();

        for (int i = 0; i < SIZE; i++) {
            System.out.print(i + 1 + " ");
            for (int j = 0; j < SIZE; j++) {
                System.out.printf("%s ", map[i][j]);
            }
            System.out.println();
        }
    }

//    public static void humanTurn() {
////        int x;
////        int y;
////
////        do {
////            System.out.println("input koord X Y");
////            x = sc.nextInt() - 1;
////            y = sc.nextInt() - 1;
////        } while (!isCellValid(y, x));
////        map[y][x] = DOT_X;
////
////    }

    public static void setHumanXY(int x, int y){
        if(isCellValid(y,x)){
            map[y][x] = DOT_X;
            go();
        }
    }

    public static void setHumanX(int x, int y){
        if(isCellValid(y,x)){
            map[y][x] = DOT_X;
            goHuman1();
        }
    }

    public static void setHumanO(int x, int y){
        if(isCellValid(y,x)){
            map[y][x] = DOT_O;
            goHuman2();
        }
    }

    public static boolean isCellValid(int y, int x) {
        if (x < 0 || y < 0 || x >= SIZE || y >= SIZE) {
            return false;
        }
        return map[y][x] == DOT_EMPTY;
    }

    public static void aiTurn() {
        int x;
        int y;

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (isCellValid(i, j)) {
                    map[i][j] = DOT_O;
                    if (checkWinLines(DOT_O)) {
                        return;
                    }
                    map[i][j] = DOT_EMPTY;
                }
            }
        }

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (isCellValid(i, j)) {
                    map[i][j] = DOT_X;
                    if (checkWinLines(DOT_X)) {
                        map[i][j] = DOT_O;
                        return;
                    }
                    map[i][j] = DOT_EMPTY;
                }
            }
        }


        do {
            y = random.nextInt(SIZE);
            x = random.nextInt(SIZE);
        } while (!isCellValid(y, x));
        map[y][x] = DOT_O;
    }

    public static boolean isFull() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (map[i][j] == DOT_EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }

//    public static boolean checkWin(char c) {
//
//    }

    static boolean checkLine(int cy, int cx, int vy, int vx, char dot) {
        if (cx + vx * DOTS_TO_WIN - 1 > SIZE - 1 || cy + vy * DOTS_TO_WIN - 1 > SIZE - 1 ||
                cy + vy * (DOTS_TO_WIN - 1) < 0) {
            return false;
        }

        for (int i = 0; i < DOTS_TO_WIN; i++) {
            if (map[cy + i * vy][cx + i * vx] != dot) {
                return false;
            }
        }
        return true;
    }

    static boolean checkWinLines(char dot) {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (checkLine(i, j, 0, 1, dot) || checkLine(i, j, 1, 0, dot) ||
                        checkLine(i, j, 1, 1, dot) || checkLine(i, j, -1, 1, dot)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void goHuman1() {
        winnerName = "";
        gameFinished = true;
        printMap();
        if (checkWinLines(DOT_X)) {
            winnerName = "Human 1";
            return;
        }

        if (isFull()) {
            System.out.println("Ничья...");
            winnerName = "Nobody";
            return;
        }
        gameFinished = false;
    }

    public static void goHuman2() {
        winnerName = "";
        gameFinished = true;
        printMap();
        if (checkWinLines(DOT_O)) {
            winnerName = "Human 2";
            return;
        }

        if (isFull()) {
            System.out.println("Ничья...");
            winnerName = "Nobody";
            return;
        }
        gameFinished = false;
    }

}