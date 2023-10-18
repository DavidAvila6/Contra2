package Objetos;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;

import modelo.GameObject;

public class Enemy extends GameObject {
    private int enemySpeedX;
    private int enemySpeedY;
    private long timeSinceDirectionChange;
    private static final long TIME_TO_CHANGE_DIRECTION = 2000;
    private Image enemyImage; // Nueva propiedad para la imagen del enemigo

    public Enemy(int x, int y, int width, int height, String imagePath, int speedX, int speedY) {
        super(x, y, width, height);
        this.enemySpeedX = speedX;
        this.enemySpeedY = speedY;
        this.timeSinceDirectionChange = System.currentTimeMillis();
        this.enemyImage = new ImageIcon(imagePath).getImage(); // Cargar la imagen desde la ruta
    }

    // Resto de los métodos y getters/setters según tus necesidades

    public Image getEnemyImage() {
        return enemyImage;
    }

    public void setEnemyImage(Image enemyImage) {
        this.enemyImage = enemyImage;
    }

    public int getEnemySpeedX() {
        return enemySpeedX;
    }

    public int getEnemySpeedY() {
        return enemySpeedY;
    }

    public long getTimeSinceDirectionChange() {
        return timeSinceDirectionChange;
    }

    public void setEnemySpeedX(int enemySpeedX) {
        this.enemySpeedX = enemySpeedX;
    }

    public void setEnemySpeedY(int enemySpeedY) {
        this.enemySpeedY = enemySpeedY;
    }

    public void setTimeSinceDirectionChange(long time) {
        this.timeSinceDirectionChange = time;
    }

    // Otros métodos según las necesidades

    // Método para invertir la dirección del enemigo
    public void reverseDirection() {
        this.enemySpeedX = -this.enemySpeedX;
    }

    // Método para manejar la lógica de colisiones con otros objetos (puedes ajustarlo según tus necesidades)
    public boolean collidesWith(GameObject otherObject) {
        // Lógica de colisiones específica según tus necesidades
        return getX() < otherObject.getX() + otherObject.getWidth() &&
               getX() + getWidth() > otherObject.getX() &&
               getY() < otherObject.getY() + otherObject.getHeight() &&
               getY() + getHeight() > otherObject.getY();
    }

    @Override
    public void draw(Graphics g) {
        // Dibuja la imagen del enemigo en lugar de un rectángulo de color
        g.drawImage(getEnemyImage(), getX(), getY(), getWidth(), getHeight(), null);
    }

    @Override
    public Enemy clone() throws CloneNotSupportedException {
        
            Enemy clonedEnemy = (Enemy) super.clone();
            // Si tienes objetos mutables, clónalos también
            if (getEnemyImage() != null) {
                clonedEnemy.setEnemyImage(getEnemyImage()); // Copiar la imagen
            }
            // Copiar otros campos si son mutables
    
            return clonedEnemy;
        
    }
}