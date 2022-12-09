import java.io.FileNotFoundException;
import java.util.*;

public class Day09 {
    static List<String> lines;
    static List<List<Character>> grid = new ArrayList<>();
    static int[] s = {0, 0}, H = {0, 0}, T = {0, 0};
    static Set<String> tailTraits = new HashSet<>();

    public static void main(String[] args) throws FileNotFoundException {
        lines = Utils.readLines("./data/day09");
        List<Character> tmp = new ArrayList<>();
        tmp.add('.');
        grid.add(tmp);
        tailTraits.add("0 0");

        for (String line : lines) {
            readMove(line);
        }

        System.out.println(tailTraits.size());
//        printTraits();
    }

    static void readMove(String line) {
        String[] split = line.split(" ");
        char move = split[0].charAt(0);
        int amount = Integer.parseInt(split[1]);

//        System.out.println("== " + line + " ==");
        while (amount > 0) {
            switch (move) {
                case 'U' -> {
                    moveHead(0, 1);
                    checkForOverflow(move);
                }
                case 'R' -> {
                    moveHead(1, 1);
                    checkForOverflow(move);
                }
                case 'D' -> {
                    moveHead(0, -1);
                }
                case 'L' -> {
                    moveHead(1, -1);
                }
            }
//            printState();
            amount--;
        }
//        System.out.println();
    }

    static void moveHead(int i, int val) {
        int[] tmp = H.clone();
        tmp[i] += val;

        double x = T[0] - tmp[0], y = T[1] - tmp[1];
        double distance = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));

        if (distance > Math.sqrt(2)) {
            T = H.clone();
            String trait = T[0] + " " + T[1];
            tailTraits.add(trait);
        }

        H = tmp;
    }

    static void checkForOverflow(char move) {
        if (move == 'U' || move == 'R') {
            if (grid.size() <= H[0]) {
                grid.add(grid.get(0));
            }
            if (grid.get(0).size() <= H[1]) {
                grid.get(0).add('.');
            }
        }
    }

    static void printState() {
        for (int i = grid.size() - 1; i >= 0; i--) {
            List<Character> g = grid.get(i);
            for (int j = 0; j < g.size(); j++) {
                if (i == H[0] && j == H[1]) {
                    System.out.print('H');
                } else if (i == T[0] && j == T[1]) {
                    System.out.print('T');

                } else if (i == s[0] && j == s[1]) {
                    System.out.print('s');
                } else {
                    System.out.print('.');
                }
                System.out.print(' ');
            }
            System.out.println();
        }
        System.out.println();
    }

    static void printTraits() {
        for (int i = grid.size() - 1; i >= 0; i--) {
            for (int j = 0; j < grid.get(i).size(); j++) {
                String xd = i + " " + j;
                if (tailTraits.contains(xd)) {
                    if (i == 0 && j == 0) {
                        System.out.print('s');
                    } else
                        System.out.print('#');
                } else {
                    System.out.print('.');
                }
                System.out.print(' ');

            }
            System.out.println();
        }

    }
}
