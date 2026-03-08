import controller.Controller;
import model.GameModel;
import view.MainView;
import javax.swing.SwingUtilities;

void main(String[] args) {
    SwingUtilities.invokeLater(() -> {

        GameModel model = new GameModel();
        MainView vue = new MainView(model);
        Controller controleur = new Controller(model, vue);

        model.addRedShape(new model.Circle(new model.Point(100, 100), 50));
        model.addRedShape(new model.Rectangle(new model.Point(300, 100), new model.Point(400, 200)));

        vue.setVisible(true);
    });
}

