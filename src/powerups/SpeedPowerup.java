package powerups;
import java.awt.Graphics;

import systemComponents.Sprite;
import vital.Hitbox;
import vital.MovableEntity;

/**
 * Powerup which looks like a yellow lightning bolt
 *
 */
public class SpeedPowerup extends MovableEntity
{
	/**
	 * Reference to the sprite of this power up
	 */
	private static Sprite sprite;
	
	/**
	 * Default constructor randomly places power up
	 */
	public SpeedPowerup()
	{
		sprite = new Sprite("/res/sprites/SpeedUpPowerUp.png", 96, 96);
		x = (int) (Math.random()*500);
		y = (int) (Math.random()*500);
		hitbox = new Hitbox();
		hitbox.updateDimensions(x, y, (int)(96*.5), (int)(96*.5));
		hitbox.activate();
	}
	
	/**
	 * Constructor loads and sets location
	 */
	public SpeedPowerup(int x, int y){
		sprite = new Sprite("/res/sprites/SpeedUpPowerUp.png", 96, 96);
		this.x = x;
		this.y = y;
		hitbox = new Hitbox();
		hitbox.updateDimensions(x, y, (int)(96*.5), (int)(96*.5));
		hitbox.activate();
	}
	
	/**
	 * Update the frame of the sprite
	 */
	public void update()
	{
		sprite.nextFrame();
	}
	
	/**
	 * Draw the sprite to its location
	 */
	public void draw(Graphics pane)
	{
		sprite.draw(pane, x, y, .5, .5);
	}
}
