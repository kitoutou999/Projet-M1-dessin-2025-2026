package view;

import model.GameModel;

import javax.swing.*;
import java.awt.*;

public class DrawingCanvas extends JPanel {
    private GameModel model;

    public DrawingCanvas(GameModel model) {
        this.model = model;
        this.setBackground(Color.WHITE);
        this.setPreferredSize(new Dimension(800, 600));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        g.setColor(Color.RED);
        for (model.Shape f : model.getRedShapes()) {
            dessinerShape(g, f);
        }
        
        g.setColor(Color.BLUE);
        for (model.Shape f : model.getBlueShapes()) {
            dessinerShape(g, f);
        }
    }

    private void dessinerShape(Graphics g, model.Shape f) {
        if (f instanceof model.Circle) {
            model.Circle c = (model.Circle) f;
            int r = c.getRayon();
            g.drawOval(c.getCentre().getX() - r, c.getCentre().getY() - r, 2 * r, 2 * r);
        } else if (f instanceof model.Rectangle) {
            model.Rectangle r = (model.Rectangle) f;
            int x = Math.min(r.getStart().getX(), r.getEnd().getX());
            int y = Math.min(r.getStart().getY(), r.getEnd().getY());
            int w = Math.abs(r.getStart().getX() - r.getEnd().getX());
            int h = Math.abs(r.getStart().getY() - r.getEnd().getY());
            g.drawRect(x, y, w, h);
        }
    }
}
