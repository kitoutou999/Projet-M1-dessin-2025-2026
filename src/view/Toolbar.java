package view;

import javax.swing.*;
import java.awt.*;

public class Toolbar extends JPanel {
    private JButton btnCircle;
    private JButton btnRectangle;
    private JButton btnUndo;
    private JButton btnRedo;

    public Toolbar() {
        this.setLayout(new BorderLayout());

        JPanel leftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        leftPanel.setOpaque(false);
        btnCircle = new JButton("Cercle");
        btnRectangle = new JButton("Rectangle");
        leftPanel.add(btnCircle);
        leftPanel.add(btnRectangle);

        JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        rightPanel.setOpaque(false);
        btnUndo = new JButton("Undo");
        btnRedo = new JButton("Redo");
        rightPanel.add(btnUndo);
        rightPanel.add(btnRedo);

        this.add(leftPanel, BorderLayout.WEST);
        this.add(rightPanel, BorderLayout.EAST);
    }

    public JButton getBtnCircle() { return btnCircle; }
    public JButton getBtnRectangle() { return btnRectangle; }
    public JButton getBtnUndo() { return btnUndo; }
    public JButton getBtnRedo() { return btnRedo; }
}
