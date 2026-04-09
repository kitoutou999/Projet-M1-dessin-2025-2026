package controller.states;

import controller.CommandHandler;
import controller.commands.AddShapeCommand;
import model.GameModel;
import model.Point;
import model.shapes.Shape;
import view.MainView;
import java.awt.event.MouseEvent;
/**
 * État du contrôleur permettant d'ajouter une forme dans le cas d'un jeu à deux joueurs.
 * 
 * Cette classe fait partie du pattern State et définit
 * le comportement du contrôleur lors de l'ajout de formes.
 */
public class TwoPlayerAddShapeState implements ControllerState {

    private final GameModel    model;
    private final MainView     view;
    private final CommandHandler handler;
    private Point firstClickPoint = null;

    public TwoPlayerAddShapeState(GameModel model, MainView view, CommandHandler handler) {
        this.model   = model;
        this.view    = view;
        this.handler = handler;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (model.isGameOver()) return;

        Point clickPoint = new Point(e.getX(), e.getY());
        if (firstClickPoint == null) {
            firstClickPoint = clickPoint;
        } else {
            Shape shape = model.getShapeFactory().createShape(firstClickPoint, clickPoint);
            if (!model.isIntersecting(shape)) {
                handler.executeCommand(new AddShapeCommand(shape, model));
            }
            firstClickPoint = null;
            view.getDrawingCanvas().setPreviewShape(null, true);
        }
    }

    @Override public void mouseReleased(MouseEvent e) {}
    @Override public void mouseDragged(MouseEvent e)  {}

    @Override
    public void mouseMoved(MouseEvent e) {
        if (firstClickPoint == null) return;
        Point current = new Point(e.getX(), e.getY());
        Shape preview = model.getShapeFactory().createShape(firstClickPoint, current);
        view.getDrawingCanvas().setPreviewShape(preview, !model.isIntersecting(preview));
    }
}
