package view;

import model.GameModel;
import model.Observer;
import model.TwoPlayerGame;
import plugin.AppContext;
import plugin.AppContextImpl;
import plugin.PluginDessin;
import plugin.PluginLoader;
import plugin.theme.ThemeManager;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class MainView extends JFrame implements Observer {
    private GameModel model;
    private DrawingCanvas drawingCanvas;
    private Toolbar toolbar;
    private final ThemeManager themeManager;

    public MainView(GameModel model, ThemeManager themeManager) {
        this.model = model;
        this.themeManager = themeManager;
        this.model.addObserver(this);
        this.model.getBlueGroup().addObserver(this);

        this.setTitle("Jeu d'Occupation Maximale d'Espace");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());

        this.drawingCanvas = new DrawingCanvas(model, themeManager);
        this.toolbar = new Toolbar(themeManager);

        this.add(toolbar, BorderLayout.NORTH);
        this.add(drawingCanvas, BorderLayout.CENTER);

        loadPlugins();

        this.pack();
        this.setResizable(false);
        this.setLocationRelativeTo(null);
    }

    /**
     * Crée la barre de menus de la fenêtre et charge les plug-ins dedans.
     * Les plug-ins sont intégrés dans le menu "Affichage".
     */
    private void loadPlugins() {
        AppContext ctx = new AppContextImpl(model, themeManager);
        List<PluginDessin> plugins = new PluginLoader().loadAll();

        JMenuBar menuBar = new JMenuBar();
        JMenu affichage = new JMenu("Affichage");

        for (PluginDessin plugin : plugins) {
            plugin.initData(ctx);
            affichage.add(plugin.getPanel());
        }

        menuBar.add(affichage);
        this.setJMenuBar(menuBar);
    }

    public DrawingCanvas getDrawingCanvas() {
        return drawingCanvas;
    }

    public Toolbar getToolbar() {
        return toolbar;
    }

    @Override
    public void update() {
        this.drawingCanvas.repaint();
        this.toolbar.updateScore(model.getScore());
        this.toolbar.updateRound(model.getCurrentRound(), GameModel.ROUND_COUNT);
    }

    public GameModel getModel() {
        return this.model;
    }

    public void showNextRoundDialog() {
        int score = Math.round(model.getScore());
        int next  = model.getCurrentRound() + 1;
        JOptionPane.showMessageDialog(
            this,
            "Manche " + model.getCurrentRound() + " terminée !\nScore : " + score + "%\n\nManche " + next + " sur " + GameModel.ROUND_COUNT + "...",
            "Manche suivante",
            JOptionPane.INFORMATION_MESSAGE
        );
    }

    public void switchToTwoPlayerMode() {
        toolbar.switchToTwoPlayerMode();
    }

    public void showRoundResultDialog(int reproducer, float score, int round) {
        JOptionPane.showMessageDialog(
            this,
            "Manche " + round + " terminée !\nJoueur " + reproducer + " — Score : " + (int) score + " / 100",
            "Résultat de la manche",
            JOptionPane.INFORMATION_MESSAGE
        );
    }

    public void showFinalTwoPlayerScoreDialog(TwoPlayerGame game) {
        int s1 = Math.round(game.getPlayer1Score() / (TwoPlayerGame.ROUND_COUNT / 2));
        int s2 = Math.round(game.getPlayer2Score() / (TwoPlayerGame.ROUND_COUNT / 2));
        String winner = s1 > s2 ? "Joueur 1" : s2 > s1 ? "Joueur 2" : "Égalité !";
        JOptionPane.showMessageDialog(
            this,
            "Partie terminée !\n\nJoueur 1 : " + s1 + " / 100\nJoueur 2 : " + s2 + " / 100\n\nVainqueur : " + winner,
            "Fin de partie — 2 Joueurs",
            JOptionPane.INFORMATION_MESSAGE
        );
    }

    public void showFinalScoreDialog() {
        int global = Math.round(model.getGlobalScore());
        JOptionPane.showMessageDialog(
            this,
            "Partie terminée !\nScore global : " + global + "% (moyenne sur " + GameModel.ROUND_COUNT + " manches).",
            "Fin de partie",
            JOptionPane.INFORMATION_MESSAGE
        );
    }
}
