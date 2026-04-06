package model.strategy;

import model.Collision;
import model.Point;
import model.shapes.Circle;
import model.shapes.Rectangle;
import model.shapes.Shape;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomLevelStrategy implements LevelStrategy {

    private static final int CANVAS_WIDTH  = 800;
    private static final int CANVAS_HEIGHT = 600;
    private static final int SHAPE_COUNT   = 3;
    private static final int MIN_RADIUS    = 30;
    private static final int MAX_RADIUS    = 80;
    private static final int MIN_SIDE      = 60;
    private static final int MAX_SIDE      = 150;
    private static final int MAX_ATTEMPTS  = 100;

    private final Random random = new Random();

    @Override
    public List<Shape> generateLevel() {
        List<Shape> shapes = new ArrayList<>();

        for (int i = 0; i < SHAPE_COUNT; i++) {
            Shape candidate = null;
            int attempts = 0;

            while (candidate == null && attempts < MAX_ATTEMPTS) {
                candidate = random.nextBoolean() ? randomCircle() : randomRectangle();
                if (overlapsAny(candidate, shapes) || !fitsInCanvas(candidate)) {
                    candidate = null;
                }
                attempts++;
            }

            if (candidate != null) shapes.add(candidate);
        }

        return shapes;
    }

    private Circle randomCircle() {
        int radius = MIN_RADIUS + random.nextInt(MAX_RADIUS - MIN_RADIUS + 1);
        int x = radius + random.nextInt(CANVAS_WIDTH  - 2 * radius);
        int y = radius + random.nextInt(CANVAS_HEIGHT - 2 * radius);
        return new Circle(new Point(x, y), radius);
    }

    private Rectangle randomRectangle() {
        int w = MIN_SIDE + random.nextInt(MAX_SIDE - MIN_SIDE + 1);
        int h = MIN_SIDE + random.nextInt(MAX_SIDE - MIN_SIDE + 1);
        int x = random.nextInt(CANVAS_WIDTH  - w);
        int y = random.nextInt(CANVAS_HEIGHT - h);
        return new Rectangle(new Point(x, y), new Point(x + w, y + h));
    }

    private boolean overlapsAny(Shape candidate, List<Shape> existing) {
        for (Shape s : existing) {
            if (s.collidesWith(candidate)) return true;
        }
        return false;
    }

    private boolean fitsInCanvas(Shape s) {
        return s.isInsideCanvas(CANVAS_WIDTH, CANVAS_HEIGHT);
    }
}
