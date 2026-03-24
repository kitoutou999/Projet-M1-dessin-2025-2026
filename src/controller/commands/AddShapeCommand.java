package controller.commands;

import model.GameModel;
import model.shapes.Shape;

public class AddShapeCommand implements OperationCommand {
    private Shape shape;
    private GameModel model;

    public AddShapeCommand(Shape shape, GameModel model) {
        this.shape = shape;
        this.model = model;
    }

    @Override
    public void operate() {
        model.addBlueShape(shape);
    }

    @Override
    public void compensate() {
        model.removeBlueShape(shape);
    }
}
