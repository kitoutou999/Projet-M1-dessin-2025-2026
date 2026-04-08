package plugin;

import model.GameModel;
import model.shapes.Shape;
import plugin.theme.ThemeManager;

import java.util.List;

/**
 * Implementation de AppContext fournie aux plugins.
 * Donne acces aux formes du modele et au gestionnaire de themes.
 */
public class AppContextImpl implements AppContext {
    private final GameModel model;
    private final ThemeManager themeManager;

    public AppContextImpl(GameModel model, ThemeManager themeManager) {
        this.model = model;
        this.themeManager = themeManager;
    }

    @Override
    public List<Shape> getShapes() {
        return model.getBlueShapes();
    }

    @Override
    public ThemeManager getThemeManager() {
        return themeManager;
    }
}
