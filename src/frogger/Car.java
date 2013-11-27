package frogger;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class Car extends Sprite{

    Image image;
    JFrame frame;
    View view;
    
    public Car(){
    	this.x = -10;
    	this.y = 48;    	// this is a little arbitrary...
    	this.dx = 1; 		// starting velocity
    	this.dy = 0;
    }    
    
    @Override
    void update() {
    	x += dx; 	
    	setChanged();
    	notifyObservers(); 
    }
    
   
	@Override
	void draw(Graphics g) {	
		
		Random random = new Random();		
        this.image = loadImage("src/Images_for_Frogger/white-car-right.png");
		g.drawImage(this.image, x, y, this.view);	
	}

    
    public Image loadImage(String fileName){
            BufferedImage img = null;
            try {
                img = ImageIO.read(new File(fileName));
            }
            catch (IOException e) {
        System.out.println("Can't load image.");
            }
            return img;
            
    }

    @Override
    public Image getImage() {
            return this.image;
    }

	@Override
	int getX() {
		return this.x;
	}

	@Override
	int getY() {
		return y;
	}

	@Override
	String getSpriteType() {
		return "Car";
	}

    
    
}
