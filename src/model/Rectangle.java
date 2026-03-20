package model;

public class Rectangle implements Shape{

	private Point start;
	private Point end;
	
	public Rectangle(Point start, Point end){
		if(start.getX() < end.getX() && start.getY() < end.getY()){
			this.start = start;
			this.end = end;
		}
		else if(start.getX() > end.getX() && start.getY() < end.getY()){
			this.start = new Point(end.getX(),start.getY());
			this.end = new Point(start.getX(),end.getY());
		}else if(start.getX() < end.getX() && start.getY() > end.getY()){
			this.start = new Point(start.getX(),end.getY());
			this.end = new Point(end.getX(),start.getY());
		}else{
			this.start = end;
			this.end = start;
		}
	}
	
	public Point getStart(){
		return this.start;
	}
	
	public Point getEnd(){
		return this.end;
	}

	public int getScoreFromShape(){
		int score = (this.end.getX()-this.start.getX())*(this.end.getY()-this.start.getY());
		return score;
	}

}
