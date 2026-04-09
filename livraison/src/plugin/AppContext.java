package plugin;

import model.shapes.Shape;
import plugin.theme.ColorScheme;
import plugin.theme.ThemeManager;

import java.util.List;

/**
 * Interface exposant ce que l'application rend visible aux plug-ins.
 * Limite volontairement l'accès au modèle pour la sécurité :
 * un plug-in ne peut agir que via ce contrat, pas sur le GameModel complet.
 */
public interface AppContext {

    List<Shape> getShapes();

    /** Permet au plug-in de changer le thème visuel de l'application. */
    ThemeManager getThemeManager();
}
