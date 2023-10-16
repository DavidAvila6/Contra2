package Objetos;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class EnemyFactory {
    private static Map<String, Enemy> enemyCache = new HashMap<>();

    public static Enemy getEnemy(String type, int x, int y, int width, int height, Color color, int speedX, int speedY) {
        Enemy cachedEnemy = enemyCache.get(type);
        if (cachedEnemy != null) {
            try {
                // Si existe en el caché, clonar y ajustar la posición y velocidad
                Enemy newEnemy = cachedEnemy.clone();
                newEnemy.setEnemyX(x);
                newEnemy.setEnemyY(y);
                newEnemy.setEnemySpeedX(speedX);
                newEnemy.setEnemySpeedY(speedY);
                return newEnemy;
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
        }

        // Si no existe en el caché, crear uno nuevo y agregarlo al caché
        Enemy newEnemy = new Enemy(x, y, width, height, color, speedX, speedY);
        enemyCache.put(type, newEnemy);
        return newEnemy;
    }

    // Otros métodos según las necesidades
}
