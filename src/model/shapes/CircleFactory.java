package model.shapes;

import model.Point;

/**
 * Cree un cercle a partir de deux points : le centre et un point sur le rayon.
 */
public class CircleFactory implements ShapeFactory {
    @Override
    public Shape createShape(Point p1, Point p2) {
        int radius = (int) Math.sqrt(Math.pow(p2.getX() - p1.getX(), 2) + Math.pow(p2.getY() - p1.getY(), 2));
        return new Circle(p1, radius);
    }
}
