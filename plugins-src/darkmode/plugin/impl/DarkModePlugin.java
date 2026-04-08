package plugin.impl;

import plugin.AppContext;
import plugin.PluginDessin;
import plugin.theme.DarkTheme;
import plugin.theme.LightTheme;

import javax.swing.*;
import java.awt.*;

/**
 * Plug-in Dark Mode.
 */
public class DarkModePlugin implements PluginDessin {

    private AppContext ctx;

    @Override
    public void initData(AppContext ctx) {
        this.ctx = ctx;
    }

    @Override
    public JPanel getPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 4, 4));
        panel.setOpaque(false);

        JToggleButton btnDark = new JToggleButton("Dark Mode");
        btnDark.setSelected(false);

        btnDark.addActionListener(e -> {
            if (btnDark.isSelected()) {
                ctx.getThemeManager().setScheme(new DarkTheme());
            } else {
                ctx.getThemeManager().setScheme(new LightTheme());
            }
        });

        panel.add(btnDark);
        return panel;
    }
}
