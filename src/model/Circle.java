package model;

public class Circle implements Shape{

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

	public int getScoreFromShape(){
		double score = Math.PI * (this.radius * this.radius);
		return (int)score;
	}

}
