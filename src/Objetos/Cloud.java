package Objetos;

import java.awt.Image;
import java.util.Random;

public class Cloud extends Object {
    private Image cloudImage;
    public int x;
    public int y;

    public Cloud(int x, int y, int width, int height, Image cloudImage) {
        super(x, y, width, height, cloudImage);
        this.cloudImage = cloudImage;

        Random random = new Random();
        this.setX(random.nextInt(800) + 800);  // Ajusta según el tamaño de tu ventana
        this.setY(450);  // Ajusta según tus necesidades
    }

    

}
