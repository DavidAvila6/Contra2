package Objetos;

import java.awt.Graphics;
import java.awt.Image;

import modelo.GameObject;

public class Platform  extends GameObject {
    private Image Image;
    private int platformX;
    private int platformY;
    private int platformWidth;
    private int platformHeight;

    public Platform(int x, int y, int width, int height, Image Image) {
        super(x, y, width, height, Image);
        this.platformX = x;
        this.platformY = y;
        this.platformWidth = width;
        this.platformHeight = height;
        this.Image = Image;
    }

    // Implementación de métodos de GameObject
      public Image getImage() {
        return Image;
    }

    public void setImage(Image image) {
        Image = image;
    }

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
    public void draw(Graphics g) {
        // Dibuja la imagen del enemigo en lugar de un rectángulo de color
        g.drawImage(getImage(), getX(), getY(), getWidth(), getHeight(), null);
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

    @Override
    public Platform clone() throws CloneNotSupportedException {
        return (Platform) super.clone();
    }

  
}
