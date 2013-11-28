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
    boolean facingRight;
    
    private static final int leftMostXCoordinate = -50;
    private static final int rightMostXCoordinate = 700;
    
    public Car(){};
    
    public void initialiseCar(int carCount){
    	
    	String imagePath = this.createCarSpecs(carCount);
    	    	
    	if (this.facingRight){
            this.image = loadImage(imagePath);
        	this.y = 65;    	// NB - starting x-coordinate created in this.createCarSpecs
        	this.dx = 10; 		// starting velocity
        	this.dy = 0;  

    	} else {
            this.image = loadImage(imagePath);
        	this.y = 130;    	// NB - starting x-coordinate created in this.createCarSpecs
        	this.dx = -10; 		// starting velocity
        	this.dy = 0;    			
    	}	    	
    }
   
    /**
     * Randomly generate the direction and the colour of the car
     * @return The path of the car image
     */
    public String createCarSpecs(int carCount){
    	Random random = new Random();
    	
    	if (carCount % 2 == 0){ 	// even number cars face right, odd number cars face left
    		this.facingRight = true;
    	} else {
    		this.facingRight = false;
    	}
    	int colourChoice = random.nextInt(4);
    	this.setStartingX(carCount);
    	String imagePath = this.generateCarImage(colourChoice);
    	return imagePath;
    }
    
    public void setStartingX(int carCount){
    	
    	int carDensity = 100;
    	
    	if(this.facingRight){
    		this.x = leftMostXCoordinate + (carDensity * carCount);
    	} else {
       		this.x = rightMostXCoordinate - (carDensity * (carCount - 1));   		
    	}
    	
    }
    
    /**
     * Based on the direction and the randomly generated colour choice, return the appropriate path of the car image
     * @param colourChoice
     * @return path of car image
     */
    public String generateCarImage(int colourChoice){
    	
    	String imagePath = "";
    	    	
    	if (this.facingRight){
    		switch (colourChoice){
    		case 0:
    			imagePath = "src/Images_for_Frogger/aqua-car-right.png";
    			return imagePath;
    		case 1:
    			imagePath = "src/Images_for_Frogger/blue-car-right.png";
    			return imagePath;
    		case 2:
    			imagePath = "src/Images_for_Frogger/green-car-right.png";
    			return imagePath;
    		default:
    			imagePath = "src/Images_for_Frogger/white-car-right.png";
    			return imagePath;
    		}
    	} else {
    		switch (colourChoice){
    		case 0:
    			imagePath = "src/Images_for_Frogger/aqua-car-left.png";
    			return imagePath;
    		case 1:
    			imagePath = "src/Images_for_Frogger/blue-car-left.png";
    			return imagePath;
    		case 2:
    			imagePath = "src/Images_for_Frogger/green-car-left.png";
    			return imagePath;
    		default:
    			imagePath = "src/Images_for_Frogger/white-car-left.png";
    			return imagePath;    
    		}
    	}	
    }
    
    /**
     * Method to move car
     */
    @Override
    void update() {
    	
    	int xLimit = view.getWidth();
    	int carLength = image.getWidth(view);
    	
    	x += dx; 	
    	
    	// Return car to the beginning, if off the screen, if it's facing right
    	if (facingRight){
        	if (x > xLimit + carLength){
        		x = -carLength;
        	}    		
    	} else {
    		
    		// Return car to the end, if off the screen, if it's facing left
    		
    		if (x < 0 - carLength){
    			x = xLimit + carLength;
    		}
    	}
    	
    	setChanged();
    	notifyObservers(); 
    }
    
   
    /**
     * Method to draw image
     */
	@Override
	void draw(Graphics g) {			

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
