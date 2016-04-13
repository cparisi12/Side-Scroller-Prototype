package vital;

import java.awt.Graphics;

/**
 *  A class to be further extended into solid physics-based objects. 
 * 
 * @author Kile_DaSilva
 *
 */
public abstract class Entity
{
	/**
	 * The location of the Entity in a 3 dimensional plane
	 */
	protected int x, y, z;

	/**
	 * The Entity's dimensions in a 2 dimensional plane
	 */
	protected int width, height;

	/**
	 * The Entity's Hitbox, used for calculating Collisions
	 */
	protected Hitbox hitbox;

	/**
	 * Returns the x location of the Entity
	 */
	public int getX()
	{
		return x;
	}

	/**
	 * Returns the y location of the Entity
	 */
	public int getY()
	{
		return y;
	}

	/**
	 * Returns the width of the Entity
	 */
	public int getWidth()
	{
		return width;
	}

	/**
	 * Returns the height of the Entity
	 */
	public int getHeight()
	{
		return height;
	}

	/**
	 * Returns the Entity's hitbox
	 */
	public Hitbox getHitbox()
	{
		return hitbox;
	}

	/**
	 *  Establishes the width and height of the Entity
	 */
	public void setDimensions(int width, int height)
	{
		this.width = width;
		this.height = height;
	}
	
	/**
	 * Places the entity at a certain location
	 */
	public void place(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	
	/**
	 *  Matches the hitbox's coordinates and dimensions with the entity's
	 */
	public void syncHitbox()
	{
		hitbox.updateDimensions(x, y, width, height);
	}
	
	/**
	 * A method intended to be called every cycle of the game
	 */
	public abstract void update();

	/**
	 * A method intended to be utilized for drawing the entity to the window
	 */
	public abstract void draw(Graphics pane);
}