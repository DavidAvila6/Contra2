

package controlador;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    public static void main(String[] args) {
        // Crear la ventana de inicio
        JFrame startFrame = new JFrame("Inicio");
        startFrame.setSize(800, 400);
        startFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Agregar un botón para comenzar el juego
        JButton startButton = new JButton("Comenzar Juego");
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startFrame.dispose(); // Cerrar la ventana de inicio
                startGame(); // Iniciar el juego
            }
        });

        JPanel startPanel = new JPanel();
        startPanel.add(startButton);
        startFrame.add(startPanel);
        startFrame.setVisible(true);
    }

    // Esta función inicia el juego
    private static void startGame() {
        GamePanel gamePanel = new GamePanel();
        Controller controller = new Controller(gamePanel);

        SwingUtilities.invokeLater(() -> {
            JFrame gameFrame = new JFrame("Contra-like Game");
            gameFrame.setSize(1600, 630);
            gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            gameFrame.add(gamePanel);
            gameFrame.setVisible(true);
        });
    }
}
