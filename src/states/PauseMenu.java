package states;

import java.awt.Color;
import java.awt.Graphics;

/**
 * The Pause Menu does not take up the full screen, and shows the user the option to
 * resume whatever state they were previously on, restart the level, or return to
 * the main menu.
 *
 */
public class PauseMenu extends MenuState
{

	/**
	 * The constructor takes a GameStateManager and sets up the options
	 */
	public PauseMenu(GameStateManager gsm)
	{
		// Set the game state manager
		this.gsm = gsm;

		String[] op = {"Resume", "Restart", "Main Menu"};

		setOptions(op);
	}

	/**
	 * Render draws the menu to the screen
	 */
	public void render (Graphics g)
	{

		g.setColor(Color.black);
		g.fillRect(115, 55, 200, 100);
		g.setColor(Color.white);
		g.drawRect(115, 55, 200, 100);
		//Draw the Strings (TEXT,X,Y)
		g.drawString("Paused", 130, 70);

		// Draw menu options
		for(int i = 0; i < options.length; i++) {
			if(i == currentChoice) {
				g.setColor(Color.WHITE);
			}
			else {
				g.setColor(Color.RED);
			}
			g.drawString(options[i], 190, 82 + i * 20);
		}

	}

	/**
	 * Select will perform an action based on the currently selected option.
	 */
	protected void select()
	{
		// 0 is resume
		if(currentChoice == 0) {
			gsm.setState(getPreviousState());
		}
		// 1 is restart
		else if(currentChoice == 1) {
			Level l = new Level();
			l.setGameStateManager(gsm);
			l.setPreviousState(previous.getPreviousState());
			gsm.setState(l);
		}
		// 2 is main menu
		else if(currentChoice == 2) {
			gsm.setState(getPreviousState().getPreviousState());
		}

	}
}
