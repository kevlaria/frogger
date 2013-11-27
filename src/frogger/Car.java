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

    private Image image;
    protected View view; // this is assigned by the Frogger class
    
    public Car(){
    	this.x = -10;
    	this.y = 48;    	// this is a little arbitrary...
    	this.dx = 1; 		// starting velocity
    	this.dy = 0;
    }    
    
    
    /**
     * Method to move car
     */
    @Override
    void update() {
    	x += dx; 	
    	setChanged();
    	notifyObservers(); 
    }
    
   
    /**
     * Method to draw image
     */
	@Override
	void draw(Graphics g) {			
        this.image = loadImage("src/Images_for_Frogger/white-car-right.png");
		g.drawImage(this.image, x, y, this.view);	
	}

    /**
     * Method to load image
     * @param fileName
     * @return
     */
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

    /**
     * Method to identify what type of Sprite this object is
     * TODO - determine whether this is actually needed or not
     */
	@Override
	String getSpriteType() {
		return "Car";
	}   
    
	/***********
	 * GETTER AND SETTER METHODS
	 * *********
	 */
	
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



    
    
}
