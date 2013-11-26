package frogger;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;
public class View extends JPanel implements Observer{

	Car car;
	protected Background background;
	protected Frogger controller;

	public View(){
	}
	
	
    @Override
    public void paint(Graphics g) {

		this.background = controller.getBackgroundObject();

    	background.draw(g);
    	
    	Cast cast = controller.getCast();
    	ArrayList<Sprite> spriteList = cast.getSprites();
    	int castSize = spriteList.size();
    	for (int i = 0; i < castSize; i++){
    		Sprite sprite = spriteList.get(i);
    		Image spriteImage = sprite.getImage();
    		sprite.draw(g);
    	}
    }


	@Override
	public void update(Observable arg0, Object arg1) {
		repaint();
		
	}
	

    
	
}
