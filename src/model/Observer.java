package model;

/**
 * Interface du pattern Observer.
 * Les classes qui implementent cette interface sont notifiees lors d'un changement dans un Observable.
 */
public interface Observer {
    void update();
}
