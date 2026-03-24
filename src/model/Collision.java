package model;

import model.shapes.Circle;
import model.shapes.Rectangle;

public class Collision {

    public static boolean collisionCircleCircle(Circle c1, Circle c2) {
        double distance = Math.sqrt(Math.pow(c2.getCenter().getX() - c1.getCenter().getX(), 2)
                                  + Math.pow(c2.getCenter().getY() - c1.getCenter().getY(), 2));
        return distance <= c1.getRadius() + c2.getRadius();
    }

    public static boolean collisionRectangleCircle(Rectangle r, Circle c) {
        int cx = c.getCenter().getX();
        int cy = c.getCenter().getY();
        int closestX = Math.max(r.getMinX(), Math.min(cx, r.getMaxX()));
        int closestY = Math.max(r.getMinY(), Math.min(cy, r.getMaxY()));
        int dx = cx - closestX;
        int dy = cy - closestY;
        return dx * dx + dy * dy <= c.getRadius() * c.getRadius();
    }

    public static boolean collisionRectangleRectangle(Rectangle r1, Rectangle r2) {
        return !(r1.getMaxX() < r2.getMinX() || r1.getMinX() > r2.getMaxX()
              || r1.getMinY() > r2.getMaxY() || r1.getMaxY() < r2.getMinY());
    }
}
