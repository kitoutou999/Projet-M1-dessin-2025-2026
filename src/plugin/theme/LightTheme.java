package plugin.theme;

import java.awt.Color;

public class LightTheme implements ColorScheme {
    @Override public Color getCanvasBackground()    { return Color.WHITE; }
    @Override public Color getRedShapeColor()       { return Color.RED; }
    @Override public Color getBlueShapeColor()      { return Color.BLUE; }
    @Override public Color getGizmoColor()          { return Color.MAGENTA; }
    @Override public Color getPreviewValidColor()   { return Color.GREEN; }
    @Override public Color getPreviewInvalidColor() { return Color.RED; }
    @Override public Color getToolbarBackground()   { return new Color(238, 238, 238); }
    @Override public Color getForeground()          { return Color.BLACK; }
    @Override public Color getShapeNumberColor()    { return Color.WHITE; }
    @Override public Color getButtonBackground()    { return new Color(220, 220, 220); }
    @Override public Color getButtonForeground()    { return Color.BLACK; }
}
