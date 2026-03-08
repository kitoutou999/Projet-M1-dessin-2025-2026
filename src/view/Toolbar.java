package view;

import javax.swing.*;
import java.awt.*;

public class Toolbar extends JPanel {
    private JButton btnCercle;
    private JButton btnRectangle;
    private JButton btnUndo;
    private JButton btnRedo;

    public Toolbar() {
        this.setLayout(new BorderLayout());

        JPanel panGauche = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panGauche.setOpaque(false);
        btnCercle = new JButton("Cercle");
        btnRectangle = new JButton("Rectangle");
        panGauche.add(btnCercle);
        panGauche.add(btnRectangle);

        JPanel panDroite = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panDroite.setOpaque(false);
        btnUndo = new JButton("Undo");
        btnRedo = new JButton("Redo");
        panDroite.add(btnUndo);
        panDroite.add(btnRedo);

        this.add(panGauche, BorderLayout.WEST);
        this.add(panDroite, BorderLayout.EAST);
    }

    public JButton getBtnCercle() { return btnCercle; }
    public JButton getBtnRectangle() { return btnRectangle; }
    public JButton getBtnUndo() { return btnUndo; }
    public JButton getBtnRedo() { return btnRedo; }
}
