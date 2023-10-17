package controlador;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import Objetos.Cloud;
import Objetos.Enemy;
import Objetos.EnemyFactory;
import Objetos.OakTree;
import Objetos.Platform;
import Objetos.PlatformFactory;
import Objetos.ProceduralBackground;
import Objetos.SpecialObject;
import Objetos.SpecialObjectFactory;
import Objetos.Tree;
import Objetos.ObjectFactory;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class GamePanel extends JPanel {
    private int playerX = 50;
    private int playerY = 300;
    private int playerSpeedX = 0;
    private int playerSpeedY = 0;
    private Color playerColor = Color.RED; // o cualquier otro color predeterminado

    private int backgroundSpeed = 2;
    private Set<Integer> pressedKeys = new HashSet<>();
    private List<Tree> trees = new ArrayList<>();
    private Image backgroundImage;
    private List<Platform> platforms = new ArrayList<>();
    private List<Enemy> enemies = new ArrayList<>();
    private int enemySpeed = 3;
    private boolean movingRight = true; // Variable para rastrear la dirección de movimiento
    private int enemyGravity = 1; // Ajusta la fuerza de la gravedad según sea necesario
    private long timeSinceDirectionChange = System.currentTimeMillis();
    private static final int TIME_TO_CHANGE_DIRECTION = 10;
    private ProceduralBackground proceduralBackground;
    private int backgroundOffsetX = 0;
    private List<SpecialObject> specialObjects = new ArrayList<>();


    // Panel Inicial
    public GamePanel() {
        Random random = new Random();
        String imagePath = "src/sprite/bg.jpg";
        backgroundImage = new ImageIcon(imagePath).getImage();
        proceduralBackground = new ProceduralBackground(800, 600);
        generateInitialSpecialObjects();
        generateInitialTrees();
        generateInitialPlatforms();
        generateInitialEnemies();

        Timer timer = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                update();
                updateEnemies();
               
                repaint();

            }
        });
        Timer specialObjectTimer = new Timer(5000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                generateSpecialObjectAbovePlayer();
            }
        });
        specialObjectTimer.start();
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

        ObjectFactory.loadCache();
        // Crea algunos árboles en posiciones aleatorias fuera de la ventana
        for (int i = 0; i < 5; i++) {
            trees.add(ObjectFactory.getTree("oak"));
        }
    }

    // Inicializa Arboles
    private void generateInitialTrees() {
    trees.clear(); // Limpiar árboles existentes

    int treeHeight = 100; // Ajusta según el tamaño de tus árboles
    int treeWidth = 50; // Ajusta según el tamaño de tus árboles
    int treeSpacingX = 200; // Espaciado entre árboles (ajusta según tus necesidades)

    // Generar árboles o nubes en posiciones aleatorias cerca de la altura inicial del jugador
    for (int i = 0; i < 5; i++) {
        int treeX = i * treeSpacingX;
        int treeY = getHeight() - treeHeight; // Ajustar la altura de los árboles según la posición vertical del jugador

        // Agrega árboles o nubes aleatoriamente
        Tree newTree = getRandomTreeOrCloud(treeX, treeY, treeWidth, treeHeight);
        
        trees.add(newTree);
    }
}

