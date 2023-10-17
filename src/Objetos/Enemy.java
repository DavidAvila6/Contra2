package Objetos;

import java.awt.Color;
import java.awt.Graphics;

import modelo.GameObject;

public class Enemy extends GameObject {
    private int enemySpeedX; // Nueva propiedad para la velocidad horizontal
    private int enemySpeedY; // Nueva propiedad para la velocidad vertical
    private long timeSinceDirectionChange; // Tiempo del último cambio de dirección
    private static final long TIME_TO_CHANGE_DIRECTION = 2000; // 2000 milisegundos (2 segundos)

    public Enemy(int x, int y, int width, int height, Color color, int speedX, int speedY) {
        super(x, y, width, height, color);
        this.enemySpeedX = speedX;
        this.enemySpeedY = speedY;
        this.timeSinceDirectionChange = System.currentTimeMillis();
    }

    // Getters y setters de las propiedades específicas de Enemy

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
        g.setColor(getColor());
        g.fillRect(getX(), getY(), getWidth(), getHeight());
    }

    @Override
    public Enemy clone() throws CloneNotSupportedException {
        try {
            // Copiar primitivos y objetos inmutables directamente
            Enemy clonedEnemy = (Enemy) super.clone();
            // Si tienes objetos mutables, clónalos también
            clonedEnemy.setColor(new Color(this.getColor().getRGB())); // Copiar Color

            return clonedEnemy;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(); // Esto no debería ocurrir
        }
    }
}
