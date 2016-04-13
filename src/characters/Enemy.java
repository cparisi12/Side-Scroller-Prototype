package characters;

import java.awt.*;

import javax.swing.ImageIcon;

import systemComponents.Sprite;
import vital.Hitbox;
import vital.MovableEntity;

/**
 * The Enemy class represents a snake that the player should avoid.
 * It has a location and a hitbox
 */
public class Enemy extends MovableEntity
{
	/**
	 * The sprite used to draw the snake
	 */
	private static Sprite sprite;
	
	/**
	 * The location of the enemy
	 */
	int x, y;
	
	/**
	 * Whether or not the enemy is alive
	 */
	boolean isAlive = true;
	
	
	/**
	 * Enemy constructor sets up location and loads sprite
	 */
	public Enemy(int startX, int startY)
	{
		x = startX;
		y = startY;
		sprite = new Sprite("/res/sprites/moonsnake 96 x 96.png", 96, 96);
		hitbox = new Hitbox();
		hitbox.updateDimensions(x, y, (int)(96*.5), (int)(96*.5));
	}
	
	/**
	 * retrieve x coordinates
	 */
	public int getX()
	{
		return x;
	}
	
	/**
	 * retrieve y coordinates
	 */
	public int getY()
	{
		return y;
	}
	
	/**
	 * live during game play boolean
	 */
	public boolean alive()
	{
		return isAlive;
	}

	/**
	 * Sets whether or not the enemy is alive
	 */
	public void setIsAlive(boolean isAlive)
	{
		this.isAlive = isAlive;
	}
	
	/**
	 * update sprite frames
	 */
	public void update()
	{
		sprite.nextFrame();
		
	}

	/**
	 * paint method
	 */
	public void draw(Graphics pane) {
		pane.setColor(Color.green);
		//pane.fillRect(x, y, (int)(96*.5), (int)(96*.5));
		sprite.draw(pane, x, y,.5,.5);
	}

}
