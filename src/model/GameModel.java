package model;

import java.util.ArrayList;
import java.util.List;

public class GameModel extends Observable {
    private List<Shape> redShapes;
    private List<Shape> blueShapes;
    private ShapeType currentShapeType;
    private Mode currentMode;

    public GameModel() {
        this.redShapes = new ArrayList<>();
        this.blueShapes = new ArrayList<>();
        this.currentShapeType = ShapeType.CIRCLE;
        this.currentMode = Mode.DRAW;
    }

    public boolean isIntersecting(Shape newShape) {
        for (Shape shape : redShapes) {
            if (Intersection.checkIntersection(shape, newShape)) {
                return true;
            }
        }
        return false;
    }

    public Shape getShapeAt(Point p) {
        for (Shape shape : blueShapes.reversed()) {
            if (shape instanceof Circle) {
                Circle c = (Circle) shape;
                double dx = p.getX() - c.getCenter().getX();
                double dy = p.getY() - c.getCenter().getY();
                if (Math.sqrt(dx * dx + dy * dy) <= c.getRadius()) {
                    return shape;
                }
            } else if (shape instanceof Rectangle) {
                Rectangle r = (Rectangle) shape;
                int minX = Math.min(r.getStart().getX(), r.getEnd().getX());
                int maxX = Math.max(r.getStart().getX(), r.getEnd().getX());
                int minY = Math.min(r.getStart().getY(), r.getEnd().getY());
                int maxY = Math.max(r.getStart().getY(), r.getEnd().getY());
                if (p.getX() >= minX && p.getX() <= maxX && p.getY() >= minY && p.getY() <= maxY) {
                    return shape;
                }
            }
        }
        return null;
    }

    public void translateShape(Shape shapeToMove, int dx, int dy) {
        if (shapeToMove instanceof Circle) {
            Circle c = (Circle) shapeToMove;
            c.setCenter(new Point(c.getCenter().getX() + dx, c.getCenter().getY() + dy));
        } else if (shapeToMove instanceof Rectangle) {
            Rectangle r = (Rectangle) shapeToMove;
            r.setStart(new Point(r.getStart().getX() + dx, r.getStart().getY() + dy));
            r.setEnd(new Point(r.getEnd().getX() + dx, r.getEnd().getY() + dy));
        }
         notifyObservers();
    }

    public void resizeRectangle(Rectangle r, Point corner1, Point corner2) {
        r.setStart(new Point(Math.min(corner1.getX(), corner2.getX()), Math.min(corner1.getY(), corner2.getY())));
        r.setEnd(new Point(Math.max(corner1.getX(), corner2.getX()), Math.max(corner1.getY(), corner2.getY())));
        notifyObservers();
    }



    public ShapeType getCurrentShapeType() {
        return currentShapeType;
    }

    public void setCurrentShapeType(ShapeType currentShapeType) {
        this.currentShapeType = currentShapeType;
    }

    public List<Shape> getRedShapes() {
        return redShapes;
    }

    public List<Shape> getBlueShapes() {
        return blueShapes;
    }

    public int getScore(){
        return blueShapes.size();
    }

    public void addBlueShape(Shape f, Observer observer) {
        blueShapes.add(f);
        f.addObserver(observer);
        notifyObservers();
    }

    public void addRedShape(Shape f) {
        redShapes.add(f);
        notifyObservers();
    }

    public Mode getCurrentMode() {
        return currentMode;
    }

    public void setCurrentMode(Mode currentMode) {
        this.currentMode = currentMode;
    }

    public void removeBlueShape(Shape shapeToRemove) {
        blueShapes.remove(shapeToRemove);
        notifyObservers();
    }
}
