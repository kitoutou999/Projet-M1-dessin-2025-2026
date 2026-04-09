import controller.CommandHandler;
import controller.Controller;
import controller.HardModeTimer;
import controller.TwoPlayerController;
import model.*;
import model.strategy.LevelStrategy;
import plugin.theme.ThemeManager;
import view.MainView;
import view.ModeSelectionDialog;

import javax.swing.SwingUtilities;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {

            GameSettings settings = ModeSelectionDialog.show(null);
            if (settings == null) return;

            // Le ThemeManager est créé ici et injecté dans toute l'application.
            // Les plug-ins y accèdent via AppContext (injection de dépendance).
            ThemeManager themeManager = new ThemeManager();

            if (settings.getMode() == GameMode.TWO_PLAYERS) {
                startTwoPlayerMode(themeManager);
            } else {
                startSoloMode(settings, themeManager);
            }
        });
    }

    private static void startSoloMode(GameSettings settings, ThemeManager themeManager) {
        GameModel model = new GameModel(settings.getLevelStrategy());
        MainView view = new MainView(model, themeManager);
        CommandHandler handler = new CommandHandler();

        HardModeTimer hardModeTimer = settings.getDifficulty() == Difficulty.HARD
                ? new HardModeTimer(model)
                : null;

        new Controller(view, handler, hardModeTimer);
        view.setVisible(true);
    }

    private static void startTwoPlayerMode(ThemeManager themeManager) {
        GameModel model = new GameModel(() -> new ArrayList<>());
        TwoPlayerGame game = new TwoPlayerGame();
        MainView view = new MainView(model, themeManager);
        view.switchToTwoPlayerMode();
        CommandHandler handler = new CommandHandler();
        new TwoPlayerController(view, handler, game);
        view.setVisible(true);
    }
}
