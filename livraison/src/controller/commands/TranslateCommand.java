package controller.commands;

import model.GameModel;
import model.shapes.Shape;
/**
 * Commande permettant de déplacer une forme.
 * 
 * Implémente le pattern Command pour permettre l'exécution
 * et éventuellement l'annulation d'une action utilisateur.
 */
public class TranslateCommand implements OperationCommand {
    private Shape shape;
    private int dx;
    private int dy;
    private GameModel model;

    public TranslateCommand(Shape shape, int dx, int dy, GameModel model) {
        this.shape = shape;
        this.dx = dx;
        this.dy = dy;
        this.model = model;
    }

    @Override
    public void operate() {
        model.translateShape(shape, dx, dy);
    }

    @Override
    public void compensate() {
        model.translateShape(shape, -dx, -dy);
    }
}
