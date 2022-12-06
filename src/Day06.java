import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Day06 {
    static File file = new File("./data/day06");
    static String datastream;

    public static void main(String[] args) throws FileNotFoundException {
        datastream = new Scanner(file).next();
        lookForMarkers();
    }

    static void lookForMarkers() {
        int partOne = -1;
        for (int i = 3; i < datastream.length(); i++) {
            if (isMarker(i, 4)) {
                partOne = i + 1;
                break;
            }
        }
        System.out.println(partOne);


        int partTwo = -1;
        for (int i = 13; i < datastream.length(); i++) {
            if (isMarker(i, 14)) {
                partTwo = i + 1;
                break;
            }
        }

        System.out.println(partTwo);
    }

    static boolean isMarker(int i, int size) {
        if (i <= size - 2) return false;

        Set<Character> set = new HashSet<>();
        String marker = datastream.substring(i - (size - 1), i + 1);

        for (char c : marker.toCharArray())
            set.add(c);

        return set.size() == size;
    }
}