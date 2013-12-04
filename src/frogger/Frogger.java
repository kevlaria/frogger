package frogger;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;



public class Frogger extends JFrame implements Runnable{

	private JFrame frame;
	private View view;
	private Background background;
	private JButton goButton;
	private JButton pauseButton;
	private JPanel buttonPanel;

	private Cast cast;
	protected Frog frog;
	public enum Orientation { UP, RIGHT, DOWN, LEFT };
	private int carCount;
	
	private JTextField scorePanel;
	int score;
	private JTextField livesPanel;
	private int lives;
	boolean isGameOver;
	boolean isPaused;
	private Timer timer;

	
	/**
	 * Launch the application.
	 */
	public Frogger(){
		this.cast = new Cast();
		this.view = new View();
		this.background = new Background();
		this.carCount = 0;
		this.score = 0;
		this.lives = 3;
		this.isGameOver = false;		
		this.isPaused = true;
	}
	
	public static void main(String[] args) {
		new Frogger().run();
	}
	
	/**
	 * Method to run the game
	 */
	public void run(){	

		/**
		 * Controller assigns Background and View to each other, as well as the controller
		 */
		this.view.controller = this;		
		this.background.view = this.view;
		
		
		this.addCars(10);
		//Add Frog
		this.frog = this.createNewFrog();
		this.cast.addSprites(frog);
		
		this.frame = this.drawJFrame();
 		this.frame.add(view, BorderLayout.CENTER);
 		
 		this.buttonPanel = this.createControlPanel();
 		this.frame.add(buttonPanel, BorderLayout.SOUTH);
		this.frame.setVisible(true);
		this.frame.requestFocus();
		this.pack();
	    this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	    
	    this.implementKeyListeners();
	        
	}
	

	
	/**
	 * Determines if the game has finished or not. Game finishes if this.lives = 0;
	 * @return boolean to determine whether game has finished or not.
	 */
	public boolean isGameOver(){
		this.isGameOver = false;
		if (this.lives <= 0){
			this.isGameOver = true;
			background.setIsGameOver(true);
		}
		return this.isGameOver;
	}
	
	
	/***********
	 * SPRITE RELATED METHODS
	 * *********
	 */
	
