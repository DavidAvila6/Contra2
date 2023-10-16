package Objetos;

// SpecialObject.java
import java.awt.Color;
import java.awt.Graphics;

public class SpecialObject {
    private int x;
    private int y;
    private int width;
    private int height;
    private Color color;

    public SpecialObject(int x, int y, int width, int height, Color color) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = color;
    }

    public void draw(Graphics g) {
        g.setColor(color);
        g.fillRect(x, y, width, height);
    }

    // Otros métodos y propiedades según sea necesario
}

