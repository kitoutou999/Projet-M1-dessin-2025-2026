package model;

public class Rectangle implements Shape{

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

}
