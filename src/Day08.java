import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Day08 {
    static File file = new File("./data/day08");
    static List<String> lines = new ArrayList<>();
    static int visibleCount;
    static int maxScenic;

    public static void main(String[] args) throws FileNotFoundException {
        readLines(new Scanner(file));
        visibleCount = ((lines.size() + lines.get(0).length()) * 2) - 4;

        loopGrid();

        System.out.println(visibleCount);
        System.out.println(maxScenic);
    }

    static void loopGrid() {
        for (int i = 1; i < lines.size() - 1; i++) {
            String line = lines.get(i);
            for (int j = 1; j < line.length() - 1; j++) {
                int tree = Character.getNumericValue(line.charAt(j));
                boolean top = true, right = true, bottom = true, left = true;
                int treeCompare;
                int scenic = 1;

                int n = j;
                int count = 0;
                do {
                    count++;
                    n--;
                } while (n > 0 && Character.getNumericValue(lines.get(i).charAt(n)) < tree);
                scenic *= count;

                n = j;
                count = 0;
                do {
                    count++;
                    n++;
                } while (n < line.length() - 1 && Character.getNumericValue(lines.get(i).charAt(n)) < tree);
                scenic *= count;

                for (n = 0; n < line.length(); n++) {
                    if (n == j) continue;
                    treeCompare = Character.getNumericValue(line.charAt(n));

                    if (treeCompare >= tree) {
                        if (n < j) {
                            left = false;
                        } else {
                            right = false;
                        }
                    }
                }

                int k = i;
                count = 0;
                do {
                    count++;
                    k--;
                } while (k > 0 && Character.getNumericValue(lines.get(k).charAt(j)) < tree);
                scenic *= count;

                k = i;
                count = 0;
                do {
                    count++;
                    k++;
                } while (k < lines.size() - 1 && Character.getNumericValue(lines.get(k).charAt(j)) < tree);
                scenic *= count;

                for (k = 0; k < lines.size(); k++) {
                    if (k == i) continue;
                    treeCompare = Character.getNumericValue(lines.get(k).charAt(j));

                    if (treeCompare >= tree) {
                        if (k < i) {
                            top = false;
                        } else {
                            bottom = false;
                        }
                    }
                }

                maxScenic = Math.max(maxScenic, scenic);
                if (top || right || bottom || left) {
                    visibleCount++;
                }
            }
        }
    }


    static void readLines(Scanner scan) {
        if (!scan.hasNextLine()) return;
        lines.add(scan.nextLine());
        readLines(scan);
    }
}
