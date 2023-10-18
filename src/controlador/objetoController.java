package controlador;

import controlador.GamePanel;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import Objetos.Platform;

public class objetoController {
    private GamePanel gamePanel;
    public List<Object> trees = new ArrayList<>();
    public List<Platform> platforms = new ArrayList<>();

    public objetoController(GamePanel gamePanel) {
        this.gamePanel = gamePanel;

    }

    public void generateInitialTrees() {
        trees.clear(); // Limpiar árboles existentes

        int treeHeight = 100; // Ajusta según el tamaño de tus árboles
        int treeWidth = 50; // Ajusta según el tamaño de tus árboles
        int treeSpacingX = 200; // Espaciado entre árboles (ajusta según tus necesidades)

        // Generar árboles o nubes en posiciones aleatorias cerca de la altura inicial
        // del jugador
        for (int i = 0; i < 5; i++) {
            int treeX = i * treeSpacingX;
            int treeY = gamePanel.getHeight() - treeHeight; // Ajustar la altura de los árboles según la posición
                                                            // vertical del
            // jugador

            // Agrega árboles o nubes aleatoriamente
            Object newTree = gamePanel.getRandomTreeOrCloud(treeX, treeY, treeWidth, treeHeight);

            gamePanel.trees.add((Objetos.Object) newTree);

        }
    }

    public void generateInitialPlatforms() {
        int bluePlatformWidth = 350; // Ancho de las plataformas azules
        int bluePlatformHeight = 10;
        int bluePlatformSpacingX = 400;
        Random random = new Random();

        for (int i = 0; i < 4; i++) {
            int bluePlatformX = gamePanel.playerX + i * bluePlatformSpacingX;
            int bluePlatformY = random.nextInt(51) + 500; // Ajusta según la altura deseada de las plataformas azules
            Platform newPlatform = new Platform(bluePlatformX, bluePlatformY, bluePlatformWidth, bluePlatformHeight,
                    Color.BLUE);
            gamePanel.platforms.add(newPlatform);
            gamePanel.gameObjects.add(newPlatform);
        }

        int purplePlatformWidth = 250; // Ancho de las plataformas moradas
        int purplePlatformHeight = 20;
        int purplePlatformSpacingX = 600; // Ajusta el espaciado entre las plataformas moradas

        for (int i = 0; i < 5; i++) {
            int purplePlatformX = gamePanel.playerX + i * purplePlatformSpacingX;
            int purplePlatformY;

            // Ajusta según la altura deseada de las plataformas moradas y su posición en la
            // ventana
            if (i % 2 == 0) {
                purplePlatformY = random.nextInt(51) + 350;
            } else {
                purplePlatformY = random.nextInt(51) + 150; // Coloca las plataformas moradas más arriba
            }
            Platform newPlatform = new Platform(purplePlatformX, purplePlatformY, purplePlatformWidth,
                    purplePlatformHeight,
                    Color.MAGENTA);
            gamePanel.platforms.add(newPlatform);
            gamePanel.gameObjects.add(newPlatform);
        }
    }

}
