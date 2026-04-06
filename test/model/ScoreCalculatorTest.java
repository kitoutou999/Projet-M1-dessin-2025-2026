package model;

import model.shapes.Circle;
import org.junit.Test;
import java.util.Arrays;
import java.util.Collections;
import static org.junit.Assert.*;

public class ScoreCalculatorTest {

    @Test
    public void scoreParfaitCercleIdentique() {
        Circle drawn = new Circle(new Point(200, 200), 60);
        Circle reproduced = new Circle(new Point(200, 200), 60);
        float score = ScoreCalculator.calculate(Arrays.asList(drawn), Arrays.asList(reproduced));
        assertEquals(100f, score, 0.01f);
    }

    @Test
    public void scoreDecroitAvecDistance() {
        Circle drawn  = new Circle(new Point(100, 100), 50);
        Circle proche = new Circle(new Point(130, 100), 50);
        Circle loin = new Circle(new Point(400, 100), 50);
        float scoreProche = ScoreCalculator.calculate(Arrays.asList(drawn), Arrays.asList(proche));
        float scoreLoin = ScoreCalculator.calculate(Arrays.asList(drawn), Arrays.asList(loin));
        assertTrue(scoreProche > scoreLoin);
    }

    @Test
    public void scoreToujursPositifOuNul() {
        Circle drawn = new Circle(new Point(100, 100), 50);
        Circle tresLoin = new Circle(new Point(700, 500), 10);
        float score = ScoreCalculator.calculate(Arrays.asList(drawn), Arrays.asList(tresLoin));
        assertTrue(score >= 0f);
    }

    @Test
    public void scoreSansReproductionEstZero() {
        Circle drawn = new Circle(new Point(100, 100), 50);
        float score = ScoreCalculator.calculate(Arrays.asList(drawn), Collections.emptyList());
        assertEquals(0f, score, 0.01f);
    }
}
