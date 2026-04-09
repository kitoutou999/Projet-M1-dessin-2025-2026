package controller.states;

import controller.CommandHandler;
import controller.commands.RemoveCommand;
import model.*;
import model.shapes.Shape;
import view.MainView;
import java.awt.event.MouseEvent;
/**
 * État du contrôleur permettant de supprimer une forme.
 * 
 * Cette classe fait partie du pattern State et définit
 * le comportement du contrôleur lors de l'ajout de formes.
 */
public class RemoveState implements ControllerState {
    private GameModel model;
    private MainView view;
    private CommandHandler handler;

    public RemoveState(GameModel model, MainView view, CommandHandler handler) {
        this.model = model;
        this.view = view;
        this.handler = handler;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        Point clickPoint = new Point(e.getX(), e.getY());
        Shape shapeToRemove = model.getShapeAt(clickPoint);
        if (shapeToRemove != null) {
            handler.executeCommand(new RemoveCommand(shapeToRemove,model));

        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseDragged(MouseEvent e) {}

    @Override
    public void mouseMoved(MouseEvent e) {}
}
