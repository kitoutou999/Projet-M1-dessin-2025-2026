package model;

import java.util.ArrayList;
import java.util.List;

public class GameModel extends Observable {
    private List<Shape> redShapes;
    private List<Shape> blueShapes;
    private ShapeType currentShapeType;

    public GameModel() {
        this.redShapes = new ArrayList<>();
        this.blueShapes = new ArrayList<>();
        this.currentShapeType = ShapeType.CIRCLE;
    }

    public ShapeType getCurrentShapeType() {
        return currentShapeType;
    }

    public void setCurrentShapeType(ShapeType currentShapeType) {
        this.currentShapeType = currentShapeType;
    }

    public List<Shape> getRedShapes() {
        return redShapes;
    }

    public List<Shape> getBlueShapes() {
        return blueShapes;
    }

    public int getScore(){
        int score = 0;
        for(int i = 0;i<this.blueShapes.size();i++){
            if(this.wellPlacedForm(this.blueShapes.get(i))){
                score = score + this.blueShapes.get(i).getScoreFromShape();
            }
        }
        return score;
    }

    public void addBlueShape(Shape f) {
        blueShapes.add(f);
        notifyObservers();
    }

    public void addRedShape(Shape f) {
        redShapes.add(f);
        notifyObservers();
    }

    public boolean wellPlacedForm(Shape s1){
        for(int i = 0;i<this.redShapes.size();i++){
            if(Collision.collisionBetween(s1,this.redShapes.get(i))){
                return false;
            }
        }
        for(int i = 0;i<this.blueShapes.size();i++){
            if(Collision.collisionBetween(s1,this.blueShapes.get(i))){
                if(!this.blueShapes.get(i).equals(s1)){
                    return false;
                }
            }
        }

        return true;
    }
}
