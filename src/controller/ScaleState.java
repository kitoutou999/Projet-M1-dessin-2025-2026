package controller;

import model.*;
import view.MainView;
import java.awt.event.MouseEvent;

public class ScaleState implements ControllerState {
    private GameModel model;
    private MainView view;
    private Shape selectedShape = null;
    private Point draggedGizmoCorner = null;
    private Point fixedGizmoCorner = null;

    public ScaleState(GameModel model, MainView view) {
        this.model = model;
        this.view = view;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        int x = e.getX(), y = e.getY();
        Point clickPoint = new Point(x, y);

        if (selectedShape instanceof Rectangle) {
            int idx = view.getDrawingCanvas().getGizmoIndexAt(x, y);
            if (idx != -1) {
                draggedGizmoCorner = view.getDrawingCanvas().getGizmoCorner(idx);
                fixedGizmoCorner = view.getDrawingCanvas().getGizmoCorner(3 - idx);
                return;
            }
            selectedShape = null;
            view.getDrawingCanvas().clearGizmo();
        } else if (selectedShape instanceof Circle) {
            if (view.getDrawingCanvas().isCircleGizmoAt(x, y)) {
                draggedGizmoCorner = ((Circle) selectedShape).getCenter();
                return;
            }
            selectedShape = null;
            view.getDrawingCanvas().clearGizmo();
        }

        Shape shapeToScale = model.getShapeAt(clickPoint);
        if (shapeToScale instanceof Rectangle || shapeToScale instanceof Circle) {
            selectedShape = shapeToScale;
            view.getDrawingCanvas().createGizmo(shapeToScale);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (draggedGizmoCorner == null) return;
        if (selectedShape instanceof Rectangle) {
            model.resizeRectangle((Rectangle) selectedShape, fixedGizmoCorner, new Point(e.getX(), e.getY()));
            fixedGizmoCorner = null;
        } else if (selectedShape instanceof Circle) {
            Circle c = (Circle) selectedShape;
            model.resizeCircle(c, c.getCenter(), new Point(e.getX(), e.getY()));
        }
        view.getDrawingCanvas().createGizmo(selectedShape);
        view.getDrawingCanvas().setPreviewShape(null, true);
        draggedGizmoCorner = null;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (draggedGizmoCorner == null) return;
        Point currentPoint = new Point(e.getX(), e.getY());
        if (selectedShape instanceof Rectangle) {
            Rectangle preview = new Rectangle(fixedGizmoCorner, currentPoint);
            view.getDrawingCanvas().setPreviewShape(preview, !model.isIntersecting(preview, selectedShape));
            view.getDrawingCanvas().createGizmo(preview);
        } else if (selectedShape instanceof Circle) {
            Circle c = (Circle) selectedShape;
            int newRadius = (int) Math.sqrt(Math.pow(currentPoint.getX() - c.getCenter().getX(), 2) + Math.pow(currentPoint.getY() - c.getCenter().getY(), 2));
            Circle preview = new Circle(c.getCenter(), newRadius);
            view.getDrawingCanvas().setPreviewShape(preview, !model.isIntersecting(preview, selectedShape));
            view.getDrawingCanvas().createGizmo(preview);
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {}
}
