import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

// Rock [A,X]
// Paper [B,Y]
// Scissors [C,X]

public class Day2 {
    static File file = new File("./data/day2_example");

    public static void main(String[] args) throws FileNotFoundException {
        // part one
        int totalScore = calculateTotalScore(new Scanner(file));
        System.out.println("score: " + totalScore);

        // part two
        int totalScore2 = calculateTotalScore2(new Scanner(file));
        System.out.println("score: " + totalScore2);

    }

    static int calculateTotalScore(Scanner scan) {
        int totalScore = 0;

        do {
            String line = scan.nextLine();
            char[] guide = (line.replace(" ", "")).toCharArray();
            totalScore += outcome(guide);
        } while (scan.hasNextLine());

        return totalScore;
    }


    static int outcome(char[] tab) {
        int score;
        int elf = tab[0] - 65, me = tab[1] - 88;

        if (elf == me) {
            // draw
            score = 3;
        } else if ((elf == 0 && me == 1) || (elf == 1 && me == 2) || (elf == 2 && me == 0)) {
            // won
            score = 6;
        } else {
            // lost
            score = 0;
        }

        return me + 1 + score;
    }

    static int calculateTotalScore2(Scanner scan) {
        int totalScore = 0;

        do {
            String line = scan.nextLine();
            char[] guide = (line.replace(" ", "")).toCharArray();
            totalScore += outcome2(guide);
        } while (scan.hasNextLine());

        return totalScore;
    }

    static int outcome2(char[] tab) {
        int elf = tab[0] - 65, need = (tab[1] - 88) * 3;
        int score = 0, me = 0;

        for (int i = 0; i < 3; i++) {
            if (elf == i) {
                // draw
                score = 3;
            } else if ((elf == 0 && i == 1) || (elf == 1 && i == 2) || (elf == 2 && i == 0)) {
                // won
                score = 6;
            } else {
                // lost
                score = 0;
            }

            if (score == need) {
                me = i;
                break;
            }
        }

        return me + 1 + score;
    }

}
