package controller;

import model.GameModel;
import view.MainView;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Controller {
    private GameModel modele;
    private MainView vue;

    public Controller(GameModel modele, MainView vue) {
        this.modele = modele;
        this.vue = vue;

        this.vue.getZoneDessin().addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                System.out.println("Click " + e.getX() + " " + e.getY());
            }
        });

        this.vue.getBarreOutils().getBtnCercle().addActionListener(e -> {
            System.out.println("Cercle");
        });

        this.vue.getBarreOutils().getBtnRectangle().addActionListener(e -> {
            System.out.println("Rectangle");
        });

        this.vue.getBarreOutils().getBtnUndo().addActionListener(e -> {
            System.out.println("Undo");
        });

        this.vue.getBarreOutils().getBtnRedo().addActionListener(e -> {
            System.out.println("Redo");
        });
    }
}
