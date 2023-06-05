/*
 * Universidad Politecnica de San Luis Potosi
 * Programacion III Java
 * Programacion Orientada a Objetos
 */

package prueba;

/**
 *
 * @author Ana Karen Cuenca Esquivel
 * @Ana Karen Cuenca Esquivel
 */
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ImageCarousel extends JFrame {

    private List<String> imagePaths;
    private List<Image> images;
    private int currentImageIndex = 0;
    private JLabel imageLabel;
    private JButton prevButton;
    private JButton nextButton;

    public ImageCarousel() {
        super("Image Carousel");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Configurar rutas de imágenes
        imagePaths = new ArrayList<>();
        imagePaths.add("\\src\\img1.gif");
        imagePaths.add("..\\src\\img2.png");
        imagePaths.add("..\\src\\img3.gif");

        // Cargar imágenes
        images = new ArrayList<>();
        for (String imagePath : imagePaths) {
            try {
                Image image = ImageIO.read(new File(imagePath));
                images.add(image);
            } catch (IOException ex) {
                System.out.println("Error al cargar la imagen: " + imagePath);
            }
        }

        // Crear elementos de interfaz
        imageLabel = new JLabel();
        updateImage();
        prevButton = new JButton("Prev");
        prevButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentImageIndex--;
                if (currentImageIndex < 0) {
                    currentImageIndex = images.size() - 1;
                }
                updateImage();
            }
        });
        nextButton = new JButton("Next");
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentImageIndex++;
                if (currentImageIndex >= images.size()) {
                    currentImageIndex = 0;
                }
                updateImage();
            }
        });

        // Añadir elementos de interfaz al JFrame
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(prevButton);
        buttonPanel.add(nextButton);
        add(buttonPanel, BorderLayout.SOUTH);
        add(imageLabel, BorderLayout.CENTER);

        // Configurar tamaño y visibilidad
        setSize(new Dimension(800, 600));
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void updateImage() {
        Image image = images.get(currentImageIndex);
        ImageIcon imageIcon = new ImageIcon(image);
        imageLabel.setIcon(imageIcon);
    }

    public static void main(String[] args) {
        ImageCarousel carousel = new ImageCarousel();
    }

}
