package plugin;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;
import java.util.jar.JarFile;
import java.util.jar.Manifest;

/**
 * Charge dynamiquement les plug-ins depuis un répertoire.
 *
 * Principe (cours CM7) :
 *  1. Scanner le répertoire "plugins/" à la recherche de fichiers .jar
 *  2. Pour chaque JAR : créer un URLClassLoader branché sur ce jar
 *  3. Lire l'attribut "Plugin-Class" dans le Manifest du jar
 *  4. Instancier la classe via Class.newInstance() et la caster en PluginDessin
 */
public class PluginLoader {

    private static final String PLUGIN_DIR = "plugins";
    private static final String MANIFEST_KEY = "Plugin-Class";

    /**
     * Charge tous les plug-ins dans le répertoire plugins/.
     * Retourne une liste vide si le répertoire n'existe pas ou est vide.
     */
    public List<PluginDessin> loadAll() {
        List<PluginDessin> plugins = new ArrayList<>();
        File dir = new File(PLUGIN_DIR);

        if (!dir.exists() || !dir.isDirectory()) return plugins;

        File[] jars = dir.listFiles(f -> f.getName().endsWith(".jar"));
        if (jars == null) return plugins;

        for (File jar : jars) {
            PluginDessin plugin = load(jar);
            if (plugin != null) plugins.add(plugin);
        }
        return plugins;
    }

    /**
     * Charge un plug-in depuis un fichier JAR.
     * Lit le nom de classe dans le Manifest, l'instancie via le ClassLoader.
     */
    private PluginDessin load(File jarFile) {
        try {
            return loadJarPlugIn(jarFile);
        } catch (PlugInLoadingException e) {
            System.out.println("Le plug-in du fichier " + jarFile + " n'a pu être chargé, voir exception ci-dessous");
            e.printStackTrace();
            return null;
        }
    }

    public PluginDessin loadJarPlugIn(File file) throws PlugInLoadingException {
        try {
            JarFile jar = new JarFile(file);
            String className = jar.getManifest().getMainAttributes().getValue(MANIFEST_KEY);
            jar.close();

            URL[] jarURLs = { file.toURI().toURL() };
            ClassLoader classLoader = new URLClassLoader(jarURLs, getClass().getClassLoader());

            Class<?> pluginClass = classLoader.loadClass(className);
            return (PluginDessin) pluginClass.newInstance();

        } catch (IOException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            throw new PlugInLoadingException(e);
        }
    }
}
