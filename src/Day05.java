import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Day05 {
    static File file = new File("./data/day05");
    static char[][] stacks = new char[0][0];


    public static void main(String[] args) throws FileNotFoundException {
        readLines(new Scanner(file));

        String partOne = cratesAtTheTop();
        System.out.println(partOne);
    }

    static String cratesAtTheTop() {
        StringBuilder top = new StringBuilder();

        for (char[] stack : stacks) {
            char c = stack[0];
            top.append(c);
        }

        return top.toString();
    }


    static void readLines(Scanner scan) {
        String line = scan.nextLine();

        while (!line.equals("")) {
            readCrates(line);
            line = scan.nextLine();
        }

        while (scan.hasNextLine()) {
            line = scan.nextLine();
            readInstructions(line);
        }

    }

    static void readInstructions(String line) {
        String[] inst = line.split(" ");
        int amount = Integer.parseInt(inst[1]);
        int from = Integer.parseInt(inst[3]) - 1;
        int to = Integer.parseInt(inst[5]) - 1;

        // part one
//        while (amount-- > 0) {
//            moveCrate(from, to);
//        }

        // part two
        moveCrates(amount, from, to);
    }

    static void moveCrate(int from, int to) {
        char tmp = stacks[from][0];
        stacks[from] = Arrays.copyOfRange(stacks[from], 1, stacks[from].length);

        char[] tmpArr = new char[stacks[to].length + 1];
        tmpArr[0] = tmp;
        System.arraycopy(stacks[to], 0, tmpArr, 1, stacks[to].length);
        stacks[to] = tmpArr;
    }

    static void moveCrates(int amount, int from, int to) {
        char[] payload = Arrays.copyOf(stacks[from], amount);
        stacks[from] = Arrays.copyOfRange(stacks[from], amount, stacks[from].length);

        char[] tmp = new char[stacks[to].length + amount];
        System.arraycopy(payload, 0, tmp, 0, amount);
        System.arraycopy(stacks[to], 0, tmp, amount, stacks[to].length);

        stacks[to] = tmp;
    }

    static void readCrates(String line) {
        int i = 0;
        while ((1 + i * 4) < line.length()) {
            char c = line.charAt(1 + i * 4);
            appendCrate(c, i);
            i++;
        }
    }

    static void appendStack() {
        stacks = Arrays.copyOf(stacks, stacks.length + 1);
        stacks[stacks.length - 1] = new char[0];
    }

    static void appendCrate(char crate, int i) {
        if (i >= stacks.length) appendStack();
        if (crate == ' ' || (crate >= '0' && crate <= '9')) return;

        char[] stack = stacks[i];
        stack = Arrays.copyOf(stack, stack.length + 1);
        stack[stack.length - 1] = crate;
        stacks[i] = stack;
    }
}
