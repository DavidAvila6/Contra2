package Objetos;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

import modelo.GameObject;

public class SpecialObject extends GameObject {
    private String imagePath; // Nueva propiedad para la ruta de la imagen

    public SpecialObject(int x, int y, int width, int height, String imagePath) {
        super(x, y, width, height);
        this.imagePath = imagePath;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public void draw(Graphics g) {
        // Carga la imagen desde la ruta
        ImageIcon imageIcon = new ImageIcon(imagePath);
        Image image = imageIcon.getImage();

        // Dibuja la imagen del objeto especial
        g.drawImage(image, getX(), getY(), getWidth(), getHeight(), null);
    }
}
