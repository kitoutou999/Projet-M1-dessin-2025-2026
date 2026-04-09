package controller;

import controller.states.*;
import model.*;
import model.shapes.CircleFactory;
import model.shapes.RectangleFactory;
import model.shapes.Shape;
import controller.commands.AddShapeCommand;
import view.MainView;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JToggleButton;

/**
 * Controleur principal du jeu en mode solo.
 * Gere les interactions souris et delegue les actions aux etats via le pattern State.
 */
public class Controller {
    private GameModel model;
    private MainView view;
    private ControllerState currentState;
    private CommandHandler handler;
    private HardModeTimer hardModeTimer;

    public Controller(MainView view, CommandHandler handler, HardModeTimer hardModeTimer) {
        this.model = view.getModel();
        this.view = view;
        this.handler = handler;
        this.hardModeTimer = hardModeTimer;
        this.currentState = new AddShapeState(model, view, handler, hardModeTimer);
        if (hardModeTimer != null) hardModeTimer.start();

        this.view.getDrawingCanvas().addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) { currentState.mousePressed(e); }
            @Override
            public void mouseReleased(MouseEvent e) { currentState.mouseReleased(e); }
        });

        this.view.getDrawingCanvas().addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) { currentState.mouseDragged(e); }
            @Override
            public void mouseMoved(MouseEvent e) { currentState.mouseMoved(e); }
        });

        this.view.getToolbar().getBtnCircle().addActionListener(e -> model.setShapeFactory(new CircleFactory()));
        this.view.getToolbar().getBtnRectangle().addActionListener(e -> model.setShapeFactory(new RectangleFactory()));

        this.view.getToolbar().getBtnDraw().addActionListener(e -> {
            if (((JToggleButton) e.getSource()).isSelected()) setState(new AddShapeState(model, view, handler, hardModeTimer));
        });
        this.view.getToolbar().getBtnMove().addActionListener(e -> {
            if (((JToggleButton) e.getSource()).isSelected()) setState(new TranslateState(model, view,handler));
        });
        this.view.getToolbar().getBtnScale().addActionListener(e -> {
            if (((JToggleButton) e.getSource()).isSelected()) setState(new ScaleState(model, view,handler));
        });
        this.view.getToolbar().getBtnRemove().addActionListener(e -> {
            if (((JToggleButton) e.getSource()).isSelected()) setState(new RemoveState(model, view,handler));
        });

        this.view.getToolbar().getBtnUndo().addActionListener(e -> handler.undo());
        this.view.getToolbar().getBtnRedo().addActionListener(e -> handler.redo());

        this.view.getToolbar().getBtnIa().setVisible(hardModeTimer == null);
        Ia ia = new Ia(model);
        this.view.getToolbar().getBtnIa().addActionListener(e -> {
            if (model.isGameOver()) return;
            Shape shape = ia.bestFromX(200);
            handler.executeCommand(new AddShapeCommand(shape, model));
            if (model.isGameOver()) {
                model.recordScore();
                if (model.isLastRound()) {
                    if (hardModeTimer != null) hardModeTimer.stop();
                    view.showFinalScoreDialog();
                } else {
                    view.showNextRoundDialog();
                    model.loadNextLevel();
                    if (hardModeTimer != null) hardModeTimer.start();
                }
            }
        });
    }

    private void setState(ControllerState newState) {
        view.getDrawingCanvas().clearGizmo();
        view.getDrawingCanvas().setPreviewShape(null, true);
        currentState = newState;
    }
}
