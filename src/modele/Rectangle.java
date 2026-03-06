package modele;

public class Rectangle implements Forme{

	private Point debut;
	private Point fin;
	
	public Rectangle(Point debut, Point fin){
		this.debut = debut;
		this.fin = fin;
	}
	
	public Point getDebut(){
		return this.debut;
	}
	
	public Point getFin(){
		return this.fin;
	}

}
