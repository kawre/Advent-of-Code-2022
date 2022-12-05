import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Day03 {
    static File file = new File("./data/day03");
    static String[][] rucksacks = new String[0][2];

    public static void main(String[] args) throws FileNotFoundException {
        int[] res = sumOfPriorities(new Scanner(file));

        int partOne = res[0];
        System.out.println("part one: " + partOne);

        int partTwo = res[1];
        System.out.println("part two: " + partTwo);
    }

    static int[] sumOfPriorities(Scanner scan) {
        String[] group = new String[0];
        int sum = 0, sum2 = 0;

        do {
            String line = scan.nextLine();
            splitRucksack(line);

            group = Arrays.copyOf(group, group.length + 1);
            group[group.length - 1] = line;

            if (group.length >= 3) {
                sum2 += findDuplicatesOfThree(group);
                group = new String[0];
            }
        } while (scan.hasNextLine());

        for (String[] rucksack : rucksacks) {
            sum += sumOfDuplicates(findDuplicates(rucksack));
        }

        return new int[]{sum, sum2};
    }


    static int findDuplicatesOfThree(String[] group) {
        char[] dup1 = findDuplicates(new String[]{group[0], group[1]});
        char[] dup2 = findDuplicates(new String[]{group[1], group[2]});

        return sumOfDuplicates(findDuplicates(new String[]{String.valueOf(dup1), String.valueOf(dup2)}));
    }

    static int sumOfDuplicates(char[] duplicates) {
        int sum = 0;
        for (char c : duplicates) {
            int n = (c > 97 ? c - 97 : c - 65 + 26) + 1;
            sum += n;
        }

        return sum;
    }

    static char[] findDuplicates(String[] rucksack) {
        Set<Character> set = new HashSet<>();
        char[] left = rucksack[0].toCharArray();
        char[] right = rucksack[1].toCharArray();
        Set<Character> duplicates = new HashSet<>();

        for (char c : left) {
            set.add(c);
        }

        for (char c : right) {
            if (set.contains(c)) {
                duplicates.add(c);
            }
        }

        char[] res = new char[duplicates.size()];
        int i = 0;
        for (char c : duplicates) {
            res[i++] = c;
        }

        return res;
    }

    static void splitRucksack(String line) {
        String[] rucksack = new String[2];

        rucksack[0] = line.substring(0, line.length() / 2);
        rucksack[1] = line.substring(line.length() / 2);

        appendRucksack(rucksack);
    }

    static void appendRucksack(String[] rucksack) {
        rucksacks = Arrays.copyOf(rucksacks, rucksacks.length + 1);
        rucksacks[rucksacks.length - 1] = rucksack;
    }
}
