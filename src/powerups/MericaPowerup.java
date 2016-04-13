package powerups;
import java.awt.Graphics;

import systemComponents.Sprite;
import vital.Hitbox;
import vital.MovableEntity;

/**
 * Powerup which looks like a flag
 *
 */
public class MericaPowerup extends MovableEntity
{
	/**
	 * The sprite for this power up
	 */
	private static Sprite sprite;

	/**
	 * Controls when the next frame of the animation shows
	 */
	private int animationCounter = 0;
	
	/**
	 * The max value that the Animation Counter resets to
	 */
	private final int ANIMA_TIME = 15;
	
	
	/**
	 * Default constructor loads and randomizes location
	 */
	public MericaPowerup(){
		sprite = new Sprite("/res/sprites/MericaPowerUp.png", 96, 96);
		x = (int) (Math.random()*500);
		y = (int) (Math.random()*500);
		hitbox = new Hitbox();
		hitbox.updateDimensions(x, y, (int)(96*.5), (int)(96*.5));
		hitbox.activate();
	}
	
	/**
	 * Constructor loads and sets location
	 */
	public MericaPowerup(int x, int y){
		sprite = new Sprite("/res/sprites/MericaPowerUp.png", 96, 96);
		this.x = x;
		this.y = y;
		hitbox = new Hitbox();
		hitbox.updateDimensions(x, y, (int)(96*.5), (int)(96*.5));
		hitbox.activate();
	}
	
	/**
	 * Update the animation
	 */
	public void update(){
		if(animationCounter--==0)
		{
			sprite.nextFrame();	
			animationCounter=ANIMA_TIME;
		}
	}
	
	/**
	 * Draw the sprite to its location
	 */
	public void draw(Graphics pane)
	{
		sprite.draw(pane, x, y,.5,.5);
	}
}
