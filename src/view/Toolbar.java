package view;

import javax.swing.*;
import java.awt.*;

public class Toolbar extends JPanel {
    private JButton btnCircle;
    private JButton btnRectangle;
    private JButton btnUndo;
    private JButton btnRedo;
    private JLabel scoreText;
    private JLabel currentScoreText;
    private JToggleButton btnDraw;
    private JToggleButton btnScale;
    private JToggleButton btnMove;
    private JToggleButton btnRemove;

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
        btnDraw = new JToggleButton("Draw");
        btnScale = new JToggleButton("Scale");
        btnMove = new JToggleButton("Move");
        btnRemove = new JToggleButton("Remove");
        ButtonGroup modeGroup = new ButtonGroup();
        modeGroup.add(btnDraw);
        modeGroup.add(btnMove);
        modeGroup.add(btnScale);
        modeGroup.add(btnRemove);
        btnDraw.setSelected(true);

        btnUndo = new JButton("Undo");
        btnRedo = new JButton("Redo");
        rightPanel.add(btnDraw);
        rightPanel.add(btnScale);
        rightPanel.add(btnMove);
        rightPanel.add(btnRemove);
        rightPanel.add(Box.createHorizontalStrut(10));
        rightPanel.add(btnUndo);
        rightPanel.add(btnRedo);

        JPanel scorePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        scorePanel.setOpaque(false);
        scoreText = new JLabel("Score : ");
        currentScoreText = new JLabel("0");
        scorePanel.add(scoreText);
        scorePanel.add(currentScoreText);


        this.add(leftPanel, BorderLayout.WEST);
        this.add(rightPanel, BorderLayout.EAST);
        this.add(scorePanel, BorderLayout.CENTER);
    }

    public void updateScore(int newScore){
        currentScoreText.setText(String.valueOf(newScore));
    }

    public JButton getBtnCircle() { return btnCircle; }
    public JButton getBtnRectangle() { return btnRectangle; }
    public JButton getBtnUndo() { return btnUndo; }
    public JButton getBtnRedo() { return btnRedo; }
    public JToggleButton getBtnDraw() { return btnDraw; }
    public JToggleButton getBtnScale() { return btnScale; }
    public JToggleButton getBtnMove() { return btnMove; }
    public JToggleButton getBtnRemove() { return btnRemove; }
}
