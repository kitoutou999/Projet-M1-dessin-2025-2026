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
    private Boolean lastIsIntersecting = false;

    private CommandHandler handler;

    public ScaleState(GameModel model, MainView view, CommandHandler handler) {
        this.model = model;
        this.view = view;
        this.handler = handler;
    }

    private int getGizmoIndexAt(int px, int py) {
        if (!(selectedShape instanceof Rectangle)) return -1;
        int[][] corners = getGizmoCorners();
        for (int i = 0; i < corners.length; i++) {
            if (Math.abs(px - corners[i][0]) <= 5 && Math.abs(py - corners[i][1]) <= 5) return i;
        }
        return -1;
    }

    private Point getGizmoCorner(int index) {
        int[][] corners = getGizmoCorners();
        return new Point(corners[index][0], corners[index][1]);
    }

    private int[][] getGizmoCorners() {
        Rectangle r = (Rectangle) selectedShape;
        int x = Math.min(r.getStart().getX(), r.getEnd().getX());
        int y = Math.min(r.getStart().getY(), r.getEnd().getY());
        int w = Math.abs(r.getStart().getX() - r.getEnd().getX());
        int h = Math.abs(r.getStart().getY() - r.getEnd().getY());
        return new int[][]{{x, y}, {x + w, y}, {x, y + h}, {x + w, y + h}};
    }

    private boolean isCircleGizmoAt(int px, int py) {
        if (!(selectedShape instanceof Circle)) return false;
        Circle c = (Circle) selectedShape;
        int cx = c.getCenter().getX(), cy = c.getCenter().getY(), r = c.getRadius();
        int[][] handles = {{cx + r, cy}, {cx, cy - r}, {cx - r, cy}, {cx, cy + r}};
        for (int[] h : handles) {
            if (Math.abs(px - h[0]) <= 5 && Math.abs(py - h[1]) <= 5) return true;
        }
        return false;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        int x = e.getX(), y = e.getY();
        Point clickPoint = new Point(x, y);

        if (selectedShape instanceof Rectangle) {
            int idx = getGizmoIndexAt(x, y);
            if (idx != -1) {
                draggedGizmoCorner = getGizmoCorner(idx);
                fixedGizmoCorner = getGizmoCorner(3 - idx);
                return;
            }
            selectedShape = null;
            view.getDrawingCanvas().clearGizmo();
        } else if (selectedShape instanceof Circle) {
            if (isCircleGizmoAt(x, y)) {
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
            if (lastIsIntersecting) {
                Rectangle r = (Rectangle) selectedShape;
                Point oldStart = r.getStart();
                Point oldEnd = r.getEnd();
                Point releasePoint = new Point(e.getX(), e.getY());
                Point newStart = new Point(Math.min(fixedGizmoCorner.getX(), releasePoint.getX()), Math.min(fixedGizmoCorner.getY(), releasePoint.getY()));
                Point newEnd = new Point(Math.max(fixedGizmoCorner.getX(), releasePoint.getX()), Math.max(fixedGizmoCorner.getY(), releasePoint.getY()));
                handler.executeCommand(new ResizeRectangleCommand(r, oldStart, oldEnd, newStart, newEnd, model));
            }
            fixedGizmoCorner = null;
        } else if (selectedShape instanceof Circle) {
            Circle c = (Circle) selectedShape;
            if (lastIsIntersecting) {
                int oldRadius = c.getRadius();
                int newRadius = (int) Math.sqrt(Math.pow(e.getX() - c.getCenter().getX(), 2) + Math.pow(e.getY() - c.getCenter().getY(), 2));
                handler.executeCommand(new ResizeCircleCommand(c, oldRadius, newRadius, model));
            }
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
            lastIsIntersecting = !model.isIntersecting(preview, selectedShape);
            view.getDrawingCanvas().setPreviewShape(preview, lastIsIntersecting);
            view.getDrawingCanvas().createGizmo(preview);
        } else if (selectedShape instanceof Circle) {
            Circle c = (Circle) selectedShape;
            int newRadius = (int) Math.sqrt(Math.pow(currentPoint.getX() - c.getCenter().getX(), 2) + Math.pow(currentPoint.getY() - c.getCenter().getY(), 2));
            Circle preview = new Circle(c.getCenter(), newRadius);
            lastIsIntersecting = !model.isIntersecting(preview, selectedShape);
            view.getDrawingCanvas().setPreviewShape(preview, lastIsIntersecting);
            view.getDrawingCanvas().createGizmo(preview);
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {}
}
