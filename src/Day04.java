import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Day04 {
    static File file = new File("./data/day04");
    static String[][] pairs = new String[0][2];

    public static void main(String[] args) throws FileNotFoundException {
        int[] res = sumOfFullyContain();

        int partOne = res[0];
        System.out.println("part one: " + partOne);

        int partTwo = res[1];
        System.out.println("part two: " + partTwo);
    }

    static int[] sumOfFullyContain() throws FileNotFoundException {
        toPairs(new Scanner(file));
        int sumOne = 0, sumTwo = 0;

        for (String[] pair : pairs) {
            String[] elfOne = pair[0].split("-");
            String[] elfTwo = pair[1].split("-");

            int elf01 = Integer.parseInt(elfOne[0]);
            int elf02 = Integer.parseInt(elfOne[1]);
            int elf11 = Integer.parseInt(elfTwo[0]);
            int elf12 = Integer.parseInt(elfTwo[1]);

            if (
                    (elf01 >= elf11 && elf02 <= elf12) ||
                            (elf01 <= elf11 && elf02 >= elf12)
            ) {
                sumOne++;
            } else if (
                    (elf01 < elf11 && elf02 < elf12 && elf02 >= elf11) ||
                            (elf01 > elf11 && elf02 > elf12 && elf12 >= elf01)
            ) {
                sumTwo++;
            }
        }

        return new int[]{sumOne, sumOne + sumTwo};
    }

    static void toPairs(Scanner scan) {
        while (scan.hasNextLine()) {
            String line = scan.nextLine();
            appendPair(line.split(","));
        }
    }

    static void appendPair(String[] pair) {
        pairs = Arrays.copyOf(pairs, pairs.length + 1);
        pairs[pairs.length - 1] = pair;
    }
}
