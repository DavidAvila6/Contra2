package Objetos;

import java.awt.Color;

public class Platform {
    private int platformX;
    private int platformY;
    private int platformWidth;
    private int platformHeight;
    private Color platformColor;  // Nuevo campo para almacenar el color

    public Platform(int x, int y, int width, int height, Color color) {
        this.platformX = x;
        this.platformY = y;
        this.platformWidth = width;
        this.platformHeight = height;
        this.platformColor = color;
    }

    // Getters
    public int getPlatformX() {
        return platformX;
    }

    public int getPlatformY() {
        return platformY;
    }

    public int getPlatformWidth() {
        return platformWidth;
    }

    public int getPlatformHeight() {
        return platformHeight;
    }

    public Color getPlatformColor() {
        return platformColor;
    }

    // Setters
    public void setPlatformX(int platformX) {
        this.platformX = platformX;
    }

    public void setPlatformY(int platformY) {
        this.platformY = platformY;
    }

    public void setPlatformWidth(int platformWidth) {
        this.platformWidth = platformWidth;
    }

    public void setPlatformHeight(int platformHeight) {
        this.platformHeight = platformHeight;
    }

    public void setPlatformColor(Color platformColor) {
        this.platformColor = platformColor;
    }
}
