import controller.CommandHandler;
import controller.Controller;
import controller.HardModeTimer;
import model.Difficulty;
import model.GameModel;
import model.GameSettings;
import model.LevelType;
import model.strategy.LevelStrategy;
import model.strategy.PresetLevelStrategy;
import model.strategy.RandomLevelStrategy;
import view.MainView;
import view.ModeSelectionDialog;

import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {

            GameSettings settings = ModeSelectionDialog.show(null);
            if (settings == null) return;

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
        });
    }
}
