package frogger;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class Frog extends Sprite {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	



	@Override
	void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	void draw() {
		// TODO Auto-generated method stub
		
		BufferedImage img = null;
		try {
		    img = ImageIO.read(new File("frog.jpg"));
		}
		catch (IOException e) {
	//	throw new IOExceptionError;
		
		}
		

		
	}

}
