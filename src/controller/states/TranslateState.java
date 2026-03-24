package controller.states;

import controller.CommandHandler;
import controller.commands.TranslateCommand;
import model.GameModel;
import model.Point;
import model.shapes.Shape;
import view.MainView;
import java.awt.event.MouseEvent;

public class TranslateState implements ControllerState {
    private GameModel model;
    private MainView view;
    private Shape selectedShape = null;
    private Point clickPoint = null;
    private boolean lastIsIntersecting = false;

    private CommandHandler handler;

    public TranslateState(GameModel model, MainView view, CommandHandler handler) {
        this.model = model;
        this.view = view;
        this.handler = handler;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        clickPoint = new Point(e.getX(), e.getY());
        selectedShape = model.getShapeAt(clickPoint);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (selectedShape == null) return;
        Point releasePoint = new Point(e.getX(), e.getY());
        int dx = releasePoint.getX() - clickPoint.getX();
        int dy = releasePoint.getY() - clickPoint.getY();
        if (lastIsIntersecting && (dx != 0 || dy != 0)) {
            handler.executeCommand(new TranslateCommand(selectedShape, dx, dy, model));
        }
        selectedShape = null;
        view.getDrawingCanvas().setPreviewShape(null, true);


    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (selectedShape == null) return;
        Point currentPoint = new Point(e.getX(), e.getY());
        int dx = currentPoint.getX() - clickPoint.getX();
        int dy = currentPoint.getY() - clickPoint.getY();
        Shape preview = selectedShape.translated(dx, dy);
        lastIsIntersecting = !model.isIntersecting(preview, selectedShape);
        view.getDrawingCanvas().setPreviewShape(preview, lastIsIntersecting);
    }

    @Override
    public void mouseMoved(MouseEvent e) {}
}
