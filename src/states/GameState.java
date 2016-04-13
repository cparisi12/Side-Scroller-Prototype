package states;

import systemComponents.Keyboard;

/**
 * A Game State is a stage of the game with functionality, such as a menu, or a level.
 *
 */
public abstract class GameState {
	
	/**
	 * A GameStateManager reference for telling it to switch states
	 */
	protected GameStateManager gsm;
	
	/**
	 * A reference to the previous state, for returning purposes
	 */
	protected GameState previous;
	
	/**
	 * A reference to the keyboard for user input
	 */
	protected static Keyboard keyboard;
	
	/**
	 * Update is where the logic of the state is calculated. 
	 */
	public abstract void update();
	
	/**
	 * Render is where the state is drawn
	 */
	public abstract void render(java.awt.Graphics g);
	
	/**
	 * Sets the static reference to the keyboard for all classes extending this
	 */
	public static void setKeyboard(Keyboard key)
	{
		keyboard = key;
	}

	/**
	 * Returns a reference to the previous state
	 */
	public GameState getPreviousState()
	{
		return previous;
	}
	
	/**
	 * Sets the previous state
	 */
	public void setPreviousState(GameState previous)
	{
		this.previous = previous;
	}
}
