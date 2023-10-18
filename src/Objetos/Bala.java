package Objetos;

import java.awt.Image;
import javax.swing.ImageIcon;

public class Bala extends Object {

    private Image balaImage;
    public int x;
    public int y;
    public int width;
    public int height;

    public Bala(int x, int y, int width, int height, Image balaImage) {
        super(x, y, width, height, balaImage);
        this.balaImage = balaImage;
    }

    public void mover(int velocidad, int caidaAjuste) {
        // Mueve la bala hacia la derecha (o en la dirección que desees)
        setX(getX() + velocidad);
        if (getX() > 100) {
            setY(getY() + 1);
            if (getX() > 250) {
                setY(getY() + 1);
                if (getX() > 400) {
                    setY(getY() + 1);
                }
            }
        }
    }

    // Métodos Getter y Setter para acceder a las propiedades de la bala

    public Image getBalaImage() {
        return balaImage;
    }

    public void setBalaImage(Image balaImage) {
        this.balaImage = balaImage;
    }

    // Métodos Getter y Setter para las variables de instancia

    
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
}
