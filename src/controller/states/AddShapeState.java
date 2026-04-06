package controller.states;

import controller.CommandHandler;
import controller.commands.AddShapeCommand;
import model.GameModel;
import model.Point;
import model.shapes.Shape;
import view.MainView;
import java.awt.event.MouseEvent;

public class AddShapeState implements ControllerState {
    private GameModel model;
    private MainView view;
    private Point firstClickPoint = null;

    private CommandHandler handler;

    public AddShapeState(GameModel model, MainView view, CommandHandler handler) {
        this.model = model;
        this.view = view;
        this.handler = handler;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (model.isGameOver()) return;

        Point clickPoint = new Point(e.getX(), e.getY());
        if (firstClickPoint == null) {
            firstClickPoint = clickPoint;
        } else {
            Shape shape = model.getCurrentShapeType().createFrom(firstClickPoint, clickPoint);
            if (!model.isIntersecting(shape)) {
                handler.executeCommand(new AddShapeCommand(shape, model));
                if (model.isGameOver()) {
                    model.recordScore();
                    if (model.isLastRound()) {
                        view.showFinalScoreDialog();
                    } else {
                        view.showNextRoundDialog();
                        model.loadNextLevel();
                    }
                }
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
        Shape preview = model.getCurrentShapeType().createFrom(firstClickPoint, currentPoint);
        view.getDrawingCanvas().setPreviewShape(preview, !model.isIntersecting(preview));
    }

}
