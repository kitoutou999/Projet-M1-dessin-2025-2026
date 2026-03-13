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

}
