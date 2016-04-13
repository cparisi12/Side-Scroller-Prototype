package objects;

import java.awt.Graphics;

import systemComponents.Sprite;
import vital.Entity;
import vital.Hitbox;

/**
 * Moon Ground to Stand on
 * @author Kile_DaSilva
 *
 */
public class Ground extends Entity
{
	/**
	 * A reference to the sprite of this piece of Ground
	 */
	private static Sprite sprite;

	/**
	 * The constructor takes the x and y location of the ground, and sets up the hitbox accordingly.
	 */
	public Ground(int x, int y)
	{
		place(x,y);
		setDimensions(400,100);
		if(sprite==null)
			sprite = new Sprite("/res/sprites/ground 400 x 100.png",400,100);
		this.hitbox = new Hitbox();
		hitbox.updateDimensions(x, y, width, height);
	}
	
	/**
	 * Update is unused in this class
	 */
	public void update() {
		
	}

	/**
	 * Draws the sprite of the ground to its location on the frame
	 */
	public void draw(Graphics pane) {
		sprite.draw(pane, x, y);
		
	}
}
