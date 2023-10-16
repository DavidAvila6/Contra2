package controlador;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import Objetos.OakTree;
import Objetos.Platform;
import Objetos.ProceduralBackground;
import Objetos.Tree;
import Objetos.TreeFactory;

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
    private int backgroundSpeed = 2;
    private Set<Integer> pressedKeys = new HashSet<>();
    private List<Tree> trees = new ArrayList<>();
    private Image backgroundImage;
    private List<Platform> platforms = new ArrayList<>();
    
    private ProceduralBackground proceduralBackground;
    private int backgroundOffsetX = 0;

    public GamePanel() {
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
        trees.clear();  // Limpiar árboles existentes

        int treeHeight = 100;  // Ajusta según el tamaño de tus árboles
        int treeWidth = 50;    // Ajusta según el tamaño de tus árboles
        int treeSpacingX = 200; // Espaciado entre árboles (ajusta según tus necesidades)

        // Generar árboles en posiciones aleatorias cerca de la altura inicial del jugador
        for (int i = 0; i < 5; i++) {
            int treeX = i * treeSpacingX;
            int treeY = getHeight() - treeHeight;  // Ajustar la altura de los árboles según la posición vertical del jugador

            trees.add(new OakTree(treeX, treeY, treeWidth, treeHeight));
        }
    }
    
    private void generateInitialPlatforms() {
        int platformWidth = 350;  // Ajusta el ancho de las plataformas
        int platformHeight = 10;
        int platformSpacingX = 400;
        Random random = new Random();
        for (int i = 0; i < 4; i++) {
            int platformX = playerX + i * platformSpacingX;
            int platformY = random.nextInt(51) + 600;  // Ajusta según la altura deseada de las plataformas

            platforms.add(new Platform(platformX, platformY, platformWidth, platformHeight));
        }
    }


    private void handleKeyPress(KeyEvent e) {
        int keyCode = e.getKeyCode();
        pressedKeys.add(keyCode);
        updatePlayerSpeed();
    }

    private void handleKeyRelease(KeyEvent e) {
        int keyCode = e.getKeyCode();
        pressedKeys.remove(keyCode);
        updatePlayerSpeed();
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
        playerSpeedY += 1;
        playerX += playerSpeedX;
        playerY += playerSpeedY;
        backgroundOffsetX += playerSpeedX;

        if (playerY > getHeight() - 50) {
            playerY = getHeight() - 50;
            playerSpeedY = 0;
        }

        // Ajusta la lógica para que el jugador pueda recorrer más allá de los límites originales
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
        Random random = new Random();

        for (Platform platform : platforms) {
            platform.setPlatformX(platform.getPlatformX() - playerSpeedX);
            System.out.println("Plataforma en X: " + platform.getPlatformX() + ", Y: " + platform.getPlatformY());
           
            if (platform.getPlatformX() + platform.getPlatformWidth() < 0) {
                platform.setPlatformX(getWidth() + new Random().nextInt(200));
                platform.setPlatformY(random.nextInt(51) + 550);
            }
        }
        //Colisiones
        for (Platform platform : platforms) {
            if (playerX < platform.getPlatformX() + platform.getPlatformWidth() &&
                playerX + 50 > platform.getPlatformX() &&
                playerY < platform.getPlatformY() + platform.getPlatformHeight() &&
                playerY + 50 > platform.getPlatformY()) {
                // Hay una colisión con la plataforma, ajusta la posición del jugador
                playerY = platform.getPlatformY() - 50;
                playerSpeedY = 0;
            }
        }
    
    }

    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Dibujar el fondo procedimental
        drawProceduralBackground(g);

        // Dibujar los elementos del juego
        g.setColor(Color.RED);
        g.fillRect(playerX, playerY, 50, 50);

        // Dibujar las plataformas
        g.setColor(Color.BLUE);
        
        for (Platform platform : platforms) {
            g.setColor(Color.BLUE);
            g.fillRect(platform.getPlatformX(), platform.getPlatformY(), platform.getPlatformWidth(), platform.getPlatformHeight());
        }

        // Dibujar los árboles
        for (Tree tree : trees) {
            int treeX = tree.getTreeX();  // Ajustar la posición de los árboles según la velocidad del jugador
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
