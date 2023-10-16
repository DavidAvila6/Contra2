package controlador;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashSet;
import java.util.Set;

public class GamePanel extends JPanel {
    private int playerX = 50;
    private int playerY = 300;
    private int playerSpeedX = 0; // Velocidad horizontal del jugador
    private int playerSpeedY = 0; // Velocidad vertical del jugador
    private Set<Integer> pressedKeys = new HashSet<>();

    public GamePanel() {
        Timer timer = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                update();
                repaint();
            }
        });
        timer.start();

        setFocusable(true);
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                handleKeyPress(e);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                handleKeyRelease(e);
            }
        });
    }

    private void handleKeyPress(KeyEvent e) {
        int keyCode = e.getKeyCode();
        pressedKeys.add(keyCode);

        updatePlayerSpeed();
    }

    private void handleKeyRelease(KeyEvent e) {
        int keyCode = e.getKeyCode();
        pressedKeys.remove(keyCode);

        updatePlayerSpeed();
    }

    private void updatePlayerSpeed() {
        playerSpeedX = 0;
        playerSpeedY = 0;

        if (pressedKeys.contains(KeyEvent.VK_LEFT)) {
            playerSpeedX -= 5;
        }
        if (pressedKeys.contains(KeyEvent.VK_RIGHT)) {
            playerSpeedX += 5;
        }
        if (pressedKeys.contains(KeyEvent.VK_UP)) {
            // Salto solo si el jugador está en el suelo
            if (playerY == getHeight() - 50) {
                playerSpeedY = -15;
            }
        }
    }

    private void update() {
        // Aplicar gravedad
        playerSpeedY += 1;

        // Actualizar la posición del jugador
        playerX += playerSpeedX;
        playerY += playerSpeedY;

        // Detección de colisiones con la ventana
        if (playerY > getHeight() - 50) {
            playerY = getHeight() - 50;
            playerSpeedY = 0;
        }

        // Detección de colisiones con los bordes de la ventana
        if (playerX < 0) {
            playerX = 0;
        } else if (playerX > getWidth() - 50) {
            playerX = getWidth() - 50;
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    private void draw(Graphics g) {
        // Dibujar los elementos del juego (jugador, enemigos, fondos, etc.)
        g.setColor(Color.RED);
        g.fillRect(playerX, playerY, 50, 50);
    }
}
