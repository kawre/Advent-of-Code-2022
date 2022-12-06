import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Day06 {
    static File file = new File("./data/day06");
    static String datastream;
    static int size;

    public static void main(String[] args) throws FileNotFoundException {
        datastream = new Scanner(file).next();
        lookForMarkers();
    }

    static void lookForMarkers() {
        size = 4;
        int partOne = firstMarker(size - 1, size);
        System.out.println(partOne);

        size = 14;
        int partTwo = firstMarker(size - 1, size);
        System.out.println(partTwo);
    }

    static int firstMarker(int i, int size) {
        if (i > datastream.length()) {
            return -1;
        }

        String marker = datastream.substring(i - (size - 1), i + 1);

        Set<Character> set = new HashSet<>();
        for (char c : marker.toCharArray())
            set.add(c);

        return set.size() == size ? i + 1 : firstMarker(i + 1, size);
    }
}