package controller;

import model.GameModel;
import javax.swing.Timer;

/**
 * Comportement représentant le mode de jeu Timer
 * Contient toutes les méthodes qui y sont associées (compter, commencer, arrêter)
 */
public class HardModeTimer {

    private static final int DELAY_MS = 5000;
    private final Timer timer;

    public HardModeTimer(GameModel model) {
        timer = new Timer(DELAY_MS, e -> model.hideRedShapes());
        timer.setRepeats(false);
    }

    public void start() {
        timer.restart();
    }

    public void stop() {
        timer.stop();
    }
}
