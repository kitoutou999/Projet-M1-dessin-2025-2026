package model;

public class Intersection {

    public static boolean checkIntersection(Shape s1, Shape s2) {
        if (s1 instanceof Circle && s2 instanceof Circle) {
            return circleCircle((Circle) s1, (Circle) s2);
        } else if (s1 instanceof Circle && s2 instanceof Rectangle) {
            return circleRectangle((Circle) s1, (Rectangle) s2);
        } else if (s1 instanceof Rectangle && s2 instanceof Circle) {
            return circleRectangle((Circle) s2, (Rectangle) s1);
        } else if (s1 instanceof Rectangle && s2 instanceof Rectangle) {
            return rectangleRectangle((Rectangle) s1, (Rectangle) s2);
        }
        return false;
    }

    private static boolean circleCircle(Circle c1, Circle c2) {
        double dx = c2.getCenter().getX() - c1.getCenter().getX();
        double dy = c2.getCenter().getY() - c1.getCenter().getY();
        double distance = Math.sqrt(dx * dx + dy * dy);
        return distance <= (c1.getRadius() + c2.getRadius());
    }

    private static boolean circleRectangle(Circle c, Rectangle r) {
        int minX = Math.min(r.getStart().getX(), r.getEnd().getX());
        int maxX = Math.max(r.getStart().getX(), r.getEnd().getX());
        int minY = Math.min(r.getStart().getY(), r.getEnd().getY());
        int maxY = Math.max(r.getStart().getY(), r.getEnd().getY());

        int cx = c.getCenter().getX();
        int cy = c.getCenter().getY();

        int closestX = Math.max(minX, Math.min(cx, maxX));
        int closestY = Math.max(minY, Math.min(cy, maxY));

        int dx = cx - closestX;
        int dy = cy - closestY;
        return dx * dx + dy * dy <= c.getRadius() * c.getRadius();
    }

    private static boolean rectangleRectangle(Rectangle r1, Rectangle r2) {
        int r1minX = Math.min(r1.getStart().getX(), r1.getEnd().getX());
        int r1maxX = Math.max(r1.getStart().getX(), r1.getEnd().getX());
        int r1minY = Math.min(r1.getStart().getY(), r1.getEnd().getY());
        int r1maxY = Math.max(r1.getStart().getY(), r1.getEnd().getY());

        int r2minX = Math.min(r2.getStart().getX(), r2.getEnd().getX());
        int r2maxX = Math.max(r2.getStart().getX(), r2.getEnd().getX());
        int r2minY = Math.min(r2.getStart().getY(), r2.getEnd().getY());
        int r2maxY = Math.max(r2.getStart().getY(), r2.getEnd().getY());

        return r1maxX >= r2minX && r1minX <= r2maxX
            && r1maxY >= r2minY && r1minY <= r2maxY;
    }
}
