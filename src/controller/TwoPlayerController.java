package controller;

import controller.states.*;
import model.*;
import model.shapes.Shape;
import model.shapes.CircleFactory;
import model.shapes.RectangleFactory;
import view.MainView;

import javax.swing.JToggleButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * Controleur du jeu en mode deux joueurs.
 * Gere l'alternance entre la phase de dessin et la phase de reproduction.
 */
public class TwoPlayerController implements Observer {

    private final GameModel model;
    private final MainView view;
    private final CommandHandler handler;
    private final TwoPlayerGame game;
    private ControllerState currentState;

    public TwoPlayerController(MainView view, CommandHandler handler, TwoPlayerGame game) {
        this.model = view.getModel();
        this.view = view;
        this.handler = handler;
        this.game = game;

        model.getBlueGroup().addObserver(this);
        setupMouseListeners();
        setupToolbarListeners();
        startDrawingPhase();
    }


    private void setupMouseListeners() {
        view.getDrawingCanvas().addMouseListener(new MouseAdapter() {
            @Override public void mousePressed(MouseEvent e)  { currentState.mousePressed(e); }
            @Override public void mouseReleased(MouseEvent e) { currentState.mouseReleased(e); }
        });
        view.getDrawingCanvas().addMouseMotionListener(new MouseAdapter() {
            @Override public void mouseDragged(MouseEvent e) { currentState.mouseDragged(e); }
            @Override public void mouseMoved(MouseEvent e)   { currentState.mouseMoved(e); }
        });
    }

    private void setupToolbarListeners() {
        view.getToolbar().getBtnCircle().addActionListener(e -> model.setShapeFactory(new CircleFactory()));
        view.getToolbar().getBtnRectangle().addActionListener(e -> model.setShapeFactory(new RectangleFactory()));

        view.getToolbar().getBtnDraw().addActionListener(e -> {
            if (((JToggleButton) e.getSource()).isSelected())
                setState(new TwoPlayerAddShapeState(model, view, handler));
        });
        view.getToolbar().getBtnMove().addActionListener(e -> {
            if (((JToggleButton) e.getSource()).isSelected())
                setState(new TranslateState(model, view, handler));
        });
        view.getToolbar().getBtnScale().addActionListener(e -> {
            if (((JToggleButton) e.getSource()).isSelected())
                setState(new ScaleState(model, view, handler));
        });
        view.getToolbar().getBtnRemove().addActionListener(e -> {
            if (((JToggleButton) e.getSource()).isSelected())
                setState(new RemoveState(model, view, handler));
        });

        view.getToolbar().getBtnUndo().addActionListener(e -> handler.undo());
        view.getToolbar().getBtnRedo().addActionListener(e -> handler.redo());
        view.getToolbar().getBtnTerminer().addActionListener(e -> onDrawingComplete());
    }


    private void startDrawingPhase() {
        model.resetBlueShapes();
        handler.reset();
        view.getDrawingCanvas().setShowShapeNumbers(true);
        setState(new TwoPlayerAddShapeState(model, view, handler));
        setEditToolsEnabled(true);
        view.getToolbar().showTerminerButton(true);
    }

    private void startReproducingPhase() {
        game.startReproducingPhase(model.getBlueShapes());
        model.resetBlueShapes();
        handler.reset();
        view.getDrawingCanvas().setShowShapeNumbers(false);
        setState(new TwoPlayerAddShapeState(model, view, handler));
        setEditToolsEnabled(false);
        view.getToolbar().showTerminerButton(false);
        refreshReproducingInfo();
    }

    private void onDrawingComplete() {
        if (model.getBlueShapes().isEmpty()) return;
        startReproducingPhase();
    }



    @Override
    public void update() {
        if (game.getPhase() != TwoPlayerGame.Phase.REPRODUCING) return;
        refreshReproducingInfo();
        if (model.getBlueShapes().size() >= game.getDrawnShapes().size()) onReproducingComplete();
    }

    private void onReproducingComplete() {
        model.getBlueGroup().removeObserver(this);

        int reproducer = game.getReproducer();
        List<Shape> reproduced = new ArrayList<>(model.getBlueShapes());
        float roundScore = ScoreCalculator.calculate(game.getDrawnShapes(), reproduced);
        game.recordRoundScore(reproduced);

        if (game.isLastRound()) {
            model.resetBlueShapes();
            view.showFinalTwoPlayerScoreDialog(game);
        } else {
            view.showRoundResultDialog(reproducer, roundScore, game.getCurrentRound());
            game.nextRound();
            startDrawingPhase();
        }

        model.getBlueGroup().addObserver(this);
    }




    private void refreshReproducingInfo() {
        view.getToolbar().updateTwoPlayerInfo(
            game.getCurrentRound() + "/" + TwoPlayerGame.ROUND_COUNT
        );
    }

    private void setEditToolsEnabled(boolean enabled) {
        view.getToolbar().getBtnMove().setEnabled(enabled);
        view.getToolbar().getBtnScale().setEnabled(enabled);
        view.getToolbar().getBtnRemove().setEnabled(enabled);
        view.getToolbar().getBtnUndo().setEnabled(enabled);
        view.getToolbar().getBtnRedo().setEnabled(enabled);
    }

    private void setState(ControllerState newState) {
        view.getDrawingCanvas().clearGizmo();
        view.getDrawingCanvas().setPreviewShape(null, true);
        currentState = newState;
    }
}
