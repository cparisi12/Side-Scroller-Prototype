package states;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import characters.Enemy;
import characters.Player;
import objects.Ground;
import objects.Ufo;
import powerups.MericaPowerup;
import powerups.SlowPowerdown;
import powerups.SpeedPowerup;
import powerups.ZombiePowerup;
import systemComponents.Keyboard;
import systemComponents.Sprite;
import vital.Entity;
import vital.Hitbox;
import vital.Node;

/**
 * The level is a prototype stage designed to test the functionality of the player.
 * It features a hole that the player could fall into, solid ground to stand on, a platform
 * (in the shape of a UFO for thematic purposes), an unmoving enemy, and powerups. It also
 * features fully implemented physics
 * 
 * @author Kile_DaSilva
 *
 */
public class Level extends GameState
{
	/**
	 * The reference to the player
	 */
	private Player p;
	
	/**
	 * A Hitbox strecting the trajectory of each second's movement for the player. For more
	 * precise collision detection when the velocity is very large (i.e. falling)
	 */
	private Hitbox pTravelBox;
	
	/**
	 * A linked list of all things the player can stand on (called blocks as tribute to retro Mario)
	 */
	private Node<Entity> blockHead;
	
	/**
	 * The background of the level
	 */
	private Sprite backdrop;

	/**
	 * The single enemy
	 */
	private Enemy enemy;

	/**
	 * The strength of gravity in the level
	 */
	private int gravity;

	/**
	 * The "Merica" power up
	 */
	private MericaPowerup usa;

	/**
	 * The "Slow down" power up
	 */
	private SlowPowerdown slow;

	/**
	 * The "Speed Up" power up
	 */
	private SpeedPowerup fast;

	/**
	 * The "Zombie" power up
	 */
	private ZombiePowerup zombie;

	/**
	 * A hitbox which will remove a life from the player if entered
	 */
	private Hitbox killBox;

	/**
	 * The default constructor instantiates everything
	 */
	public Level()
	{
		// Set up the travel hitbox
		pTravelBox = new Hitbox();
		pTravelBox.activate();

		// Set up gravity
		gravity = 1;

		// Set up player
		p = new Player();

		// Set up each block in the linked list
		Node<Entity> blockCurrent;
		blockHead = blockCurrent = null;
		
		// Add first ground to list
		Entity b =new Ground(-25,500);
		blockHead = new Node();
		blockHead.setItem(b);

		// Add second ground to list
		b =new Ground(450,500);
		blockHead.setNext(new Node());
		blockCurrent = blockHead.getNext();
		blockCurrent.setItem(b);

		// Add UFO to list
		b =new Ufo(50,400);
		blockCurrent.setNext(new Node());
		blockCurrent = blockCurrent.getNext();
		blockCurrent.setItem(b);

		// Set up power ups
		usa = new MericaPowerup(550, 404);
		slow = new SlowPowerdown(500, 404);
		fast = new SpeedPowerup(600, 404);
		zombie = new ZombiePowerup(650, 404);

		// Set up enemy
		enemy = new Enemy(700, 450);

		// Load background
		backdrop = new Sprite("/res/sprites/SpaceyBack.png", 800, 700);

		// Set up kill box
		killBox = new Hitbox();
		killBox.updateDimensions(0, 1000, 10000, 10000);
		killBox.activate();
	}

	/**
	 * Determine what happens with each keystroke
	 */
	protected void handleKeys()
	{
		// Update the keyboard
		keyboard.updateKeyStroke();
		
		// If 'A' move left
		if(keyboard.getA() && !p.getZombie())
		{
			p.getVector().accelerate(-2, 0, 0);
			p.setIsWalking(true);
			p.setIsFacingRight(false);
		}
		// if 'D' move right
		if(keyboard.getD() && !p.getZombie())
		{
			p.getVector().accelerate(2, 0, 0);
			p.setIsWalking(true);
			p.setIsFacingRight(true);
		}

		// If both 'A' and 'D' are pressed stop moving
		if((keyboard.getA()&&keyboard.getD()) || (keyboard.getLeft()&&keyboard.getRight()))
		{
			p.stopHorizontalMovement();
		}

		// If 'W' is tapped jump
		if((keyboard.getWTap() || keyboard.getUpTap()) && !p.getZombie())
		{
			p.tryJump();
		}

		// If space is tapped shoot
		if(keyboard.getSpaceTap())
		{
			p.tryBullet();
		}

		// If backspace is tapped reset location
		if(keyboard.getBackspaceTap())
			p.place(100,200);
		
		// If enter is tapped pause
		if(keyboard.getEnterTap())
		{
			PauseMenu next = new PauseMenu(gsm);
			next.setPreviousState(this);
			gsm.setState(next);
			
		}
	}

