package controller.commands;

import model.GameModel;
import model.shapes.Shape;
/**
 * Commande permettant d'enlever une forme du modèle.
 * 
 * Implémente le pattern Command pour permettre l'exécution
 * et éventuellement l'annulation d'une action utilisateur.
 */
public class RemoveCommand implements OperationCommand {
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
