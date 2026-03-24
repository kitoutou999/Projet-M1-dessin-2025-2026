package model.shapes;

import model.Point;

public enum ShapeType {
    CIRCLE {
        @Override
        public Shape createFrom(Point p1, Point p2) {
            int radius = (int) Math.sqrt(Math.pow(p2.getX() - p1.getX(), 2) + Math.pow(p2.getY() - p1.getY(), 2));
            return new Circle(p1, radius);
        }
    },
    RECTANGLE {
        @Override
        public Shape createFrom(Point p1, Point p2) {
            return new Rectangle(p1, p2);
        }
    };

    public abstract Shape createFrom(Point p1, Point p2);
}
