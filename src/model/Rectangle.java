package model;

public class Rectangle extends Shape{

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

}
