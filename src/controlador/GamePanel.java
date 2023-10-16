package controlador;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import Objetos.OakTree;
import Objetos.Platform;
import Objetos.ProceduralBackground;
import Objetos.Tree;
import Objetos.TreeFactory;
import modelo.game;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class GamePanel extends JPanel {
    private Set<Integer> pressedKeys = new HashSet<>();
    private game game;
    private int playerX = 50;
    private int playerY = 300;
    private int playerSpeedX = 0;
    private int playerSpeedY = 0;
    private int backgroundSpeed = 2;

    private List<Tree> trees = new ArrayList<>();
    private Image backgroundImage;
    private List<Platform> platforms = new ArrayList<>();

    private ProceduralBackground proceduralBackground;
    private int backgroundOffsetX = 0;

    public GamePanel(game game) {
        this.game = game;
        String imagePath = "src/sprite/bg.jpg";
        backgroundImage = new ImageIcon(imagePath).getImage();
        proceduralBackground = new ProceduralBackground(800, 600);
        generateInitialPlatforms();
        generateInitialTrees();

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

        TreeFactory.loadCache();
        // Crea algunos árboles en posiciones aleatorias fuera de la ventana
        for (int i = 0; i < 5; i++) {
            trees.add(TreeFactory.getTree("oak"));
        }
    }

    private void generateInitialTrees() {
        trees.clear(); // Limpiar árboles existentes

        int treeHeight = 100; // Ajusta según el tamaño de tus árboles
        int treeWidth = 50; // Ajusta según el tamaño de tus árboles
        int treeSpacingX = 200; // Espaciado entre árboles (ajusta según tus necesidades)

        // Generar árboles en posiciones aleatorias cerca de la altura inicial del
        // jugador
        for (int i = 0; i < 5; i++) {
            int treeX = i * treeSpacingX;
            int treeY = getHeight() - treeHeight; // Ajustar la altura de los árboles según la posición vertical del
                                                  // jugador

            trees.add(new OakTree(treeX, treeY, treeWidth, treeHeight));
        }
    }

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
            int purplePlatformY = random.nextInt(51) + 350; // Ajusta según la altura deseada de las plataformas moradas

            platforms.add(new Platform(purplePlatformX, purplePlatformY, purplePlatformWidth, purplePlatformHeight,
                    Color.MAGENTA));

        }
    }

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

    /*
     * private void handleKeyRelease(KeyEvent e) {
     * int keyCode = e.getKeyCode();
     * pressedKeys.remove(keyCode);
     * updatePlayerSpeed();
     * }
     */
    private void handleKeyRelease(KeyEvent e) {
        int keyCode = e.getKeyCode();
        pressedKeys.remove(keyCode);
        updatePlayerSpeed();

        if (keyCode == KeyEvent.VK_LEFT) {
            playerSpeedX = 0; // Detener el movimiento hacia la izquierda cuando se suelta la tecla
        }
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
            if (playerY == getHeight() - 50) {
                playerSpeedY = -15;
            }
        }
    }

    private void update() {
        playerX += playerSpeedX;
        playerY += playerSpeedY;
        Random random = new Random();
        playerSpeedY += 1;
        playerX += playerSpeedX;
        playerY += playerSpeedY;
        backgroundOffsetX += backgroundSpeed;

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
            tree.setTreeX(tree.getTreeX() - playerSpeedX);

            if (tree.getTreeX() + tree.getTreeWidth() < 0) {
                tree.setTreeX(getWidth() + new Random().nextInt(200));
                tree.setTreeY(450);
            }
        }

        // Actualizar la posición de las plataformas con el fondo
        for (Platform platform : platforms) {
            platform.setPlatformX(platform.getPlatformX() - playerSpeedX);

            // Si una plataforma se sale completamente de la ventana, colócala en una nueva
            // posición aleatoria a la derecha de la ventana
            if (platform.getPlatformX() + platform.getPlatformWidth() < 0) {
                platform.setPlatformX(getWidth() + new Random().nextInt(200));

                // Ajustar la posición Y según el color de la plataforma
                if (platform.getPlatformColor() == Color.BLUE) {
                    platform.setPlatformY(random.nextInt(51) + 500);
                } else if (platform.getPlatformColor() == Color.MAGENTA) {
                    platform.setPlatformY(random.nextInt(151) + 250);
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
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Dibuja el fondo procedural
        drawProceduralBackground(g);

        // Dibuja los elementos del juego
        g.setColor(Color.RED);
        g.fillRect(playerX, playerY, 50, 50);

        // Dibuja las plataformas
        for (Platform platform : platforms) {
            g.setColor(platform.getPlatformColor());
            g.fillRect(platform.getPlatformX(), platform.getPlatformY(), platform.getPlatformWidth(),
                    platform.getPlatformHeight());
        }

        // Dibuja los árboles
        for (Tree tree : trees) {
            int treeX = tree.getTreeX();
            int treeY = tree.getTreeY();

            g.setColor(Color.GREEN);
            g.fillRect(treeX, treeY, tree.getTreeWidth(), tree.getTreeHeight());
        }
    }

    private void draw(Graphics g) {

        g.setColor(Color.RED);
        g.fillRect(playerX, playerY, 50, 50);

        for (Tree tree : trees) {
            int treeX = tree.getTreeX();
            int treeY = tree.getTreeY();

            g.setColor(Color.GREEN);
            g.fillRect(treeX, treeY, tree.getTreeWidth(), tree.getTreeHeight());
        }
    }

    private void drawProceduralBackground(Graphics g) {
        // Dibujar partes del fondo en función de la posición del jugador
        int drawX = -backgroundOffsetX % proceduralBackground.getBackgroundImage().getWidth(null);
        while (drawX < getWidth()) {
            g.drawImage(proceduralBackground.getBackgroundImage(), drawX, 0, null);
            drawX += proceduralBackground.getBackgroundImage().getWidth(null);
        }
    }

}