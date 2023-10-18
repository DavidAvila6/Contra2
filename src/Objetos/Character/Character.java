package Objetos;
import java.awt.Color;

public abstract class  Character {        
    private int playerX = 50;
    private int playerY = 300;
    private int playerWidth = 50;
    private int playerHeight = 50;
    private int speedBoost = 0;
    private int playerSpeedX = 0 + speedBoost;
    private int jumpBoost = 0;
    private int playerSpeedY = 0 - jumpBoost;
    private Color playerColor = Color.RED;
    private int playerLives = 3;

    public int getPlayerX() {
        return playerX;
    }

    public void setPlayerX(int playerX) {
        this.playerX = playerX;
    }

    public int getPlayerY() {
        return playerY;
    }

    public void setPlayerY(int playerY) {
        this.playerY = playerY;
    }

   

    public void move(int deltaX, int deltaY) {
        playerX += deltaX;
        playerY += deltaY;
    }

    public abstract void jump();

    public abstract void attack();
}