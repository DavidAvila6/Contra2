package modelo;

import java.util.ArrayList;
import java.util.List;
import Objetos.Platform;
import Objetos.Tree;

public class game {
    private int playerX;
    private int playerY;
    private List<Tree> trees = new ArrayList<>();
    private List<Platform> platform = new ArrayList<>();
    // Otras propiedades y métodos del juego

    public List<Tree> getTrees() {
        return trees;
    }

    public List<Platform> getPlatforms() {
        return platform;
    }

    public int getPlayerX() {
        return playerX;
    }

    public int getPlayerY() {
        return playerY;
    }


    

    // Otros métodos para modificar el estado del juego
}
