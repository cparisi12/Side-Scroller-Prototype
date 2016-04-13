package states;


import java.awt.*;
import java.awt.event.KeyEvent;

import systemComponents.Sprite;

/**
 * The MenuState acts as a Main Menu, and a basis for all menu's to inherit from
 *
 */
public class MenuState extends GameState {

	/**
	 * A reference to the background image
	 */
	protected Sprite bg;

	/**
	 * The index of the current choice that the user has selected
	 */
	protected int currentChoice = 0;

	/**
	 * The options for the menu
	 */
	protected String[] options = {
			"Start",
			"Help",
			"Quit"
	};

	/**
	 * The color of the title
	 */
	protected Color titleColor;

	/**
	 * The font used for the title
	 */
	protected Font titleFont;

	/**
	 * The normal font
	 */
	protected Font font;

	/**
	 * The default constructor is unimplemented
	 */
	public MenuState(){}

	/**
	 * The Constructor takes a game state manager and initializes everything
	 */
	public MenuState(GameStateManager gsm) {

		// Store Game State Manager
		this.gsm = gsm;

		//Setup Background image
		bg = new Sprite("/res/sprites/bg.png",800,600);

		//Setup title text
		titleColor = new Color(255, 255, 255);
		titleFont = new Font(
				"Century Gothic",
				Font.PLAIN,
				28);
		font = new Font("Arial", Font.PLAIN, 16);
	}

	/**
	 * Update the keyboard
	 */
	public void update()
	{
		keyboard.updateKeyStroke();

		handleKey();

	}

	/**
	 * Select the current menu option and perform its functionality
	 */
	protected void select()
	{
		// 0 us play level
		if(currentChoice == 0) {
			Level l = new Level();
			l.setPreviousState(this);
			l.setGameStateManager(gsm);
			gsm.setState(l);
		}
		// 1 is help menu
		else if(currentChoice == 1) {
			HelpMenu h = new HelpMenu(gsm);
			h.setPreviousState(this);
			gsm.setState(h);
		}
		// 2 is exit
		else if(currentChoice == 2) {
			System.exit(0);
		}
	}

	/**
	 * Perform teh appropriate operations based on whatever keys were pressed.
	 */
	protected void handleKey() {

		//Press key ENTER
		if(keyboard.getEnterTap()){
			select();
		}
		//Press Key UP (Scroll up)
		if(keyboard.getUpTap()) {
			currentChoice--;
			if(currentChoice == -1) {
				currentChoice = options.length - 1;
			}
		}
		//Press Key DOWN (Scroll down)
		if(keyboard.getDownTap()) {
			currentChoice++;
			if(currentChoice == options.length) {
				currentChoice = 0;
			}
		}
	}

	/**
	 * Draw the menu
	 */
	public void render(Graphics g) {

		Font f = g.getFont();
		//Draw Background
		bg.draw(g,0,0);

		//Draw the title
		g.setColor(titleColor);
		g.setFont(titleFont);
		//Draw the Strings (TEXT,X,Y)
		g.drawString("Project S.S. East", 325, 70);
		g.drawString("A Moderately Firmware Prototype", 200, 100);

		// Draw menu options
		g.setFont(font);
		for(int i = 0; i < options.length; i++) {
			if(i == currentChoice) {
				g.setColor(Color.WHITE);
			}
			else {
				g.setColor(Color.RED);
			}
			g.drawString(options[i], 390, 260 + i * 50);
		}
		g.setFont(f);

	}

	/**
	 * Set the array of options
	 */
	protected void setOptions(String[] op)
	{
		options = op;
	}

}










