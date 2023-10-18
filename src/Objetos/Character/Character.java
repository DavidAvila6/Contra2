package Objetos.Character;
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

    public int getPlayerWidth() {
        return playerWidth;
    }

    public void setPlayerWidth(int playerWidth) {
        this.playerWidth = playerWidth;
    }

    public int getPlayerHeight() {
        return playerHeight;
    }

    public void setPlayerHeight(int playerHeight) {
        this.playerHeight = playerHeight;
    }

    public int getSpeedBoost() {
        return speedBoost;
    }

    public void setSpeedBoost(int speedBoost) {
        this.speedBoost = speedBoost;
    }

    public int getPlayerSpeedX() {
        return playerSpeedX;
    }

    public void setPlayerSpeedX(int playerSpeedX) {
        this.playerSpeedX = playerSpeedX;
    }

    public int getJumpBoost() {
        return jumpBoost;
    }

    public void setJumpBoost(int jumpBoost) {
        this.jumpBoost = jumpBoost;
    }

    public int getPlayerSpeedY() {
        return playerSpeedY;
    }

    public void setPlayerSpeedY(int playerSpeedY) {
        this.playerSpeedY = playerSpeedY;
    }

    public Color getPlayerColor() {
        return playerColor;
    }

    public void setPlayerColor(Color playerColor) {
        this.playerColor = playerColor;
    }

    public int getPlayerLives() {
        return playerLives;
    }

    public void setPlayerLives(int playerLives) {
        this.playerLives = playerLives;
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

    
}