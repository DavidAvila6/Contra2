package Objetos;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class ProceduralBackground {
    private Image backgroundImage;
    private int backgroundWidth;
    private int backgroundHeight;
    private int tileSize = 300;  // Ajusta el tamaño de cada "tile" del fondo

    public ProceduralBackground(int width, int height) {
        backgroundWidth = width;
        backgroundHeight = height;
        generateBackground();
    }

    private void generateBackground() {
        // Crear una imagen de fondo procedural
        backgroundImage = new BufferedImage(backgroundWidth, backgroundHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = (Graphics2D) backgroundImage.getGraphics();

        Random random = new Random();

        // Generar colores aleatorios para las partes del fondo
        for (int x = 0; x < backgroundWidth; x += tileSize) {
            for (int y = 0; y < backgroundHeight; y += tileSize) {
                int red = random.nextInt(256);
                int green = random.nextInt(256);
                int blue = random.nextInt(256);
                g.setColor(new Color(red, green, blue));
                g.fillRect(x, y, tileSize, tileSize);
            }
        }
    }

    public Image getBackgroundImage() {
        return backgroundImage;
    }
}
