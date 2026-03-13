package model;

public class Circle extends Shape{

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

}
