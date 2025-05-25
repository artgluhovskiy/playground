package org.art.playground.misc.core.amber;

public class PatternMatchingSample {

    record Point(int x, int y) {
    }

    record ColorPoint(Point p, String color) {
    }

    public static void main(String[] args) {
        ColorPoint rec = new ColorPoint(new Point(10, 15), "green");

        System.out.println(describe(rec));
    }

    static String describe(ColorPoint point) {
        return switch (point) {
            case ColorPoint(Point(int x, int y), String color) -> "X: %s, Y: %s, color: %s".formatted(x, y, color);
            default -> "Unknown Color";
        };
    }

    static String describe2(ColorPoint point) {
        return switch (point) {
            case ColorPoint cp -> {
                Point p = cp.p();
                int x = p.x();
                int y = p.y();
                String color = cp.color();
                yield "X: %s, Y: %s, color: %s".formatted(x, y, color);
            }
        };
    }
}
