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

    public Controller(GameModel model, MainView view) {
        this.model = model;
        this.view = view;

        this.view.getDrawingCanvas().addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();
                Point clickPoint = new Point(x, y);

                if (model.getCurrentShapeType() == ShapeType.CIRCLE) {
                    model.addBlueShape(new Circle(clickPoint, 30));
                    System.out.println("Blue Circle placed at " + x + "," + y);
                } else if (model.getCurrentShapeType() == ShapeType.RECTANGLE) {
                    Point endPoint = new Point(x + 50, y + 50);
                    model.addBlueShape(new Rectangle(clickPoint, endPoint));
                    System.out.println("Blue Rectangle placed at " + x + "," + y);
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
