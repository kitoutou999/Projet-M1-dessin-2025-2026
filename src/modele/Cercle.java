package modele;

public class Cercle implements Forme{

	private Point centre;
	private int rayon; //Peut être un Point
	
	public Cercle(Point centre, int rayon){
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