	/**
	 * Check if the player is outside of the left or right edge of the screen, and relocate
	 * if so
	 */
	private void checkScreenWrap()
	{
		if(p.getX()<-50)
			p.place(850, p.getY());
		else if(p.getX()>850)
			p.place(-50, p.getY());
	}

	/**
	 * Handle ground/block physics (each ground piece and platform is considered a block)
	 */
	private void blockCollision()
	{
		/*
		 * Check for blocks as the player is falling
		 */
		Node current = blockHead;
		Entity highest = null;
		boolean stopped = false;

		// Check landing collisions
		if(p.getVelocity()[1]>0){
			while(current!= null && pTravelBox.getHeight()>2)
			{
				Entity b = (Entity) current.getItem();
				if(pTravelBox.doesBoxIntercept(b.getHitbox()))
				{

					if(highest==null)
						highest = b;
					else if (highest!=null)
						if(highest.getY()>b.getY())
							highest = b;

				}
				current = current.getNext();
			}
			if(highest!=null)
			{
				p.place(p.getX(),highest.getY()-p.getHeight());

				p.stopVerticalMovement();

				p.setIsJumping(false);

				p.updateHitboxData();
			}
		}

		/*
		 * Check overhead collision
		 */
		else if(p.getVelocity()[1]<0){
			current = blockHead;
			while (current!=null && p.getVelocity()[1]<0 && !stopped)
			{
				Entity b = (Entity) current.getItem();
				if(p.getHigherBox().doesBoxIntercept(b.getHitbox()))
				{
					p.stopVerticalMovement();

					p.place(p.getX(),b.getY()+b.getHeight()+3);

					p.updateHitboxData();
				}
				current = current.getNext();
			}
		}

		/*
		 * Apply movement friction, cancel jumps
		 */
		if(p.getVelocity()[1]>0){
			current = blockHead;
			while(current!= null && !stopped)
			{
				Entity b = (Entity) current.getItem();
				if(p.getLowerBox().doesBoxIntercept(b.getHitbox()))
				{
					if(p.getVector().getVi()<0 && !keyboard.getA() && !keyboard.getD())
						p.getVector().accelerate((int)(p.getVector().getAi()*(-.9)), 0, 0);
					else if(p.getVector().getVi()>0 && !keyboard.getA() && !keyboard.getD())
						p.getVector().accelerate((int)(p.getVector().getAi()*(-.9)), 0, 0);

					if(p.getVector().getAi()<2&&p.getVector().getAi()>-2)
						p.stopHorizontalMovement();

					if(p.getVelocity()[1]>0){
						p.place(p.getX(),b.getY()-p.getHeight());
						//System.out.println("Moving up friction");
					}

					if(!p.getIsJumping())
						p.stopVerticalMovement();

					stopped=true;
					p.setIsJumping(false);

					p.updateHitboxData();
				}
				current = current.getNext();
			}
		}

		/*
		 * Accelerate due to gravity if nothing is below
		 */
		if(!stopped)
		{
			p.getVector().accelerate(0, gravity, 0);
		}

		/*
		 * Check horizontal collisions
		 */
		if(p.getVelocity()[0]!=0)
		{
			current = blockHead;
			while(current!= null)
			{
				Entity b = (Entity) current.getItem();
				if(p.getMidBox().doesBoxIntercept(b.getHitbox()))
				{
					//System.out.println("Gosh");
					if(p.getX()<=b.getX())
						p.place(b.getX()-p.getWidth()-3,p.getY());
					else if(p.getX()>b.getX())
						p.place(b.getX()+b.getWidth()+3,p.getY());

					p.stopHorizontalMovement();

					p.updateHitboxData();
				}
				current = current.getNext();
			}
		}

	}

