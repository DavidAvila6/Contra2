package controlador;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import modelo.game;
import controlador.GamePanel;
import Objetos.Enemy;
import Objetos.Platform;

import javax.swing.*;

import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import modelo.game;

public class EnemyController {
    private game game;
    private GamePanel gamePanel;
    private Set<Integer> pressedKeys = new HashSet<>();
    public List<Enemy> enemies = new ArrayList<>();
    
    // Constructor que acepta un GamePanel
    public EnemyController(GamePanel gamePanel) {
        this.gamePanel = gamePanel;

    }

    public void startGame() {
        // Inicia el juego, configura el panel, etc.

        // Puedes obtener el objeto game usando el método getGame()
        game game = gamePanel.getGame();

        // Resto del código...
    }

    public void generateInitialEnemies() {
        int enemyWidth = 30;
        int enemyHeight = 30;
        int enemySpacingX = 200;
        Random random = new Random();

        for (int i = 0; i < 5; i++) {
            int enemyX = random.nextInt(200) + i * enemySpacingX; // Ajusta según el rango deseado
            int enemyY = random.nextInt(51) + 475; // Ajusta según la altura deseada de los enemigos
            Enemy newEnemy = new Enemy(enemyX, enemyY
            , enemyWidth, enemyHeight
            , Color.BLACK, gamePanel.enemySpeed, 0);
            gamePanel.enemies.add(newEnemy);
            gamePanel.gameObjects.add(newEnemy);
        }
    }

    public void updateEnemies() {
        for (Enemy enemy : gamePanel.enemies) {
            long currentTime = System.currentTimeMillis();

            // Verifica si ha pasado un cierto tiempo desde el último cambio de dirección
            if (currentTime - enemy.getTimeSinceDirectionChange() > gamePanel.TIME_TO_CHANGE_DIRECTION) {
                // Cambia la dirección y reinicia el temporizador
                enemy.reverseDirection();
                enemy.setTimeSinceDirectionChange(currentTime);
            }

            // Aplica la gravedad a la velocidad vertical
            enemy.setEnemySpeedY(enemy.getEnemySpeedY() + gamePanel.enemyGravity);

            // Mueve los enemigos en función de sus velocidades
            enemy.setX(enemy.getX() + enemy.getEnemySpeedX());
            enemy.setY(enemy.getY() + enemy.getEnemySpeedY());

            // Mueve los enemigos
            if (gamePanel.movingRight) {
                enemy.setX(enemy.getX() + gamePanel.enemySpeed);
            } else {
                enemy.setX(enemy.getX() - gamePanel.enemySpeed);
            }

            // Verifica si el enemigo alcanzó el límite derecho o izquierdo
            if (enemy.getX() > gamePanel.getWidth() && gamePanel.movingRight) {
                // Si está yendo a la derecha y llegó al límite derecho, cambia la dirección a
                // izquierda
                gamePanel.movingRight = false;
            } else if (enemy.getX() < 0 && !gamePanel.movingRight) {
                // Si está yendo a la izquierda y llegó al límite izquierdo, cambia la dirección
                // a derecha
                gamePanel.movingRight = true;
            }

            // Aplica la velocidad vertical (gravedad) a la posición vertical
            enemy.setY(enemy.getY() + enemy.getEnemySpeedY());

            // Verifica colisiones con las plataformas
            boolean onPlatform = false;

            for (Platform platform : gamePanel.platforms) {
                if (enemyCollidesWithPlatform(enemy, platform)) {
                    // Ajusta la posición del enemigo según la colisión con la plataforma

                    adjustEnemyPositionOnCollision(enemy, platform);

                    // Indica que el enemigo está en una plataforma
                    onPlatform = true;
                }
            }

            // Verifica si el enemigo llegó al suelo y no está en una plataforma
            if (enemy.getY() > gamePanel.getHeight() - enemy.getHeight() && !onPlatform) {
                enemy.setY(gamePanel.getHeight() - enemy.getHeight()); // Ajusta la posición al suelo
                enemy.setEnemySpeedY(0); // Detiene la caída
            }
            // Mueve los enemigos con el fondo
            if (gamePanel.playerSpeedX > 0) {
                enemy.setX(enemy.getX() - gamePanel.playerSpeedX);
            }

            // Verifica si el enemigo está fuera de la pantalla y reposiciónalo
            if (enemy.getX() + enemy.getWidth() < 0) {
                Random random = new Random();
                // Reposiciona el enemigo fuera del borde derecho de la pantalla
                enemy.setX(gamePanel.getWidth() + new Random().nextInt(200));

                enemy.setY(random.nextInt(51) + 200); // Ajusta según la altura deseada de los enemigos
            }
        }
    }

    // Colision Enemigo Plataforma
    public boolean enemyCollidesWithPlatform(Enemy enemy, Platform platform) {
        return enemy.getX() < platform.getPlatformX() + platform.getPlatformWidth() &&
                enemy.getX() + enemy.getWidth() > platform.getPlatformX() &&
                enemy.getY() < platform.getPlatformY() + platform.getPlatformHeight() &&
                enemy.getY() + enemy.getHeight() > platform.getPlatformY();
    }
    // Ajusta Posicion de collision enemigos
    public void adjustEnemyPositionOnCollision(Enemy enemy, Platform platform) {
        // Ajusta la posición del enemigo para que esté justo encima de la plataforma
        enemy.setY(platform.getPlatformY() - enemy.getHeight());

        // Detiene el movimiento vertical
        enemy.setEnemySpeedY(0);

        // Ajusta el movimiento horizontal (invierte la dirección)
        enemy.setEnemySpeedX(-enemy.getEnemySpeedX());
    }
    
}
