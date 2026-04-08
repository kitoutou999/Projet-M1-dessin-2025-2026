package plugin.theme;

import java.awt.Color;

public class DarkTheme implements ColorScheme {
    @Override public Color getCanvasBackground()    { return new Color(28, 28, 28); }
    @Override public Color getRedShapeColor()       { return new Color(220, 80, 80); }
    @Override public Color getBlueShapeColor()      { return new Color(80, 130, 220); }
    @Override public Color getGizmoColor()          { return new Color(200, 100, 220); }
    @Override public Color getPreviewValidColor()   { return new Color(80, 200, 100); }
    @Override public Color getPreviewInvalidColor() { return new Color(220, 80, 80); }
    @Override public Color getToolbarBackground()   { return new Color(45, 45, 45); }
    @Override public Color getForeground()          { return new Color(220, 220, 220); }
    @Override public Color getShapeNumberColor()    { return new Color(220, 220, 220); }
    @Override public Color getButtonBackground()    { return new Color(65, 65, 65); }
    @Override public Color getButtonForeground()    { return new Color(220, 220, 220); }
}
