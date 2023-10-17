package Objetos;

import java.awt.Color;
import java.util.Random;

public class Cloud extends Object {
    private int height;
    private int width;
    private int x;
    private int y;
    private Color color;
    public Cloud(int x, int y, int width, int height) {
        super(x, y, width, height);
        setColor(Color.WHITE);  // Establecer el color de la nube a blanco
        this.height = 100;
        this.width = 500;
        this.x = 200;
        this.y = 400;

        Random random = new Random();
        this.x = random.nextInt(800) + 800; // Ajusta según el tamaño de tu ventana
        this.y = 450;
    }
   
}