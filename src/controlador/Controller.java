package controlador;

import modelo.game;
import controlador.GamePanel;
import Objetos.Enemy;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class Controller {
    private game game;
    private GamePanel gamePanel;
    private List<Enemy> enemies = new ArrayList<>();

    public Controller(game game, GamePanel gamePanel) {
        this.game = game;
        this.gamePanel = gamePanel;

    }

    public void startGame() {
        // Inicia el juego, configura el panel, etc.
        // Puedes poner aquí el código que tienes en el método main
        

    }

    public void handleKeyPress(int keyCode) {
        // Lógica para manejar la tecla presionada
        // Actualiza el modelo y llama a gamePanel.actualizar

        if (keyCode == KeyEvent.VK_SPACE) {
            // Invertir la dirección de todos los enemigos
            for (Enemy enemy : enemies) {
                enemy.reverseDirection();
            }
        }

    }

    // Otros métodos para manejar eventos
}
