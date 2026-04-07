package controller.commands;

import model.GameModel;
import model.Point;
import model.shapes.Shape;
/**
 * Commande permettant de modifier les dimensions d'une forme.
 * 
 * Implémente le pattern Command pour permettre l'exécution
 * et éventuellement l'annulation d'une action utilisateur.
 */
public class ScaleCommand implements OperationCommand {
    private Shape shape;
    private Point anchor;
    private Point oldDrag;
    private Point newDrag;
    private GameModel model;

    public ScaleCommand(Shape shape, Point anchor, Point oldDrag, Point newDrag, GameModel model) {
        this.shape = shape;
        this.anchor = anchor;
        this.oldDrag = oldDrag;
        this.newDrag = newDrag;
        this.model = model;
    }

    @Override
    public void operate() {
        model.resize(shape, anchor, newDrag);
    }

    @Override
    public void compensate() {
        model.resize(shape, anchor, oldDrag);
    }
}
