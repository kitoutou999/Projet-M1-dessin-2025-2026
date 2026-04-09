package controller.commands;

import model.GameModel;
import model.shapes.Shape;
/**
 * Commande permettant d'ajouter une forme au modèle.
 * 
 * Implémente le pattern Command pour permettre l'exécution
 * et éventuellement l'annulation d'une action utilisateur.
 */
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
