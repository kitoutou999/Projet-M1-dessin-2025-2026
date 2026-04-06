package view;

import model.GameModel;
import model.Observer;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class MainView extends JFrame implements Observer {
    private GameModel model;
    private DrawingCanvas drawingCanvas;
    private Toolbar toolbar;

    public MainView(GameModel model) {
        this.model = model;
        this.model.addObserver(this);
        this.model.getBlueGroup().addObserver(this);

        this.setTitle("Jeu d'Occupation Maximale d'Espace");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());

        this.drawingCanvas = new DrawingCanvas(model);
        this.toolbar = new Toolbar();

        this.add(toolbar, BorderLayout.NORTH);
        this.add(drawingCanvas, BorderLayout.CENTER);

        this.pack();
        this.setLocationRelativeTo(null);
    }

    public DrawingCanvas getDrawingCanvas() {
        return drawingCanvas;
    }

    public Toolbar getToolbar() {
        return toolbar;
    }

    @Override
    public void update() {
        this.drawingCanvas.repaint();
        this.toolbar.updateScore(model.getScore()); 
    }

    public GameModel getModel() {
        return this.model;
    }

    public void showGameOverDialog() {
        int score = Math.round(model.getScore());
        String message = " Partie terminée !\n Score : "+score+" de la surface occupée.";

        JOptionPane.showMessageDialog(this, message, "Fin de partie", JOptionPane.INFORMATION_MESSAGE);
    }
}
