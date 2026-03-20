package view;

import model.GameModel;
import model.Shape;
import model.Circle;
import model.Rectangle;
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
        Graphics2D g2D=(Graphics2D) g;
        AlphaComposite ac = AlphaComposite.getInstance( AlphaComposite.SRC_OVER, 0.5f );
        g2D.setComposite( ac );
        
        g.setColor(Color.RED);
        for (Shape s : model.getRedShapes()) {
            drawShape(g, s);
        }
        
        g.setColor(Color.BLUE);
        for (Shape s : model.getBlueShapes()) {
            drawShape(g, s);
        }
    }

    private void drawShape(Graphics g, Shape s) {
        if (s instanceof Circle) {
            Circle c = (Circle) s;
            int r = c.getRadius();
            g.drawOval(c.getCenter().getX() - r, c.getCenter().getY() - r, 2 * r, 2 * r);
            g.fillOval(c.getCenter().getX() - r, c.getCenter().getY() - r, 2 * r, 2 * r);
        } else if (s instanceof Rectangle) {
            Rectangle r = (Rectangle) s;
            int x = Math.min(r.getStart().getX(), r.getEnd().getX());
            int y = Math.min(r.getStart().getY(), r.getEnd().getY());
            int w = Math.abs(r.getStart().getX() - r.getEnd().getX());
            int h = Math.abs(r.getStart().getY() - r.getEnd().getY());
            g.drawRect(x, y, w, h);
            g.fillRect(x, y, w, h);
        }
    }
}
