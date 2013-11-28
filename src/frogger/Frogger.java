package frogger;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


public class Frogger extends JFrame implements Runnable{

	private Cast cast;
	private View view;
	private JFrame frame;
	private Background background;
	private Timer timer;
	private JButton goButton;
	private JButton pauseButton;
	private JPanel buttonPanel;
	private int carCount;
	
	/**
	 * Launch the application.
	 */
	public Frogger(){
		this.cast = new Cast();
		this.view = new View();
		this.background = new Background();
		this.carCount = 0;
		
	}
	
	public static void main(String[] args) {
		new Frogger().run();
	}
	
	public void run(){	
		
		/**
		 * Controller assigns Background and View to each other, as well as the controller
		 */
		this.view.controller = this;		
		background.view = this.view;
		
		this.cast.addSprites(this.createNewCar());
		this.cast.addSprites(this.createNewCar());
		this.cast.addSprites(this.createNewCar());
		this.cast.addSprites(this.createNewCar());
		this.cast.addSprites(this.createNewCar());
		this.cast.addSprites(this.createNewCar());
		
		this.frame = this.drawJFrame();
 		this.frame.add(view, BorderLayout.CENTER);
 		
 		this.buttonPanel = this.createButtonPanel();
 		this.frame.add(buttonPanel, BorderLayout.SOUTH);
		this.frame.setVisible(true);
	}
	
	/**
	 * Method to create the frame for the game
	 * @return The frame for the game
	 */
	public JFrame drawJFrame(){
		frame = new JFrame();
		frame.setTitle("Frogger");
		frame.setMinimumSize(new Dimension(800, 300));
	 	frame.setResizable(false);
	 	frame.setLayout(new BorderLayout());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		return frame;
	}
	
	/**
	 * Method to create the Go / Pause button panel
	 * @return the button panel
	 */
	public JPanel createButtonPanel(){
		JPanel panel = new JPanel();
		panel.setBackground(Color.BLACK);

		// add buttons
		
		this.goButton = new JButton("Go!");
		this.pauseButton = new JButton("Pause");
		panel.add(goButton);
		panel.add(pauseButton);
		this.attachListenersToButtons();
		return panel;
	}
	
	/**
	 * Method to create a new Car object
	 * @return a Car object
	 */
	public Car createNewCar(){
		Car car = new Car();
		car.view = this.view;
		car.addObserver(view);
		car.initialiseCar(this.carCount);
		this.carCount = this.carCount + 1;
		return car;
	}
	

	/***********
	 * ADDING BUTTON ACTION COMPONENTS
	 * *********
	 */
	
	/**
	 * Method to add functionality to Go and Pause buttons
	 * Go initiates a new timer object when pressed
	 * Pause stops the timer
	 */
	public void attachListenersToButtons(){
		goButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event){
				goButton.setEnabled(false);
				pauseButton.setEnabled(true);
				timer = new Timer(true);
				timer.schedule(new Move(), 0, 50); // task, delay, duration (milliseconds)
			}
		});
		
		pauseButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				pauseButton.setEnabled(false);
				goButton.setEnabled(true);
				timer.cancel();
			}		
		});
	}
	
	/**
	 * Inner class that is implemented when Timer is running
	 * Updates all Sprite objects when Timer runs
	 *
	 */
	private class Move extends TimerTask {
		public void run(){
			Cast cast = getCast();
			ArrayList<Sprite> spriteList = cast.getSprites();
	    	int castSize = spriteList.size();
	    	for (int i = 0; i < castSize; i++){
	    		Sprite sprite = spriteList.get(i);
	    		sprite.update();
	    	}
			
		}
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
