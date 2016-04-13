package vital;

import java.awt.Color;
import java.awt.Graphics;

/**
 * A basic geometric representation of some object on a 2 Dimensional plane; Current class is
 * designed with fighting games in mind, but should be implementable to all projects featuring
 * 2 dimensional collision detection.
 * 
 * @author Kile_DaSilva
 *
 */
public class Hitbox
{
	/**
	 * X location of hitbox
	 */
	protected int hitBoxX;

	/**
	 * Y location of hitbox
	 */
	protected int hitBoxY;

	/**
	 * Width of hitbox
	 */
	protected int hitBoxWidth;

	/**
	 * Height location of hitbox
	 */
	protected int hitBoxHeight;
	
	/**
	 * Color of hitbox if rendered
	 */
	protected Color boxColor;
	
	/**
	 *  Boolean switch to determine if the hitbox checks collisions
	 */
	protected boolean isActive;
	
	/**
	 *  Default constructor does not do much aside from color stuff
	 */
	public Hitbox()
	{
		boxColor = Color.cyan;
	}

	/**
	 *  Constructor sets a color
	 */
	public Hitbox(Color inputColor)
	{
		setColor(inputColor);
	}


	/**
	 *  Set all of the locational and size information for the hitbox
	 */
	public void updateDimensions(int newX, int newY, int newWidth, int newHeight)
	{
		hitBoxX = newX;
		hitBoxY = newY;
		hitBoxWidth = newWidth;
		hitBoxHeight = newHeight;
	}
	
	
	/**
	 * Hitbox will now be active
	 */
	public void activate()
	{
		isActive = true;
	}
	
	
	/**
	 * Hitbox will no longer be active
	 */
	public void deactivate()
	{
		isActive = false;
	}
	
	
	/**
	 *  Change the color of the hitbox
	 */
	public void setColor(Color inputColor)
	{
		boxColor = inputColor;
	}
	

	/**
	 *  Return the x-coordinate of the box
	 */
	public int getX()
	{
		return hitBoxX;
	}


	/**
	 *  Return the y-coordinate of the box
	 */
	public int getY()
	{
		return hitBoxY;
	}
	

	/**
	 *  Return the width of the box
	 */
	public int getWidth()
	{
		return hitBoxWidth;
	}
	

	/**
	 *  Return the height of the box
	 */
	public int getHeight()
	{
		return hitBoxHeight;
	}
	
	
	/**
	 *  Return the color of the box
	 */
	public Color getColor()
	{
		return boxColor;
	}
	
	
	/**
	 * Receives x and y coordinates as parameters and returns true if the given point is inside the box.
	 */
	public boolean doesPointIntercept(int x, int y)
	{
		boolean isColliding;
		
		if ((x >= hitBoxX) && (x <= (hitBoxWidth + hitBoxX)) && (y >= hitBoxY) && (y <= (hitBoxHeight + hitBoxY)))
			isColliding = true;
		else
			isColliding = false;
		
		return isColliding;
	}
	
	
	/**
	 * Return true if the given argument is in any way overlapping the hitbox. Definitely not
	 * the most efficient algorithm, but I'm sure it's fine for a small fighting game that will
	 * probably never see the light of day.
	 */
	public boolean doesBoxIntercept(Hitbox inputBox)
	{
		if(inputBox==null)
			return false;
		
		boolean isColliding = false;
		
		// Check the four corners of the first box
		if (doesPointIntercept(inputBox.getX(), inputBox.getY()) ||
				doesPointIntercept(inputBox.getX() + inputBox.getWidth(), inputBox.getY()) ||
				doesPointIntercept(inputBox.getX() + inputBox.getWidth(), inputBox.getY() + inputBox.getHeight()) ||
				doesPointIntercept(inputBox.getX(), inputBox.getY() + inputBox.getHeight())) 
		{
			isColliding = true;
		}
		
		// Check the four corners of the first box
		else if (inputBox.doesPointIntercept(hitBoxX, hitBoxY) ||
						inputBox.doesPointIntercept(hitBoxX + hitBoxWidth, hitBoxY) ||
						inputBox.doesPointIntercept(hitBoxX + hitBoxWidth, hitBoxY + hitBoxHeight) ||
						inputBox.doesPointIntercept(hitBoxX, hitBoxY + hitBoxHeight)) 
		{
			isColliding = true;
		}
				
		
		else
			if (hitBoxWidth > inputBox.getWidth())
			{
				if ( ( (hitBoxX < inputBox.getX()) && (hitBoxX + hitBoxWidth > inputBox.getX() + inputBox.getWidth()) && 
						(hitBoxY > inputBox.getY()) && (hitBoxY + hitBoxHeight < inputBox.getY() + inputBox.getHeight()) ) )
				{
					isColliding = true;
				}
						
			}
			else if (hitBoxWidth < inputBox.getWidth())
			{
				
				if ( ( (inputBox.getX() < hitBoxX) && (inputBox.getX() + inputBox.getWidth() > hitBoxX + hitBoxWidth) && 
						(inputBox.getY() > hitBoxY) && (inputBox.getY() + inputBox.getHeight() < hitBoxY + hitBoxHeight) ) )
				{
					isColliding = true;
				}
				
			}
				
		return isColliding;
	}
	
	
	/**
	 *  Draw the box to the given pane
	 */
	public void drawBox(Graphics pane)
	{
		pane.setColor(boxColor);
		pane.drawRect(hitBoxX, hitBoxY, hitBoxWidth, hitBoxHeight);
		
	}
}
