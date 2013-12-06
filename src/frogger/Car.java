// Gary Cheung, Kevin Lee

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

    private View view; // this is assigned by the Frogger class
    private int startingNumberOfCars; // this is assigned by the Frogger class
    private boolean facingRight;
    private Image image;

	private static final int upperLaneYCoordinate = 65;
	private static final int lowerLaneYCoordinate = 130;
    
    private static final int leftMostXCoordinate = -10;
    private static final int rightMostXCoordinate = 750;
    
    public Car(){
    	this.dy = 0;
    };
    
    /**
     * Creates each car object and places it in its starting position
     * @param carCount
     */
    public void initialiseCar(int carCount){	
    	String imagePath = this.createCarSpecs(carCount);

    	if (this.facingRight){
            this.image = loadImage(imagePath);
        	this.y = lowerLaneYCoordinate;    	// NB - starting x-coordinate created in this.createCarSpecs
    	} else {
            this.image = loadImage(imagePath);
        	this.y = upperLaneYCoordinate;    	// NB - starting x-coordinate created in this.createCarSpecs
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
    	int colourChoice = random.nextInt(5);
    	this.setStartingX(carCount);
    	String imagePath = this.generateCarImage(colourChoice);
    	return imagePath;
    }
    
    /**
     * Method to determine the starting X position of the car
     * @param carCount
     */
    public void setStartingX(int carCount){
    	
    	if (carCount < this.startingNumberOfCars){
        	Random random = new Random();
        	int carDensity = 150 + random.nextInt(20);
        	
        	if(this.facingRight){
        		this.x = leftMostXCoordinate + (carDensity * carCount);
        	} else {
           		this.x = rightMostXCoordinate - (carDensity * (carCount - 1));   		
        	}    		
    	}
    	
    	else {
        	if(this.facingRight){
        		this.x = leftMostXCoordinate;
        	} else {
           		this.x = rightMostXCoordinate;   		
        	}    			
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
    		case 3:
    			imagePath = "src/Images_for_Frogger/white-car-right.png";
    			return imagePath;
    		default:
    			imagePath = "src/Images_for_Frogger/yellow-car-right.png";
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
    		case 3:
    			imagePath = "src/Images_for_Frogger/white-car-left.png";
    			return imagePath;
    		default:
    			imagePath = "src/Images_for_Frogger/yellow-car-left.png";
    			return imagePath;    
    		}
    	}	
    }
    
    

    
    /**
     * Method to move car
     */
    @Override
    void update( ) {
    	
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
     * @return the image
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

	/***********
	 * GETTER AND SETTER METHODS
	 * *********
	 */
    
    /**
     * Method to change velocity of the car
     */
    public void setDX(int newVelocity)
    {
    	if (this.facingRight) {
    	this.dx = newVelocity;
    	} else {
    	this.dx = -newVelocity;
    	}
    }
    
    public void setView(View view){
    	this.view = view;
    }
    
    public void setStartingNumberOfCars(int startingNumberOfCars){
    	this.startingNumberOfCars = startingNumberOfCars;
    }
        

	@Override
	public int getX() {
		return this.x;
	}

	@Override
	public int getY() {
		return this.y;
	}

	@Override
	public int getImageWidth() {
		return this.image.getWidth(view);
	}

	@Override
	public int getImageHeight() {
		return this.image.getHeight(view);
	}
	
	@Override
	public String getType(){
		return "Car";
	}
	
}
