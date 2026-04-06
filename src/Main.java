import controller.CommandHandler;
import controller.Controller;
import controller.HardModeTimer;
import controller.TwoPlayerController;
import model.*;
import model.strategy.LevelStrategy;
import model.strategy.PresetLevelStrategy;
import model.strategy.RandomLevelStrategy;
import view.MainView;
import view.ModeSelectionDialog;

import javax.swing.SwingUtilities;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {

            GameSettings settings = ModeSelectionDialog.show(null);
            if (settings == null) return;

            if (settings.getMode() == GameMode.TWO_PLAYERS) {
                startTwoPlayerMode();
            } else {
                startSoloMode(settings);
            }
        });
    }

    private static void startSoloMode(GameSettings settings) {
        LevelStrategy strategy = settings.getLevelType() == LevelType.RANDOM
                ? new RandomLevelStrategy()
                : new PresetLevelStrategy();

        GameModel model = new GameModel(strategy);
        MainView view = new MainView(model);
        CommandHandler handler = new CommandHandler();

        HardModeTimer hardModeTimer = settings.getDifficulty() == Difficulty.HARD
                ? new HardModeTimer(model)
                : null;

        new Controller(view, handler, hardModeTimer);
        view.setVisible(true);
    }

    private static void startTwoPlayerMode() {
        GameModel model = new GameModel(() -> new ArrayList<>());
        TwoPlayerGame game = new TwoPlayerGame();
        MainView view = new MainView(model);
        view.switchToTwoPlayerMode();
        CommandHandler handler = new CommandHandler();
        new TwoPlayerController(view, handler, game);
        view.setVisible(true);
    }
}
