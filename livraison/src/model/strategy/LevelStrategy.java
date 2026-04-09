package model.strategy;

import model.shapes.Shape;

import java.util.List;

/**
 * Interface du pattern Strategy pour la generation de niveaux.
 * Permet de choisir entre niveaux predefinis et niveaux aleatoires.
 */
public interface LevelStrategy {
    List<Shape> generateLevel();
}
