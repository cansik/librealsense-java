package org.intel.rs.ui;

import javax.swing.*;
import java.awt.*;

public class SimpleImageViewer {
    private JFrame editorFrame;
    private ImageIcon imageIcon = new ImageIcon();

    public void display(Image image) {
        display(image, false);
    }

    public void display(Image image, boolean adjustSize) {
        imageIcon.setImage(image);

        if(adjustSize) {
            editorFrame.setSize(new Dimension(image.getWidth(null), image.getHeight(null)));
        }

        if (editorFrame != null)
            editorFrame.repaint();
    }

    public void setTitle(String title) {
        this.editorFrame.setTitle(title);
    }

    public void open(int width, int height) {
        open(width, height, "Simple Image Viewer");
    }

    public void open(int width, int height, String title) {
        SwingUtilities.invokeLater(() -> {
            editorFrame = new JFrame(title);
            editorFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

            editorFrame.setSize(width, height);
            editorFrame.setPreferredSize(new Dimension(width, height));

            JLabel jLabel = new JLabel();
            jLabel.setIcon(imageIcon);
            editorFrame.getContentPane().add(jLabel, BorderLayout.CENTER);

            editorFrame.pack();
            editorFrame.setLocationRelativeTo(null);
            editorFrame.setVisible(true);
        });
    }
}
