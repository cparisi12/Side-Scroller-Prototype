package states;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

/**
 * The game over menu shows when the user is out of lives, and allows the user to determine
 * if they should return to the main menu, restart, or quit.
 *
 */
public class GameOverMenu extends MenuState
{
	/**
	 * Constructor sets the game state manager and sets up the options
	 * 
	 */
	public GameOverMenu(GameStateManager gsm)
	{
		// Set the game state manager
		this.gsm = gsm;

		String[] op = {"Restart", "Main Menu", "Quit"};

		setOptions(op);
	}

	/**
	 * Draws the menu
	 */
	public void render (Graphics g)
	{

		Font font = g.getFont();

		g.setColor(Color.black);
		g.fillRect(0, 0, 800, 600);

		g.setFont(new Font(
				"Century Gothic",
				Font.PLAIN,
				28));
		g.setColor(Color.white);
		//Draw the Strings (TEXT,X,Y)
		g.drawString("Game Over", 325, 70);

		g.setFont(font);

		// Draw menu options
		for(int i = 0; i < options.length; i++) {
			if(i == currentChoice) {
				g.setColor(Color.WHITE);
			}
			else {
				g.setColor(Color.RED);
			}
			g.drawString(options[i], 395, 122 + i * 50);
		}

	}

	/**
	 * Select the user selected option and perform its functionality
	 */
	protected void select()
	{
		// 0 is restart the level
		if(currentChoice == 0) {
			Level l = new Level();
			l.setGameStateManager(gsm);
			l.setPreviousState(new MenuState(gsm));
			gsm.setState(l);
		}
		// 1 is return to main menu
		else if(currentChoice == 1) {
			MenuState m = new MenuState(gsm);
			gsm.setState(m);
		}
		// 2 is exit
		else if(currentChoice == 2)
		{
			System.exit(0);
		}

	}
}
