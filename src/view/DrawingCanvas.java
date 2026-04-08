package view;

import model.GameModel;
import model.Observer;
import model.Point;
import model.shapes.Shape;
import model.shapes.Circle;
import model.shapes.Rectangle;
import plugin.theme.ThemeManager;
import plugin.theme.ColorScheme;

import javax.swing.*;
import java.awt.*;
import java.util.List;



/**
 * Classe de la composante DrawingCanvas de la vue en jeu
 * Contient toutes les formes déjà créées et dessinées et la zone dans laquelle les joueurs placent leurs formes
 * 
 */
public class DrawingCanvas extends JPanel implements Observer {
    private GameModel model;
    private ThemeManager themeManager;
    private Shape previewShape = null;
    private boolean placable = true;
    private boolean showShapeNumbers = false;

    public void setShowShapeNumbers(boolean show) {
        this.showShapeNumbers = show;
        repaint();
    }

    public DrawingCanvas(GameModel model, ThemeManager themeManager) {
        this.model = model;
        this.themeManager = themeManager;
        themeManager.addObserver(this);
        this.setBackground(themeManager.getScheme().getCanvasBackground());
        this.setPreferredSize(new Dimension(model.getCanvasWidth(), model.getCanvasHeight()));
        this.setFocusable(true);
    }

    /** Appelé par ThemeManager quand le thème change. */
    @Override
    public void update() {
        this.setBackground(themeManager.getScheme().getCanvasBackground());
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        ColorScheme scheme = themeManager.getScheme();
        Graphics2D g2D = (Graphics2D) g;
        AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f);
        g2D.setComposite(ac);

        g.setColor(scheme.getRedShapeColor());
        for (Shape s : model.getRedShapes()) {
            drawShape(g, s);
        }

        g.setColor(scheme.getBlueShapeColor());
        for (Shape s : model.getBlueShapes()) {
            drawShape(g, s);
        }

        if (previewShape != null) {
            g.setColor(placable ? scheme.getPreviewValidColor() : scheme.getPreviewInvalidColor());
            drawShape(g, previewShape);
        }

        if (showShapeNumbers) drawShapeNumbers(g, scheme);

        drawGizmo(g, scheme);
    }

    private Shape gizmoShape = null;

    public void setPreviewShape(Shape s, boolean placable) {
        this.placable = placable;
        this.previewShape = s;
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

    // (droite, haut, gauche, bas)
    private int[][] getCircleGizmoHandles() {
        Circle c = (Circle) gizmoShape;
        int cx = c.getCenter().getX(), cy = c.getCenter().getY(), r = c.getRadius();
        return new int[][]{{cx + r, cy}, {cx, cy - r}, {cx - r, cy}, {cx, cy + r}};
    }

    private int[][] getGizmoCorners() {
        Rectangle r = (Rectangle) gizmoShape;
        int x = Math.min(r.getStart().getX(), r.getEnd().getX());
        int y = Math.min(r.getStart().getY(), r.getEnd().getY());
        int w = Math.abs(r.getStart().getX() - r.getEnd().getX());
        int h = Math.abs(r.getStart().getY() - r.getEnd().getY());
        return new int[][]{{x, y}, {x + w, y}, {x, y + h}, {x + w, y + h}};
    }

    private void drawShapeNumbers(Graphics g, ColorScheme scheme) {
        g.setColor(scheme.getShapeNumberColor());
        g.setFont(new Font("Arial", Font.BOLD, 16));
        FontMetrics fm = g.getFontMetrics();
        List<Shape> shapes = model.getBlueShapes();
        for (int i = 0; i < shapes.size(); i++) {
            String num = String.valueOf(i + 1);
            Point center = getShapeCenter(shapes.get(i));
            int x = center.getX() - fm.stringWidth(num) / 2;
            int y = center.getY() + fm.getAscent() / 2 - 2;
            g.drawString(num, x, y);
        }
    }

    private Point getShapeCenter(Shape s) {
        if (s instanceof Circle) return ((Circle) s).getCenter();
        if (s instanceof Rectangle) {
            Rectangle r = (Rectangle) s;
            return new Point((r.getMinX() + r.getMaxX()) / 2, (r.getMinY() + r.getMaxY()) / 2);
        }
        return new Point(0, 0);
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

    private void drawGizmo(Graphics g, ColorScheme scheme) {
        g.setColor(scheme.getGizmoColor());
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
