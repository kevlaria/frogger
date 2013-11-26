package frogger;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


public class Frogger extends JFrame implements Runnable{

	private Cast cast;
	private View view;
	private JFrame frame;
	private Car car;
	private Background background;
	
	/**
	 * Launch the application.
	 */
	public Frogger(){
		this.cast = new Cast();
		this.view = new View();
		this.background = new Background();
		
	}
	
	public static void main(String[] args) {
		new Frogger().run();
	}
	
	public void run(){	
		
		/**
		 * Controller assigns Background and View to each other, as well as the controller
		 */
		this.view.controller = this;
		this.background.controller = this;
		
		background.view = this.view;
		
		this.cast.addSprites(this.createNewCar());
		this.cast.addSprites(this.createNewCar());
		this.cast.addSprites(this.createNewCar());
		
		this.frame = this.drawJFrame();
 		this.frame.add(view);
		this.frame.setVisible(true);
	}
	
	
	public JFrame drawJFrame(){
		frame = new JFrame();
		frame.setTitle("Frogger");
		frame.setMinimumSize(new Dimension(800, 200));
	 	frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		return frame;
	}
	
	
	public Car createNewCar(){
		Car car = new Car();
		car.view = this.view;
		return car;
	}

	
	/***********
	 * GETTER AND SETTER METHODS
	 * *********
	 */
	public Cast getCast(){
		return this.cast;
	}
	
	public Background getBackgroundObject(){
		return this.background;
	}
	
	public View getView(){
		return this.view;
	}
	
}
