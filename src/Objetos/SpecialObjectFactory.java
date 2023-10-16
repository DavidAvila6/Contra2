package Objetos;

// SpecialObjectFactory.java
import java.awt.Color;
import java.util.Random;

public class SpecialObjectFactory {
    public static SpecialObject createSpecialObject(int x, int y, int width, int height) {
        Random random = new Random();
        int numObjectTypes = 3; // o el número que necesites
        int objectType = random.nextInt(numObjectTypes); // Cambia el número según la cantidad de tipos de objetos
                                                         // especiales

        switch (objectType) {
            case 0:
                return new SpecialObject(x, y, width, height, Color.BLUE);
            case 1:
                return new SpecialObject(x, y, width, height, Color.YELLOW);
            case 2:
                return new SpecialObject(x, y, width, height, Color.GREEN);
            // Agrega más casos según sea necesario para otros tipos de objetos especiales
            default:
                return null;
        }

    }
}
