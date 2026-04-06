package view;

import javax.swing.*;
import java.awt.*;

public class Toolbar extends JPanel {
    private JToggleButton btnCircle;
    private JToggleButton btnRectangle;
    private JButton btnUndo;
    private JButton btnRedo;
    private JLabel scoreText;
    private JLabel currentScoreText;
    private JLabel roundLabel;
    private JLabel twoPlayerInfo;
    private JButton btnTerminer;
    private JToggleButton btnDraw;
    private JToggleButton btnScale;
    private JToggleButton btnMove;
    private JToggleButton btnRemove;

    public Toolbar() {
        this.setLayout(new BorderLayout());

        JPanel leftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        leftPanel.setOpaque(false);
        btnCircle = new JToggleButton("Cercle");
        btnRectangle = new JToggleButton("Rectangle");
        ButtonGroup shapeGroup = new ButtonGroup();
        shapeGroup.add(btnCircle);
        shapeGroup.add(btnRectangle);
        btnCircle.setSelected(true);
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

        JPanel scorePanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 4, 0));
        scorePanel.setOpaque(false);
        roundLabel = new JLabel("Manche 1 / 10");
        roundLabel.setPreferredSize(new Dimension(110, 20));
        scoreText = new JLabel("  |  Score : ");
        currentScoreText = new JLabel("0%");
        currentScoreText.setPreferredSize(new Dimension(45, 20));
        twoPlayerInfo = new JLabel("");
        twoPlayerInfo.setFont(twoPlayerInfo.getFont().deriveFont(11f));
        twoPlayerInfo.setVisible(false);
        scorePanel.add(roundLabel);
        scorePanel.add(scoreText);
        scorePanel.add(currentScoreText);
        scorePanel.add(twoPlayerInfo);

        btnTerminer = new JButton("Terminer le dessin");
        btnTerminer.setVisible(false);
        rightPanel.add(Box.createHorizontalStrut(10));
        rightPanel.add(btnTerminer);

        this.add(leftPanel, BorderLayout.WEST);
        this.add(rightPanel, BorderLayout.EAST);
        this.add(scorePanel, BorderLayout.CENTER);
        this.setPreferredSize(new Dimension(0, 40));
    }

    public void updateScore(float newScore) {
        currentScoreText.setText((int) newScore + "%");
    }

    public void updateRound(int current, int total) {
        roundLabel.setText("Manche " + current + " / " + total);
    }

    public void switchToTwoPlayerMode() {
        roundLabel.setVisible(false);
        scoreText.setVisible(false);
        currentScoreText.setVisible(false);
        twoPlayerInfo.setVisible(true);
    }

    public void updateTwoPlayerInfo(String text) {
        twoPlayerInfo.setText(text);
    }

    public void showTerminerButton(boolean visible) {
        btnTerminer.setVisible(visible);
    }

    public JButton getBtnTerminer() { return btnTerminer; }

    public JToggleButton getBtnCircle() { return btnCircle; }
    public JToggleButton getBtnRectangle() { return btnRectangle; }
    public JButton getBtnUndo() { return btnUndo; }
    public JButton getBtnRedo() { return btnRedo; }
    public JToggleButton getBtnDraw() { return btnDraw; }
    public JToggleButton getBtnScale() { return btnScale; }
    public JToggleButton getBtnMove() { return btnMove; }
    public JToggleButton getBtnRemove() { return btnRemove; }
}
