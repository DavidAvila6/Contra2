package modelo;

import java.awt.Rectangle;

public class CollisionHandler {
    // Método para verificar colisiones entre dos objetos rectangulares
    public static boolean checkCollision(Rectangle r1, Rectangle r2) {
        return r1.intersects(r2);
    }

    // Método para verificar colisiones entre el jugador y un objeto
    public static boolean playerCollidesWithObject(int playerX, int playerY, int playerWidth, int playerHeight,
            GameObject object) {
        Rectangle playerBounds = new Rectangle(playerX, playerY, playerWidth, playerHeight);
        Rectangle objectBounds = new Rectangle(object.getX(), object.getY(), object.getWidth(), object.getHeight());
        return checkCollision(playerBounds, objectBounds);
    }

    // Método para verificar colisiones entre dos objetos en el juego
    public static boolean objectsCollide(GameObject object1, GameObject object2) {
        Rectangle bounds1 = new Rectangle(object1.getX(), object1.getY(), object1.getWidth(), object1.getHeight());
        Rectangle bounds2 = new Rectangle(object2.getX(), object2.getY(), object2.getWidth(), object2.getHeight());
        return checkCollision(bounds1, bounds2);
    }

    // Otros métodos de colisión según sea necesario
}
