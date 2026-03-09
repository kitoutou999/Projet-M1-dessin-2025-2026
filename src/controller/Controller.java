package controller;

import model.GameModel;
import model.ShapeType;
import model.Point;
import model.Circle;
import model.Rectangle;
import view.MainView;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Controller {
    private GameModel model;
    private MainView view;
    private Point firstClickPoint = null;

    public Controller(GameModel model, MainView view) {
        this.model = model;
        this.view = view;

        this.view.getDrawingCanvas().addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                
                if (firstClickPoint == null){
                    int x = e.getX();
                    int y = e.getY();
                    firstClickPoint = new Point(x, y);
                    System.out.println("FirstClick NULL");
                }

                else {
                    int x = e.getX();
                    int y = e.getY();
                    Point secondClickPoint = new Point(x, y);

                    if (model.getCurrentShapeType() == ShapeType.CIRCLE) {
                        int rayon = (int) Math.sqrt(Math.pow(secondClickPoint.getX() - firstClickPoint.getX(), 2) + Math.pow(secondClickPoint.getY() - firstClickPoint.getY(), 2));
                        model.addBlueShape(new Circle(firstClickPoint, rayon));
                        System.out.println("Blue Circle placed at " + x + "," + y);
                    } else if (model.getCurrentShapeType() == ShapeType.RECTANGLE) {
                        model.addBlueShape(new Rectangle(firstClickPoint, secondClickPoint));
                        System.out.println("Blue Rectangle placed at " + x + "," + y);
                    }

                    firstClickPoint = null;
                    System.out.println("FirstClick PAS NULL");
                }

            }
        });

        this.view.getToolbar().getBtnCircle().addActionListener(e -> {
            model.setCurrentShapeType(ShapeType.CIRCLE);
            System.out.println("Mode : Création de Cercle");
        });

        this.view.getToolbar().getBtnRectangle().addActionListener(e -> {
            model.setCurrentShapeType(ShapeType.RECTANGLE);
            System.out.println("Mode : Création de Rectangle");
        });

        this.view.getToolbar().getBtnUndo().addActionListener(e -> {
            System.out.println("Undo");
        });

        this.view.getToolbar().getBtnRedo().addActionListener(e -> {
            System.out.println("Redo");
        });
    }
}
