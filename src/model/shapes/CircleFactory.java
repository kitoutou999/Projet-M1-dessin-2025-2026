package model.shapes;

import model.Point;

public class CircleFactory implements ShapeFactory {
    @Override
    public Shape createShape(Point p1, Point p2) {
        int radius = (int) Math.sqrt(Math.pow(p2.getX() - p1.getX(), 2) + Math.pow(p2.getY() - p1.getY(), 2));
        return new Circle(p1, radius);
    }
}
