package frogger;

import java.awt.Color;
import java.awt.Graphics;


public class Background {

	private static final int centerDivideHeight = 10;
	private static final int centerDivideWidth = 20;
	
	protected View view; // this is assigned by the Frogger class
	private int width;
	private int height;
	
	public Background() {}
	
	
	/**
	 * Method to draw background onto canvas
	 * @param g
	 */
	public void draw(Graphics g){

		this.width = view.getWidth();
		this.height = view.getHeight();
		
		// draw the road
		
		int margin = height / 4;
		
		g.setColor(Color.GRAY);
		g.fillRect(0, margin, width, margin * 2);

		// Draw the center divide
		
		int xCoordinate = 0;
		int yCoordinate = (height / 2) - (centerDivideHeight / 2);
		
		g.setColor(Color.YELLOW);
		
		while (width > (xCoordinate + centerDivideWidth)){
			g.fillRect(xCoordinate, yCoordinate, centerDivideWidth, centerDivideHeight);
			xCoordinate = xCoordinate + (2 * centerDivideWidth);
		}	
	}
}
