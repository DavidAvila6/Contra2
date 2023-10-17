package Objetos;
// ObjectFactory.javapackage Objetos;

import java.util.HashMap;
import java.util.Map;

public class ObjectFactory {
    private static Map<String, Object> treeCache = new HashMap<>();

    public static Object getTree(String type) {
        Object cachedTree = treeCache.get(type);
        if (cachedTree != null) {
            try {
                return cachedTree.clone();
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static void loadCache() {
        // Carga inicial de diferentes tipos de árboles en el cache
        OakTree oakTree = new OakTree(0, 0, 0, 0);
        treeCache.put("oak", oakTree);

        // Agrega más tipos de árboles según sea necesario
    }

    // Nueva fábrica para crear nubes
    public static Cloud createCloud(int x, int y, int width, int height) {
        return new Cloud(x, y, width, height);
    }

    // Agrega más fábricas según sea necesario para otros tipos de objetos especiales
}
