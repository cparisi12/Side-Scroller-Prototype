package states;


/**
 * A GameStateManager can set a state, and also update that State
 *
 */
public interface GameStateManager
{	
	/**
	 * Sets the state being run
	 */
	public void setState(GameState state);
	
	/**
	 * Updates values
	 */
	public void update();
	
	
}









