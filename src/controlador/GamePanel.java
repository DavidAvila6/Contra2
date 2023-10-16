package controlador;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GamePanel extends JPanel {
    private int playerX = 50;
    private int playerY = 300;

    public GamePanel() {
        Timer timer = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                update();
                repaint();
            }
        });
        timer.start();

        setFocusable(true);  // Permitir que el panel tenga el foco para recibir eventos de teclado
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                handleKeyPress(e);
            }
        });
    }

    private void handleKeyPress(KeyEvent e) {
        int keyCode = e.getKeyCode();
        switch (keyCode) {
            case KeyEvent.VK_LEFT:
                playerX -= 5;
                break;
            case KeyEvent.VK_RIGHT:
                playerX += 5;
                break;
            case KeyEvent.VK_UP:
                playerY -= 5;
                break;
            case KeyEvent.VK_DOWN:
                playerY += 5;
                break;
        }
    }

    private void update() {
        // Lógica de actualización del juego (por ahora, simplemente mueve el jugador)
        if (playerX < 0) {
            playerX = 0;
        } else if (playerX > getWidth() - 50) {
            playerX = getWidth() - 50;
        }

        if (playerY < 0) {
            playerY = 0;
        } else if (playerY > getHeight() - 50) {
            playerY = getHeight() - 50;
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    private void draw(Graphics g) {
        // Dibuja los elementos del juego (jugador, enemigos, fondos, etc.)
        g.setColor(Color.RED);
        g.fillRect(playerX, playerY, 50, 50);
    }
}
