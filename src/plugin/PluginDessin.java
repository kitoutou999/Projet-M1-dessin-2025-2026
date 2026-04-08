package plugin;

import javax.swing.JPanel;

/**
 * Interface de plugin(general).
 */
public interface PluginDessin {
    /**
     * Initialise le plug-in avec le contexte de l'application.
     */
    void initData(AppContext ctx);

    /**
     * Renvoie le panneau graphique fourni par le plug-in,
     */
    JPanel getPanel();
}
