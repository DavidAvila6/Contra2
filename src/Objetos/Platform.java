package Objetos;

import java.awt.Color;
import java.awt.Graphics;

import modelo.GameObject;

public class Platform  extends GameObject {
    private int platformX;
    private int platformY;
    private int platformWidth;
    private int platformHeight;
    private Color platformColor;

    public Platform(int x, int y, int width, int height, Color color) {
        super(x, y, width, height, color);
        this.platformX = x;
        this.platformY = y;
        this.platformWidth = width;
        this.platformHeight = height;
        this.platformColor = color;
    }

    // Implementación de métodos de GameObject

    @Override
    public int getX() {
        return platformX;
    }

    @Override
    public int getY() {
        return platformY;
    }

    @Override
    public int getWidth() {
        return platformWidth;
    }

    @Override
    public int getHeight() {
        return platformHeight;
    }

    @Override
    public Color getColor() {
        return platformColor;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(getColor());
        g.fillRect(getX(), getY(), getWidth(), getHeight());
    }

    
    public boolean collidesWith(GameObject otherObject) {
        // Lógica de colisión específica para Platform
        return getX() < otherObject.getX() + otherObject.getWidth() &&
               getX() + getWidth() > otherObject.getX() &&
               getY() < otherObject.getY() + otherObject.getHeight() &&
               getY() + getHeight() > otherObject.getY();
    }

    // Métodos Getter y Setter

    public int getPlatformX() {
        return platformX;
    }

    public void setPlatformX(int platformX) {
        this.platformX = platformX;
    }

    public int getPlatformY() {
        return platformY;
    }

    public void setPlatformY(int platformY) {
        this.platformY = platformY;
    }

    public int getPlatformWidth() {
        return platformWidth;
    }

    public void setPlatformWidth(int platformWidth) {
        this.platformWidth = platformWidth;
    }

    public int getPlatformHeight() {
        return platformHeight;
    }

    public void setPlatformHeight(int platformHeight) {
        this.platformHeight = platformHeight;
    }

    public Color getPlatformColor() {
        return platformColor;
    }

    public void setPlatformColor(Color platformColor) {
        this.platformColor = platformColor;
    }

    @Override
    public Platform clone() throws CloneNotSupportedException {
        return (Platform) super.clone();
    }
}
