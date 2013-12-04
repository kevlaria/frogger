package frogger;

import java.util.ArrayList;


public class Cast {
	 ArrayList<Sprite> spriteList = new ArrayList<Sprite>();
	
	public ArrayList<Sprite> getSprites(){
		return this.spriteList;	
	}
	
	public void addSprites(Sprite sprite){
		spriteList.add(sprite);	
	}

	
	public void removeSprites(Sprite sprite){
		spriteList.remove(sprite);	
	}
	
	public void setSprites(Sprite sprite, int position){
		spriteList.set(position,sprite);
	}
}
