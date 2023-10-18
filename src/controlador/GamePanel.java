package controlador;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import Objetos.Bala;
import Objetos.Cloud;
import Objetos.Enemy;
import Objetos.EnemyFactory;
import Objetos.OakTree;
import Objetos.Platform;
import Objetos.PlatformFactory;
import Objetos.ProceduralBackground;
import Objetos.SpecialObject;
import Objetos.SpecialObjectFactory;
import Objetos.Object;
import modelo.GameObject;
import modelo.game;
import Objetos.ObjectFactory;
import controlador.objetoController;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import Objetos.Character.Character;
import Objetos.Character.Character1Factory;
import Objetos.Character.Character2Factory;
import Objetos.Character.CharacterFactory;

public class GamePanel extends JPanel {
    private Character c;
    public int playerX = 50;
    public int playerY = 300;
    public int playerWith = 50;
    public int playerHeigh = 50;
    public int speedBoost = 0;
    public int playerSpeedX = 0 + speedBoost;
    public int jumpBoost = 0;
    public int playerSpeedY = 0 - jumpBoost;
    public Color playerColor = Color.RED; // o cualquier otro color predeterminado
    public int playerLives = 3;
    public Image pahtimagen;

    public int backgroundSpeed = 2;
    public Set<Integer> pressedKeys = new HashSet<>();
    public List<Object> trees = new ArrayList<>();

    public boolean movingRight = true; // Variable para rastrear la dirección de movimiento
    public Image backgroundImage;
    public List<Platform> platforms = new ArrayList<>();
    public List<Enemy> enemies = new ArrayList<>();
    // Ajusta la fuerza de la gravedad según sea necesario
    public long timeSinceDirectionChange = System.currentTimeMillis();
    public static final int TIME_TO_CHANGE_DIRECTION = 10;
    public ProceduralBackground proceduralBackground;
    public int backgroundOffsetX = 0;
    public List<SpecialObject> specialObjects = new ArrayList<>();
    public List<GameObject> gameObjects = new ArrayList<>();
    public game game;
    public Controller controller = new Controller(this);
    public objetoController objcontroller = new objetoController(this);
    public EnemyController EnemyController = new EnemyController(this);
    public characterController charcontroller = new characterController(this);
    private List<Bala> balas = new ArrayList<>();
    List<Bala> balasParaEliminar = new ArrayList<>();
    List<Enemy> enemigosParaEliminar = new ArrayList<>();

