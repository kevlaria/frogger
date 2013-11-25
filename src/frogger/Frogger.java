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
	
	/**
	 * Launch the application.
	 */
	public Frogger(){
		this.cast = new Cast();
		this.view = new View();
	}
	
	public static void main(String[] args) {
		new Frogger().run();
	}
	
	public void run(){	
		view.controller = this;
		
		this.cast.addSprites(new Car());
		this.cast.addSprites(new Car());
		this.cast.addSprites(new Car());
		
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
	

	
	/***********
	 * GETTER AND SETTER METHODS
	 * *********
	 */
	public Cast getCast(){
		return this.cast;
	}
	
}
