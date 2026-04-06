package model;

import model.shapes.Shape;
import model.shapes.ShapeType;
import model.strategy.LevelStrategy;

import java.util.ArrayList;
import java.util.List;

public class GameModel extends Observable {
    public static final int MAX_BLUE_SHAPES = 4;
    public static final int ROUND_COUNT     = 10;

    private final LevelStrategy levelStrategy;
    private List<Shape> redShapes;
    private GroupeForme blueShapes;
    private ShapeType currentShapeType;
    private int canvasWidth  = 800;
    private int canvasHeight = 600;

    private int currentRound = 1;
    private final List<Float> roundScores = new ArrayList<>();

    public GameModel(LevelStrategy levelStrategy) {
        this.levelStrategy = levelStrategy;
        this.blueShapes = new GroupeForme();
        this.currentShapeType = ShapeType.CIRCLE;
        this.redShapes = new ArrayList<>(levelStrategy.generateLevel());
    }

    public void recordScore() {
        roundScores.add(getScore());
    }

    public void loadNextLevel() {
        currentRound++;
        blueShapes.clear();
        this.redShapes = new ArrayList<>(levelStrategy.generateLevel());
        notifyObservers();
    }

    public boolean isLastRound() {
        return currentRound >= ROUND_COUNT;
    }

    public int getCurrentRound() {
        return currentRound;
    }

    public float getGlobalScore() {
        if (roundScores.isEmpty()) return 0;
        float sum = 0;
        for (float s : roundScores) sum += s;
        return sum / roundScores.size();
    }

    public boolean isIntersecting(Shape newShape) {
        return isIntersecting(newShape, null);
    }

    public boolean isIntersecting(Shape newShape, Shape exclude) {
        for (Shape shape : redShapes) {
            if (shape.collidesWith(newShape)) return true;
        }
        for (Shape shape : blueShapes.getShapes()) {
            if (shape.equals(exclude)) continue;
            if (shape.collidesWith(newShape)) return true;
        }
        return !newShape.isInsideCanvas(canvasWidth, canvasHeight);
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
        return totalArea / (canvasWidth * canvasHeight) * 100f;
    }

    public boolean isGameOver() {
        return blueShapes.getShapes().size() >= MAX_BLUE_SHAPES;
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
        notifyObservers();
    }

    public void addRedShape(Shape f) {
        redShapes.add(f);
        notifyObservers();
    }

    public void removeBlueShape(Shape shapeToRemove) {
        blueShapes.remove(shapeToRemove);
    }
}