	/**
	 * Method to add cars in the game
	 * @param numberOfCars
	 */
	public void addCars(int numberOfCars){	
		for (int i = 0; i < numberOfCars; i++){
			this.cast.addSprites(this.createNewCar());			
		}
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
	

	/**
	 * Method to create a new Frog object
	 * @return a Frog object
	 */
	public Frog createNewFrog(){
		Frog frog = new Frog();
		frog.view = this.view;
		frog.addObserver(view);
		return frog;
	}
	
	/**
	 * Increase the velocity of cars when score increases
	 * @param velocity
	 */
	public void changeVelocity(int velocity)
	{
		Cast cast = getCast();
		ArrayList<Sprite> spriteList = cast.getSprites();
    	int castSize = spriteList.size();
    	//Every Sprite except the last sprite, Frog 
    	for (int i = 0; i < castSize - 1; i++){
    		Sprite sprite = spriteList.get(i);
    		sprite.changeVelocity(velocity);
    	}
	}



	/**
	 * Method to determine whether frog is hit or not
	 * Compares the x / y coordinates of the frog and all the cars
	 */
	public boolean isFrogSquished(Sprite sprite){	
		boolean isFrogSquished = false;
		
		int spriteWidth = sprite.getImageWidth();
		int spriteHeight = sprite.getImageHeight(); 
		
		int frogWidth = frog.getImageWidth() - 6; // a small offset to adjust for the whitespace borders of the image
		int frogHeight = frog.getImageHeight(); 
		
		int spriteMinX = sprite.getX();
		int spriteMaxX = sprite.getX() + spriteWidth;
		
		int spriteMinY = sprite.getY();
		int spriteMaxY = sprite.getY() + spriteHeight;
		
		int frogMinX = this.frog.getX();
		int frogMaxX = this.frog.getX() + frogWidth;

		int frogMinY = this.frog.getY();
		int frogMaxY = this.frog.getY() + frogHeight;
		
		if (	(frogMinX > spriteMinX && frogMinX < spriteMaxX) || // frog's left side is within car range
			(frogMaxX > spriteMinX && frogMaxX < spriteMaxX)	) { // frog's right side within car range

			if (	(frogMinY > spriteMinY && frogMinY < spriteMaxY) || // frog's bottom side is within car range
						(frogMaxY > spriteMinY && frogMaxY < spriteMaxY)	) { // frog's top side within car range
								isFrogSquished = true;
				    			this.frog.alive = false;
				    			this.lives = lives - 1;
				 }
    	}
    	return isFrogSquished;
	}
	
	/**
	 * Resets the frog when it either crosses the road or gets crushed
	 */
	public void resetFrog()
	{
		this.cast.removeSprites(this.frog);
		this.frog = createNewFrog();
		this.cast.addSprites(this.frog);
	}
	
	
	/***********
	 * COMPONENT CREATION METHODS
	 * *********
	 */

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
	 * Method to create the control panel
	 * @return the control panel
	 */
	public JPanel createControlPanel(){
		JPanel panel = new JPanel();
		panel.setBackground(Color.BLACK);
		panel.setLayout(new BorderLayout());
		
		JPanel buttonPanel = this.createButtonPanel();
		panel.add(buttonPanel, BorderLayout.CENTER);
		
		JPanel scorePanel = this.createScorePanel();
		panel.add(scorePanel, BorderLayout.EAST);
		
		JPanel livesPanel = this.createLivesPanel();
		panel.add(livesPanel, BorderLayout.WEST);

		return panel;
	}

	/**
	 * Method to create the Go / Pause button panel
	 * @return the button panel
	 */
	public JPanel createButtonPanel(){
		JPanel panel = new JPanel();
		panel.setBackground(Color.BLACK);
		this.goButton = new JButton("Go!");
		goButton.setFocusable(false);
		this.pauseButton = new JButton("Pause");
		pauseButton.setFocusable(false);
		panel.add(goButton);
		panel.add(pauseButton);
		this.attachListenersToButtons();
		return panel;
	}
	
	/**
	 * Creates a text panel that registers the score
	 * @return a text panel with the current score
	 */
	public JPanel createScorePanel(){
		JPanel panel = new JPanel();
		panel.setBackground(Color.BLACK);
		
		this.scorePanel = new JTextField();
		this.scorePanel.setFont(new Font("San Serif", Font.BOLD, 15));
		this.scorePanel.setText("Current score: " + Integer.toString(this.score));
		this.scorePanel.setEditable(false);
		panel.add(this.scorePanel);
		return panel;
	}
	
	/**
	 * Creates a text panel that registers the number of lives remaining
	 * @return a text panel with the number of lives remaining
	 */
	public JPanel createLivesPanel(){
		JPanel panel = new JPanel();
		panel.setBackground(Color.BLACK);
		
		this.livesPanel = new JTextField();
		this.livesPanel.setFont(new Font("San Serif", Font.BOLD, 15));
		this.livesPanel.setText("Lives left: " + Integer.toString(this.lives));
		this.livesPanel.setEditable(false);
		panel.add(this.livesPanel);
		return panel;
	}
	
	
	/***********
	 * KEY ACTION COMPONENTS
	 * *********
	 */
	
	/**
	 * Implements key listeners to record movement left / right / forward / backwards
	 */
	public void implementKeyListeners(){
		 
        this.frame.addKeyListener(new KeyListener() {	
			@Override
            public void keyPressed(KeyEvent e) {
				if (!isPaused){
					if(e.getKeyCode() == KeyEvent.VK_UP) {                 
						frog.update();
						Boolean roadCrossed = frog.crossedRoad();
						if (roadCrossed){
							score +=1;
							resetFrog();
							scorePanel.setText("Current score: " + Integer.toString(score));
							int velocity = (score*2) ;
							changeVelocity(velocity);
						}
						frame.requestFocus();
						frame.repaint();
					}
					
					if(e.getKeyCode() == KeyEvent.VK_DOWN) {
						frog.rotate180();
						frame.requestFocus();
						frame.repaint();                    
					}
            
					if(e.getKeyCode() == KeyEvent.VK_LEFT) {
						frog.rotateCounterClockwise();
						frame.requestFocus();
						frame.repaint();
					}
           
					if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
						frog.rotateClockwise();
						frame.requestFocus();
						frame.repaint();
					}
				}
			}//End of isPaused
			
            @Override
            public void keyReleased(KeyEvent e) {
            }

            @Override
            public void keyTyped(KeyEvent e) {
            }	
        });
	}
	
	/***********
	 * BUTTON ACTION COMPONENTS
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
				isPaused = false;
			}
		});
		
		pauseButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				pauseButton.setEnabled(false);
				goButton.setEnabled(true);
				timer.cancel();
				isPaused = true;
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
			
			
			if(!isGameOver()){	 // Only run if the game is not yet over			
				Cast cast = getCast();
				ArrayList<Sprite> spriteList = cast.getSprites();
		    	int castSize = spriteList.size();
		    	
		    	//Every Sprite except the last sprite, Frog 
		    	for (int i = 0; i < castSize-1; i++){
		    		Sprite sprite = spriteList.get(i);
		    		sprite.update();

			    	if (isFrogSquished(sprite)){
			    		livesPanel.setText("Lives left: " + Integer.toString(lives));
			    		try {
							frog.squishFrog();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}			    		
			    		resetFrog();
			    	}
		    	}

			} else { // if game is over, simply repaint a "Game Over" background
				frame.repaint();
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
