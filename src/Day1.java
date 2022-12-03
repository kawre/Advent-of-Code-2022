import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Day1 {
    static File file = new File("./data/day1");
    static int[] elf_inventory = new int[0];

    static Elf[] elves = new Elf[0];

    static class Elf {
        int totalCalories;
        int[] inventory;

        public Elf(int[] elf_inventory) {
            inventory = elf_inventory;
            for (int n : inventory)
                totalCalories += n;
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        toArray();
        int max = findMaxTotalCalories();
        System.out.println(max);
        int topThree = sumOfTopThreeMaxTotalCalories();
        System.out.println(topThree);
    }


    // Elf carrying the most calories
    static int findMaxTotalCalories() {
        int maxTotalCalories = 0;
        for (Elf elf : elves) {
            if (elf.totalCalories > maxTotalCalories) {
                maxTotalCalories = elf.totalCalories;
            }
        }

        return maxTotalCalories;
    }

    // Sum of the top three elves carrying the most calories
    static int sumOfTopThreeMaxTotalCalories() {
        int sum = 0;
        int[] tab = new int[0];

        for (Elf elf : elves) {
            int[] tmp = tab;
            tab = new int[tmp.length + 1];
            System.arraycopy(tmp, 0, tab, 0, tmp.length);
            tab[tab.length - 1] = elf.totalCalories;
        }

        for (int i = 0; i < tab.length; i++) {
            for (int j = 1; j < tab.length - i; j++) {
                if (tab[j - 1] < tab[j]) {
                    int tmp = tab[j - 1];
                    tab[j - 1] = tab[j];
                    tab[j] = tmp;
                }

            }
        }

        for (int i : Arrays.copyOfRange(tab, 0, 3)) {
            sum += i;
        }

        return sum;
    }

    // expand elves
    static void append(Elf elf) {
        elves = Arrays.copyOf(elves, elves.length + 1);
        elves[elves.length - 1] = elf;
    }

    // expand elf inventory
    static void append(int n) {
        elf_inventory = Arrays.copyOf(elf_inventory, elf_inventory.length + 1);
        elf_inventory[elf_inventory.length - 1] = n;
    }

    // Split lines into array of calories being carried by single elf
    static void toArray() throws FileNotFoundException {
        Scanner scan = new Scanner(file);

        do {
            String line = scan.nextLine();

            if (line.equals("")) {
                append(new Elf(elf_inventory));
                elf_inventory = new int[0];
            } else {
                append(Integer.parseInt(line));
            }
        } while (scan.hasNextLine());
    }
}