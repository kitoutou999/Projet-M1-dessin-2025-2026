package model;

import model.shapes.Shape;

import java.util.ArrayList;
import java.util.List;

public class GroupeForme extends Observable implements Observer {
    private List<Shape> shapes = new ArrayList<>();

    public void add(Shape s) {
        shapes.add(s);
        s.addObserver(this);
        notifyObservers();
    }

    public void remove(Shape s) {
        shapes.remove(s);
        s.removeObserver(this);
        notifyObservers();
    }

    public void clear() {
        List<Shape> copy = new ArrayList<>(shapes);
        for (Shape s : copy) remove(s);
    }

    public List<Shape> getShapes() {
        return shapes;
    }

    @Override
    public void update() {
        notifyObservers();
    }
}
