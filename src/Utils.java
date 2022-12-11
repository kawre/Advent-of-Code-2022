import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Utils {
    public static List<String> readLines(String path) throws FileNotFoundException {
        List<String> lines = new ArrayList<>();
        File file = new File(path);
        Scanner scan = new Scanner(file);

        while (scan.hasNextLine()) {
            lines.add(scan.nextLine());
        }

        return lines;
    }

    public static List<List<String>> readLinesWithBreak(String path, String stop) throws FileNotFoundException {
        List<List<String>> lines = new ArrayList<>();
        File file = new File(path);
        Scanner scan = new Scanner(file);

        while (scan.hasNextLine()) {
            String line = scan.nextLine();
            List<String> tmp = new ArrayList<>();

            do {
                tmp.add(line);
            } while (scan.hasNextLine() && !(line = scan.nextLine()).equals(stop));

            lines.add(tmp);
        }

        return lines;
    }
}
