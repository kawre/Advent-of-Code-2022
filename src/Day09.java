import java.awt.*;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.List;

public class Day09 {
    static List<String> lines;
    static Set<Point> traits = new HashSet<>();
    static List<Point> snake = new ArrayList<>();

    public static void main(String[] args) throws FileNotFoundException {
        lines = Utils.readLines("./data/day09");
        traits.add(new Point(0, 0));

        for (int i = 0; i < 10; i++) {
            snake.add(new Point(0, 0));
        }

        for (String line : lines) {
            readMove(line);
        }

        printTraits();
        System.out.println("size: " + traits.size());
    }

    static void readMove(String line) {
        String[] parts = line.split(" ");
        int amount = Integer.parseInt(parts[1]);
        String move = parts[0];

        while (amount > 0) {
            switch (move) {
                case "U" -> moveSnake(Direction.UP);
                case "R" -> moveSnake(Direction.RIGHT);
                case "D" -> moveSnake(Direction.DOWN);
                case "L" -> moveSnake(Direction.LEFT);
            }
            amount--;
        }
    }

    static void moveSnake(Direction dir) {
        Point head = snake.get(0);
        switch (dir) {
            case UP -> head.move(head.x, head.y + 1);
            case RIGHT -> head.move(head.x + 1, head.y);
            case DOWN -> head.move(head.x, head.y - 1);
            case LEFT -> head.move(head.x - 1, head.y);
        }
        snake.set(0, head);

        for (int i = 1; i < snake.size(); i++) {
            head = snake.get(i - 1);
            Point tail = snake.get(i);

            if (head.distance(tail) > Math.sqrt(2)) {
                double minDist = Double.MAX_VALUE;
                Point closest = head;
                Point tmp;

                // East
                tmp = new Point(head.x + 1, head.y);
                if (tmp.distance(tail) < minDist) {
                    minDist = tmp.distance(tail);
                    closest = tmp;
                }

                // West
                tmp = new Point(head.x - 1, head.y);
                if (tmp.distance(tail) < minDist) {
                    minDist = tmp.distance(tail);
                    closest = tmp;
                }

                // North
                tmp = new Point(head.x, head.y + 1);
                if (tmp.distance(tail) < minDist) {
                    minDist = tmp.distance(tail);
                    closest = tmp;
                }

                // South
                tmp = new Point(head.x, head.y - 1);
                if (tmp.distance(tail) < minDist) {
                    minDist = tmp.distance(tail);
                    closest = tmp;
                }

                snake.set(i, closest);
                if (i == snake.size() - 1) {
                    traits.add(closest);
                }
            }
        }
    }

    static void printTraits() {
        int max_x = traits.stream().max(Comparator.comparing(Point::getX)).get().x;
        int min_x = traits.stream().min(Comparator.comparing(Point::getX)).get().x;
        int max_y = traits.stream().max(Comparator.comparing(Point::getY)).get().y;
        int min_y = traits.stream().min(Comparator.comparing(Point::getY)).get().y;

        for (int i = max_y; i >= min_y; i--) {
            for (int j = min_x; j <= max_x; j++) {
                Point p = new Point(j, i);
                System.out.print(traits.contains(p) ? "#" : ".");
            }
            System.out.println();
        }
    }

    private enum Direction {
        UP,
        RIGHT,
        DOWN,
        LEFT
    }
}
