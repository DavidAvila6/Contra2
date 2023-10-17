package Objetos;
// ObjectFactory.javapackage Objetos;

import java.util.HashMap;
import java.util.Map;

public class ObjectFactory {
    private static Map<String, Object> ObjectCache = new HashMap<>();

    public static Object getObject(String type) {
        Object cachedObject = ObjectCache.get(type);
        if (cachedObject != null) {
            try {
                return cachedObject.clone();
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static void loadCache() {
        // Carga inicial de diferentes tipos de árboles en el cache
        OakTree oakObject = new OakTree(0, 0, 0, 0);
        ObjectCache.put("oak", oakObject);

        // Agrega más tipos de árboles según sea necesario
    }

    // Nueva fábrica para crear nubes
    public static Cloud createCloud(int x, int y, int width, int height) {
        return new Cloud(x, y, width, height);
    }

    // Agrega más fábricas según sea necesario para otros tipos de objetos especiales
}
