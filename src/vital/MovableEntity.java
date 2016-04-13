package vital;

/**
 * The MovableEntityClass has the capabilities of a regular entity class, yet also is updated to
 * allow vector based movement.
 * 
 * @author Kile_DaSilva
 *
 */
public abstract class MovableEntity extends Entity
{
	/**
	 * Represents the movement of the Entity
	 */
	protected Vector v;

	/**
	 * How heavy this object is, in what is assumed to be Kilograms
	 */
	protected int mass;

	/**
	 *  The maxVelocity determines the top speed the entity can travel at
	 */
	private int maxVelocity;

	/**
	 *  The maxAcceleration determines the top acceleration the entity can have
	 */
	private int maxAcceleration;

	/**
	 *  A boolean to determine if the velocity has a set limit
	 */
	private boolean isVelocityLimited;
	
	/**
	 * A boolean to determine if the acceleration has a set limit
	 */
	private boolean isAccelerationLimited;
	
	/**
	 * Returns a numeric representation of the density of this Entity's matter
	 */
	public int getMass()
	{
		return mass;
	}

	/**
	 * Returns an array of three integers when invoked, representing the i, j, and k
	 * components of the velocity vector.
	 */
	public int[] getVelocity()
	{
		int[] velocity = new int[3];
		velocity[0] = v.getVi();
		velocity[1] = v.getVj();
		velocity[2] = v.getVk();

		return velocity;
	}

	
	/**
	 * Returns an array of three integers when invoked, representing the i, j, and k
	 * components of the acceleration vector.
	 */
	public int[] getAcceleration()
	{
		int[] acceleration = new int[3];
		acceleration[0] = v.getAi();
		acceleration[1] = v.getAj();
		acceleration[2] = v.getAk();

		return acceleration;
	}

	
	/**
	 * Apply a force to this Entity, ultimately resulting in an acceleration.
	 */
	public void applyForce(int i, int j, int k)
	{
		i = i/mass;
		j = j/mass;
		k = k/mass;
		v.accelerate(i,j,k);
	}

	
	/**
	 * Apply a force to this Entity, ultimately resulting in an acceleration.
	 */
	public void accelerate(int i, int j, int k)
	{
		v.accelerate(i,j,k);
		
		if(isAccelerationLimited){
			int nAi = v.getAi();
			int nAj = v.getAj();
			int nAk = v.getAk();

			if(nAi<0 && nAi<maxAcceleration*-1)
				nAi = maxAcceleration*-1;
			else if(nAi>0 && nAi>maxAcceleration)
				nAi = maxAcceleration;
			
			if(nAj<0 && nAj<maxAcceleration*-1)
				nAj = maxAcceleration*-1;
			else if(nAj>0 && nAj>maxAcceleration)
				nAj = maxAcceleration;
			
			if(nAk<0 && nAk<maxAcceleration*-1)
				nAk = maxAcceleration*-1;
			else if(nAk>0 && nAk>maxAcceleration)
				nAk = maxAcceleration;
				
			v.setAcceleration(nAi, nAj, nAk);
		}
	}

	
	/**
	 * Stops any and all movement
	 */
	public void stopMovement()
	{
		v.setAcceleration(0,0,0);
		v.setVelocity(0,0,0);
	}

	
	/**
	 *  Halt all vertical movement, reseting v and a to 0
	 */
	public void stopVerticalMovement(){
		v.setVelocity(v.getVi(), 0, v.getVk());
		v.setAcceleration(v.getAi(), 0, v.getAk());
	}
	

	/**
	 *  Halt all horizontal movement, reseting v and a to 0
	 */
	public void stopHorizontalMovement(){
		v.setVelocity(0, v.getVj(), v.getVk());
		v.setAcceleration(0, v.getAj(), v.getAk());
	}
	
	/**
	 * Moves the entity in accordance to its vector
	 */
	public void move()
	{
		x = x+v.getVi();
		y = y+v.getVj();
		z = z+v.getVk();
		
		v.setVelocity(v.getVi()+v.getAi(), v.getVj()+v.getAj(), v.getVk()+v.getAk());
		
		if(isVelocityLimited)
		{
			int nVi,nVj,nVk;
			int verticalLim = 70;
			
			if(v.getVi()>maxVelocity)
				nVi=maxVelocity;
			else if((-1*v.getVi())>maxVelocity)
				nVi=(maxVelocity*-1);
			else
				nVi = v.getVi();

			if(v.getVj()>verticalLim)
				nVj=verticalLim;
			else if((-1*v.getVj())>verticalLim)
				nVj=-1*verticalLim;
			else
				nVj = v.getVj();
/*			if(v.getVk()>maxVelocity)
				nVk=maxVelocity;
			else if((-1*v.getVk())>maxVelocity)
				nVk=maxVelocity;
			else
				nVk = v.getVk();
	*/		
			v.setVelocity(nVi, nVj, v.getVk());
		}
	}

	/**
	 *  Do not allow the velocity to exceed a certain value when accelerating
	 */
	public void setVelocityLimiter(boolean isVelocityLimited, int maxVelocity)
	{
		this.isVelocityLimited = isVelocityLimited;
		this.maxVelocity = maxVelocity;
	}
	
	/**
	 * Do not allow the acceleration to exceed a certain value
	 */
	public void setAccelerationLimiter(boolean isAccelerationLimited, int maxAcceleration)
	{
		this.isAccelerationLimited = isAccelerationLimited;
		this.maxAcceleration = maxAcceleration;
	}
	
}
