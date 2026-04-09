package controller.states;

import java.awt.event.MouseEvent;

/**
 * Interface du pattern State pour le controleur.
 * Chaque etat definit le comportement de la souris dans un mode d'interaction donne.
 */
public interface ControllerState {
    void mousePressed(MouseEvent e);
    void mouseReleased(MouseEvent e);
    void mouseDragged(MouseEvent e);
    void mouseMoved(MouseEvent e);
}
