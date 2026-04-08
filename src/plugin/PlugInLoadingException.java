package plugin;

/**
 * Exception levee lors d'un echec de chargement d'un plugin.
 */
public class PlugInLoadingException extends Exception {

    public PlugInLoadingException(Exception e) {
        super(e);
    }
}
