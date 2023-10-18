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
import Objetos.Character.Character;

public class Controller {
    private game game;
    private GamePanel gamePanel;
    private Set<Integer> pressedKeys = new HashSet<>();
    private CharacterController characterController;
    private Character c;

    // Constructor que acepta un GamePanel
    public Controller(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        this.characterController = new CharacterController(gamePanel);
        c=gamePanel.initializeCharacter(2);

    }

    public void handleKeyPress(KeyEvent e) {
        int keyCode = e.getKeyCode();
        gamePanel.pressedKeys.add(keyCode);
        characterController.updatePlayerSpeed();

        if (keyCode == KeyEvent.VK_UP) {
            // Permitir saltar en cualquier momento
            if (c.getPlayerX() == gamePanel.getHeight() - 50) {
                c.setPlayerY( -15 - c.getJumpBoost()) ;
            }
        }
    }

    public void handleKeyRelease(KeyEvent e) {
        int keyCode = e.getKeyCode();
        gamePanel.pressedKeys.remove(keyCode);
        characterController.updatePlayerSpeed();
    }

}
