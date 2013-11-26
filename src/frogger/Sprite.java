package frogger;

import java.awt.Graphics;
import java.awt.Image;
import java.util.Observable;

public abstract class Sprite extends Observable{

	int x, y; 
	int dx,  dy;
	abstract void update(); 
	abstract void draw(Graphics g);
	
	abstract Image getImage();
	abstract int getX();
	abstract int getY();
}
