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
	private Car car;
	private Background background;
	private Timer timer;
	private JButton goButton;
	private JButton pauseButton;
	private JPanel buttonPanel;
	
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
 		this.frame.add(view, BorderLayout.CENTER);
 		
 		this.buttonPanel = this.createButtonPanel();
 		this.frame.add(buttonPanel, BorderLayout.SOUTH);
		this.frame.setVisible(true);
		this.attachListenersToButtons();
	}
	
	
	public JFrame drawJFrame(){
		frame = new JFrame();
		frame.setTitle("Frogger");
		frame.setMinimumSize(new Dimension(800, 250));
	 	frame.setResizable(false);
	 	frame.setLayout(new BorderLayout());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		return frame;
	}
	
	public JPanel createButtonPanel(){
		JPanel panel = new JPanel();
		this.goButton = new JButton("Go!");
		this.pauseButton = new JButton("Pause");
		panel.add(goButton);
		panel.add(pauseButton);
		panel.setBackground(Color.BLACK);
		return panel;
	}
	
	
	public Car createNewCar(){
		Car car = new Car();
		car.view = this.view;
		car.addObserver(view);
		return car;
	}
	
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
