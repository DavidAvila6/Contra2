package controlador;

import modelo.game;
import controlador.GamePanel;
import Objetos.Enemy;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashSet;
import java.util.Set;

public class Controller {
    private game game;
    private GamePanel gamePanel;
    private Set<Integer> pressedKeys = new HashSet<>();

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

    // Otros métodos para manejar eventos
}
