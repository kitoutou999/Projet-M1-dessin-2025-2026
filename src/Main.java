import controller.Controller;
import model.GameModel;
import view.MainView;
import model.Point;
import model.Circle;
import model.Rectangle;
import javax.swing.SwingUtilities;

public class Main{
	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {

			GameModel model = new GameModel();
			MainView view = new MainView(model);
			Controller controller = new Controller(model, view);

			model.addRedShape(new Circle(new Point(100, 100), 50));
			model.addRedShape(new Rectangle(new Point(300, 100), new Point(400, 200)));

			view.setVisible(true);
		});
	}
}
