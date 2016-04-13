package powerups;
import java.awt.Graphics;

import systemComponents.Sprite;
import vital.Hitbox;
import vital.MovableEntity;

/**
 * Power up which looks like a skull
 *
 */
public class ZombiePowerup extends MovableEntity
{
	/**
	 * Reference to the sprite of this power up
	 */
	private static Sprite sprite;
	
	/**
	 * Default constructor randomly places power up
	 */
	public ZombiePowerup(){
		sprite = new Sprite("/res/sprites/ZombiePowerUp.png", 96, 96);
		x = (int) (Math.random()*500);
		y = (int) (Math.random()*500);
		hitbox = new Hitbox();
		hitbox.updateDimensions(x, y, (int)(96*.5), (int)(96*.5));
		hitbox.activate();
	}
	

	/**
	 * Constructor loads and sets location
	 */
	public ZombiePowerup(int x, int y){
		sprite = new Sprite("/res/sprites/ZombiePowerUp.png", 96, 96);
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
