package controller;

import model.GameModel;
import model.Point;
import model.Rectangle;

public class ResizeRectangleCommand implements OperationCommand {
    private Rectangle rect;
    private Point oldStart;
    private Point oldEnd;
    private Point newStart;
    private Point newEnd;
    private GameModel model;

    public ResizeRectangleCommand(Rectangle rect, Point oldStart, Point oldEnd, Point newStart, Point newEnd, GameModel model) {
        this.rect = rect;
        this.oldStart = oldStart;
        this.oldEnd = oldEnd;
        this.newStart = newStart;
        this.newEnd = newEnd;
        this.model = model;
    }

    @Override
    public void operate() {
        model.resizeRectangle(rect, newStart, newEnd);
    }

    @Override
    public void compensate() {
        model.resizeRectangle(rect, oldStart, oldEnd);
    }
}
