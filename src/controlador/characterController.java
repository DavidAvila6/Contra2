package controlador;

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

}