// Método para obtener un árbol o una nube de manera aleatoria
private Tree getRandomTreeOrCloud(int x, int y, int width, int height) {
    Random random = new Random();
    int objectType = random.nextInt(2); // Cambia el número según la cantidad de tipos (árboles y nubes)

    switch (objectType) {
        case 0:
            // Devuelve un árbol con dimensiones específicas
            return new OakTree(x, y, 50, 100); // Ajusta las dimensiones según tus necesidades
        case 1:
            // Devuelve una nube con dimensiones específicas
            Cloud cloud = new Cloud(x, y, 100, 70); // Ajusta las dimensiones según tus necesidades
            cloud.setColor(Color.WHITE); // Establece el color de la nube
            return cloud;
        // Puedes agregar más casos para otros tipos si es necesario
        default:
            return null;
    }
}



    // Inicializa Plataformas
    private void generateInitialPlatforms() {
        int bluePlatformWidth = 350; // Ancho de las plataformas azules
        int bluePlatformHeight = 10;
        int bluePlatformSpacingX = 400;
        Random random = new Random();

        for (int i = 0; i < 4; i++) {
            int bluePlatformX = playerX + i * bluePlatformSpacingX;
            int bluePlatformY = random.nextInt(51) + 500; // Ajusta según la altura deseada de las plataformas azules

            platforms
                    .add(new Platform(bluePlatformX, bluePlatformY, bluePlatformWidth, bluePlatformHeight, Color.BLUE));
        }

        int purplePlatformWidth = 250; // Ancho de las plataformas moradas
        int purplePlatformHeight = 20;
        int purplePlatformSpacingX = 600; // Ajusta el espaciado entre las plataformas moradas

        for (int i = 0; i < 5; i++) {
            int purplePlatformX = playerX + i * purplePlatformSpacingX;
            int purplePlatformY;

            // Ajusta según la altura deseada de las plataformas moradas y su posición en la
            // ventana
            if (i % 2 == 0) {
                purplePlatformY = random.nextInt(51) + 350;
            } else {
                purplePlatformY = random.nextInt(51) + 150; // Coloca las plataformas moradas más arriba
            }

            platforms.add(new Platform(purplePlatformX, purplePlatformY, purplePlatformWidth, purplePlatformHeight,
                    Color.MAGENTA));
        }
    }

    // Método para generar enemigos iniciales
    private void generateInitialEnemies() {
        int enemyWidth = 30;
        int enemyHeight = 30;
        int enemySpacingX = 200;
        Random random = new Random();

        for (int i = 0; i < 5; i++) {
            int enemyX = random.nextInt(200) + i * enemySpacingX; // Ajusta según el rango deseado
            int enemyY = random.nextInt(51) + 475; // Ajusta según la altura deseada de los enemigos

            enemies.add(EnemyFactory.getEnemy("basic", enemyX, enemyY, enemyWidth, enemyHeight, Color.BLACK, enemySpeed,
                    0));
        }
    }

    private void generateInitialSpecialObjects() {
    int specialObjectWidth = 30;
    int specialObjectHeight = 30;
    int specialObjectSpacingX = 200;
    Random random = new Random();

    for (int i = 0; i < 2; i++) {
        int specialObjectX = playerX + i * specialObjectSpacingX;
        int specialObjectY = random.nextInt(51) + 200; // Ajusta según la altura deseada de los objetos especiales

        specialObjects.add(SpecialObjectFactory.createSpecialObject(specialObjectX, specialObjectY,
                specialObjectWidth, specialObjectHeight));
    }
}

    

    // Update enemigos iniciales
    private void updateEnemies() {
        for (Enemy enemy : enemies) {
            long currentTime = System.currentTimeMillis();

            // Verifica si ha pasado un cierto tiempo desde el último cambio de dirección
            if (currentTime - enemy.getTimeSinceDirectionChange() > TIME_TO_CHANGE_DIRECTION) {
                // Cambia la dirección y reinicia el temporizador
                enemy.reverseDirection();
                enemy.setTimeSinceDirectionChange(currentTime);
            }

            // Aplica la gravedad a la velocidad vertical
            enemy.setEnemySpeedY(enemy.getEnemySpeedY() + enemyGravity);

            // Mueve los enemigos en función de sus velocidades
            enemy.setEnemyX(enemy.getEnemyX() + enemy.getEnemySpeedX());
            enemy.setEnemyY(enemy.getEnemyY() + enemy.getEnemySpeedY());

            // Mueve los enemigos
            if (movingRight) {
                enemy.setEnemyX(enemy.getEnemyX() + enemySpeed);
            } else {
                enemy.setEnemyX(enemy.getEnemyX() - enemySpeed);
            }

            // Verifica si el enemigo alcanzó el límite derecho o izquierdo
            if (enemy.getEnemyX() > getWidth() && movingRight) {
                // Si está yendo a la derecha y llegó al límite derecho, cambia la dirección a
                // izquierda
                movingRight = false;
            } else if (enemy.getEnemyX() < 0 && !movingRight) {
                // Si está yendo a la izquierda y llegó al límite izquierdo, cambia la dirección
                // a derecha
                movingRight = true;
            }

            // Aplica la velocidad vertical (gravedad) a la posición vertical
            enemy.setEnemyY(enemy.getEnemyY() + enemy.getEnemySpeedY());

            // Verifica colisiones con las plataformas
            boolean onPlatform = false;

            for (Platform platform : platforms) {
                if (enemyCollidesWithPlatform(enemy, platform)) {
                    // Ajusta la posición del enemigo según la colisión con la plataforma

                    adjustEnemyPositionOnCollision(enemy, platform);

                    // Indica que el enemigo está en una plataforma
                    onPlatform = true;
                }
            }

            // Verifica si el enemigo llegó al suelo y no está en una plataforma
            if (enemy.getEnemyY() > getHeight() - enemy.getEnemyHeight() && !onPlatform) {
                enemy.setEnemyY(getHeight() - enemy.getEnemyHeight()); // Ajusta la posición al suelo
                enemy.setEnemySpeedY(0); // Detiene la caída
            }
            // Mueve los enemigos con el fondo
            if (playerSpeedX > 0) {
                enemy.setEnemyX(enemy.getEnemyX() - playerSpeedX);
            }

            // Verifica si el enemigo está fuera de la pantalla y reposiciónalo
            if (enemy.getEnemyX() + enemy.getEnemyWidth() < 0) {
                Random random = new Random();
                // Reposiciona el enemigo fuera del borde derecho de la pantalla
                enemy.setEnemyX(getWidth() + new Random().nextInt(200));

                enemy.setEnemyY(random.nextInt(51) + 200); // Ajusta según la altura deseada de los enemigos
            }
        }
    }

    // Colision Enemigo Plataforma
    private boolean enemyCollidesWithPlatform(Enemy enemy, Platform platform) {
        return enemy.getEnemyX() < platform.getPlatformX() + platform.getPlatformWidth() &&
                enemy.getEnemyX() + enemy.getEnemyWidth() > platform.getPlatformX() &&
                enemy.getEnemyY() < platform.getPlatformY() + platform.getPlatformHeight() &&
                enemy.getEnemyY() + enemy.getEnemyHeight() > platform.getPlatformY();
    }

    // Ajusta Posicion de collision enemigos
    private void adjustEnemyPositionOnCollision(Enemy enemy, Platform platform) {
        // Ajusta la posición del enemigo para que esté justo encima de la plataforma
        enemy.setEnemyY(platform.getPlatformY() - enemy.getEnemyHeight());

        // Detiene el movimiento vertical
        enemy.setEnemySpeedY(0);

        // Ajusta el movimiento horizontal (invierte la dirección)
        enemy.setEnemySpeedX(-enemy.getEnemySpeedX());
    }

    // Keys
    private void handleKeyPress(KeyEvent e) {
        int keyCode = e.getKeyCode();
        pressedKeys.add(keyCode);
        updatePlayerSpeed();

        if (keyCode == KeyEvent.VK_UP) {
            // Permitir saltar en cualquier momento
            if (playerY == getHeight() - 50) {
                playerSpeedY = -15;
            }
        }
    }

    // Keys
    private void handleKeyRelease(KeyEvent e) {
        int keyCode = e.getKeyCode();
        pressedKeys.remove(keyCode);
        updatePlayerSpeed();
    }

    // Update enemigos iniciales
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
            if (playerY == getHeight() - 50) {
                playerSpeedY = -15;
            }
        }
    }

    // Update Principal
    private void update() {
        updateSpecialObjects();
        Random random = new Random();
        playerSpeedY += 1;
        playerX += playerSpeedX;
        playerY += playerSpeedY;
        if (playerSpeedX > 0) {
            backgroundOffsetX += playerSpeedX;
        }

        if (playerY > getHeight() - 50) {
            playerY = getHeight() - 50;
            playerSpeedY = 0;
        }

        // Ajusta la lógica para que el jugador pueda recorrer más allá de los límites
        // originales
        if (playerX < 0) {
            playerX = getWidth() - 50;
        } else if (playerX > getWidth() - 50) {
            playerX = 0;
        }

        // Actualizar la posición de los árboles con el fondo
        for (Tree tree : trees) {
            if (playerSpeedX > 0) {
                tree.setX(tree.getX() - playerSpeedX);
            }
            if (tree.getX() + tree.getWidth() < 0) {
                tree.setX(getWidth() + new Random().nextInt(200));
                tree.setY(450);
                if (tree instanceof Cloud) {
                    tree.setY(150);
                }
            }
        }

        // Actualizar la posición de las plataformas con el fondo
        for (Platform platform : platforms) {
            if (playerSpeedX > 0) {
                platform.setPlatformX(platform.getPlatformX() - playerSpeedX);
            }

            // Si una plataforma se sale completamente de la ventana, colócala en una nueva
            // posición aleatoria a la derecha de la ventana
            if (platform.getPlatformX() + platform.getPlatformWidth() < 0) {
                platform.setPlatformX(getWidth() + new Random().nextInt(200));

                // Ajusta la altura de las plataformas según el tipo
                if (platform.getPlatformColor() == Color.MAGENTA) {
                    // Si es morada, aparece en Y: random.nextInt(51) + 350
                    platform.setPlatformY(random.nextInt(51) + 350);
                } else {
                    // Si no, aparece en Y: random.nextInt(51) + 500
                    platform.setPlatformY(random.nextInt(51) + 500);
                }
            }
        }

        // Colisiones
        boolean isOnPlatform = false; // Variable para rastrear si el jugador está en una plataforma

        for (Platform platform : platforms) {
            if (playerX < platform.getPlatformX() + platform.getPlatformWidth() &&
                    playerX + 50 > platform.getPlatformX() &&
                    playerY < platform.getPlatformY() + platform.getPlatformHeight() &&
                    playerY + 50 > platform.getPlatformY()) {
                // Hay una colisión con la plataforma, ajusta la posición del jugador
                playerY = platform.getPlatformY() - 50;
                playerSpeedY = 0;
                isOnPlatform = true; // El jugador está en una plataforma
            }
        }

        // Si el jugador está en una plataforma, permite saltar incluso si no está en el
        // suelo
        if (isOnPlatform && pressedKeys.contains(KeyEvent.VK_UP)) {
            playerSpeedY = -15;
        }

        for (Enemy enemy : enemies) {
            if (playerCollidesWithEnemy(playerX, playerY, 50, 50, enemy)) {
                // Colisión con un enemigo, muestra "Game Over"
                JOptionPane.showMessageDialog(this, "Game Over");

                // Reinicia el juego
                resetGame();
            }
        }
    }
    private void generateSpecialObjectAbovePlayer() {
        Random random = new Random();
    
        // Ajusta las dimensiones del objeto especial según tus necesidades
        int specialObjectWidth = 30;
        int specialObjectHeight = 30;
    
        // Ajusta la posición del objeto especial para que aparezca encima del jugador
        int specialObjectX = playerX;
        int specialObjectY = playerY - specialObjectHeight-200;
    
        // Crea un nuevo objeto especial y agrégalo a la lista
        SpecialObject specialObject = SpecialObjectFactory.createSpecialObject(
                specialObjectX, specialObjectY, specialObjectWidth, specialObjectHeight);
        specialObjects.add(specialObject);
    }
    
    private void updateSpecialObjects() {
        int gravity = 1; // Ajusta según la fuerza de gravedad deseada
        int speedX = 2;
        for (SpecialObject specialObject : specialObjects) {
             // Aplica la gravedad a la velocidad vertical
        specialObject.setY(specialObject.getY() + gravity);

        // Mueve los objetos especiales en una dirección constante
        // Mueve los objetos especiales junto con el jugador solo si este se mueve hacia la derecha
        if (playerSpeedX > 0) {
            specialObject.setX(specialObject.getX() - playerSpeedX);
        } 

    
            // Verifica colisiones con las plataformas
            boolean onPlatform = false;
            // Verifica si hay colisión con el jugador
        if (playerCollidesWithSpecialObject(playerX, playerY, 50, 50, specialObject)) {
            // Cambia el color del jugador al color del objeto especial
            playerColor = specialObject.getColor();
            
        }
            for (Platform platform : platforms) {
                if (specialObjectCollidesWithPlatform(specialObject, platform)) {
                    // Ajusta la posición del objeto especial según la colisión con la plataforma
                    adjustSpecialObjectPositionOnCollision(specialObject, platform);
    
                    // Indica que el objeto especial está en una plataforma
                    onPlatform = true;
                }
            }
    
            // Si el objeto especial llegó al suelo y no está en una plataforma, detén su caída
            if (specialObject.getY() > getHeight() - specialObject.getHeight() && !onPlatform) {
                specialObject.setY(getHeight() - specialObject.getHeight());
            }
        }
    }

    private boolean playerCollidesWithSpecialObject(int playerX, int playerY, int playerWidth, int playerHeight, SpecialObject specialObject) {
        return playerX < specialObject.getX() + specialObject.getWidth() &&
                playerX + playerWidth > specialObject.getX() &&
                playerY < specialObject.getY() + specialObject.getHeight() &&
                playerY + playerHeight > specialObject.getY();
    }
    
    // Método para verificar colisiones entre un objeto especial y una plataforma
    private boolean specialObjectCollidesWithPlatform(SpecialObject specialObject, Platform platform) {
        return specialObject.getX() < platform.getPlatformX() + platform.getPlatformWidth() &&
                specialObject.getX() + specialObject.getWidth() > platform.getPlatformX() &&
                specialObject.getY() < platform.getPlatformY() + platform.getPlatformHeight() &&
                specialObject.getY() + specialObject.getHeight() > platform.getPlatformY();
    }
    
    // Método para ajustar la posición de un objeto especial en caso de colisión con una plataforma
    private void adjustSpecialObjectPositionOnCollision(SpecialObject specialObject, Platform platform) {
        // Ajusta la posición del objeto especial para que esté justo encima de la plataforma
        specialObject.setY(platform.getPlatformY() - specialObject.getHeight());
    }
    
    
    // Collisiones player
    private boolean playerCollidesWithEnemy(int playerX, int playerY, int playerWidth, int playerHeight, Enemy enemy) {
        return playerX < enemy.getEnemyX() + enemy.getEnemyWidth() &&
                playerX + playerWidth > enemy.getEnemyX() &&
                playerY < enemy.getEnemyY() + enemy.getEnemyHeight() &&
                playerY + playerHeight > enemy.getEnemyY();
    }

    // Reinicia Juego
    private void resetGame() {
        // Restablece los valores del juego al estado inicial
        playerX = 50;
        playerY = 300;
        playerSpeedX = 0;
        playerSpeedY = 0;
        // Restablece otras variables y objetos del juego según sea necesario

        // Vuelve a generar árboles, plataformas, enemigos, etc.
        generateInitialTrees();
        generateInitialPlatforms();
        generateInitialEnemies();

        // Reinicia cualquier otra lógica de juego que necesites

        // Cierra la aplicación actual y lanza una nueva instancia del programa
        restartApplication();
    }

    // Reinicia aplicacion
    private void restartApplication() {
        try {
            // Obtiene el comando para ejecutar la aplicación Java actual
            String java = System.getProperty("java.home") + "/bin/java";
            String jarPath = new java.io.File(
                    GamePanel.class.getProtectionDomain().getCodeSource().getLocation().toURI()).getPath();
            String[] command = new String[] { java, "-jar", jarPath };

            // Crea un nuevo proceso para ejecutar la aplicación Java
            ProcessBuilder builder = new ProcessBuilder(command);
            builder.start();

            // Cierra la aplicación actual
            System.exit(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Pinta Componentes
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Dibujar el fondo procedimental
        drawProceduralBackground(g);
        
        // Dibujar los elementos del juego
        g.setColor(playerColor);
        g.fillRect(playerX, playerY, 50, 50);

        // Dibujar las plataformas
        for (Platform platform : platforms) {
            g.setColor(platform.getPlatformColor());
            g.fillRect(platform.getPlatformX(), platform.getPlatformY(), platform.getPlatformWidth(),
                    platform.getPlatformHeight());
        }

        // Dibujar los árboles
        for (Tree tree : trees) {
            int treeX = tree.getX();
            int treeY = tree.getY();
        
            Color treeColor = tree.getColor();
            g.setColor(treeColor);
            g.fillRect(treeX, treeY, tree.getWidth(), tree.getHeight());
        }
        

        for (SpecialObject specialObject : specialObjects) {
            specialObject.draw(g);
        }

        // Dibujar los enemigos
        drawEnemies(g);
    }

    // Dibuja enemigos
    private void drawEnemies(Graphics g) {
        for (Enemy enemy : enemies) {
            g.setColor(enemy.getEnemyColor());
            g.fillRect(enemy.getEnemyX(), enemy.getEnemyY(), enemy.getEnemyWidth(), enemy.getEnemyHeight());
        }
    }

    // Draw
    

    // Dibuja Fondo Procedural
    private void drawProceduralBackground(Graphics g) {
        // Dibujar partes del fondo en función de la posición del jugador
        int drawX = -backgroundOffsetX % proceduralBackground.getBackgroundImage().getWidth(null);
        while (drawX < getWidth()) {
            g.drawImage(proceduralBackground.getBackgroundImage(), drawX, 0, null);
            drawX += proceduralBackground.getBackgroundImage().getWidth(null);
        }
    }

}