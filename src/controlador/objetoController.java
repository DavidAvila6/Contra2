package controlador;

import controlador.GamePanel;

import java.awt.Color;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.ImageIcon;

import Objetos.Cloud;
import Objetos.OakTree;
import Objetos.Platform;
import Objetos.SpecialObject;
import Objetos.SpecialObjectFactory;

public class objetoController {
    private GamePanel gamePanel;
    public List<Object> trees = new ArrayList<>();
    public List<Platform> platforms = new ArrayList<>();

    public objetoController(GamePanel gamePanel) {
        this.gamePanel = gamePanel;

    }

    public void generateInitialTrees() {
        trees.clear(); // Limpiar árboles existentes

        int treeHeight = 100; // Ajusta según el tamaño de tus árboles
        int treeWidth = 50; // Ajusta según el tamaño de tus árboles
        int treeSpacingX = 500; // Espaciado entre árboles (ajusta según tus necesidades)

        // Generar árboles o nubes en posiciones aleatorias cerca de la altura inicial
        // del jugador
        for (int i = 0; i < 5; i++) {
            int treeX = i * treeSpacingX;
            int treeY = gamePanel.getHeight() - treeHeight; // Ajustar la altura de los árboles según la posición
                                                            // vertical del
            // jugador

            // Agrega árboles o nubes aleatoriamente
            Object newTree = getRandomTreeOrCloud(treeX, treeY, treeWidth, treeHeight);

            gamePanel.trees.add((Objetos.Object) newTree);

        }
    }

    public void generateInitialPlatforms() {
        int bluePlatformWidth = 350; // Ancho de las plataformas azules
        int bluePlatformHeight = 10;
        int bluePlatformSpacingX = 400;
        Random random = new Random();

        for (int i = 0; i < 4; i++) {
            int bluePlatformX = gamePanel.playerX + i * bluePlatformSpacingX;
            int bluePlatformY = random.nextInt(51) + 500; // Ajusta según la altura deseada de las plataformas azules
            Image platImage = new ImageIcon("sprite\\plataforma.png").getImage();
            Platform plataform = new Platform(0, 0, 0, 0, platImage);
            System.out.println("hola mundo");
            
        }

        int purplePlatformWidth = 250; // Ancho de las plataformas moradas
        int purplePlatformHeight = 20;
        int purplePlatformSpacingX = 600; // Ajusta el espaciado entre las plataformas moradas

        for (int i = 0; i < 5; i++) {
            int purplePlatformX = gamePanel.playerX + i * purplePlatformSpacingX;
            int purplePlatformY;

            // Ajusta según la altura deseada de las plataformas moradas y su posición en la
            // ventana
            if (i % 2 == 0) {
                purplePlatformY = random.nextInt(51) + 350;
            } else {
                purplePlatformY = random.nextInt(51) + 150; // Coloca las plataformas moradas más arriba
            }
            Image platImage = new ImageIcon("sprite\\plataforma.png").getImage();
            Platform plataform = new Platform(0, 0, 0, 0, platImage);

        }
    }

    public Object getRandomTreeOrCloud(int x, int y, int width, int height) {
        Random random = new Random();
        int objectType = random.nextInt(2); // Cambia el número según la cantidad de tipos (árboles y nubes)

        switch (objectType) {
            case 0:
                Image oakImage = new ImageIcon("sprite\\Tree.gif").getImage();
                
                return new OakTree(x, y, 580, 800, oakImage);
                
                
            case 1:
                // Devuelve una nube con dimensiones específicas
                Image cloudImage = new ImageIcon("sprite\\cloud.png").getImage();
                Cloud cloud = new Cloud(x, y, 100, 70, cloudImage); // Ajusta las dimensiones según tus necesidades
                 // Establece el color de la nube
                return cloud;
            // Puedes agregar más casos para otros tipos si es necesario
            default:
                return null;
        }
    }

    public void generateSpecialObjectAbovePlayer() {

        // Ajusta las dimensiones del objeto especial según tus necesidades
        int specialObjectWidth = 30;
        int specialObjectHeight = 30;

        // Ajusta la posición del objeto especial para que aparezca encima del jugador
        int specialObjectX = gamePanel.playerX;
        int specialObjectY = gamePanel.playerY - specialObjectHeight - 200;

        // Crea un nuevo objeto especial y agrégalo a la lista
        SpecialObject specialObject = SpecialObjectFactory.createSpecialObject(
                specialObjectX, specialObjectY, specialObjectWidth, specialObjectHeight);
        gamePanel.specialObjects.add(specialObject);
        gamePanel.gameObjects.add(specialObject);

    }

    public void generateInitialSpecialObjects() {
        int specialObjectWidth = 30;
        int specialObjectHeight = 30;
        int specialObjectSpacingX = 200;
        Random random = new Random();

        for (int i = 0; i < 2; i++) {
            int specialObjectX = gamePanel.playerX + i * specialObjectSpacingX;
            int specialObjectY = random.nextInt(51) + 200; // Ajusta según la altura deseada de los objetos especiales

            SpecialObject newSpecial = SpecialObjectFactory.createSpecialObject(specialObjectX, specialObjectY,
                    specialObjectWidth, specialObjectHeight);
            gamePanel.specialObjects.add(newSpecial);
            gamePanel.gameObjects.add(newSpecial);
        }
    }

    // Método para verificar colisiones entre un objeto especial y una plataforma
    public boolean specialObjectCollidesWithPlatform(SpecialObject specialObject, Platform platform) {
        return specialObject.getX() < platform.getPlatformX() + platform.getPlatformWidth() &&
                specialObject.getX() + specialObject.getWidth() > platform.getPlatformX() &&
                specialObject.getY() < platform.getPlatformY() + platform.getPlatformHeight() &&
                specialObject.getY() + specialObject.getHeight() > platform.getPlatformY();
    }
    public void adjustSpecialObjectPositionOnCollision(SpecialObject specialObject, Platform platform) {
        // Ajusta la posición del objeto especial para que esté justo encima de la
        // plataforma
        specialObject.setY(platform.getPlatformY() - specialObject.getHeight());
    }

}
