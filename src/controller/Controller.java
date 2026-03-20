package controller;

import model.*;
import view.MainView;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JToggleButton;

public class Controller {
    private GameModel model;
    private MainView view;
    private Point firstClickPoint = null;
    private Shape selectedShape = null;
    private Point clickPoint = null;
    private Point draggedGizmoCorner = null;
    private Point fixedGizmoCorner = null;


    public Controller(GameModel model, MainView view) {
        this.model = model;
        this.view = view;


        this.view.getDrawingCanvas().addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();
                clickPoint = new Point(x, y);

                if(model.getCurrentMode() == Mode.DRAW) {


                    if (firstClickPoint == null) {
                        firstClickPoint = clickPoint;
                        System.out.println("FirstClick NULL");
                    } else {
                        Point secondClickPoint = clickPoint;

                        if (model.getCurrentShapeType() == ShapeType.CIRCLE) {
                            int rayon = (int) Math.sqrt(Math.pow(secondClickPoint.getX() - firstClickPoint.getX(), 2) + Math.pow(secondClickPoint.getY() - firstClickPoint.getY(), 2));
                            model.addBlueShape(new Circle(firstClickPoint, rayon),view);
                        } else if (model.getCurrentShapeType() == ShapeType.RECTANGLE) {
                            model.addBlueShape(new Rectangle(firstClickPoint, secondClickPoint),view);
                        }

                        firstClickPoint = null;
                        view.getDrawingCanvas().setPreviewShape(null, true);
                        System.out.println("FirstClick PAS NULL");
                    }
                }else if(model.getCurrentMode() == Mode.TRANSLATE) {
                    Shape shapeToMove = model.getShapeAt(clickPoint);
                    if (shapeToMove != null) {
                        selectedShape = shapeToMove;
                    }
                } else if (model.getCurrentMode() == Mode.SCALE) {
                    if (selectedShape instanceof Rectangle) {
                        int idx = view.getDrawingCanvas().getGizmoIndexAt(x, y);
                        if (idx != -1) {
                            draggedGizmoCorner = view.getDrawingCanvas().getGizmoCorner(idx);
                            fixedGizmoCorner = view.getDrawingCanvas().getGizmoCorner(3 - idx);
                            return;
                        }
                        selectedShape = null;
                        view.getDrawingCanvas().clearGizmo();
                    } else if (selectedShape instanceof Circle) {
                        if (view.getDrawingCanvas().isCircleGizmoAt(x, y)) {
                            draggedGizmoCorner = ((Circle) selectedShape).getCenter();
                            return;
                        }
                        selectedShape = null;
                        view.getDrawingCanvas().clearGizmo();
                    }
                    Shape shapeToScale = model.getShapeAt(clickPoint);
                    if (shapeToScale instanceof Rectangle) {
                        selectedShape = shapeToScale;
                        view.getDrawingCanvas().createGizmo(shapeToScale);
                    } else if (shapeToScale instanceof Circle) {
                        selectedShape = shapeToScale;
                        view.getDrawingCanvas().createGizmo(shapeToScale);
                    }
                } else if (model.getCurrentMode() == Mode.REMOVE) {
                    Shape shapeToRemove = model.getShapeAt(clickPoint);
                    if (shapeToRemove != null) {
                        if (shapeToRemove.equals(selectedShape)) {
                            selectedShape = null;
                            view.getDrawingCanvas().clearGizmo();
                        }
                        model.removeBlueShape(shapeToRemove);
                    }

                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if(model.getCurrentMode() == Mode.TRANSLATE) {
                    if (selectedShape != null) {
                        int x = e.getX();
                        int y = e.getY();
                        Point releasePoint = new Point(x, y);
                        int dx = releasePoint.getX() - clickPoint.getX();
                        int dy = releasePoint.getY() - clickPoint.getY();
                        model.translateShape(selectedShape, dx, dy);
                        selectedShape = null;
                        view.getDrawingCanvas().setPreviewShape(null, true);
                    }

                } else if (model.getCurrentMode() == Mode.SCALE) {
                    if (draggedGizmoCorner != null && selectedShape instanceof Rectangle) {
                        Point releasePoint = new Point(e.getX(), e.getY());
                        model.resizeRectangle((Rectangle) selectedShape, fixedGizmoCorner, releasePoint);
                        view.getDrawingCanvas().createGizmo(selectedShape);
                        view.getDrawingCanvas().setPreviewShape(null, true);
                        draggedGizmoCorner = null;
                        fixedGizmoCorner = null;
                    } else if (draggedGizmoCorner != null && selectedShape instanceof Circle) {
                        Circle c = (Circle) selectedShape;
                        model.resizeCircle(c, c.getCenter(), new Point(e.getX(), e.getY()));
                        view.getDrawingCanvas().createGizmo(selectedShape);
                        view.getDrawingCanvas().setPreviewShape(null, true);
                        draggedGizmoCorner = null;
                    }
                }
            }

        });
        this.view.getDrawingCanvas().addMouseMotionListener( new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();
                Point currentPoint = new Point(x, y);
                if (model.getCurrentMode() == Mode.TRANSLATE) {
                    if (selectedShape != null) {
                        int dx = currentPoint.getX() - clickPoint.getX();
                        int dy = currentPoint.getY() - clickPoint.getY();
                        Shape previewShape;
                        if (selectedShape instanceof Circle) {
                            Circle c = (Circle) selectedShape;
                            previewShape = new Circle(new Point(c.getCenter().getX() + dx, c.getCenter().getY() + dy), c.getRadius());
                        } else if (selectedShape instanceof Rectangle) {
                            Rectangle r = (Rectangle) selectedShape;
                            previewShape = new Rectangle(new Point(r.getStart().getX() + dx, r.getStart().getY() + dy), new Point(r.getEnd().getX() + dx, r.getEnd().getY() + dy));
                        } else {
                            return;
                        }
                        view.getDrawingCanvas().setPreviewShape(previewShape, !model.isIntersecting(previewShape, selectedShape));
                    }
                } else if (model.getCurrentMode() == Mode.SCALE) {
                    if (draggedGizmoCorner != null && selectedShape instanceof Rectangle) {
                        Rectangle preview = new Rectangle(fixedGizmoCorner, currentPoint);
                        view.getDrawingCanvas().setPreviewShape(preview, !model.isIntersecting(preview, selectedShape));
                        view.getDrawingCanvas().createGizmo(preview);
                    } else if (draggedGizmoCorner != null && selectedShape instanceof Circle) {
                        Circle c = (Circle) selectedShape;
                        int newRadius = (int) Math.sqrt(Math.pow(currentPoint.getX() - c.getCenter().getX(), 2) + Math.pow(currentPoint.getY() - c.getCenter().getY(), 2));
                        Circle preview = new Circle(c.getCenter(), newRadius);
                        view.getDrawingCanvas().setPreviewShape(preview, !model.isIntersecting(preview, selectedShape));
                        view.getDrawingCanvas().createGizmo(preview);
                    }
                }
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                if(model.getCurrentMode() == Mode.DRAW){
                    if (firstClickPoint != null) { //preview
                        int x = e.getX();
                        int y = e.getY();
                        Point currentPoint = new Point(x, y);
                        if (model.getCurrentShapeType() == ShapeType.CIRCLE) {
                            int rayon = (int) Math.sqrt(Math.pow(currentPoint.getX() - firstClickPoint.getX(), 2) + Math.pow(currentPoint.getY() - firstClickPoint.getY(), 2));
                            Circle previewCircle = new Circle(firstClickPoint, rayon);
                            view.getDrawingCanvas().setPreviewShape(previewCircle, !model.isIntersecting(previewCircle));
                        } else if (model.getCurrentShapeType() == ShapeType.RECTANGLE) {
                            Rectangle previewRectangle = new Rectangle(firstClickPoint, currentPoint);
                            view.getDrawingCanvas().setPreviewShape(previewRectangle, !model.isIntersecting(previewRectangle));
                        }
                    }
                }

            }
        });

        this.view.getToolbar().getBtnCircle().addActionListener(e -> {
            model.setCurrentShapeType(ShapeType.CIRCLE);
            System.out.println("Mode : Création de Cercle");
        });

        this.view.getToolbar().getBtnRectangle().addActionListener(e -> {
            model.setCurrentShapeType(ShapeType.RECTANGLE);
            System.out.println("Mode : Création de Rectangle");
        });

        this.view.getToolbar().getBtnDraw().addActionListener(e -> {
            JToggleButton btn = (JToggleButton) e.getSource();
            if (btn.isSelected()) {
                resetSelection();
                model.setCurrentMode(Mode.DRAW);
                System.out.println("Mode : Draw");
            }
        });
        this.view.getToolbar().getBtnMove().addActionListener(e -> {
            JToggleButton btn = (JToggleButton) e.getSource();
            if (btn.isSelected()) {
                resetSelection();
                model.setCurrentMode(Mode.TRANSLATE);
                System.out.println("Mode : Move");
            }
        });
        this.view.getToolbar().getBtnScale().addActionListener(e -> {
            JToggleButton btn = (JToggleButton) e.getSource();
            if (btn.isSelected()) {
                resetSelection();
                model.setCurrentMode(Mode.SCALE);
                System.out.println("Mode : Scale");
            }
        });
        this.view.getToolbar().getBtnRemove().addActionListener(e -> {
            JToggleButton btn = (JToggleButton) e.getSource();
            if (btn.isSelected()) {
                resetSelection();
                model.setCurrentMode(Mode.REMOVE);
                System.out.println("Mode : Remove");
            }
        });

        this.view.getToolbar().getBtnUndo().addActionListener(e -> {

            System.out.println("Undo");
        });

        this.view.getToolbar().getBtnRedo().addActionListener(e -> {
            System.out.println("Redo");
        });
    }

    private void resetSelection() {
        selectedShape = null;
        draggedGizmoCorner = null;
        fixedGizmoCorner = null;
        view.getDrawingCanvas().clearGizmo();
        view.getDrawingCanvas().setPreviewShape(null, true);
    }
}
