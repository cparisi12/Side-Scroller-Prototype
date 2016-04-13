package characters;

import java.awt.Color;
import java.awt.Graphics;

import systemComponents.Sprite;
import vital.Hitbox;
import vital.MovableEntity;
import vital.Vector;

/**
 * The Player class represents the user in the game, it can move, jump, interact with
 * power ups, change sprites, fire, and get injured/die.
 *
 */
public class Player extends MovableEntity
{

	/**
	 * The Player's various sprites
	 */
	private static Sprite spriteRWalk, spriteLWalk, spriteRIdle, spriteLIdle,
	usaRWalk, usaLWalk, usaRIdle, usaLIdle,
	zomRWalk, zomLWalk, zomRIdle, zomLIdle,
	regRWalk, regLWalk, regRIdle, regLIdle;

	/**
	 * The player's various hitboxes, one for the top, one for the bottom, one for the entirity
	 * Useful in collision detection
	 */
	private Hitbox lowerBox, midBox, higherBox;

	/**
	 * Boolean variables to determine the various states of the player
	 */
	private boolean isJumping,isWalking,isFacingRight;

	/**
	 * frame is used for animation purposes
	 */
	private int frame;

	/**
	 * Necessary numerical values for our character. Determines vitality
	 */
	private int lives, hp;

	/**
	 * These booleans will act as "switches" to turn powers on
	 *   and off. Once they're flipped to true, the power activates.
	 * After, they are flipped back to false
	 */
	private boolean slow, fast, zombie, merica;

	/**
	 * Control the coordinates that make up and control the bullet
	 */
	private int bulletX, bulletY, bulletWidth, bulletHeight, bulletTimeToLive;

	/**
	 * The amount of time a bullet lives
	 */
	private final int BULLET_TIME;

	/**
	 * Determines if the player can fire, and the direction;
	 */
	private boolean isBulletAvailable, bulletIsRight;

	/**
	 * The box that controls the bullet
	 */
	private Hitbox bulletBox;

	/**
	 * The amount of time this player spends as a zombie
	 */
	private final int ZOMBIE_TIME;

	/**
	 * The amount of time remaining as a zombie
	 */
	private int zombieTime;

	/**
	 * The speed the player moves at if a zombie
	 */
	private int zombieSpeed;

	/**
	 * The score is how many points the user has earned from playing the game
	 */
	private double score;
	
