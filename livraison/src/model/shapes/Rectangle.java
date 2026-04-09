package model.shapes;

import model.Collision;
import model.Point;

/**
 * Forme representant un rectangle aligne sur les axes, defini par deux coins opposes.
 */
public class Rectangle extends Shape {

	private Point start;
	private Point end;
	
	public Rectangle(Point start, Point end){
		this.start = start;
		this.end = end;
	}
	
	public Point getStart(){
		return this.start;
	}
	
	public Point getEnd(){
		return this.end;
	}

	public void setStart(Point start) {
		this.start = start;
		notifyObservers();
	}

	public void setEnd(Point end) {
		this.end = end;
		notifyObservers();
	}

	public float getArea(){
		return Math.abs((end.getX() - start.getX()) * (end.getY() - start.getY()));
	}

	public int getMinX() { return Math.min(start.getX(), end.getX()); }
	public int getMaxX() { return Math.max(start.getX(), end.getX()); }
	public int getMinY() { return Math.min(start.getY(), end.getY()); }
	public int getMaxY() { return Math.max(start.getY(), end.getY()); }

	@Override
	public boolean contains(Point p) {
		return p.getX() >= getMinX() && p.getX() <= getMaxX()
			&& p.getY() >= getMinY() && p.getY() <= getMaxY();
	}

	@Override
	public void translate(int dx, int dy) {
		setStart(new Point(start.getX() + dx, start.getY() + dy));
		setEnd(new Point(end.getX() + dx, end.getY() + dy));
	}

	@Override
	public Shape translated(int dx, int dy) {
		return new Rectangle(new Point(start.getX() + dx, start.getY() + dy),
							 new Point(end.getX() + dx, end.getY() + dy));
	}

	@Override
	public void resize(Point anchor, Point drag) {
		setStart(new Point(Math.min(anchor.getX(), drag.getX()), Math.min(anchor.getY(), drag.getY())));
		setEnd(new Point(Math.max(anchor.getX(), drag.getX()), Math.max(anchor.getY(), drag.getY())));
	}

	@Override
	public Shape resized(Point anchor, Point drag) {
		return new Rectangle(anchor, drag);
	}

	@Override
	public boolean isInsideCanvas(int width, int height) {
		return getMinX() >= 0 && getMaxX() <= width
			&& getMinY() >= 0 && getMaxY() <= height;
	}

	@Override
	public boolean collidesWith(Shape other) {
		return other.collidesWithRectangle(this);
	}

	@Override
	public boolean collidesWithCircle(Circle c) {
		return Collision.collisionRectangleCircle(this, c);
	}

	@Override
	public boolean collidesWithRectangle(Rectangle r) {
		return Collision.collisionRectangleRectangle(this, r);
	}

}
