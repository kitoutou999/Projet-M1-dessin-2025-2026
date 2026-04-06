import controller.CommandHandler;
import controller.Controller;
import model.*;
import model.shapes.Circle;
import model.shapes.Rectangle;
import view.MainView;
import view.ModeSelectionDialog;

import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {

            GameSettings settings = ModeSelectionDialog.show(null);

            GameModel model = new GameModel();
            MainView view = new MainView(model);
            CommandHandler handler = new CommandHandler();
            Controller controller = new Controller(view, handler);

            model.addRedShape(new Circle(new Point(100, 100), 50));
            model.addRedShape(new Rectangle(new Point(300, 100), new Point(400, 200)));

            view.setVisible(true);
        });
    }
}
