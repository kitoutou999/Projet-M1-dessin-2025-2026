package model;

import java.util.ArrayList;
import java.util.List;

public class Collision{
    public static boolean collisionBetween(Shape shape1,Shape shape2){
        int comp = 0;
        Circle c1 = null;
        Circle c2 = null;
        Rectangle r1 = null;
        Rectangle r2 = null;
        if(shape1 instanceof Circle){
            if(shape2 instanceof Circle){
                c1 = (Circle) shape1;
                c2 = (Circle) shape2;
                return collisionCircleCircle(c1,c2);
            }else if(shape2 instanceof Rectangle){
                c1 = (Circle) shape1;
                r2 = (Rectangle) shape2;
                return collisionRectangleCircle(r2,c1);
            }
        }else if(shape1 instanceof Rectangle){
            if(shape2 instanceof Circle){
                r1 = (Rectangle) shape1;
                c2 = (Circle) shape2;
                return collisionRectangleCircle(r1,c2);
            }else if(shape2 instanceof Rectangle){
                r1 = (Rectangle) shape1;
                r2 = (Rectangle) shape2;
                return collisionRectangleRectangle(r1,r2);
            }
        }
        return false;
    }

    public static boolean collisionCircleCircle(Circle c1,Circle c2){
        double distance = Math.sqrt(Math.pow(c2.getCenter().getX()-c1.getCenter().getX(),2)+Math.pow(c2.getCenter().getY()-c1.getCenter().getY(),2));
        if(distance <= (c1.getRadius()+c2.getRadius())){
            return true;
        }return false;
    }

    public static boolean collisionRectangleCircle(Rectangle r,Circle c){
        int cx = c.getCenter().getX();
        int cy = c.getCenter().getY();

        int closestX = Math.max(r.getMinX(), Math.min(cx, r.getMaxX()));
        int closestY = Math.max(r.getMinY(), Math.min(cy, r.getMaxY()));

        int dx = cx - closestX;
        int dy = cy - closestY;

        return dx*dx + dy*dy <= c.getRadius()*c.getRadius();
    }

    public static boolean collisionRectangleRectangle(Rectangle r1,Rectangle r2){
        if (r1.getMaxX() < r2.getMinX() || r1.getMinX() > r2.getMaxX()
         || r1.getMinY() > r2.getMaxY() || r1.getMaxY() < r2.getMinY()) {
            return false;
        }
        return true;
    }

    public static boolean isOutsideCanvas(Shape shape, int canvasWidth, int canvasHeight) {
        if (shape instanceof Circle) {
            Circle c = (Circle) shape;
            return c.getCenter().getX() - c.getRadius() < 0 || c.getCenter().getX() + c.getRadius() > canvasWidth ||
                   c.getCenter().getY() - c.getRadius() < 0 || c.getCenter().getY() + c.getRadius() > canvasHeight;
        } else if (shape instanceof Rectangle) {
            Rectangle r = (Rectangle) shape;
            return r.getMinX() < 0 || r.getMaxX() > canvasWidth || r.getMinY() < 0 || r.getMaxY() > canvasHeight;
        }
        return false;
    }
}
