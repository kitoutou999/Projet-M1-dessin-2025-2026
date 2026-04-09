package model;

import model.shapes.Circle;
import model.shapes.Rectangle;
import model.shapes.Shape;
import java.util.List;

/**
 * Calcule le score de reproduction dans le mode deux joueurs.
 * Compare les formes dessinees aux formes reproduites en tenant compte de la position et de la taille.
 */
public class ScoreCalculator {

    /**
     * Calcule le score en fonction d'à quel point on a réussi à reproduire des formes.
     *
     * @return le score calculé qui est un pourcentage de reproduction.
     */
    public static float calculate(List<Shape> drawn, List<Shape> reproduced) {
        if (drawn.isEmpty()) return 0;
        float total = 0;
        int count = Math.min(drawn.size(), reproduced.size());
        for (int i = 0; i < count; i++) {
            total += scoreShapePair(drawn.get(i), reproduced.get(i));
        }
        return total / drawn.size();
    }

    private static float scoreShapePair(Shape drawn, Shape reproduced) {
        double distance   = centerDistance(drawn, reproduced);
        float distPenalty = (float) Math.min(distance * 0.3, 80.0);
        float sizePenalty = Math.min(Math.abs(drawn.getArea() - reproduced.getArea()) / 500f, 20f);
        return Math.max(0f, 100f - distPenalty - sizePenalty);
    }

    private static double centerDistance(Shape a, Shape b) {
        Point ca = getCenter(a);
        Point cb = getCenter(b);
        return Math.sqrt(Math.pow(ca.getX() - cb.getX(), 2) + Math.pow(ca.getY() - cb.getY(), 2));
    }

    private static Point getCenter(Shape s) {
        if (s instanceof Circle) {
            return ((Circle) s).getCenter();
        }
        if (s instanceof Rectangle) {
            Rectangle r = (Rectangle) s;
            return new Point((r.getMinX() + r.getMaxX()) / 2, (r.getMinY() + r.getMaxY()) / 2);
        }
        return new Point(0, 0);
    }
}