	/**
	 * Default constructor Instantiates everything. 
	 */
	public Player()
	{
		// Set up dimensions and location
		this.setDimensions(21, 38);
		this.place(200, -50);
		this.mass = 100;

		// Set up vectors and hitboxes
		v = new Vector();
		hitbox = new Hitbox();
		lowerBox = new Hitbox();
		lowerBox.updateDimensions(x, y+height, width, 1);
		higherBox = new Hitbox();
		higherBox.updateDimensions(x, y-1, width, 1);
		midBox = new Hitbox();
		midBox.updateDimensions(x-1, y, width+2, height);

		// Initialize state booleans
		isJumping = false;
		isWalking = false;
		isFacingRight = true;
		isBulletAvailable = true;

		// Set up the next bullet
		BULLET_TIME = 40;
		bulletBox = new Hitbox();
		bulletWidth = bulletHeight = 10;
		bulletIsRight = true;

		// Lives and health 
		hp = 1;
		lives = 3;

		// Set up the traits for zombie mode
		ZOMBIE_TIME = 100;
		zombieTime = 0;
		zombieSpeed = 5;

		// Turn all power-up/power-downs to false
		slow = false;
		fast = false;
		zombie = false;
		merica = false;

		// Animation starts at first frame
		frame = 0;

		// Set limits on velocity and acceleration
		this.setVelocityLimiter(true, 5);
		this.setAccelerationLimiter(true, 10);

		// Load the sprites
		if(regRWalk==null)
			regRWalk = new Sprite("/res/sprites/Astronaut Walk Cycle 22 x 36.png", 22, 36);
		if(regLWalk==null)
			regLWalk = new Sprite("/res/sprites/Astronaut Walk Cycle Left 22 x 36.png", 22, 36);
		if(regRIdle==null)
			regRIdle = new Sprite("/res/sprites/Astronaut idle 22 by 36.png", 22, 36);
		if(regLIdle==null)
			regLIdle = new Sprite("/res/sprites/Astronaut idle Left 22 by 36.png", 22, 36);
		if(usaRWalk==null)
			usaRWalk = new Sprite("/res/sprites/America Walk Cycle 22 x 36.png", 22, 36);
		if(usaLWalk==null)
			usaLWalk = new Sprite("/res/sprites/America Walk Cycle Left 22 x 36.png", 22, 36);
		if(usaRIdle==null)
			usaRIdle = new Sprite("/res/sprites/America idle 22 by 36.png", 22, 36);
		if(usaLIdle==null)
			usaLIdle = new Sprite("/res/sprites/America idle Left 22 by 36.png", 22, 36);
		if(zomRWalk==null)
			zomRWalk = new Sprite("/res/sprites/Astrozom Walk Cycle 22 x 36.png", 22, 36);
		if(zomLWalk==null)
			zomLWalk = new Sprite("/res/sprites/Astrozom Walk Cycle Left 22 x 36.png", 22, 36);
		if(zomRIdle==null)
			zomRIdle = new Sprite("/res/sprites/Astrozom idle 22 x 36.png", 22, 36);
		if(zomLIdle==null)
			zomLIdle = new Sprite("/res/sprites/Astrozom idle Left 22 x 36.png", 22, 36);

		// Current sprites are regular
		spriteRWalk = regRWalk;
		spriteLWalk = regLWalk;
		spriteRIdle = regRIdle;
		spriteLIdle = regLIdle;
	}

	/**
	 * Return the vector of this character
	 */
	public Vector getVector()
	{
		return v;
	}

	/**
	 * Get the lowest hitbox for this character
	 */
	public Hitbox getLowerBox()
	{
		return lowerBox;
	}

	/**
	 * Get the highest hitbox for this character
	 */
	public Hitbox getHigherBox()
	{
		return higherBox;
	}

	/**
	 * Get the main hitbox for this character
	 */
	public Hitbox getMidBox()
	{
		return midBox;
	}

	/**
	 * Get if the character is jumping or not
	 */
	public boolean getIsJumping()
	{
		return isJumping;
	}

	/**
	 * Get if the character is walking or not
	 */
	public boolean getIsWalking()
	{
		return isWalking;
	}

	/**
	 * Get if the character is facing right or not
	 */
	public boolean getIsFacingRight()
	{
		return isFacingRight;
	}

	/**
	 * Get if the character can fire
	 */
	public boolean getIsBulletReady()
	{
		return isBulletAvailable;
	}

	/**
	 * Set if the character is jumping
	 */
	public void setIsJumping(boolean isJumping)
	{
		this.isJumping = isJumping;
	}

	/**
	 * Set if the character is walking
	 */
	public void setIsWalking(boolean isWalking)
	{
		if(this.isWalking && !isWalking)
		{
			spriteRIdle.firstFrame();
			spriteLIdle.firstFrame();
			frame = 0;
		}

		this.isWalking = isWalking;
	}

	/**
	 * Set if the character is facing right\
	 */
	public void setIsFacingRight(boolean isFacingRight)
	{
		this.isFacingRight = isFacingRight;
	}

	/**
	 * Return the player's current score
	 */
	public double getScore()
	{
		return score;
	}
	
	/**
	 * Alter the score of the player
	 */
	public void increaseScore(double changeInScore)
	{
		score+=changeInScore;
	}
	
	/**
	 * Sets whether or not the bullet is fired
	 */
	public void setIsBulletReady(boolean isBulletReady)
	{
		this.isBulletAvailable=isBulletReady;
	}

