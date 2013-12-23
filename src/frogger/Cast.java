package frogger;

import java.util.ArrayList;


public class Cast {

	ArrayList<Sprite> spriteList = new ArrayList<Sprite>();

	/**
	 * Constructor for Cast
	 */
	public Cast(){}
	
	/**
	 * Getter for Cast
	 */	
	public ArrayList<Sprite> getSprites(){
		return this.spriteList;	
	}
	
	/**
	 * Add new sprite to Cast
	 */	
	public void addSprites(Sprite sprite){
		spriteList.add(sprite);	
	}

	/**
	 * Remove sprite from Cast
	 */	
	public void removeSprites(Sprite sprite){
		spriteList.remove(sprite);	
	}
	
}
