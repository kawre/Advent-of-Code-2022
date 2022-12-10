import java.io.FileNotFoundException;
import java.util.*;

public class Day10 {
    static List<String> lines = new ArrayList<>();
    static List<List<Character>> CPT = new ArrayList<>();
    static int X = 1, c = 0, partOne = 0;

    public static void main(String[] args) throws FileNotFoundException {
        lines = Utils.readLines("./data/day10");

        for (int i = 0; i < 6; i++) {
            List<Character> tmp = new ArrayList<>();
            for (int j = 0; j < 40; j++)
                tmp.add('.');
            CPT.add(tmp);
        }

        for (String line : lines) {
            execute(line);
        }

        System.out.println("part one: " + partOne);
        System.out.println();

        printCRT();
    }

    static void execute(String line) {
        String[] parts = line.split(" ");
        String inst = parts[0];

        if (inst.equals("noop")) {
            updateCycle();
        } else {
            for (int i = 0; i < 2; i++)
                updateCycle();
            X += Integer.parseInt(parts[1]);
        }
    }

    static void updateCycle() {
        int i = c / 40;
        int curr = c % 40;

        if (curr == X - 1 || curr == X || curr == X + 1)
            CPT.get(i).set(curr, '#');

        if (++c % 40 == 20)
            partOne += X * c;
    }

    static void printCRT() {
        for (List<Character> c : CPT) {
            for (Character x : c) {
                System.out.print(x);
                System.out.print(' ');
            }
            System.out.println();
        }
    }
}
