package frogger;

import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;
public class View extends JPanel implements Observer{

	protected Background background; // This is set by the Froger class
	protected Frogger controller;// This is set by the Froger class

	public View(){}
	
	/**
	 * Every time repaint() method is called, paint(Graphics g) redraws all graphics on the canvas
	 * (ie the background and the sprites)
	 */
    @Override
    public void paint(Graphics g) {

		this.background = controller.getBackgroundObject();
    	background.draw(g);
    	
    	Cast cast = controller.getCast();
    	ArrayList<Sprite> spriteList = cast.getSprites();
    	int castSize = spriteList.size();
    	for (int i = 0; i < castSize; i++){
    		Sprite sprite = spriteList.get(i);
    		sprite.draw(g);
    	}
    }

    /**
     * This is called when Sprites call notifyObserver() (ie when the Sprites move) 
     */
	@Override
	public void update(Observable arg0, Object arg1) {
		repaint();
		
	}

    
	
}
