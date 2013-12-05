package frogger;

import java.awt.Graphics;
import java.awt.Image;
import java.util.Observable;

public abstract class Sprite extends Observable{

	int x, y; 
	int dx,  dy;
	
	/**
	 * Makes all necessary changes in the state of this sprite before the next time it is drawn.
	 */
	abstract void update(); 
	
	/**
	 * Method to draw object onto the canvas (ie the View canvas)
	 * @param g
	 */
	abstract void draw(Graphics g);

	/**
	 * Changes the velocity of each sprite
	 * @param newVelocity
	 */
	abstract void setDX(int newVelocity);

	
	/***********
	 * GETTER AND SETTER METHODS
	 * *********
	 */
	
	abstract int getX();
	abstract int getY();
	abstract int getImageWidth();
	abstract int getImageHeight();
	abstract String getType();

	
}
