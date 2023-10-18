package Objetos;

import java.util.Random;

public class SpecialObjectFactory {
    public static SpecialObject createSpecialObject(int x, int y, int width, int height) {
        Random random = new Random();
        int numObjectTypes = 4; // o el número que necesites
        int objectType = random.nextInt(numObjectTypes); // Cambia el número según la cantidad de tipos de objetos especiales

        String imagePath;
        switch (objectType) {
            case 0:
                imagePath = "src\\sprite\\p1.png"; // Reemplaza con la ruta correcta a la imagen CYAN
                break;
            case 1:
                imagePath = "src\\sprite\\p2.png"; // Reemplaza con la ruta correcta a la imagen YELLOW
                break;
            case 2:
                imagePath = "src\\sprite\\p3.png"; // Reemplaza con la ruta correcta a la imagen GREEN
                break;
            case 3:
                imagePath = "src\\sprite\\p4.png"; // Reemplaza con la ruta correcta a la imagen RED
                break;
            // Agrega más casos según sea necesario para otros tipos de objetos especiales
            default:
                return null;
        }

        return new SpecialObject(x, y, width, height, imagePath);
    }
}
