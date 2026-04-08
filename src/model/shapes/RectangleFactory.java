package model.shapes;

import model.Point;

/**
 * Cree un rectangle a partir de deux coins opposes.
 */
public class RectangleFactory implements ShapeFactory {
    @Override
    public Shape createShape(Point p1, Point p2) {
        return new Rectangle(p1, p2);
    }
}
