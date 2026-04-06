package model;

import model.shapes.Circle;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class GameModelTest {

    private GameModel model;

    @Before
    public void setUp() {
        model = new GameModel(() -> java.util.Collections.emptyList());
    }

    @Test
    public void scoreInitialEstZero() {
        assertEquals(0f, model.getScore(), 0.01f);
    }

    @Test
    public void scoreEstEnPourcentage() {
        model.addBlueShape(new Circle(new Point(400, 300), 100));
        float expected = (float)(Math.PI * 100 * 100) / (800 * 600) * 100f;
        assertEquals(expected, model.getScore(), 0.01f);
    }

    @Test
    public void gameOverApres4Formes() {
        model.addBlueShape(new Circle(new Point(100, 100), 30));
        model.addBlueShape(new Circle(new Point(300, 100), 30));
        model.addBlueShape(new Circle(new Point(500, 100), 30));
        model.addBlueShape(new Circle(new Point(700, 100), 30));
        assertTrue(model.isGameOver());
    }

    @Test
    public void formeBleueSurFormeRougeEstIntersection() {
        model.addRedShape(new Circle(new Point(200, 200), 60));
        assertTrue(model.isIntersecting(new Circle(new Point(200, 200), 30)));
    }

    @Test
    public void resetVideLesFormesBleues() {
        model.addBlueShape(new Circle(new Point(200, 200), 40));
        model.resetBlueShapes();
        assertEquals(0, model.getBlueShapes().size());
    }
}
