package controlador;

import java.awt.event.KeyEvent;

import Objetos.Enemy;
import Objetos.SpecialObject;

public class characterController {
    private GamePanel gamePanel;

    public characterController(GamePanel gamePanel) {
        this.gamePanel = gamePanel;

    }

    public void resetPlayerPosition() {
        // Restablece la posición del jugador según tus necesidades
        gamePanel.playerX = 50;
        gamePanel.playerY = 300;
        gamePanel.playerSpeedX = 0;
        gamePanel.playerSpeedY = 0;
    }

    public boolean playerCollidesWithSpecialObject(int playerX, int playerY, int playerWidth, int playerHeight,
            SpecialObject specialObject) {
        return playerX < specialObject.getX() + specialObject.getWidth() &&
                playerX + playerWidth > specialObject.getX() &&
                playerY < specialObject.getY() + specialObject.getHeight() &&
                playerY + playerHeight > specialObject.getY();
    }

    public boolean playerCollidesWithEnemy(int playerX, int playerY, int playerWidth, int playerHeight, Enemy enemy) {
        return playerX < enemy.getX() + enemy.getWidth() &&
                playerX + playerWidth > enemy.getX() &&
                playerY < enemy.getY() + enemy.getHeight() &&
                playerY + playerHeight > enemy.getY();
    }

    public void updatePlayerSpeed() {
        gamePanel.playerSpeedX = 0;
        gamePanel.playerSpeedY = 0;

        if (gamePanel.pressedKeys.contains(KeyEvent.VK_LEFT)) {
            gamePanel.playerSpeedX -= 5 + gamePanel.speedBoost;

        }
        if (gamePanel.pressedKeys.contains(KeyEvent.VK_RIGHT)) {
            gamePanel.playerSpeedX += 5 + gamePanel.speedBoost;
        }
        if (gamePanel.pressedKeys.contains(KeyEvent.VK_UP)) {
            if (gamePanel.playerY == gamePanel.getHeight() - 50) {
                gamePanel.playerSpeedY = -15 - gamePanel.jumpBoost;
            }
        }
    }

}
