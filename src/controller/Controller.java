package controller;

import model.*;
import view.MainView;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JToggleButton;

public class Controller {
    private GameModel model;
    private MainView view;
    private ControllerState currentState;

    public Controller(GameModel model, MainView view) {
        this.model = model;
        this.view = view;
        this.currentState = new DrawState(model, view);

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

        this.view.getToolbar().getBtnCircle().addActionListener(e -> model.setCurrentShapeType(ShapeType.CIRCLE));
        this.view.getToolbar().getBtnRectangle().addActionListener(e -> model.setCurrentShapeType(ShapeType.RECTANGLE));

        this.view.getToolbar().getBtnDraw().addActionListener(e -> {
            if (((JToggleButton) e.getSource()).isSelected()) setState(new DrawState(model, view));
        });
        this.view.getToolbar().getBtnMove().addActionListener(e -> {
            if (((JToggleButton) e.getSource()).isSelected()) setState(new TranslateState(model, view));
        });
        this.view.getToolbar().getBtnScale().addActionListener(e -> {
            if (((JToggleButton) e.getSource()).isSelected()) setState(new ScaleState(model, view));
        });
        this.view.getToolbar().getBtnRemove().addActionListener(e -> {
            if (((JToggleButton) e.getSource()).isSelected()) setState(new RemoveState(model, view));
        });

        this.view.getToolbar().getBtnUndo().addActionListener(e -> System.out.println("Undo"));
        this.view.getToolbar().getBtnRedo().addActionListener(e -> System.out.println("Redo"));
    }

    private void setState(ControllerState newState) {
        view.getDrawingCanvas().clearGizmo();
        view.getDrawingCanvas().setPreviewShape(null, true);
        currentState = newState;
    }
}
