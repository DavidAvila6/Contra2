package modelo;

import java.util.ArrayList;
import java.util.List;
import Objetos.Platform;
import Objetos.Object;


public class game  {
    private List<Object> trees = new ArrayList<>();
    private List<Platform> platform = new ArrayList<>();
    // Otras propiedades y métodos del juego

    public List<Object> getTrees() {
        return trees;
    }

    public List<Platform> getPlatforms() {
        return platform;
    }

    // Otros métodos para modificar el estado del juego
}
