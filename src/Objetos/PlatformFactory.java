package Objetos;

import java.awt.Image;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.ImageIcon;

public class PlatformFactory {
    private static List<Platform> platformCache = new ArrayList<>();

    public static Platform getRandomPlatform(int x, int y, int width, int height) {
        if (platformCache.isEmpty()) {
            // Carga inicial de diferentes tipos de plataformas
            Image platafoImage = new ImageIcon("sprite\\plataforma.png").getImage();
            Platform plataform = new Platform(0, 0, 0, 0, platafoImage);
            return new Platform(x, y, width, height, platafoImage);
           
            
            // Agrega más tipos de plataformas según sea necesario
        }

        int randomIndex = new Random().nextInt(platformCache.size());
        try {
            Platform clonedPlatform = platformCache.get(randomIndex).clone();
            clonedPlatform.setPlatformX(x);
            clonedPlatform.setPlatformY(y);
            clonedPlatform.setPlatformWidth(width);
            clonedPlatform.setPlatformHeight(height);
            return clonedPlatform;
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }
}
