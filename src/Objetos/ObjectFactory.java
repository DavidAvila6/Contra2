package Objetos;

import java.awt.Image;
import java.util.HashMap;
import java.util.Map;
import javax.swing.ImageIcon;

public class ObjectFactory {
    private static Map<String, Object> objectCache = new HashMap<>();

    public static Object getObject(String type) {
        Object cachedObject = objectCache.get(type);
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
        // Carga inicial de diferentes tipos de objetos en el cache

        Image oakImage = new ImageIcon("sprite\\bala.jpg").getImage();
        OakTree oakObject = new OakTree(0, 0, 0, 0, oakImage);
        objectCache.put("oak", oakObject);

        Image cloudImage = new ImageIcon("sprite\\pngwing.png").getImage();
        Cloud cloudObject = new Cloud(0, 0, 0, 0, cloudImage);
        objectCache.put("cloud", cloudObject);

        // Agrega más tipos de objetos según sea necesario
    }

    // Nueva fábrica para crear nubes
    public static Cloud createCloud(int x, int y, int width, int height) {
        Image cloudImage = new ImageIcon("sprite\\cloud.png").getImage();
        return new Cloud(x, y, width, height, cloudImage);
    }

    // Agrega más fábricas según sea necesario para otros tipos de objetos
    // especiales
}
