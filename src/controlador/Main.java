package controlador;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Contra-like Game");
            frame.setSize(1600, 630);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            GamePanel gamePanel = new GamePanel();
            frame.add(gamePanel);

            frame.setVisible(true);
        });
    }
}

