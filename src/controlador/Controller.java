package controlador;

import modelo.game;
import controlador.GamePanel;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Controller {
    private game game;
    private GamePanel gamePanel;

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
    }

    // Otros métodos para manejar eventos
}
