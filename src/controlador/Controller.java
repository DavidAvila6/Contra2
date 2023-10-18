package controlador;

import modelo.game;
import controlador.GamePanel;
import Objetos.Enemy;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Controller {
    private game game;
    private GamePanel gamePanel;
    private Set<Integer> pressedKeys = new HashSet<>();
    public List<Object> trees = new ArrayList<>();

    // Constructor que acepta un GamePanel
    public Controller(GamePanel gamePanel) {
        this.gamePanel = gamePanel;

    }

    public void startGame() {
        // Inicia el juego, configura el panel, etc.

        // Puedes obtener el objeto game usando el método getGame()
        game game = gamePanel.getGame();

        // Resto del código...
    }

    public void handleKeyPress(KeyEvent e) {
        int keyCode = e.getKeyCode();
        gamePanel.pressedKeys.add(keyCode);
        gamePanel.updatePlayerSpeed();

        if (keyCode == KeyEvent.VK_UP) {
            // Permitir saltar en cualquier momento
            if (gamePanel.playerY == gamePanel.getHeight() - 50) {
                gamePanel.playerY = -15 - gamePanel.jumpBoost;
            }
        }
    }

    public void handleKeyRelease(KeyEvent e) {
        int keyCode = e.getKeyCode();
        gamePanel.pressedKeys.remove(keyCode);
        gamePanel.updatePlayerSpeed();
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

            trees.add(newTree);

        }
    }
}
