package controlador;

import java.awt.event.KeyEvent;

import Objetos.Enemy;
import Objetos.SpecialObject;
import Objetos.Character.Character1Factory;
import Objetos.Character.Character2Factory;
import Objetos.Character.CharacterFactory;
import Objetos.Character.Character;

public class CharacterController {
    private GamePanel gamePanel;
    private Character c;
    

    public CharacterController(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        c= gamePanel.initializeCharacter(2); 

    }

    

  



    public void resetPlayerPosition() {
        c.setPlayerX(50);
        c.setPlayerY(300);
        c.setPlayerSpeedX(0);
        c.setPlayerSpeedY(0);
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
        c.setPlayerSpeedX(0);
        c.setPlayerSpeedY(0);

        if (gamePanel.pressedKeys.contains(KeyEvent.VK_LEFT)) {
            c.setPlayerSpeedX(c.getPlayerSpeedX()-5+c.getSpeedBoost()); 

        }
        if (gamePanel.pressedKeys.contains(KeyEvent.VK_RIGHT)) {
            c.setPlayerSpeedX(c.getPlayerSpeedX()+5+c.getSpeedBoost()); 
        }
        if (gamePanel.pressedKeys.contains(KeyEvent.VK_UP)) {
            if (c.getPlayerY()== gamePanel.getHeight() - 50) {
               c.setPlayerSpeedY(c.getPlayerSpeedY()-15-c.getJumpBoost()); 
            }
        }
    }

}
