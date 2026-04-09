package model.shapes;

import model.Point;

/**
 * Interface du pattern Abstract Factory pour la creation de formes.
 * Permet de creer un cercle ou un rectangle selon la factory choisie.
 */
public interface ShapeFactory {
    Shape createShape(Point p1, Point p2);
}
