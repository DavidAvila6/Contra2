package controlador;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

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
    private ProceduralBackground proceduralBackground;
    private int backgroundOffsetX = 0;

    public GamePanel() {
    	String imagePath = "src/sprite/bg.jpg";
        backgroundImage = new ImageIcon(imagePath).getImage();
        proceduralBackground = new ProceduralBackground(800, 600);
        
        
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
            tree.setTreeX(tree.getTreeX() - backgroundSpeed);

            // Si un árbol se sale completamente de la ventana, colócalo en una nueva posición aleatoria a la derecha de la ventana
            if (tree.getTreeX() + tree.getTreeWidth() < 0) {
                tree.setTreeX(getWidth() + new Random().nextInt(200));
                tree.setTreeY(new Random().nextInt(getHeight() - tree.getTreeHeight()));
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

        // Dibujar los árboles
        for (Tree tree : trees) {
            int treeX = tree.getTreeX() + playerSpeedX;  // Ajustar la posición de los árboles según la velocidad del jugador
            int treeY = tree.getTreeY();

            g.setColor(Color.GREEN);
            g.fillRect(treeX, treeY, tree.getTreeWidth(), tree.getTreeHeight());
        }
    }


    private void draw(Graphics g) {
        
        g.setColor(Color.RED);
        g.fillRect(playerX, playerY, 50, 50);

        
        for (Tree tree : trees) {
            int treeX = tree.getTreeX() + playerSpeedX;  
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
