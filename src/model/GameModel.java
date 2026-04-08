package model;

import model.shapes.CircleFactory;
import model.shapes.Shape;
import model.shapes.ShapeFactory;
import model.strategy.LevelStrategy;

import java.util.ArrayList;
import java.util.List;

/**
 * Représente le modèle principal du jeu.
 * Contient l'état du jeu, les formes, le score, les paramètres, la taille de la fenetre et le round actuel.
 * 
 * Cette classe suit le pattern MVC pour pouvoir notifier les observateurs lors des changements d'etat.
 */
public class GameModel extends Observable {
    public static final int MAX_BLUE_SHAPES = 4;
    public static final int ROUND_COUNT     = 10;

    private final LevelStrategy levelStrategy;
    private List<Shape> redShapes;
    private GroupeForme blueShapes;
    private ShapeFactory shapeFactory;
    private int canvasWidth  = 1200;
    private int canvasHeight = 800;

    private int currentRound = 1;
    private final List<Float> roundScores = new ArrayList<>();
    private boolean redShapesVisible = true;

    public GameModel(LevelStrategy levelStrategy) {
        this.levelStrategy = levelStrategy;
        this.blueShapes = new GroupeForme();
        this.shapeFactory = new CircleFactory();
        this.redShapes = new ArrayList<>(levelStrategy.generateLevel());
    }

    public void recordScore() {
        roundScores.add(getScore());
    }

    public void loadNextLevel() {
        currentRound++;
        redShapesVisible = true;
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
        for (Shape shape : getAllRedShapes()) {
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
        return blueShapes.getArea() / (canvasWidth * canvasHeight) * 100f;
    }

    public boolean isGameOver() {
        return blueShapes.getShapes().size() >= MAX_BLUE_SHAPES;
    }

    public ShapeFactory getShapeFactory() {
        return shapeFactory;
    }

    public void setShapeFactory(ShapeFactory factory) {
        this.shapeFactory = factory;
    }

    public List<Shape> getRedShapes() {
        return redShapesVisible ? redShapes : new ArrayList<>();
    }

    public List<Shape> getAllRedShapes() {
        return redShapes;
    }

    public boolean isRedShapesVisible() {
        return redShapesVisible;
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
        notifyObservers();
    }

    public void resetBlueShapes() {
        blueShapes.clear();
        notifyObservers();
    }

    public void hideRedShapes() {
        redShapesVisible = false;
        notifyObservers();
    }
}
