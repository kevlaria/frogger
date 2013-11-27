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
	 * Method to identify type of Sprite object is
	 * TODO - determine whether we still need this
	 * @return
	 */
	abstract String getSpriteType();
	
	/***********
	 * GETTER AND SETTER METHODS
	 * TODO - determine whether we still need these
	 * *********
	 */
	
	abstract Image getImage();
	abstract int getX();
	abstract int getY();
}
