package frogger;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ArrowKeyTests extends JFrame {
    int frogX = 40;
    int frogY = 175;
    Image frog;

    public static void main(String[] args) {
        new ArrowKeyTests().run();
    }

    private void run() {
        // NOTE: The image (frog-up.png) must be in the same
        // directory as the .class file (ArrowKeyTests.class)
//        System.out.println(this.getClass().getResourceAsStream("frog-up.png"));
//        getImage("");
        frog = loadImage("src/Images_for_Frogger/white-car-right.png");
        
        JPanel panel = new JPanel();
        add(panel);
        pack();
        setSize(80, 200);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        addKeyListener(new KeyListener() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_UP) {
                    System.out.println("Up arrow pressed.");
                    frogY -= 20;
                    repaint();
                }
                if(e.getKeyCode() == KeyEvent.VK_DOWN) {
                    System.out.println("Down arrow pressed.");
                }
                if(e.getKeyCode() == KeyEvent.VK_LEFT) {
                    System.out.println("Left arrow pressed.");
                }
                if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    System.out.println("Right arrow pressed.");
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                System.out.println("Released key " + e.getKeyCode());
            }

            @Override
            public void keyTyped(KeyEvent e) {
                System.out.println("Typed key " + e.getKeyChar());
            }
        });
    }
    
    private Image loadImage(String fileName) {
        Image img = null;
        try {
            img = ImageIO.read(new File(fileName));
        } catch (IOException exc) {
            System.out.println("Can't load image.");
        }
        return img;
    }
    @Override
    public void paint(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
        g.drawImage(frog, frogX, frogY, this);
    }
}