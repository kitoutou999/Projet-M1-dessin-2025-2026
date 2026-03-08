package model;

public class Point{

	private int x;
	private int y;
	
	public Point(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public int getX(){
		return this.x;
	}
	
	public int getY(){
		return this.y;
	}
	
	public void translate(int dx, int dy){
		this.x = this.x + dx;
		this.y = this.y + dy;
	}
	
	public void scale(Point center, int k){
		this.x = center.getX() + k * (this.x - center.getX());
		this.y = center.getY() + k * (this.y - center.getY());
	}
	
	public void scale(int centerX, int centerY, int k){
		this.x = centerX + k * (this.x - centerX);
		this.y = centerY + k * (this.y - centerY);
	}

	public String toString(){
		return "(" + this.x + "," + this.y + ")";
	}
}
