package plugin.theme;

import java.awt.Color;

/**
 * Contrat d'un thème visuel.
 * Le plug-in dark mode implémente PluginDessin et change le ColorScheme
 * via ThemeManager, sans connaître le reste de l'application.
 */
public interface ColorScheme {
    Color getCanvasBackground();
    Color getRedShapeColor();
    Color getBlueShapeColor();
    Color getGizmoColor();
    Color getPreviewValidColor();
    Color getPreviewInvalidColor();
    Color getToolbarBackground();
    Color getForeground();
    Color getShapeNumberColor();
    Color getButtonBackground();
    Color getButtonForeground();
}
