package Objetos;

import java.awt.Graphics;
import java.awt.Image;

public class OakTree extends Object {
    private Image Image;
    
    public int x;
    public int y;
    public int width;
    public int height;
    public OakTree(int X, int Y, int Width, int Height, Image Image) {
        // Constructor con parámetros
        super(X, Y, Width, Height, Image);
        this.Image = Image;
    }

    public Image getImage() {
        return Image;
    }

    public void setImage(Image Image) {
        this.Image = Image;
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

    
    public int getWidth() {
        return width;
    }

    
    public void setWidth(int width) {
        this.width = width;
    }

    
    public int getHeight() {
        return height;
    }

    
    public void setHeight(int height) {
        this.height = height;
    }

    public void draw(Graphics g) {
        // Dibuja la imagen del árbol en lugar de un rectángulo
        g.drawImage(Image, getX(), getY(), getWidth(), getHeight(), null);
    }

}
