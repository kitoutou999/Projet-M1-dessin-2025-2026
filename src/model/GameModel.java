package model;

import model.shapes.Shape;
import model.shapes.ShapeType;

import java.util.ArrayList;
import java.util.List;

public class GameModel extends Observable {
    private List<Shape> redShapes;
    private GroupeForme blueShapes;
    private ShapeType currentShapeType;
    private int canvasWidth = 800;
    private int canvasHeight = 600;

    public GameModel() {
        this.redShapes = new ArrayList<>();
        this.blueShapes = new GroupeForme();
        this.currentShapeType = ShapeType.CIRCLE;
    }

    public boolean isIntersecting(Shape newShape) {
        return isIntersecting(newShape, null);
    }

    public boolean isIntersecting(Shape newShape, Shape exclude) {
        for (Shape shape : redShapes) {
            if (Collision.collisionBetween(shape, newShape)) {
                return true;
            }
        }
        for (Shape shape : blueShapes.getShapes()) {
            if (shape.equals(exclude)) continue;
            if (Collision.collisionBetween(shape, newShape)) {
                return true;
            }
        }

        if (Collision.isOutsideCanvas(newShape, canvasWidth, canvasHeight)) {
            return true;
        }

        return false;
    }

    public Shape getShapeAt(Point p) {
        List<Shape> shapes = blueShapes.getShapes();
        for (int i = shapes.size() - 1; i >= 0; i--) {
            if (shapes.get(i).contains(p)) return shapes.get(i);
        }
        return null;
    }

    public void translateShape(Shape shapeToMove, int dx, int dy) {
        shapeToMove.translate(dx, dy);
    }

    public void resize(Shape shape, Point anchor, Point drag) {
        shape.resize(anchor, drag);
    }

    public float getScore() {
        float totalArea = 0;
        for (Shape shape : blueShapes.getShapes()) {
            totalArea += shape.getArea();
        }
        return totalArea / 1000;
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
        return blueShapes.getShapes();
    }

    public GroupeForme getBlueGroup() {
        return blueShapes;
    }

    public int getCanvasWidth() { return canvasWidth; }

    public int getCanvasHeight() { return canvasHeight; }

    public void addBlueShape(Shape f) {
        blueShapes.add(f);
    }

    public void addRedShape(Shape f) {
        redShapes.add(f);
        notifyObservers();
    }

    public void removeBlueShape(Shape shapeToRemove) {
        blueShapes.remove(shapeToRemove);
    }
}
