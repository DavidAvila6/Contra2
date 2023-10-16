package Objetos;

import java.util.HashMap;
import java.util.Map;

public class TreeFactory {
    private static Map<String, Tree> treeCache = new HashMap<>();

    public static Tree getTree(String type) {
        Tree cachedTree = treeCache.get(type);
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
}
