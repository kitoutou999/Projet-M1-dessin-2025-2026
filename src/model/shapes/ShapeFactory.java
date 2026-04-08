package model.shapes;

import model.Point;

public interface ShapeFactory {
    Shape createShape(Point p1, Point p2);
}