    // Panel Inicial
    public GamePanel(int x) {
        

        game = new game();
        Random random = new Random();
        proceduralBackground = new ProceduralBackground("Contra2\\src\\sprite\\bg.jpg", 1600, 630);

        objcontroller.generateInitialSpecialObjects();
        objcontroller.generateInitialTrees();
        objcontroller.generateInitialPlatforms();
        EnemyController.generateInitialEnemies();
        c=initializeCharacter(x);
        playerX = c.getPlayerX();
        playerY = c.getPlayerY();
        playerWith = c.getPlayerWidth();
        playerHeigh = c.getPlayerHeight();
        speedBoost = c.getSpeedBoost();
        playerSpeedX = c.getPlayerSpeedX() + speedBoost;
        jumpBoost = c.getJumpBoost();
        playerSpeedY = c.getPlayerSpeedY() - jumpBoost;
        playerColor = c.getPlayerColor(); // o cualquier otro color predeterminado
        playerLives = c.getPlayerLives();
        pahtimagen=c.getImagen();

        Timer timer = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                update();
                EnemyController.updateEnemies();

                repaint();

            }
        });

        
        Timer specialObjectTimer = new Timer(5000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                objcontroller.generateSpecialObjectAbovePlayer();

            }
        });
        specialObjectTimer.start();
        timer.start();

        setFocusable(true);
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                controller.handleKeyPress(e);

                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    // Se ha presionado la barra espaciadora, dispara una bala
                    dispararBala();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                controller.handleKeyRelease(e);
            }

        });

        ObjectFactory.loadCache();
        // Crea algunos árboles en posiciones aleatorias fuera de la ventana
        for (int i = 0; i < 5; i++) {
            trees.add(ObjectFactory.getObject("oak"));
        }
    }

    private Character initializeCharacter(int x) {
        CharacterFactory characterFactory = null;
    
            if (x == 1) {
                characterFactory = new Character1Factory();
            } else if (x == 2) {
                characterFactory = new Character2Factory();
            }
    
            c = characterFactory.createPlayerCharacter();
            
            return c;
    }

    public void KeyPres(KeyEvent e) {
        controller.handleKeyPress(e);
    }

    // Inicializa Arboles
    public List<Object> getTrees() {
        return trees;
    }

    public game getGame() {
        return game;
    }

    // Método para obtener un árbol o una nube de manera aleatoria

    public void dispararBala() {
        // Crea una nueva bala en la posición actual del jugador
        Bala bala = new Bala(playerX, playerY, 10, 5);
        balas.add(bala);
    }

    private boolean balaColisionaConEnemy(Bala bala, Enemy enemy) {
        int balaX = bala.getX();
        int balaY = bala.getY();
        int balaWidth = bala.getWidth();
        int balaHeight = bala.getHeight();

        int enemyX = enemy.getX();
        int enemyY = enemy.getY();
        int enemyWidth = enemy.getWidth();
        int enemyHeight = enemy.getHeight();

        return balaX < enemyX + enemyWidth &&
                balaX + balaWidth > enemyX &&
                balaY < enemyY + enemyHeight &&
                balaY + balaHeight > enemyY;
    }

    private void updateBalas() {
        // Actualiza la posición de las balas y elimina las que salen de la pantalla
        List<Bala> balasEliminar = new ArrayList<>();
        for (Bala bala : balas) {
            bala.mover(playerSpeedX + 10, playerSpeedX - 10);
            if (bala.getX() > getWidth()) {
                balasEliminar.add(bala);
            }
        }
        for (Bala bala : balas) {
            for (Enemy enemy : enemies) {
                if (balaColisionaConEnemy(bala, enemy)) {
                    // Agrega la bala y el enemigo a las listas awdadde elementos a eliminar
                    balasParaEliminar.add(bala);
                    enemigosParaEliminar.add(enemy);
                    gameObjects.remove(enemy);
                }
            }
        }

        // Elimina las balas y enemigos que colisionaron
        balas.removeAll(balasParaEliminar);
        enemies.removeAll(enemigosParaEliminar);
        balas.removeAll(balasEliminar);
    }

    public void update() {
        updateBalas();
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
        for (Object tree : trees) {
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
            playerSpeedY = -15 - jumpBoost;
        }

        for (Enemy enemy : enemies) {
            if (charcontroller.playerCollidesWithEnemy(playerX, playerY, 50, 50, enemy)) {
                // Colisión con un enemigo, reduce una vida
                playerLives--;

                // Verifica si quedan vidas
                if (playerLives <= 0) {
                    // Si no quedan vidas, muestra "Game Over"
                    JOptionPane.showMessageDialog(this, "Game Over");

                    // Reinicia el juego
                    resetGame();
                    return; // Sale del método, ya que el juego se reinició
                }

                // Restablece la posición del jugador o realiza otras acciones según sea
                // necesario
                charcontroller.resetPlayerPosition();
            }
        }
    }

    public void updateSpecialObjects() {
        int gravity = 1; // Ajusta según la fuerza de gravedad deseada
        int speedX = 2;
        for (SpecialObject specialObject : specialObjects) {
            // Aplica la gravedad a la velocidad vertical
            specialObject.setY(specialObject.getY() + gravity);

            // Mueve los objetos especiales en una dirección constante
            // Mueve los objetos especiales junto con el jugador solo si este se mueve hacia
            // la derecha
            if (playerSpeedX > 0) {
                specialObject.setX(specialObject.getX() - playerSpeedX);
            }

            // Verifica colisiones con las plataformas
            boolean onPlatform = false;
            // Verifica si hay colisión con el jugador
            if (charcontroller.playerCollidesWithSpecialObject(playerX, playerY, 50, 50, specialObject)) {
                // Cambia el color del jugador al color del objeto especial
                if (specialObject.getColor().equals(Color.GREEN)) {
                    playerHeigh = 50;
                    playerWith = 50;
                    speedBoost = 0;
                    jumpBoost = 20;
                }
                if (specialObject.getColor().equals(Color.CYAN)) {
                    playerHeigh = 50;
                    playerWith = 50;
                    speedBoost = 30;
                    jumpBoost = 0;
                }
                if (specialObject.getColor().equals(Color.YELLOW)) {
                    // Ajusta el tamaño del jugador
                    playerHeigh = 5;
                    playerWith = 5;
                    speedBoost = 5;
                    jumpBoost = 5;
                    // Duplica la altura (ajusta según tus necesidades)
                }
                if (specialObject.getColor().equals(Color.RED)) {
                    playerHeigh = 50;
                    playerWith = 50;
                    speedBoost = 0;
                    jumpBoost = 0;
                    playerLives += 1;
                }
                playerColor = specialObject.getColor();
                gameObjects.remove(specialObject);
                specialObject.setX(-200);
            }
            for (Platform platform : platforms) {
                if (objcontroller.specialObjectCollidesWithPlatform(specialObject, platform)) {
                    // Ajusta la posición del objeto especial según la colisión con la plataforma
                    objcontroller.adjustSpecialObjectPositionOnCollision(specialObject, platform);

                    // Indica que el objeto especial está en una plataforma
                    onPlatform = true;
                }
            }

            // Si el objeto especial llegó al suelo y no está en una plataforma, detén su
            // caída
            if (specialObject.getY() > getHeight() - specialObject.getHeight() && !onPlatform) {
                specialObject.setY(getHeight() - specialObject.getHeight());
            }
        }

    }

    // Collisiones player

    // Reinicia Juego
    public void resetGame() {
        // Restablece los valores del juego al estado inicial
        playerX = 50;
        playerY = 300;
        playerSpeedX = 0;
        playerSpeedY = 0;
        // Restablece otras variables y objetos del juego según sea necesario

        // Vuelve a generar árboles, plataformas, enemigos, etc.
        objcontroller.generateInitialTrees();
        objcontroller.generateInitialPlatforms();
        EnemyController.generateInitialEnemies();

        // Reinicia cualquier otra lógica de juego que necesites

        // Cierra la aplicación actual y lanza una nueva instancia del programa
        restartApplication();
    }

    // Reinicia aplicacion
    public void restartApplication() {
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
        g.drawImage(pahtimagen, playerX, playerY, playerWith+20,playerHeigh+20,null);
        g.setColor(Color.BLACK);
        g.drawString("Vidas: ", 10, 20);

        int circleRadius = 15; // Ajusta el radio del círculo según tus preferencias
        int circleSpacing = 5; // Ajusta el espacio entre círculos según tus preferencias

        // Dibujar círculos representando las vidas restantes
        for (int i = 0; i < playerLives; i++) {
            int circleX = 60 + (circleRadius * 2 + circleSpacing) * i;
            int circleY = 10;

            g.setColor(Color.RED); // Puedes ajustar el color del círculo según tus preferencias
            g.fillOval(circleX, circleY, circleRadius * 2, circleRadius * 2);
        }

        for (GameObject gameObject : gameObjects) {
            gameObject.draw(g);
        }
        // Dibujar los árboles
        for (Object tree : trees) {
            int x = tree.getX(); // Ajustar la posición de los árboles según la velocidad del jugador
            int y = tree.getY();

            Color treeColor = tree.getColor();
            g.setColor(treeColor);
            g.fillRect(x, y, tree.getWidth(), tree.getHeight());
        }
        for (Bala bala : balas) {
            g.setColor(bala.getColor());
            g.fillRect(bala.getX(), bala.getY(), bala.getWidth(), bala.getHeight());
        }

    }

    // Draw

    // Dibuja Fondo Procedural
    public void drawProceduralBackground(Graphics g) {
        // Dibujar partes del fondo en función de la posición del jugador
        int drawX = -backgroundOffsetX % proceduralBackground.getBackgroundImage().getWidth(null);
        while (drawX < getWidth()) {
            g.drawImage(proceduralBackground.getBackgroundImage(), drawX, 0, null);
            drawX += proceduralBackground.getBackgroundImage().getWidth(null);
        }
    }

    // BALAS by JuloXxx

}