package frogger;
import java.awt.*;
import java.awt.event.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.*;

public class ImageLoader extends JFrame {
    BufferedImage img = null;
    ImagePanel panel = new ImagePanel();
    JButton button = new JButton("Load Image");

    public static void main(String[] args) {
        new ImageLoader().run();
    }

    void run() {
        add(panel, BorderLayout.CENTER);
        add(button, BorderLayout.SOUTH);
        try {
            img = ImageIO.read(new File("frog-up.png"));
        } catch (IOException exc) {
            System.out.println("Can't load image.");
        }
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel.installImage(img);
                panel.repaint();
            }
        });
        panel.setPreferredSize(new Dimension(100, 100));
        pack();
        setVisible(true);
    }
}

class ImagePanel extends JPanel {
    BufferedImage img;

    void installImage(BufferedImage img) {
        this.img = img;
    }

    public void paint(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, getWidth(), getHeight());
        g.drawImage(img, 40, 40, null);
    }
}