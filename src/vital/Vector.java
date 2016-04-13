package vital;

/**
 * The Vector class represents a mathematical vector (surprise!) on a 3 dimensional plane. It
 * contains values for velocity and acceleration, as well as the capability of updating.
 * 
 * @author Kile_DaSilva
 *
 */
public class Vector
{
    /**
    * The i, j, and k components of the velocity vector
    */
    int vi, vj, vk;

    /**
    * The i, j, and k components of the acceleration vector
    */
    int ai, aj, ak;

    /**
    * Returns the i component of the velocity
    */
    public int getVi()
    {
            return vi;
    }

    /**
    * Returns the j component of the velocity
    */
    public int getVj()
    {
            return vj;
    }

    /**
    * Returns the k component of the velocity
    */
    public int getVk()
    {
            return vk;
    }

    /**
    * Returns the i component of the acceleration
    */
    public int getAi()
    {
            return ai;
    }

    /**
    * Returns the j component of the acceleration
    */
    public int getAj()
    {
            return aj;
    }

    /**
    * Returns the k component of the acceleration
    */
    public int getAk()
    {
            return ak;
    }

    /**
    * Sets the velocity vector in accordance to the three integers received
    */
    public void setVelocity(int i, int j, int k)
    {
            vi = i;
            vj = j;
            vk = k;
    }

    /**
    * Sets the acceleration vector in accordance to the three integers received
    */
    public void setAcceleration(int i, int j, int k)
    {
            ai = i;
            aj = j;
            ak = k;
    }

    /**
    * Adjusts the acceleration based on the given change
    */
    public void accelerate(int i, int j, int k)
    {
            ai = ai + i;
            aj = aj + j;
            ak = ak + k;
    }

    /**
    * Adjusts the velocity based on the acceleration and how much time has passed
    */
    public void passTime(int seconds)
    {
            vi = vi + ai*seconds;
            vj = vj + aj*seconds;
            vk = vk + ak*seconds;
    }


}
