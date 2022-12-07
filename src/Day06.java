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

        int partOne = findMarker(size = 4);
        System.out.println(partOne);

        int partTwo = findMarker(size = 14);
        System.out.println(partTwo);
    }

    static int findMarker(int i) {
        if (i > datastream.length())
            return -1;

        String marker = datastream.substring(i - size, i);

        Set<Character> set = new HashSet<>();
        for (char c : marker.toCharArray())
            set.add(c);

        return set.size() == size ? i : findMarker(++i);
    }
}