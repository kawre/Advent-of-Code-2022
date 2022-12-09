import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Utils {
    public static void main(String[] args) {
    }

    public static List<String> readLines(String path) throws FileNotFoundException {
        List<String> lines = new ArrayList<>();
        File file = new File(path);
        Scanner scan = new Scanner(file);

        while (scan.hasNextLine()) {
            lines.add(scan.nextLine());
        }

        return lines;
    }
}
