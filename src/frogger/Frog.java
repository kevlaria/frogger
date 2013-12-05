package frogger;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class Frog extends Sprite {

	
	private Image image;
	protected View view;
	boolean alive;
	Orientation direction;
	public enum Orientation { UP, RIGHT, DOWN, LEFT };
	
   private static final int minX = 0;
   private static final int maxX = 800;
   private static final int maxY = 200;
   private static final int minY = 0;
   private static final int destinationY = 30;
   private static final int waitTimeForNewFrog = 1000;
   private static final int oneJump = 30;

	/**
	 * Constructor for frog
	 */
	public Frog()
	{
		this.image = loadImage("src/Images_for_Frogger/frog-up.png");
		direction = Orientation.UP;
		alive = true;
		this.x = 350;
		this.y = 180; // 175;
	}
	

	/**
	 * Updates current position of the Frog
	 */
	@Override
	void update() {
		//Update the current position
		this.dx = 0;
		this.dy = 0;
		this.moveForward();
		this.x = this.x + this.dx;
		this.y = this.y + this.dy;
		setChanged();
    	notifyObservers(); 
	}


	/***********
	 * SPRITE MOVEMENT METHODS
	 * *********
	 */
	
	/**
	 * Method Move Forward:
	 * This gets the current orientation of the object.
	 * When the user pushes the up button, this code is activated.
	 * When this method is called, it will move the position of the frog 
	 * + or - 1 depending on the current orientation of the frog.
	 */
	public void moveForward() {
		if (direction.toString() == "LEFT") { // Establish leftmost bounds
			if ((x - oneJump) >=	 minX) {
				dx = -oneJump;
			} 
		}
		else if (direction.toString() == "RIGHT") { // Establish rightmost bounds
			if ((x + oneJump) <= maxX)	{
			 dx= +oneJump;
			} 
		}
		else if (direction.toString() == "UP") { // Establish uppermost bounds
			if ((y - oneJump) >= minY) {
				dy = -oneJump;
			} 	
		}
		else if (direction.toString() == "DOWN")  { // Establish lower-most bounds
			if ((y + oneJump) <= maxY) {
				dy = +oneJump;
			}
		}
	}
	
	/**
	 * Determines if frog has crossed road yet.
	 * @return true if frog has crossed road
	 */
	public boolean crossedRoad()
	{
		int currentY = this.y;
		
		if (currentY <= destinationY){
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Changes image of frog to a squished frog, and waits a second before resuming 
	 * @throws InterruptedException
	 */
	public void squishFrog() throws InterruptedException
	{
		this.image = loadImage("src/Images_for_Frogger/splat.gif");
		
		setChanged();
    	notifyObservers(); 
    	//Wait a second 
    	Thread.currentThread().sleep(waitTimeForNewFrog);
	}

	/***********
	 * SPRITE ROTATION METHODS
	 * *********
	 */
	
	/**
	 * Rotates frog clockwise
	 */
	public void rotateClockwise()
	{
		if (direction.toString() == "LEFT")
		{
			this.direction = Orientation.UP;
			reloadImage(Orientation.UP);
		}
		else if (direction.toString() == "UP")
		{
			this.direction = Orientation.RIGHT;
			reloadImage(Orientation.RIGHT);
		}
		else if (direction.toString() == "RIGHT")
		{
			this.direction = Orientation.DOWN;
			reloadImage(Orientation.DOWN);

		}
		else if (direction.toString() == "DOWN")
		{
			this.direction = Orientation.LEFT;
			reloadImage(Orientation.LEFT);
		}
		
	}

	
	/**
	 * Rotates frog counter clockwise
	 */
	public void rotateCounterClockwise()
	{
		if (direction.toString() == "LEFT")
		{
			this.direction = Orientation.DOWN;
			reloadImage(Orientation.DOWN);
		}
		else if (direction.toString() == "UP")
		{
			this.direction = Orientation.LEFT;
			reloadImage(Orientation.LEFT);
		}
		else if (direction.toString() == "RIGHT")
		{
			this.direction = Orientation.UP;
			reloadImage(Orientation.UP);

		}
		else if (direction.toString() == "DOWN")
		{
			this.direction = Orientation.RIGHT;
			reloadImage(Orientation.RIGHT);
		}
		
	}
	
	/**
	 * Rotates frog by 180 degrees
	 */
	public void rotate180()
	{
		if (direction.toString() == "LEFT")
		{
			this.direction = Orientation.RIGHT;
			reloadImage(Orientation.RIGHT);
		}
		else if (direction.toString() == "UP")
		{
			this.direction = Orientation.DOWN;
			reloadImage(Orientation.DOWN);
		}
		else if (direction.toString() == "RIGHT")
		{
			this.direction = Orientation.LEFT;
			reloadImage(Orientation.LEFT);

		}
		else if (direction.toString() == "DOWN")
		{
			this.direction = Orientation.UP;
			reloadImage(Orientation.UP);
		}
		
	}
	
	/***********
	 * SPRITE DRAWING METHODS
	 * *********
	 */
	
	@Override
	void draw(Graphics g) {		
		g.drawImage(this.image, this.x, this.y, this.view);	
	}

	
	/**
	 * Loads the correct image based on the direction
	 * @param direction = direction based on key pressed
	 */
	public void reloadImage(Orientation direction) {
				
		// TODO Draw the position based on the position passed in
		if (direction == Orientation.LEFT) {			
			this.image = loadImage("src/Images_for_Frogger/frog-left.png");
			setChanged();
	    	notifyObservers(); 
		}
		else if (direction == Orientation.UP) {
			this.image = loadImage("src/Images_for_Frogger/frog-up.png");
			setChanged();
	    	notifyObservers(); 	
		}
		else if (direction == Orientation.DOWN) {
			this.image = loadImage("src/Images_for_Frogger/frog-down.png");
			setChanged();
	    	notifyObservers(); 
		}
		else if (direction == Orientation.RIGHT) {
			this.image = loadImage("src/Images_for_Frogger/frog-right.png");
			setChanged();
	    	notifyObservers(); 
		}	
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
    
	/***********
	 * SPRITE DIRECTION METHODS
	 * *********
	 */
	
	public void setDirection(Orientation position){
		this.direction = position;
	}
	
	public Orientation getDirection() {
		return this.direction;
	}

	/***********
	 * GETTER METHODS
	 * *********
	 */
    
	@Override
	int getX() {
		return this.x;
	}

	@Override
	int getY() {
		return this.y;
	}


	@Override
	void setDX(int newVelocity) {
		//Not applicable for Frog object
	}




	@Override
	int getImageWidth() {
		return this.image.getWidth(view);
	}




	@Override
	int getImageHeight() {
		return this.image.getHeight(view);
	}
	
	@Override
	public String getType(){
		return "Frog";
	}

	
}