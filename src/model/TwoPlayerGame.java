package model;

import model.shapes.Shape;
import java.util.ArrayList;
import java.util.List;

public class TwoPlayerGame extends Observable {

    public enum Phase { DRAWING, REPRODUCING }

    public static final int ROUND_COUNT = 10;

    private int currentRound = 1;
    private Phase phase = Phase.DRAWING;
    private int currentDrawer = 1;

    private List<Shape> drawnShapes = new ArrayList<>();
    private float player1Score = 0;
    private float player2Score = 0;

    public void startReproducingPhase(List<Shape> drawn) {
        this.drawnShapes = new ArrayList<>(drawn);
        this.phase = Phase.REPRODUCING;
        notifyObservers();
    }

    public void recordRoundScore(List<Shape> reproduced) {
        float score = ScoreCalculator.calculate(drawnShapes, reproduced);
        if (getReproducer() == 1) player1Score += score;
        else                      player2Score += score;
    }

    public void nextRound() {
        currentRound++;
        currentDrawer = (currentDrawer == 1) ? 2 : 1;
        phase = Phase.DRAWING;
        drawnShapes.clear();
        notifyObservers();
    }

    public Phase getPhase(){ return phase; }
    public int getCurrentDrawer() { return currentDrawer; }
    public int getReproducer()  { return currentDrawer == 1 ? 2 : 1; }
    public List<Shape> getDrawnShapes() { return drawnShapes; }
    public int getCurrentRound() { return currentRound; }
    public boolean isLastRound(){ return currentRound >= ROUND_COUNT; }
    public float getPlayer1Score() { return player1Score; }
    public float getPlayer2Score() { return player2Score; }
}
