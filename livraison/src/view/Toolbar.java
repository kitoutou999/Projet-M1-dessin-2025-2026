package view;

import model.Observer;
import plugin.theme.ColorScheme;
import plugin.theme.ThemeManager;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe de la composante Toolbar de la vue en jeu
 * Contient toutes les actions possibles sous forme de boutons et les informations sur le déroulement de la partie
 */
public class Toolbar extends JPanel implements Observer {
    private JToggleButton btnCircle;
    private JToggleButton btnRectangle;
    private JButton btnUndo;
    private JButton btnRedo;
    private JLabel scoreText;
    private JLabel currentScoreText;
    private JLabel roundLabel;
    private JLabel twoPlayerInfo;
    private JButton btnTerminer;
    private JButton btnIa;
    private JToggleButton btnDraw;
    private JToggleButton btnScale;
    private JToggleButton btnMove;
    private JToggleButton btnRemove;

    private JPanel leftPanel;
    private JPanel rightPanel;
    private JPanel scorePanel;

    private final ThemeManager themeManager;
    private final List<AbstractButton> allButtons = new ArrayList<>();
    private final List<JLabel> allLabels = new ArrayList<>();

    public Toolbar(ThemeManager themeManager) {
        this.themeManager = themeManager;
        themeManager.addObserver(this);

        this.setLayout(new BorderLayout());

        leftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        leftPanel.setOpaque(false);
        btnCircle = new JToggleButton("Cercle");
        btnRectangle = new JToggleButton("Rectangle");
        ButtonGroup shapeGroup = new ButtonGroup();
        shapeGroup.add(btnCircle);
        shapeGroup.add(btnRectangle);
        btnCircle.setSelected(true);
        leftPanel.add(btnCircle);
        leftPanel.add(btnRectangle);

        rightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
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

        scorePanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 4, 0));
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

        btnIa = new JButton("IA");
        btnTerminer = new JButton("Terminer le dessin");
        btnTerminer.setVisible(false);
        rightPanel.add(Box.createHorizontalStrut(10));
        rightPanel.add(btnIa);
        rightPanel.add(btnTerminer);

        this.add(leftPanel, BorderLayout.WEST);
        this.add(rightPanel, BorderLayout.EAST);
        this.add(scorePanel, BorderLayout.CENTER);
        this.setPreferredSize(new Dimension(0, 40));

        // Collecter tous les boutons et labels pour l'application du thème
        allButtons.add(btnCircle); allButtons.add(btnRectangle);
        allButtons.add(btnDraw); allButtons.add(btnScale);
        allButtons.add(btnMove); allButtons.add(btnRemove);
        allButtons.add(btnUndo); allButtons.add(btnRedo);
        allButtons.add(btnIa); allButtons.add(btnTerminer);

        allLabels.add(roundLabel); allLabels.add(scoreText);
        allLabels.add(currentScoreText); allLabels.add(twoPlayerInfo);

        applyTheme(themeManager.getScheme());
    }

    /** Appelé par ThemeManager quand le thème change. */
    @Override
    public void update() {
        applyTheme(themeManager.getScheme());
    }

    private void applyTheme(ColorScheme scheme) {
        this.setBackground(scheme.getToolbarBackground());
        for (JLabel label : allLabels) {
            label.setForeground(scheme.getForeground());
        }
        for (AbstractButton btn : allButtons) {
            btn.setBackground(scheme.getButtonBackground());
            btn.setForeground(scheme.getButtonForeground());
            btn.setOpaque(true);
            btn.setBorderPainted(false);
        }
        repaint();
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
        btnIa.setVisible(false);
    }

    public void updateTwoPlayerInfo(String text) {
        twoPlayerInfo.setText(text);
    }

    public void showTerminerButton(boolean visible) {
        btnTerminer.setVisible(visible);
    }

    public JButton getBtnIa() { return btnIa; }
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
