package states;

import java.awt.Color;
import java.awt.Graphics;

import systemComponents.Sprite;

/**
 * The Help menu displays an image with all controls on it.
 *
 */
public class HelpMenu extends MenuState
{
	/**
	 * Constructor takes a GameStateManager and sets options
	 */
	public HelpMenu(GameStateManager gsm)
	{
		// Set the game state manager
		this.gsm = gsm;

		String[] op = {"Return"};

		bg = new Sprite("/res/sprites/Help 800 x 600.png",800,600);

		setOptions(op);
	}

	/**
	 * Draw the menu
	 */
	public void render (Graphics g)
	{
		// Draw background
		bg.draw(g, 0, 0);

	}

	/**
	 * Only one option, to return
	 */
	protected void select()
	{
		if(currentChoice == 0) {
			gsm.setState(getPreviousState());
		}			
	}
}
