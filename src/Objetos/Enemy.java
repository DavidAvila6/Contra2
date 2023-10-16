package Objetos;

import java.awt.Color;

public class Enemy implements Cloneable {
    private int enemyX;
    private int enemyY;
    private int enemyWidth;
    private int enemyHeight;
    private Color enemyColor;
    private int enemySpeedX; // Nueva propiedad para la velocidad horizontal
    private int enemySpeedY; // Nueva propiedad para la velocidad vertical
    private long timeSinceDirectionChange; // Tiempo del último cambio de dirección
    private static final long TIME_TO_CHANGE_DIRECTION = 2000; // 2000 milisegundos (2 segundos)

    public Enemy(int x, int y, int width, int height, Color color, int speedX, int speedY) {
        this.enemyX = x;
        this.enemyY = y;
        this.enemyWidth = width;
        this.enemyHeight = height;
        this.enemyColor = color;
        this.enemySpeedX = speedX;
        this.enemySpeedY = speedY;
        this.timeSinceDirectionChange = System.currentTimeMillis();
    }

    // Getters
    public int getEnemyX() {
        return enemyX;
    }

    public int getEnemyY() {
        return enemyY;
    }

    public int getEnemyWidth() {
        return enemyWidth;
    }

    public int getEnemyHeight() {
        return enemyHeight;
    }

    public Color getEnemyColor() {
        return enemyColor;
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

    // Setters
    public void setEnemyX(int enemyX) {
        this.enemyX = enemyX;
    }

    public void setEnemyY(int enemyY) {
        this.enemyY = enemyY;
    }

    public void setEnemyWidth(int enemyWidth) {
        this.enemyWidth = enemyWidth;
    }

    public void setEnemyHeight(int enemyHeight) {
        this.enemyHeight = enemyHeight;
    }

    public void setEnemyColor(Color enemyColor) {
        this.enemyColor = enemyColor;
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

    @Override
    public Enemy clone() throws CloneNotSupportedException {
        try {
            // Copiar primitivos y objetos inmutables directamente
            Enemy clonedEnemy = (Enemy) super.clone();
            // Si tienes objetos mutables, clónalos también
            clonedEnemy.enemyColor = new Color(this.enemyColor.getRGB()); // Copiar Color

            return clonedEnemy;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(); // Esto no debería ocurrir
        }
    }
}
