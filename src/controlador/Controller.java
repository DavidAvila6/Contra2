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
    private characterController characterController;

    // Constructor que acepta un GamePanel
    public Controller(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        this.characterController = new characterController(gamePanel);

    }

    public void handleKeyPress(KeyEvent e) {
        int keyCode = e.getKeyCode();
        gamePanel.pressedKeys.add(keyCode);
        characterController.updatePlayerSpeed();

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
        characterController.updatePlayerSpeed();
    }

}
