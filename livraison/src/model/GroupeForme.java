package model;

import model.shapes.Circle;
import model.shapes.Rectangle;
import model.shapes.Shape;

import java.util.ArrayList;
import java.util.List;

/**
 * Composite de formes : agrège plusieurs Shape et se comporte lui-même comme une Shape.
 * Implémente le pattern Composite - toute opération est déléguée à chaque enfant.
 */
public class GroupeForme extends Shape implements Observer {

    private List<Shape> shapes = new ArrayList<>();

    public void add(Shape s) {
        shapes.add(s);
        s.addObserver(this);
        notifyObservers();
    }

    public void setShapes(List<Shape> shapes) {
        clear();
        for (Shape s : shapes) {
            add(s);
        }
    }

    public void remove(Shape s) {
        shapes.remove(s);
        s.removeObserver(this);
        notifyObservers();
    }

    public void clear() {
        for (Shape s : new ArrayList<>(shapes)) {
            remove(s);
        }
    }

    public List<Shape> getShapes() {
        return shapes;
    }

    @Override
    public void update() {
        notifyObservers();
    }

    // --- Implémentation de Shape ---

    @Override
    public float getArea() {
        float total = 0;
        for (Shape s : shapes) {
            total += s.getArea();
        }
        return total;
    }

    @Override
    public boolean contains(Point p) {
        for (Shape s : shapes) {
            if (s.contains(p)) return true;
        }
        return false;
    }

    @Override
    public void translate(int dx, int dy) {
        for (Shape s : shapes) {
            s.translate(dx, dy);
        }
        notifyObservers();
    }

    @Override
    public Shape translated(int dx, int dy) {
        GroupeForme g = new GroupeForme();
        for (Shape s : shapes) {
            g.add(s.translated(dx, dy));
        }
        return g;
    }

    @Override
    public void resize(Point anchor, Point drag) {
        for (Shape s : shapes) {
            s.resize(anchor, drag);
        }
        notifyObservers();
    }

    @Override
    public Shape resized(Point anchor, Point drag) {
        GroupeForme g = new GroupeForme();
        for (Shape s : shapes) {
            g.add(s.resized(anchor, drag));
        }
        return g;
    }

    @Override
    public boolean isInsideCanvas(int width, int height) {
        for (Shape s : shapes) {
            if (!s.isInsideCanvas(width, height)) return false;
        }
        return true;
    }




    @Override
    public boolean collidesWith(Shape other) {
        for (Shape s : shapes) {
            if (s.collidesWith(other)) return true;
        }
        return false;
    }

    @Override
    public boolean collidesWithCircle(Circle c) {
        for (Shape s : shapes) {
            if (s.collidesWithCircle(c)) return true;
        }
        return false;
    }

    @Override
    public boolean collidesWithRectangle(Rectangle r) {
        for (Shape s : shapes) {
            if (s.collidesWithRectangle(r)) return true;
        }
        return false;
    }
}
