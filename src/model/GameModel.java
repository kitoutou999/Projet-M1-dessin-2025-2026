package model;

import java.util.ArrayList;
import java.util.List;

public class GameModel extends Observable {
    private List<Shape> redShapes;
    private List<Shape> blueShapes;
    private ShapeType currentShapeType;

    public GameModel() {
        this.redShapes = new ArrayList<>();
        this.blueShapes = new ArrayList<>();
        this.currentShapeType = ShapeType.CIRCLE;
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

    public void addBlueShape(Shape f) {
        blueShapes.add(f);
        notifyObservers();
    }

    public void addRedShape(Shape f) {
        redShapes.add(f);
        notifyObservers();
    }
}
