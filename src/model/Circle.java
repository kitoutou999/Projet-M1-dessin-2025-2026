package model;

public class Circle implements Shape{

	private Point centre;
	private int rayon; //Peut être un Point
	
	public Circle(Point centre, int rayon){
		this.centre = centre;
		this.rayon = rayon;
	}
	
	public Point getCentre(){
		return this.centre;
	}
	
	public int getRayon(){
		return this.rayon;
	}

}
