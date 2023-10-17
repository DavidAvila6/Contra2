
package controlador;

import modelo.game;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Main {
    public static void main(String[] args) {
    GamePanel gamePanel = new GamePanel();
    Controller controller = new Controller(gamePanel);

        // Asignar el controlador como escuchador de eventos
        gamePanel.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                controller.handleKeyPress(e.getKeyCode());
            }

            @Override
            public void keyReleased(KeyEvent e) {
                // Lógica para keyReleased si es necesaria
            }
        });

        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Contra-like Game");
            frame.setSize(1600, 630);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.add(gamePanel);
            frame.setVisible(true);
        });

        controller.startGame();
    }
}
