package view;

import model.GameModel;
import model.Shape;
import model.Circle;
import model.Rectangle;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class DrawingCanvas extends JPanel {
    private GameModel model;
    private Shape previewShape = null;
    private Color previewColor = Color.BLUE;

    public DrawingCanvas(GameModel model) {
        this.model = model;
        this.setBackground(Color.WHITE);
        this.setPreferredSize(new Dimension(800, 600));
        this.setFocusable(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D) g;
        AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f);
        g2D.setComposite(ac);

        g.setColor(Color.RED);
        for (Shape s : model.getRedShapes()) {
            drawShape(g, s);
        }

        g.setColor(Color.BLUE);
        for (Shape s : model.getBlueShapes()) {
            drawShape(g, s);
        }

        if(previewShape!=null){
            g.setColor(previewColor);
            drawShape(g, previewShape);
        }

        drawGizmo(g);
    }

    private Shape gizmoShape = null;

    public void setPreviewShape(Shape s,boolean placable) {
        if(!placable){
            previewColor = Color.RED;
        }else{
            previewColor = Color.GREEN;
        }
        previewShape = s;
        repaint();
    }

    public void createGizmo(Shape shapeToScale) {
        gizmoShape = shapeToScale;
        repaint();
    }

    public void clearGizmo() {
        gizmoShape = null;
        repaint();
    }

    public boolean isCircleGizmoAt(int px, int py) {
        if (!(gizmoShape instanceof Circle)) return false;
        for (int[] h : getCircleGizmoHandles()) {
            if (Math.abs(px - h[0]) <= 5 && Math.abs(py - h[1]) <= 5) return true;
        }
        return false;
    }

    // (droite, haut, gauche, bas)
    private int[][] getCircleGizmoHandles() {
        Circle c = (Circle) gizmoShape;
        int cx = c.getCenter().getX(), cy = c.getCenter().getY(), r = c.getRadius();
        return new int[][]{{cx + r, cy}, {cx, cy - r}, {cx - r, cy}, {cx, cy + r}};
    }

    //(0=TL,1=TR,2=BL,3=BR) ou -1 si aucun
    public int getGizmoIndexAt(int px, int py) {
        if (!(gizmoShape instanceof Rectangle)) return -1;
        int[][] corners = getGizmoCorners();
        for (int i = 0; i < corners.length; i++) {
            if (Math.abs(px - corners[i][0]) <= 5 && Math.abs(py - corners[i][1]) <= 5) {
                return i;
            }
        }
        return -1;
    }

    // Retourne le point du coin à l'index (0=TL,1=TR,2=BL,3=BR)
    public model.Point getGizmoCorner(int index) {
        int[][] corners = getGizmoCorners();
        return new model.Point(corners[index][0], corners[index][1]);
    }

    private int[][] getGizmoCorners() {
        Rectangle r = (Rectangle) gizmoShape;
        int x = Math.min(r.getStart().getX(), r.getEnd().getX());
        int y = Math.min(r.getStart().getY(), r.getEnd().getY());
        int w = Math.abs(r.getStart().getX() - r.getEnd().getX());
        int h = Math.abs(r.getStart().getY() - r.getEnd().getY());
        return new int[][]{{x, y}, {x + w, y}, {x, y + h}, {x + w, y + h}};
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

    private void drawGizmo(Graphics g) {
        g.setColor(Color.MAGENTA);
        if (gizmoShape instanceof Rectangle) {
            Rectangle r = (Rectangle) gizmoShape;
            int x = r.getMinX();
            int y = r.getMinY();
            int w = r.getMaxX() - x;
            int h = r.getMaxY() - y;
            g.drawRect(x - 5, y - 5, 10, 10);
            g.drawRect(x + w - 5, y - 5, 10, 10);
            g.drawRect(x - 5, y + h - 5, 10, 10);
            g.drawRect(x + w - 5, y + h - 5, 10, 10);
        } else if (gizmoShape instanceof Circle) {
            for (int[] h : getCircleGizmoHandles()) {
                g.drawRect(h[0] - 5, h[1] - 5, 10, 10);
            }
        }
    }
}
