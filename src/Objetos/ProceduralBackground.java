package Objetos;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;

public class ProceduralBackground {
    private Image backgroundImage;

    public ProceduralBackground(String imagePath) {
        // Cargar la imagen de fondo desde el archivo
        loadImage(imagePath);
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

