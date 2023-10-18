package controlador;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Main {
    private static JFrame characterSelectionFrame;

    public static void main(String[] args) {
        showCharacterSelection();
    }

    private static ImageIcon scaleImage(String imagePath, int width, int height) {
        ImageIcon originalIcon = new ImageIcon(imagePath);
        Image originalImage = originalIcon.getImage();
        Image scaledImage = originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(scaledImage);
    }

    private static void showCharacterSelection() {
        characterSelectionFrame = new JFrame("Selección de Personajes");
        characterSelectionFrame.setSize(800, 400);
        characterSelectionFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel characterPanel = new JPanel();
        characterPanel.setLayout(new GridLayout(1, 2));

        JLabel titleLabel = new JLabel("Selecciona un personaje:");

        // Escala las imágenes de los personajes al tamaño deseado
<<<<<<< HEAD
        ImageIcon character1Icon = scaleImage("Contra2\\src\\sprite\\pngwing.com.png", 100, 300); // Reemplaza con la ruta correcta
        ImageIcon character2Icon = scaleImage("Contra2\\src\\sprite\\pngwing.com (1).png", 100, 300); // Reemplaza con la ruta correcta
=======
        ImageIcon character1Icon = scaleImage("src\\sprite\\pngwing.com.png", 100, 300); // Reemplaza con la ruta correcta
        ImageIcon character2Icon = scaleImage("src\\sprite\\bg.jpg", 100, 100); // Reemplaza con la ruta correcta
>>>>>>> c959b7ea05a2e6c524e4f106a8dedcb4139f3fe8

        JLabel character1Label = new JLabel(character1Icon);
        JLabel character2Label = new JLabel(character2Icon);

        character1Label.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                startGameWithCharacter(1);
            }
        });

        character2Label.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                startGameWithCharacter(2);
            }
        });
        characterPanel.add(titleLabel);
        characterPanel.add(character1Label);
        characterPanel.add(character2Label);

        characterSelectionFrame.add(characterPanel);
        characterSelectionFrame.setVisible(true);
    }

    private static void startGameWithCharacter(int characterNumber) {
        GamePanel gamePanel = new GamePanel(characterNumber);
        Controller controller = new Controller(gamePanel);

        SwingUtilities.invokeLater(() -> {
            JFrame gameFrame = new JFrame("Contra-like Game");
            gameFrame.setSize(1600, 630);
            gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            gameFrame.add(gamePanel);
            gameFrame.setVisible(true);
        });

        characterSelectionFrame.dispose();
    }
}