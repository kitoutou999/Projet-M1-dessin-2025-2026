package model;

import model.shapes.Circle;
import model.shapes.Rectangle;
import org.junit.Test;
import static org.junit.Assert.*;

public class CollisionTest {

    @Test
    public void cerclesChevauchants() {
        Circle c1 = new Circle(new Point(0, 0), 50);
        Circle c2 = new Circle(new Point(60, 0), 50);
        assertTrue(Collision.collisionCircleCircle(c1, c2));
    }

    @Test
    public void cerclesSepares() {
        Circle c1 = new Circle(new Point(0, 0), 30);
        Circle c2 = new Circle(new Point(200, 0), 30);
        assertFalse(Collision.collisionCircleCircle(c1, c2));
    }

    @Test
    public void rectanglesChevauchants() {
        Rectangle r1 = new Rectangle(new Point(0, 0), new Point(100, 100));
        Rectangle r2 = new Rectangle(new Point(50, 50), new Point(150, 150));
        assertTrue(Collision.collisionRectangleRectangle(r1, r2));
    }

    @Test
    public void rectanglesSepares() {
        Rectangle r1 = new Rectangle(new Point(0, 0), new Point(100, 100));
        Rectangle r2 = new Rectangle(new Point(200, 0), new Point(300, 100));
        assertFalse(Collision.collisionRectangleRectangle(r1, r2));
    }

    @Test
    public void rectangleCircleChevauchants() {
        Rectangle r = new Rectangle(new Point(0, 0), new Point(100, 100));
        Circle c = new Circle(new Point(80, 50), 40);
        assertTrue(Collision.collisionRectangleCircle(r, c));
    }

    @Test
    public void rectangleCircleSepares() {
        Rectangle r = new Rectangle(new Point(0, 0), new Point(100, 100));
        Circle c = new Circle(new Point(300, 300), 50);
        assertFalse(Collision.collisionRectangleCircle(r, c));
    }
}
