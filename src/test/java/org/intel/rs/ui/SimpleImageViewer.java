package org.intel.rs.ui;

import javax.swing.*;
import java.awt.*;

public class SimpleImageViewer {
    private JFrame editorFrame;
    private ImageIcon imageIcon = new ImageIcon();

    public void display(Image image) {
        imageIcon.setImage(image);

        if (editorFrame != null)
            editorFrame.repaint();
    }

    public void setTitle(String title) {
        this.editorFrame.setTitle(title);
    }

    public void open(int width, int height) {
        SwingUtilities.invokeLater(() -> {
            editorFrame = new JFrame("Simple Image Viewer");
            editorFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

            editorFrame.setSize(width, height);

            JLabel jLabel = new JLabel();
            jLabel.setIcon(imageIcon);
            editorFrame.getContentPane().add(jLabel, BorderLayout.CENTER);

            editorFrame.pack();
            editorFrame.setLocationRelativeTo(null);
            editorFrame.setVisible(true);
        });
    }
}
