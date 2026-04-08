package view;

import model.Difficulty;
import model.GameMode;
import model.GameSettings;
import model.LevelType;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;


/**
 * Classe de ModeSelectionDialog,la vue de séléction de jeu
 * Contient tous les divers modes de jeu et paramètres activables avant de lancer une partie
 * 
 */
public class ModeSelectionDialog extends JDialog {

    private final JRadioButton btnSolo = new JRadioButton("Solo", true);
    private final JRadioButton btnTwoPlayers = new JRadioButton("2 Joueurs");

    private final JRadioButton btnNormal = new JRadioButton("Normal", true);
    private final JRadioButton btnHard = new JRadioButton("Hard (formes rouges disparaissent après 10s)");

    private final JRadioButton btnPreset = new JRadioButton("Niveaux généré", true);
    private final JRadioButton btnRandom= new JRadioButton("Niveaux random");

    private GameSettings result = null;

    private ModeSelectionDialog(Frame owner) {
        super(owner, "Sélection du mode de jeu", true);
        buildUI();
        pack();
        setResizable(false);
        setLocationRelativeTo(owner);
    }

    private void buildUI() {
        JPanel root = new JPanel();
        root.setLayout(new BoxLayout(root, BoxLayout.Y_AXIS));
        root.setBorder(BorderFactory.createEmptyBorder(12, 16, 8, 16));

        root.add(buildModePanel());
        root.add(Box.createVerticalStrut(8));
        root.add(buildDifficultyPanel());
        root.add(Box.createVerticalStrut(8));
        root.add(buildLevelPanel());
        root.add(Box.createVerticalStrut(12));
        root.add(buildButtonPanel());

        setContentPane(root);

        btnTwoPlayers.addActionListener(e -> setSubOptionsEnabled(false));
        btnSolo.addActionListener(e -> setSubOptionsEnabled(true));
    }

    private JPanel buildModePanel() {
        JPanel panel = new JPanel(new GridLayout(2, 1, 0, 4));
        panel.setBorder(titledBorder("Mode de jeu"));

        ButtonGroup group = new ButtonGroup();
        group.add(btnSolo);
        group.add(btnTwoPlayers);

        panel.add(btnSolo);
        panel.add(btnTwoPlayers);
        return panel;
    }

    private JPanel buildDifficultyPanel() {
        JPanel panel = new JPanel(new GridLayout(2, 1, 0, 4));
        panel.setBorder(titledBorder("Difficulté"));

        ButtonGroup group = new ButtonGroup();
        group.add(btnNormal);
        group.add(btnHard);

        panel.add(btnNormal);
        panel.add(btnHard);
        return panel;
    }

    private JPanel buildLevelPanel() {
        JPanel panel = new JPanel(new GridLayout(2, 1, 0, 4));
        panel.setBorder(titledBorder("Niveaux"));

        ButtonGroup group = new ButtonGroup();
        group.add(btnPreset);
        group.add(btnRandom);

        panel.add(btnPreset);
        panel.add(btnRandom);
        return panel;
    }

    private JPanel buildButtonPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));
        JButton btnPlay = new JButton("Jouer");
        btnPlay.addActionListener(e -> confirm());
        panel.add(btnPlay);
        return panel;
    }

    private void setSubOptionsEnabled(boolean enabled) {
        btnNormal.setEnabled(enabled);
        btnHard.setEnabled(enabled);
        btnPreset.setEnabled(enabled);
        btnRandom.setEnabled(enabled);
    }

    private void confirm() {
        GameMode mode = btnSolo.isSelected() ? GameMode.SOLO : GameMode.TWO_PLAYERS;
        Difficulty difficulty = btnHard.isSelected() ? Difficulty.HARD : Difficulty.NORMAL;
        LevelType levelType = btnRandom.isSelected() ? LevelType.RANDOM : LevelType.PRESET;
        result = new GameSettings(mode, difficulty, levelType);
        dispose();
    }

    private static TitledBorder titledBorder(String title) {
        return BorderFactory.createTitledBorder(
            BorderFactory.createEtchedBorder(), title
        );
    }

    /**
     * Ouvre la dialog et retourne les paramètres choisis par l'utilisateur.
     * Retourne null si la fenêtre est fermée sans confirmer.
     */
    public static GameSettings show(Frame owner) {
        ModeSelectionDialog dialog = new ModeSelectionDialog(owner);
        dialog.setVisible(true);
        return dialog.result;
    }
}
