package objects;

import java.awt.Graphics;

import systemComponents.Sprite;
import vital.Entity;
import vital.Hitbox;

/**
 * A Ufo is an animated platform. It draws a 100 by 50 Entity which looks like a UFO.
 * @author Kile_DaSilva
 *
 */
public class Ufo extends Entity
{
	/**
	 * The Sprite reference is used to hold the animated image of a UFO
	 */
	private static Sprite sprite;

	/**
	 * The animation timer is used to tell how many updates before the next frame of the animation
	 * is shown
	 */
	private int animationTimer = 40;
	
	/**
	 * The Constant ANIM_TIME is the amount of time that the animation timer uses
	 */
	private final int ANIM_TIME = 40;
	
	/**
	 * The constructor takes a starting x and y location for this ufo to be placed
	 */
	public Ufo(int x, int y)
	{
		place(x,y);
		setDimensions(100,50);
		sprite = new Sprite("/res/sprites/ufo anim 100x50.png",100,50);
		this.hitbox = new Hitbox();
		hitbox.updateDimensions(x, y, width, height);
	}
	
	/**
	 * Update is used just for animating the next frame of the sprite (if the sprite's animation
	 * counter is zero)
	 */
	public void update()
	{
		if(animationTimer--<=0)
		{
			animationTimer = ANIM_TIME;
			sprite.nextFrame();
		}
	}

	/**
	 * draws the sprite to its location on the frame.
	 */
	public void draw(Graphics pane)
	{
		sprite.draw(pane, x, y);
		
	}
}
