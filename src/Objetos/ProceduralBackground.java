package Objetos;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class ProceduralBackground {
    private Image backgroundImage;
    private int backgroundWidth;
    private int backgroundHeight;
    private int tileSize = 200;  // Ajusta el tamaño de cada "tile" del fondo
    private int backgroundOffsetX = 0;  // Nuevo: rastrea el desplazamiento del fondo

    public ProceduralBackground(int width, int height) {
        backgroundWidth = width;
        backgroundHeight = height;
        generateBackground();
    }

    private void generateBackground() {
        // Carga una imagen de sprite
        ImageIcon spriteIcon = new ImageIcon("src/sprite/bg.jpg");
        Image sprite = spriteIcon.getImage();
        int spriteWidth = spriteIcon.getIconWidth();
        int spriteHeight = spriteIcon.getIconHeight();

        // Crea una imagen de fondo procedural
        backgroundImage = new BufferedImage(backgroundWidth, backgroundHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = (Graphics2D) backgroundImage.getGraphics();

        // Repite el sprite en bucle a lo largo de la anchura del fondo
        for (int x = 0; x < backgroundWidth; x += spriteWidth) {
            for (int y = 0; y < backgroundHeight; y += spriteHeight) {
                g.drawImage(sprite, x, y, null);
            }
        }
    }

    public Image getBackgroundImage() {
        // Calcula la parte visible del fondo según el desplazamiento
        return ((BufferedImage) backgroundImage).getSubimage(backgroundOffsetX, 0, backgroundWidth, backgroundHeight);
    }

    public void update() {
        // Actualiza el desplazamiento del fondo (por ejemplo, hacia la derecha)
        backgroundOffsetX += 2; // Ajusta la velocidad según tus necesidades

        // Si el fondo llega al final, reinícialo
        if (backgroundOffsetX >= backgroundWidth) {
            backgroundOffsetX = 0;
        }
    }
}
