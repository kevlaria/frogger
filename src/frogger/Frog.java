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
	boolean Alive;
	Orientation direction;
	public enum Orientation { UP, RIGHT, DOWN, LEFT };
	
	//int maxY = 1000;
//	int maxX = 1000;
	
   private static final int minX = 300;
   private static final int maxX = 800;
   private static final int maxY = 0;
   private static final int minY = 0;

	/**
	 * Constructor for frog
	 */
	public Frog()
	{
		this.image = loadImage("src/Images_for_Frogger/frog-up.png");
		direction = Orientation.UP;
		Alive = true;
		this.x =350;
		this.y =175;
	}
	



	@Override
	void update() {
		// TODO Auto-generated method stub
		//this.image = loadImage("src/Images_for_Frogger/frog-right.png");
		
		
		//Update the current position
		dx=0;
		dy=0;
		moveForward();
		x = x + dx;
		y = y + dy;
		//this.x += 100;
		setChanged();
    	notifyObservers(); 
	}

	public void setDirection(Orientation position){
		this.direction = position;
	}
	
	
	public Orientation getDirection()
	{
		return this.direction;
	}
	

	
	
	/**
	 * Method Move Forward:
	 * This gets the current orientation of the object.
	 * When the user pushes the up button, this code is activated.
	 * When this method is called, it will move the position of the frog 
	 * + or - 1 depending on the current orientaiton of the frog.
	 */
	public void moveForward()
	{
		if (direction.toString() == "LEFT")
		{
			if ((x-35) > minX)
			{
			// OR UPDATE DX
				dx = -35;
			}
			else
			{
				System.out.println("Out of bounds");
			}
			
		}
		else if (direction.toString() == "UP")
		{
			if ((y-35) > minY)
			{
				dy = -35;
			}
			else
			{
				System.out.println("Out of bounds");
			}
			
		}
		else if (direction.toString() == "DOWN")
		{
			if ((y+35) < maxY)
			{
				dy= +35;
			}
			else
			{
				System.out.println("Out of bounds");
			}
		}
		else if (direction.toString() == "RIGHT")
		{
			if ((x+35) < maxX)
			{
			 dx= +35;
			}
			else
			{
				System.out.println("Out of bounds");
			}
		}
		
	}
	
	public void squishFrog() throws InterruptedException
	{
		this.image = loadImage("src/Images_for_Frogger/splat.gif");
		
		setChanged();
    	notifyObservers(); 
    	//Wait a second 
    	Thread.currentThread().sleep(1000);
	}
	
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
	
	
	public boolean crossedRoad()
	{
		//Gets The "Current locaiton of the frog"
		int currentY = this.y;
		//Check to see if the Y = -100 
		
		if (currentY <= 50)
		{
			return true;
		}
		else
		{
			return false;
		}
		
		// If so Reset the frog
		// score + 1
		
		
	}
	
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
	/**
	 * Loads the correct image based on the direction
	 * 
	 */
	public void reloadImage(Orientation direction) {
				
		// TODO Draw the position based on the position passed in
		if (direction == Orientation.LEFT)
		{
			
			this.image = loadImage("src/Images_for_Frogger/frog-left.png");
			setChanged();
	    	notifyObservers(); 
	    

		}
		else if (direction == Orientation.UP)
		{
			this.image = loadImage("src/Images_for_Frogger/frog-up.png");
			setChanged();
	    	notifyObservers(); 	
		}
		else if (direction == Orientation.DOWN)
		{
			this.image = loadImage("src/Images_for_Frogger/frog-down.png");
			setChanged();
	    	notifyObservers(); 
		}
		else if (direction == Orientation.RIGHT)
		{
			this.image = loadImage("src/Images_for_Frogger/frog-right.png");
			setChanged();
	    	notifyObservers(); 
		}	
	
	}
		
	
	
	
///////////////////////////

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
    


	@Override
	String getSpriteType() {
		// TODO Auto-generated method stub
		return "Frog";
	}



	@Override
	Image getImage() {
		// TODO Auto-generated method stub
		return this.image;
	}


	@Override
	int getX() {
		// TODO Auto-generated method stub
		return this.x;
	}


	@Override
	int getY() {
		// TODO Auto-generated method stub
		return this.y;
	}





	@Override
	void draw(Graphics g) {
		
		g.drawImage(this.image, x, y, this.view);	
		
	}



	@Override
	void changeVelocity(int newVelocity) {
		
	}

	
}