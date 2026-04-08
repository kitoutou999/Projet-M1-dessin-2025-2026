package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe de base du pattern Observer.
 * Maintient une liste d'observateurs et les notifie lors de changements d'etat.
 */
public abstract class Observable {
    private List<Observer> observers = new ArrayList<>();

    public void addObserver(Observer obs) {
        observers.add(obs);
    }

    public void removeObserver(Observer obs) {
        observers.remove(obs);
    }

    public void notifyObservers() {
        for (Observer obs : observers) {
            obs.update();
        }
    }
}
