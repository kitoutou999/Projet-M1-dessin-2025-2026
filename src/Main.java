import controller.CommandHandler;
import controller.Controller;
import model.*;
import view.MainView;

import javax.swing.SwingUtilities;

public class Main{
	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {

			GameModel model = new GameModel();
			MainView view = new MainView(model);
			CommandHandler handler = new CommandHandler();
			Controller controller = new Controller(model, view, handler);


			model.addRedShape(new Circle(new Point(100, 100), 50));
			model.addRedShape(new Rectangle(new Point(300, 100), new Point(400, 200)));

			view.setVisible(true);
		});
	}
}
