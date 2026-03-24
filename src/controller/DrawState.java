package controller;

import model.*;
import view.MainView;
import java.awt.event.MouseEvent;

public class DrawState implements ControllerState {
    private GameModel model;
    private MainView view;
    private Point firstClickPoint = null;

    public DrawState(GameModel model, MainView view) {
        this.model = model;
        this.view = view;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        Point clickPoint = new Point(e.getX(), e.getY());
        if (firstClickPoint == null) {
            firstClickPoint = clickPoint;
        } else {
            Shape shape = null;
            if (model.getCurrentShapeType() == ShapeType.CIRCLE) {
                int rayon = (int) Math.sqrt(Math.pow(clickPoint.getX() - firstClickPoint.getX(), 2) + Math.pow(clickPoint.getY() - firstClickPoint.getY(), 2));
                shape = new Circle(firstClickPoint, rayon);

            } else if (model.getCurrentShapeType() == ShapeType.RECTANGLE) {
                shape = new Rectangle(firstClickPoint, clickPoint);

            }
            if(!model.isIntersecting(shape)){
                model.addBlueShape(shape, view);
            }
            firstClickPoint = null;
            view.getDrawingCanvas().setPreviewShape(null, true);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseDragged(MouseEvent e) {}

    @Override
    public void mouseMoved(MouseEvent e) {
        if (firstClickPoint == null) return;
        Point currentPoint = new Point(e.getX(), e.getY());
        if (model.getCurrentShapeType() == ShapeType.CIRCLE) {
            int rayon = (int) Math.sqrt(Math.pow(currentPoint.getX() - firstClickPoint.getX(), 2) + Math.pow(currentPoint.getY() - firstClickPoint.getY(), 2));
            Circle preview = new Circle(firstClickPoint, rayon);
            view.getDrawingCanvas().setPreviewShape(preview, !model.isIntersecting(preview));
        } else if (model.getCurrentShapeType() == ShapeType.RECTANGLE) {
            Rectangle preview = new Rectangle(firstClickPoint, currentPoint);
            view.getDrawingCanvas().setPreviewShape(preview, !model.isIntersecting(preview));
        }
    }
}