	/**
	 * Run each moment of the game
	 */
	public void update()
	{
		/*
		 * Key Handling
		 */
		handleKeys();

		int pXorigin = p.getX();
		int pYorigin = p.getY();

		p.update();
		enemy.update();
		
		if(usa!=null)
			usa.update();

		Node current = blockHead;
		while(current!=null)
		{
			((Entity) current.getItem()).update();
			current = current.getNext();
		}
		
		// Use a hitbox to stretch over the player's movement, to determine if any blocks collided
		// with the trajectory of that second
		if(p.getY()>pYorigin)
			pTravelBox.updateDimensions(pXorigin, pYorigin, pXorigin-p.getX()+p.getWidth(), p.getY()-pYorigin);
		else
			pTravelBox.updateDimensions(pXorigin, pYorigin+p.getHeight(), 
					pXorigin-p.getX()+p.getWidth(), pYorigin-p.getY());

		// Check collision with powerup
		if(usa!=null && p.getHitbox().doesBoxIntercept(usa.getHitbox()))
		{
			p.flipMerica(true);
			usa = null;
		}
		if(slow!=null && p.getHitbox().doesBoxIntercept(slow.getHitbox()))
		{
			p.flipSlow(true);
			slow = null;
		}
		if(fast!=null && p.getHitbox().doesBoxIntercept(fast.getHitbox()))
		{
			p.flipFast(true);
			fast = null;
		}
		if(zombie!=null && p.getHitbox().doesBoxIntercept(zombie.getHitbox()))
		{
			p.flipZombie(true);
			zombie = null;
		}

		// Handle enemy logic
		if(enemy.alive())
		{
			// Check if the player is touching the enemy
			if(enemy.getHitbox().doesBoxIntercept(p.getHitbox()))
			{
				enemy.setIsAlive(false);
				p.setHP(p.getHP()-1);
			}
			// Check if the player shot the enemy
			if(!p.getIsBulletReady() && 
					enemy.getHitbox().doesBoxIntercept(p.getBullet()))
			{
				enemy.setIsAlive(false);
				p.setIsBulletReady(true);
				p.increaseScore(100);
			}
		}

		/*
		 * Wrap Screen
		 */
		checkScreenWrap();

		/*
		 *  Check gravity collisions
		 */
		blockCollision();

		// Check if the player died
		handleDeath();

	}

	/**
	 * Check if the player died, and handle everything accordingly if so
	 */
	private void handleDeath()
	{
		// Check if the player is offscreen
		if(p.getHitbox().doesBoxIntercept(killBox)|| p.getHP()<=0)
		{
			// If the player still has lives, decrement that and re-enter starting position
			if(p.getLives()>1)
			{
				p.oneDown();
				p.setHP(1);
				p.setIsBulletReady(true);
				p.place(200, -50);
				// Reset power ups
				p.flipFast(false);
				p.flipSlow(false);
				p.flipMerica(false);
				p.flipZombie(false);

				// Revive the enemy
				enemy.setIsAlive(true);

				// Reset powerups
				if(usa == null)
					usa = new MericaPowerup(550, 404);
				if(slow == null)
					slow = new SlowPowerdown(500, 404);
				if(fast == null)
					fast = new SpeedPowerup(600, 404);
				if(zombie == null)
					zombie = new ZombiePowerup(650, 404);
			}
			else
			{
				p.place(100,10000);
				// Game over
				GameOverMenu g = new GameOverMenu(gsm);
				g.setPreviousState(previous);
				gsm.setState(g);

			}
		}
	}

	/**
	 * Draw the level to the screen
	 */
	public void render(Graphics pane)
	{
		// Draw a plain background
		pane.setColor(new Color(220,220,255));
		pane.fillRect(0, 0, 1000, 1000);
		backdrop.draw(pane, 0, 0);

		// Draw every physics block
		Node current = blockHead;
		while(current!=null)
		{
			Entity b = (Entity) current.getItem();
			b.draw(pane);
			current = current.getNext();
		}

		// Draw the powerup if it exists
		if(usa!=null)
			usa.draw(pane);
		if(slow!=null)
			slow.draw(pane);
		if(fast!=null)
			fast.draw(pane);
		if(zombie!=null)
			zombie.draw(pane);

		// Draw the player
		p.draw(pane);

		// Draw the test enemy
		if(enemy.alive())
			enemy.draw(pane);

		// Draw the lives information
		pane.setColor(Color.white);
		pane.drawString("Lives: "+p.getLives(), 5, 12);
		String hp = "HP: ";
		for(int i = 0; i<p.getHP(); i++)
			hp+='*';
		pane.drawString(hp, 5, 24);
		
		pane.setColor(Color.white);
		pane.drawString("-----SCORE-----", 350, 12);
		pane.drawString(""+p.getScore(), 390,24);
	}
	
	/**
	 * Set the game state manager for this state
	 */
	public void setGameStateManager(GameStateManager gsm)
	{
		this.gsm = gsm;
	}
}
