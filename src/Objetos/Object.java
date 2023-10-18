package Objetos;

import java.awt.Image;

public abstract class Object implements Cloneable {
    private int height;
    private int width;
    private int x;
    private int y;
    private Image image;  // Cambiado de String a Image

    public Object(int x, int y, int width, int height, Image image) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.image = image;
    }

    // Métodos getter y setter para image

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    // Resto de los métodos

    @Override
    public Object clone() throws CloneNotSupportedException {
        return (Object) super.clone();
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
