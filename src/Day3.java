import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Day3 {
    static File file = new File("./data/day3_example");
    static String[][] rucksacks = new String[0][2];

    public static void main(String[] args) throws FileNotFoundException {
        int[] res = sumOfPriorities(new Scanner(file));

        int partOne = res[0];
        int partTwo = res[1];

        System.out.println("part one: " + partOne);
        System.out.println("part two: " + partTwo);
    }

    static int[] sumOfPriorities(Scanner scan) {
        int sum = 0;
        int sum2 = 0;
        String[] group = new String[0];


        do {
            String line = scan.nextLine();
            splitRucksack(line);

            String[] tmp = group;
            group = new String[group.length + 1];
            System.arraycopy(tmp, 0, group, 0, tmp.length);
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
        String left = rucksack[0];
        String right = rucksack[1];
        Set<Character> duplicates = new HashSet<>();

        for (char c : left.toCharArray()) {
            set.add(c);
        }

        for (int i = 0; i < right.length(); i++) {
            char c = right.toCharArray()[i];
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

        insert(rucksack);
    }

    static void insert(String[] rucksack) {
        String[][] tmp = rucksacks;
        rucksacks = new String[tmp.length + 1][2];
        System.arraycopy(tmp, 0, rucksacks, 0, tmp.length);
        rucksacks[rucksacks.length - 1] = rucksack;
    }
}
