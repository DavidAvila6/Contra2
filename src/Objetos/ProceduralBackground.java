package Objetos;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;

public class ProceduralBackground {
    private Image backgroundImage;

    public ProceduralBackground(String imagePath, int width, int height) {
        // Cargar la imagen original
        loadImage(imagePath);

        // Redimensionar la imagen para que coincida con las dimensiones del juego
        backgroundImage = backgroundImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
    }

    private void loadImage(String imagePath) {
        ImageIcon icon = new ImageIcon(imagePath);
        backgroundImage = icon.getImage();
    }

    public Image getBackgroundImage() {
        return backgroundImage;
    }

    public void draw(Graphics g, int x, int y) {
        g.drawImage(backgroundImage, x, y, null);
    }
}


