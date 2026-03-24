package controller;

import model.GameModel;
import model.Shape;

public class RemoveCommand implements OperationCommand{
    private Shape shapeToRemove;
    private GameModel model;

    public RemoveCommand(Shape shapeToRemove, GameModel model) {
        this.shapeToRemove = shapeToRemove;
        this.model = model;
    }

    @Override
    public void operate() {
        model.removeBlueShape(shapeToRemove);
    }

    @Override
    public void compensate() {
        model.addBlueShape(shapeToRemove);
    }
}
