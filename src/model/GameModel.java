package model;

import java.util.ArrayList;
import java.util.List;

public class GameModel extends Observable {
    private List<Shape> redShapes;
    private List<Shape> blueShapes;

    public GameModel() {
        this.redShapes = new ArrayList<>();
        this.blueShapes = new ArrayList<>();
    }

    public List<Shape> getRedShapes() {
        return redShapes;
    }

    public List<Shape> getBlueShapes() {
        return blueShapes;
    }

    public void addBlueShape(Shape f) {
        if (blueShapes.size() < 4) {
            blueShapes.add(f);
            notifyObservers();
        }
    }

    public void addRedShape(Shape f) {
        redShapes.add(f);
        notifyObservers();
    }
}