	/**
	 * method to flip the slow powerdown
	 * @param someBoolean
	 */
	public void flipSlow(boolean someBoolean)
	{
		slow = someBoolean;
		if(slow)
			fast = false;

		if(slow)
		{
			// Set limits on velocity and acceleration
			this.setVelocityLimiter(true, 3);
			this.setAccelerationLimiter(true, 8);
		}
		else
		{
			// Set limits on velocity and acceleration
			this.setVelocityLimiter(true, 5);
			this.setAccelerationLimiter(true, 10);			
		}
		// Change speed for some period of time...
		// Put speed back to normal
	}

	/**
	 * method to flip the fast powerup
	 * @param someBoolean
	 */
	public void flipFast(boolean someBoolean)
	{
		fast = someBoolean;
		if(fast)
			slow = false;

		if(fast)
		{
			// Set limits on velocity and acceleration
			this.setVelocityLimiter(true, 7);
			this.setAccelerationLimiter(true, 12);
		}
		else
		{
			// Set limits on velocity and acceleration
			this.setVelocityLimiter(true, 5);
			this.setAccelerationLimiter(true, 10);			
		}
		// Change speed for some period of time...
		// Put speed back to normal
	}

	/**
	 * method to flip the zombie power(up/down?)
	 * @param someBoolean
	 */
	public void flipZombie(boolean someBoolean)
	{

		zombie = someBoolean;
		if(someBoolean)
		{
			flipFast(false);
			flipSlow(false);
			zombieTime = ZOMBIE_TIME;
			
			spriteRWalk = zomRWalk;
			spriteLWalk = zomLWalk;
			spriteRIdle = zomRIdle;
			spriteLIdle = zomLIdle;
		
		}
		else if(!zombie)
		{
			spriteRWalk = regRWalk;
			spriteLWalk = regLWalk;
			spriteRIdle = regRIdle;
			spriteLIdle = regLIdle;
		}

		// Create zombie mode and change speed
		// to constant in x direction for some
		// period of time....
		// return character back to normal
	}

	/**
	 * method to flip the merica powerup
	 * @param someBoolean
	 */
	public void flipMerica(boolean someBoolean)
	{

		merica = someBoolean;

		if(someBoolean)
			setHP(2);

		if(merica)
		{
			spriteRWalk = usaRWalk;
			spriteLWalk = usaLWalk;
			spriteRIdle = usaRIdle;
			spriteLIdle = usaLIdle;
		}
		else
		{
			spriteRWalk = regRWalk;
			spriteLWalk = regLWalk;
			spriteRIdle = regRIdle;
			spriteLIdle = regLIdle;
		}
		// Create merica powerup and make hp 2
		// Inactive after being hit the first time
	}

	/**
	 * Returns if the player is in Merica mode
	 */
	public boolean getMerica()
	{
		return merica;
	}

	/**
	 * Returns if the player is in Zombie mode
	 */
	public boolean getZombie()
	{
		return zombie;
	}

	/**
	 * Returns if the player is in fast mode
	 */
	public boolean getFast()
	{
		return fast;
	}

	/**
	 * Returns if the player is in slow mode
	 */
	public boolean getSlow()
	{
		return slow;
	}
	
	/**
	 * method sets HP, will be called in the flipMerica method
	 * @param someHP
	 */
	public void setHP(int someHP)
	{
		hp = someHP; // hp is someHP
	}

	/**
	 * Get the hitbox for the bullet
	 */
	public Hitbox getBullet()
	{
		return bulletBox;
	}

	/**
	 * method returns current hp
	 * @return
	 */
	public int getHP()
	{
		return hp; // Return hp
	}

	/**
	 * method returns current hp
	 * @return
	 */
	public int getLives()
	{
		return lives; // Return hp
	}

	/**
	 * method increments number of lives
	 */
	public void oneUp()
	{
		lives++; // Increase number of lives by one
	}

	/**
	 * method decrements number of lives
	 */
	public void oneDown()
	{
		lives--; // Decrease number of lives by one
	}

	/**
	 * If the character can jump, it will enter a jumping state
	 */
	public void tryJump()
	{
		if(!isJumping && !zombie)
		{
			isJumping = true;
			v.setAcceleration(v.getAi(), -6, v.getAk());

		}
	}

