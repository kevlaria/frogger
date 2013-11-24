package frogger;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Car extends Sprite{

	Image image;
	
	public Car(){
		draw();
	}
	
	@Override
	void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	void draw() {
		this.image = loadImage("src/Images_for_Frogger/white-car-right.png");
	}
	
	public Image loadImage(String fileName){
		BufferedImage img = null;
		try {
		    img = ImageIO.read(new File(fileName));
		}
		catch (IOException e) {
            System.out.println("Can't load image.");
		}
		return img;
		
	}

	@Override
	Image getImage() {
		return this.image;
	}

}
