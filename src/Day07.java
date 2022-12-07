import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;


public class Day07 {
    static File file = new File("./data/day07");
    static Directory head = new Directory("/", null);
    static Directory dir = head;

    static class Directory {
        HashSet<String> files = new HashSet<>();
        ArrayList<Directory> children = new ArrayList<>();
        Directory parent;
        int size;
        String name;

        public Directory(String dirName, Directory dirParent) {
            if (dirParent != null)
                parent = dirParent;
            name = dirName;
        }

        public void addDir(String name) {
            for (Directory dir : children)
                if (name.equals(dir.name))
                    return;

            children.add(new Directory(name, this));
        }

        public Directory findDir(String name) {
            for (Directory dir : children)
                if (name.equals(dir.name))
                    return dir;

            return null;
        }

        public int totalSize() {
            int sum = size;
            for (Directory dir : children)
                sum += dir.totalSize();

            return sum;
        }

        public int sumOfTotalSizesAtMost100000() {
            int totalSize = totalSize();

            int sum = 0;
            for (Directory dir : children)
                sum += dir.sumOfTotalSizesAtMost100000();

            return sum + (totalSize <= 100000 ? totalSize : 0);
        }

        public void addFile(String[] parts) {
            size += Integer.parseInt(parts[0]);
            files.add(parts[1]);
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        readLine(new Scanner(file));
        System.out.println(head.sumOfTotalSizesAtMost100000());
    }

    static void readLine(Scanner scan) {
        if (!scan.hasNextLine()) return;
        String[] parts = scan.nextLine().split(" ");

        if (parts[0].equals("$")) {
            if (parts[1].equals("cd")) {
                if (!parts[2].equals("..")) {
                    String name = parts[2];
                    if (!name.equals("/")) dir = dir.findDir(name);
                } else {
                    dir = dir.parent;
                }
            }
        } else {
            if (parts[0].equals("dir")) {
                String name = parts[1];
                dir.addDir(name);
            } else {
                dir.addFile(parts);
            }

        }

        readLine(scan);
    }
}
