package Objetos;
// SpecialObjectFactory.java
import java.awt.Color;
import java.util.Random;

public class SpecialObjectFactory {
    public static SpecialObject createSpecialObject(int x, int y, int width, int height) {
        Random random = new Random();
        int objectType = random.nextInt(2); // Cambia el número según la cantidad de tipos de objetos especiales

        switch (objectType) {
            case 0:
                return new SpecialObject(x, y, width, height, Color.BLUE);
            case 1:
                return new SpecialObject(x, y, width, height, Color.YELLOW);
            // Agrega más casos según sea necesario para otros tipos de objetos especiales
            default:
                return null;
        }
    }
}

