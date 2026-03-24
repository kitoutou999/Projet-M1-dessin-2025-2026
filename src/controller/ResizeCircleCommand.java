package controller;

import model.Circle;
import model.GameModel;

public class ResizeCircleCommand implements OperationCommand {
    private Circle circle;
    private int oldRadius;
    private int newRadius;
    private GameModel model;

    public ResizeCircleCommand(Circle circle, int oldRadius, int newRadius, GameModel model) {
        this.circle = circle;
        this.oldRadius = oldRadius;
        this.newRadius = newRadius;
        this.model = model;
    }

    @Override
    public void operate() {
        model.resizeCircle(circle, newRadius);
    }

    @Override
    public void compensate() {
        model.resizeCircle(circle, oldRadius);
    }
}
