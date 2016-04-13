package vital;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import states.*;
import systemComponents.*;

/**
 * The Game class controls which state is run, and also controls a window to act as the
 * Graphical User Interface
 * @author Kile_DaSilva
 *
 */
public class Game extends Canvas implements AlarmListener, GameStateManager
{
	/**
	 *  A JFrame to show the program
	 */
	private JFrame frame;
	
	/**
	 *  A Window to determine when the game is closed
	 */
	private Window window;
	
	/**
	 *  A Keyboard to receive key input
	 */
	private Keyboard keyboard;	
	
	/**
	 *  An Alarm to determine when to update
	 */
	private Alarm alarm;
	
	/**
	 *  Controls the speed of the alarm
	 */
	private int delay;
	
	/**
	 *  The dimensions of the frame
	 */
	private final int WIDTH, HEIGHT;

	/**
	 * The state currently being shown
	 */
	private GameState currentState;
	
	/**
	 *  The default constructor sets up the window, the keyboard, and initiates the alarm
	 */
	public Game()
	{
		keyboard = new Keyboard();
		window = new Window();
		
		WIDTH = 800;
		HEIGHT = 600;
		
		// Set up the JFrame
		frame = new JFrame();
		frame.setUndecorated(true);
		frame.addWindowListener(window);
		this.addKeyListener(keyboard);
		frame.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		frame.setResizable(false);
		frame.setTitle("Project Depth");
		frame.pack();
		frame.add(this);
		frame.setVisible(true);
		
		GameState.setKeyboard(keyboard);
		
		// Set up the level (for drive only)
		currentState = new MenuState(this);
		
		// Set up the update-timer
		delay = 16;
		alarm = new Alarm(this);
		alarm.setPeriod(delay);
		
		alarm.start();
	}
	
	
	/**
	 *  Every time the Alarm activates, this method will run. Call update.
	 */
	public void tick()
	{
		update();
	}
	
	/**
	 * Draw the current state to the frame
	 */
	public void render()
	{
		// Create a local buffer strategy
		BufferStrategy bs = getBufferStrategy();

		// If there is no current buffer strategy make one, exit method
		if (bs == null)
		{
			createBufferStrategy(2);
			return;
		}

		// Set the pane to the buffer
		Graphics pane = bs.getDrawGraphics();

		// Draw a black background
		pane.setColor(Color.black);
		//pane.fillRect(0, 0, WIDTH, HEIGHT);
		
		// Draw the contents of the screen object
		currentState.render(pane);
		
		// Show the contents of the buffer
		bs.show();

		// Clear the contents of the pane
		pane.dispose();
	}
	
	/**
	 * set the current state
	 */
	public void setState(GameState state) {
		currentState = state;
	}


	@Override
	/**
	 * Update the logic of the game and render everything
	 */
	public void update() {
		if(keyboard.getEsc())
			System.exit(0);
		
		try{
		currentState.update();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			String s = "A critical error has occurred and the application must now shut down";
			JOptionPane.showMessageDialog(frame, s, "Application Failure", 
					JOptionPane.ERROR_MESSAGE);
			System.exit(0);
		}
		render();
	}
	
	
}
