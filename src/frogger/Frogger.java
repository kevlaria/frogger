package frogger;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
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



public class Frogger extends JFrame implements Runnable{

	Cast cast;
	View view;
	JFrame frame;
	private Background background;
	private Timer timer;
	private JButton goButton;
	private JButton pauseButton;
	private JPanel buttonPanel;
	private int carCount;
	public enum Orientation { UP, RIGHT, DOWN, LEFT };

	protected Frog frog;

	
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
	
		
		//Add Frog
		this.frog = this.createNewFrog();
		this.cast.addSprites(frog);
		
		this.frame = this.drawJFrame();
 		this.frame.add(view, BorderLayout.CENTER);
 		
 		this.buttonPanel = this.createButtonPanel();
 		this.frame.add(buttonPanel, BorderLayout.SOUTH);
		this.frame.setVisible(true);
		this.frame.requestFocus();
		pack();
	    setDefaultCloseOperation(EXIT_ON_CLOSE);
	    
        this.frame.addKeyListener(new KeyListener() {
          
			//Cast cast = getCast();
			//ArrayList<Sprite> spriteList = cast.getSprites();
		
			@Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_UP) {
                    System.out.println("Up arrow pressed.");
  
                    //frog.setDirection(Orientation.LEFT);
                    //Should call method to move forward
                    frog.update();
                    frame.requestFocus();
                    frame.repaint();
       
                }
                if(e.getKeyCode() == KeyEvent.VK_DOWN) {
                    System.out.println("Down arrow pressed.");
                    frog.rotate180();
                    frame.requestFocus();
                    frame.repaint();
        
                    
                }
                if(e.getKeyCode() == KeyEvent.VK_LEFT) {
                    System.out.println("Left arrow pressed.");
                    frog.rotateCounterClockwise();
                    frame.requestFocus();
                    frame.repaint();
                }
                if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    System.out.println("Right arrow pressed.");
                    frog.rotateClockwise();
                    frame.requestFocus();
                    frame.repaint();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                System.out.println("Released key " + e.getKeyCode());
            }

            @Override
            public void keyTyped(KeyEvent e) {
                System.out.println("Typed key " + e.getKeyChar());
            }
        });
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
		goButton.setFocusable(false);
		this.pauseButton = new JButton("Pause");
		pauseButton.setFocusable(false);
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
	

	/**
	 * Method to create a new Car object
	 * @return a Car object
	 */
	public Frog createNewFrog(){
		Frog frog = new Frog();
		frog.view = this.view;
		frog.addObserver(view);
		return frog;
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
	    	//Every Sprite except the last sprite, Frog 
	    	for (int i = 0; i < castSize-1; i++){
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
