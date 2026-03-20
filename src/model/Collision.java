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
        int minX = r.getStart().getX();
        int maxX = r.getEnd().getX();
        int minY = r.getStart().getY();
        int maxY = r.getEnd().getY();

        int cx = c.getCenter().getX();
        int cy = c.getCenter().getY();

        int closestX = Math.max(minX, Math.min(cx, maxX));
        int closestY = Math.max(minY, Math.min(cy, maxY));

        int dx = cx - closestX;
        int dy = cy - closestY;

        return dx*dx + dy*dy <= c.getRadius()*c.getRadius();
    }

    public static boolean collisionRectangleRectangle(Rectangle r1,Rectangle r2){
        if (r1.getEnd().getX() < r2.getStart().getX() ||
            r1.getStart().getX() > r2.getEnd().getX() ||
            r1.getStart().getY() > r2.getEnd().getY() ||
            r1.getEnd().getY() < r2.getStart().getY()) {
                return false;
            }
        return true;
    }
}