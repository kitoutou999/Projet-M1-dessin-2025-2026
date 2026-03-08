package controller;

import model.GameModel;
import view.MainView;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Controller {
    private GameModel model;
    private MainView view;

    public Controller(GameModel model, MainView view) {
        this.model = model;
        this.view = view;

        this.view.getDrawingCanvas().addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                System.out.println("Click " + e.getX() + " " + e.getY());
            }
        });

        this.view.getToolbar().getBtnCircle().addActionListener(e -> {
            System.out.println("Cercle");
        });

        this.view.getToolbar().getBtnRectangle().addActionListener(e -> {
            System.out.println("Rectangle");
        });

        this.view.getToolbar().getBtnUndo().addActionListener(e -> {
            System.out.println("Undo");
        });

        this.view.getToolbar().getBtnRedo().addActionListener(e -> {
            System.out.println("Redo");
        });
    }
}
