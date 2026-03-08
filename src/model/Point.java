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
	
	public void translation(int dx, int dy){
		this.x = this.x + dx;
		this.y = this.y + dy;
	}
	
	public void homothetie(Point centre, int k){
		this.x = centre.getX() + k * (this.x - centre.getX());
		this.y = centre.getY() + k * (this.y - centre.getY());
	}
	
	public void homothetie(int centreX, int centreY, int k){
		this.x = centreX + k * (this.x - centreX);
		this.y = centreY + k * (this.y - centreY);
	}

	public String toString(){
		return "(" + this.x + "," + this.y + ")";
	}
}
