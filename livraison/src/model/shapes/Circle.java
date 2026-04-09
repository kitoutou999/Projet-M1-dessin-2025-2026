package model.shapes;

import model.Collision;
import model.Point;

/**
 * Forme representant un cercle, defini par un centre et un rayon.
 */
public class Circle extends Shape {

	private Point center;
	private int radius;
	
	public Circle(Point center, int radius){
		this.center = center;
		this.radius = radius;
	}
	
	public Point getCenter(){
		return this.center;
	}
	
	public int getRadius(){
		return this.radius;
	}

	public void setCenter(Point center) {
		this.center = center;
		notifyObservers();
	}

	public void setRadius(int radius) {
		this.radius = radius;
		notifyObservers();
	}

	public float getArea(){
		return (float) (Math.PI * radius * radius);
	}

	@Override
	public boolean contains(Point p) {
		double dx = p.getX() - center.getX();
		double dy = p.getY() - center.getY();
		return Math.sqrt(dx * dx + dy * dy) <= radius;
	}

	@Override
	public void translate(int dx, int dy) {
		setCenter(new Point(center.getX() + dx, center.getY() + dy));
	}

	@Override
	public Shape translated(int dx, int dy) {
		return new Circle(new Point(center.getX() + dx, center.getY() + dy), radius);
	}

	@Override
	public void resize(Point anchor, Point drag) {
		int newRadius = (int) Math.sqrt(Math.pow(drag.getX() - center.getX(), 2) + Math.pow(drag.getY() - center.getY(), 2));
		setRadius(newRadius);
	}

	@Override
	public Shape resized(Point anchor, Point drag) {
		int newRadius = (int) Math.sqrt(Math.pow(drag.getX() - center.getX(), 2) + Math.pow(drag.getY() - center.getY(), 2));
		return new Circle(center, newRadius);
	}

	@Override
	public boolean isInsideCanvas(int width, int height) {
		return center.getX() - radius >= 0 && center.getX() + radius <= width
			&& center.getY() - radius >= 0 && center.getY() + radius <= height;
	}

	@Override
	public boolean collidesWith(Shape other) {
		return other.collidesWithCircle(this);
	}

	@Override
	public boolean collidesWithCircle(Circle c) {
		return Collision.collisionCircleCircle(this, c);
	}

	@Override
	public boolean collidesWithRectangle(Rectangle r) {
		return Collision.collisionRectangleCircle(r, this);
	}

}
