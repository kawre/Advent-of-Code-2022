import java.io.FileNotFoundException;
import java.util.*;

public class Day11 extends Utils {
    static List<List<String>> lines = new ArrayList<>();
    static List<Monkey> monkeys = new ArrayList<>();

    static class Monkey {
        Deque<Long> items;
        String[] operation;
        int[] testValues;
        int inspected = 0;
        int index;
        int div;

        Monkey(String starting, String op, int d, int[] testV, int i) {
            Deque<Long> tmp = new ArrayDeque<>();
            for (String n : starting.split(", ")) {
                tmp.addLast(Long.parseLong(n));
            }

            items = tmp;
            operation = op.split(" ");
            div = d;
            testValues = testV;
            index = i;
        }

        void inspect() {
            while (!items.isEmpty()) {
                long item = updateWorry(items.pollFirst());
                monkeys.get(test(item)).items.addLast(item);
                inspected++;
            }
        }

        long updateWorry(long n) {
            long val = operation[2].equals("old") ? n : Integer.parseInt(operation[2]);
            long newWorry;

            if (operation[1].equals("+"))
                newWorry = n + val;
            else
                newWorry = n * val;

            return newWorry / 3;
        }

        int test(long item) {
            return item % div == 0 ? testValues[0] : testValues[1];
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        lines = readLinesWithBreak("./data/day11", "");
        toMonkeys();


        for (int i = 1; i <= 10000; i++) {
            for (Monkey m : monkeys) {
                m.inspect();
            }
//            // part one
//            if (i == 20) {
//                monkeys.sort((a, b) -> b.inspected - a.inspected);
//                long partOne = (long) monkeys.get(0).inspected * monkeys.get(1).inspected;
//                System.out.println("partOne: " + partOne);
//            }
            if (i == 1 || i == 20 || i % 1000 == 0) {
                System.out.println("== After round " + i + " ==");
                for (Monkey m : monkeys) {
                    System.out.println("Monkey " + m.index + " inspected items " + m.inspected + " times");
                }
                System.out.println();
            }
        }

        monkeys.sort((a, b) -> b.inspected - a.inspected);
        long partTwo = (long) monkeys.get(0).inspected * monkeys.get(1).inspected;
        System.out.println("partTwo: " + partTwo);
    }

    static void toMonkeys() {
        for (List<String> monkey : lines) {
            int i = Integer.parseInt(monkey.get(0).substring(0, monkey.get(0).length() - 1).split(" ")[1]);
            String startingItems = monkey.get(1).split(": ")[1];
            String op = monkey.get(2).split(" = ")[1];
            int div = Integer.parseInt(monkey.get(3).split(" by ")[1]);
            String ifTrue = monkey.get(4).split(" monkey ")[1];
            String ifFalse = monkey.get(5).split(" monkey ")[1];
            int[] test = {Integer.parseInt(ifTrue), Integer.parseInt(ifFalse)};

            monkeys.add(new Monkey(startingItems, op, div, test, i));
        }
    }
}
