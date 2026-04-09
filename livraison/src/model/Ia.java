package model;
import model.shapes.*;
import java.util.Random;
import java.util.*;

/**
 * Joueur automatique capable de placer des formes de maniere intelligente.
 * Genere plusieurs formes candidates et retient celle qui couvre le plus d'espace sans intersection.
 */
public class Ia{
    private GameModel model;
    public Ia(GameModel model){
        this.model = model;
    }

    //Genere x formes aléatoires et renvoie celle qui couvre le plus d'espace.
    public Shape bestFromX(int x){
        List<Shape> liste = new ArrayList<>();
        Random randomNumbers = new Random();
        Shape shape = null;
        while(liste.size()<x){
            int X1 = randomNumbers.nextInt(this.model.getCanvasWidth())+1;
            int Y1 = randomNumbers.nextInt(this.model.getCanvasHeight())+1;
            int shapeType = randomNumbers.nextInt(1)+1;
            if(shapeType == 1){
                int X2 = randomNumbers.nextInt(this.model.getCanvasWidth())+1;
                int Y2 = randomNumbers.nextInt(this.model.getCanvasHeight())+1;
                shape = new Rectangle(new Point(X1,Y1),new Point(X2,Y2));
            }
            if(shapeType == 2){
                //changer le radius en fonction de la distance avec le mur le plus proche.
                int radius = randomNumbers.nextInt(100)+1;
                shape = new Circle(new Point(X1,Y1),radius);
            }
            if(shape.isInsideCanvas(this.model.getCanvasWidth(),this.model.getCanvasHeight()) && !model.isIntersecting(shape)){
                liste.add(shape);
            }
        }

        Shape bestShape = liste.get(0);
        for(int i = 0;i<liste.size();i++){
            if(bestShape.getArea()<liste.get(i).getArea()){
                bestShape = liste.get(i);
            }
        }
        return bestShape;
    }

    public List<Shape> getXShapeFromYChoice(int x,int y){
        List<Shape> liste = new ArrayList<>();
        for(int i = 0;i<x;i++){
            liste.add(this.bestFromX(y));
        }
        return liste;
    }




}