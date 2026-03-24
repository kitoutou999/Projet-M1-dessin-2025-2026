package model.shapes;

import model.Observable;
import model.Point;

public abstract class Shape extends Observable {
        public abstract float getArea();
        public abstract boolean contains(Point p);
        public abstract void translate(int dx, int dy);
        public abstract Shape translated(int dx, int dy);
        public abstract void resize(Point anchor, Point drag);
        public abstract Shape resized(Point anchor, Point drag);
        public abstract boolean isInsideCanvas(int width, int height);
        public abstract boolean collidesWith(Shape other);
        public abstract boolean collidesWithCircle(Circle c);
        public abstract boolean collidesWithRectangle(Rectangle r);
}
