package controller;

import model.*;
import view.MainView;
import java.awt.event.MouseEvent;

public class RemoveState implements ControllerState {
    private GameModel model;
    private MainView view;

    public RemoveState(GameModel model, MainView view) {
        this.model = model;
        this.view = view;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        Point clickPoint = new Point(e.getX(), e.getY());
        Shape shapeToRemove = model.getShapeAt(clickPoint);
        if (shapeToRemove != null) {
            model.removeBlueShape(shapeToRemove);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseDragged(MouseEvent e) {}

    @Override
    public void mouseMoved(MouseEvent e) {}
}
