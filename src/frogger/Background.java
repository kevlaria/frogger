// Gary Cheung, Kevin Lee

package frogger;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;


public class Background {

	private static final int centerDivideHeight = 10;
	private static final int centerDivideWidth = 20;
	
	private View view; // this is assigned by the Frogger class
	private int width;
	private int height;
	private boolean isGameOver; // this is assigned by the Frogger class
	
	/**
	 * Constructor for Background
	 */
	public Background() {
		this.isGameOver = false;
	}
	
	
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
		if (this.isGameOver){
			// Print "GAME OVER"
			g.setColor(Color.RED);
			g.setFont(new Font("San Serif", Font.BOLD, 50));
			g.drawString("GAME OVER", view.getWidth() / 4 + 25, 50);	
		}
		
	}

	
	/***********
	 * SETTER METHODS
	 * *********
	 */
	
	public void setView(View view){
		this.view = view;
	}
	
	public void setIsGameOver(boolean setting){
		this.isGameOver = setting;
	}
}
