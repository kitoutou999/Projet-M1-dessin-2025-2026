package view;

import model.GameModel;
import model.Observer;
import javax.swing.*;
import java.awt.*;

public class MainView extends JFrame implements Observer {
    private GameModel model;
    private DrawingCanvas zoneDessin;
    private Toolbar barreOutils;

    public MainView(GameModel model) {
        this.model = model;
        this.model.addObserver(this);

        this.setTitle("Jeu d'Occupation Maximale d'Espace");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());

        this.zoneDessin = new DrawingCanvas(model);
        this.barreOutils = new Toolbar();

        this.add(barreOutils, BorderLayout.NORTH);
        this.add(zoneDessin, BorderLayout.CENTER);

        this.pack();
        this.setLocationRelativeTo(null);
    }

    public DrawingCanvas getZoneDessin() {
        return zoneDessin;
    }

    public Toolbar getBarreOutils() {
        return barreOutils;
    }

    @Override
    public void update() {
        this.zoneDessin.repaint();
    }
}
