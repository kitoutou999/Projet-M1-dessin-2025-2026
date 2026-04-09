package plugin.theme;

import model.Observable;

/**
 * Gestionnaire du thème actif.
 * Étend Observable (pattern déjà en place dans le projet) :
 * les vues s'y abonnent et se repeignent à chaque changement de thème.
 */
public class ThemeManager extends Observable {
    private ColorScheme currentScheme = new LightTheme();

    public ColorScheme getScheme() {
        return currentScheme;
    }

    public void setScheme(ColorScheme scheme) {
        this.currentScheme = scheme;
        notifyObservers();
    }

}