	/**
	 * If the character can shoot, it will shoot a bullet
	 */
	public void tryBullet()
	{
		if(isBulletAvailable && !zombie)
		{
			bulletX = x;
			bulletY = y+(int)(width*.5);

			bulletTimeToLive = BULLET_TIME;
			isBulletAvailable = false;

			if(isFacingRight)
				bulletIsRight = true;
			else
				bulletIsRight = false;
		}
	}

	/**
	 * Move the character and handle its animation
	 */
	public void update() {
		// If walking, draw walking animation
		if(isWalking || zombie)
		{
			frame++;
			if(frame>=4)
			{
				frame = 0;
				spriteRWalk.nextFrame();
				spriteLWalk.nextFrame();
			}
			if(v.getVi()==0)
				setIsWalking(false);
		}
		// Otherwise draw the idle animation
		else if(!isWalking)
		{
			frame++;
			if(frame==80)
			{
				spriteRIdle.nextFrame();
				spriteLIdle.nextFrame();
			}
			else if(frame==90){
				spriteRIdle.firstFrame();
				spriteLIdle.firstFrame();
				frame=0;
			}
		}

		// Move the character
		this.move();

		if(zombie)
		{
			if(zombieTime--<=0)
			{
				flipZombie(false);
				if(getHP()>2)
					flipMerica(true);
			}
			else
			{
				if(isFacingRight)
					getVector().accelerate(zombieSpeed,0,0);
				else
					getVector().accelerate(zombieSpeed*-1,0,0);

			}

		}

		// Realign its hitbox
		updateHitboxData();

		// Check properties
		if(hp<2 && merica)
			flipMerica(false);

		// Handle the bullet if it is fired
		if(!isBulletAvailable)
		{
			// Decrement time to live
			if(bulletTimeToLive--<0)
			{
				isBulletAvailable = true;
			}
			// Move the bullet
			else
			{
				if(bulletIsRight)
					bulletX+=8;
				else
					bulletX-=8;
				bulletBox.updateDimensions(bulletX, bulletY, bulletWidth, bulletHeight);
			}

		}

	}

	/**
	 * Ensure that all hitboxes are still surrounding the player, by moving them to the proper
	 * x and y coordinates
	 */
	public void updateHitboxData()
	{
		hitbox.updateDimensions(x, y, width, height);
		lowerBox.updateDimensions(x+1, y+height, width-2, 1);
		higherBox.updateDimensions(x+1, y-1, width-2, 1);
		midBox.updateDimensions(x-1, y+2, width+2, height-4);

	}

	/**
	 * Draw the character to the screen at its location
	 */
	public void draw(Graphics pane)
	{
		// if fired draw the bullet
		if(!isBulletAvailable)
		{
			pane.setColor(Color.red);
			pane.fillRect(bulletX, bulletY, bulletWidth, bulletHeight);
		}

		if(slow)
		{
			pane.setColor(Color.blue);
			pane.fillOval(x+(int)(width*.25), y-15, 10, 10);
		}
		else if(fast)
		{
			pane.setColor(Color.yellow);
			pane.fillOval(x+(int)(width*.25), y-15, 10, 10);
		}

		// Determine which sprite to actually draw
		if(isWalking || zombie)
			if(isFacingRight)
				spriteRWalk.draw(pane, x, y);
			else
				spriteLWalk.draw(pane, x, y);
		else if(!isWalking)
			if(isFacingRight)
				spriteRIdle.draw(pane, x, y);
			else
				spriteLIdle.draw(pane, x, y);

		// Draw the info on this sprite's vector
		drawVectorInfo(pane);
	}

	/**
	 * Draw three strings in the top corner stating the position vector, the velocity vector,
	 * and the acceleration vector.
	 */
	private void drawVectorInfo(Graphics pane)
	{
		pane.setColor(Color.red);
		pane.drawString("X = "+x+"i + "+y+"j", 700, 20);
		pane.drawString("V = "+v.getVi()+"i + "+v.getVj()+"j", 700, 32);
		pane.drawString("A = "+v.getAi()+"i + "+v.getAj()+"j", 700, 44);

	}

}
