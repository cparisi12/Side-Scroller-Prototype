package systemComponents;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Keyboard.java
 *
 * The Keyboard class is used to check any keyboard input the user may present. 
 * 
 * @author Kile DaSilva
 */
public class Keyboard implements KeyListener
{

	/**
	 *  The array keys represents the value of each key (bigger than
	 *  needed for safety). When a key is pressed, its corresponding spot in
	 *  the array will be set to true.
	 */
	private boolean[] keys = new boolean[500];
		
	/**
	 * Was the escape key pressed? true if so
	 */
	private boolean esc;
	
	/**
	 *  The Boolean variable up represents the up key. Will be set to true when
	 *  the key is pressed.
	 */
	private boolean up;

	/**
	 *  The Boolean variable down represents the down key. Will be set to true when
	 *  the key is pressed.
	 */
	private boolean down;
	
	/**
	 *  The Boolean variable left represents the left key. Will be set to true when
	 *  the key is pressed.
	 */
	private boolean left;
	
	/**
	 *  The Boolean variable right represents the right key. Will be set to true when
	 *  the key is pressed.
	 */
	private boolean right;
	
	/**
	 *  The Boolean variable space represents the space key. Will be set to true when
	 *  the key is pressed.
	 */
	private boolean space;

	/**
	 *  The Boolean variable w represents the W key. Will be set to true when
	 *  the key is pressed.
	 */
	private boolean w;

	/**
	 *  The Boolean variable a represents the A key. Will be set to true when
	 *  the key is pressed.
	 */
	private boolean a;

	/**
	 *  The Boolean variable s represents the S key. Will be set to true when
	 *  the key is pressed.
	 */
	private boolean s;

	/**
	 *  The Boolean variable d represents the D key. Will be set to true when
	 *  the key is pressed.
	 */
	private boolean d;

	/**
	 *  The Boolean variable enter represents the Enter key. Will be set to true
	 *  when the key is pressed.
	 */
	private boolean enter;

	/**
	 *  The Boolean variable backspace represents the Backspace key. Will be set 
	 *  to true when the key is pressed.
	 */
	private boolean backspace;
	
	/**
	 * The tap booleans are true when their respective key was pressed but is released
	 */
	private boolean escTap, upTap, downTap, leftTap, rightTap, spaceTap, wTap,
	aTap, sTap, dTap, enterTap, backspaceTap;
	
	/**
	 *  The getUp method returns the boolean value of the variable up.
	 *  Precondition:	None.
	 *  Postcondition:	The value of up is returned.
	 */
	public boolean getUp()
	{
		return up;
		
	} // End of getUp method
	
	/**
	 *  The getDown method returns the boolean value of the variable down.
	 *  Precondition:	None.
	 *  Postcondition:	The value of up is down.
	 */
	public boolean getDown()
	{
		return down;
		
	} // End of getDown method

	/**
	 *  The getLeft method returns the boolean value of the variable left.
	 *  Precondition:	None.
	 *  Postcondition:	The value of left is returned.
	 */
	public boolean getLeft()
	{
		return left;
		
	} // End of getLeft method
	
	/**
	 *  The getRight method returns the boolean value of the variable right.
	 *  Precondition:	None.
	 *  Postcondition:	The value of right is returned.
	 */
	public boolean getRight()
	{
		return right;
		
	} // End of getRight method

	/**
	 *  The getSpace method returns the boolean value of the variable space.
	 *  Precondition:	None.
	 *  Postcondition:	The value of space is returned.
	 */
	public boolean getSpace()
	{
		return space;
		
	} // End of getSpace method
	
	/**
	 *  The getEnter method returns the boolean value of the variable enter.
	 *  Precondition:	None.
	 *  Postcondition:	The value of enter is returned.
	 */
	public boolean getEnter()
	{
		return enter;
		
	} // End of getEnter method
	
	/**
	 *  The getBackspace method returns the boolean value of the variable backspace.
	 *  Precondition:	None.
	 *  Postcondition:	The value of backspace is returned.
	 */
	public boolean getBackspace()
	{
		return backspace;
		
	} // End of getBackspace method

	/**
	 *  The getW method returns the boolean value of the variable w.
	 *  Precondition:	None.
	 *  Postcondition:	The value of w is returned.
	 */
	public boolean getW()
	{
		return w;
		
	} // End of getW method
	
	/**
	 *  The getA method returns the boolean value of the variable a.
	 *  Precondition:	None.
	 *  Postcondition:	The value of a is returned.
	 */
	public boolean getA()
	{
		return a;
		
	} // End of getA method

	
	/**
	 *  The getS method returns the boolean value of the variable s.
	 *  Precondition:	None.
	 *  Postcondition:	The value of s is returned.
	 */
	public boolean getS()
	{
		return s;
		
	} // End of getS method

	
	/**
	 *  The getD method returns the boolean value of the variable d.
	 *  Precondition:	None.
	 *  Postcondition:	The value of d is returned.
	 */
	public boolean getD()
	{
		return d;
		
	} // End of getD method

	/**
	 *  The getEsc method returns the boolean value of the variable esc.
	 *  Precondition:	None.
	 *  Postcondition:	The value of esc is returned.
	 */
	public boolean getEsc()
	{
		return esc;
	}

	/**
	 *  The getUpTap method returns the boolean value of the variable upTap.
	 *  Precondition:	None.
	 *  Postcondition:	The value of upTap is returned.
	 */
	public boolean getUpTap()
	{
		return upTap;
	}

	/**
	 *  The getDownTap method returns the boolean value of the variable downTap.
	 *  Precondition:	None.
	 *  Postcondition:	The value of downTap is returned.
	 */
	public boolean getDownTap()
	{
		return downTap;
	}

	/**
	 *  The getLeftTap method returns the boolean value of the variable leftTap.
	 *  Precondition:	None.
	 *  Postcondition:	The value of leftTap is returned.
	 */
	public boolean getLeftTap()
	{
		return leftTap;
	}

	/**
	 *  The getRightTap method returns the boolean value of the variable rightTap.
	 *  Precondition:	None.
	 *  Postcondition:	The value of rightTap is returned.
	 */
	public boolean getRightTap()
	{
		return rightTap;
	}

	/**
	 *  The getEnterTap method returns the boolean value of the variable enterTap.
	 *  Precondition:	None.
	 *  Postcondition:	The value of enterTap is returned.
	 */
	public boolean getEnterTap()
	{
		return enterTap;
	}

	/**
	 *  The getBackspaceTap method returns the boolean value of the variable backspaceTap.
	 *  Precondition:	None.
	 *  Postcondition:	The value of backspaceTap is returned.
	 */
	public boolean getBackspaceTap()
	{
		return backspaceTap;
	}

	/**
	 *  The getSpaceTap method returns the boolean value of the variable spaceTap.
	 *  Precondition:	None.
	 *  Postcondition:	The value of spaceTap is returned.
	 */
	public boolean getSpaceTap()
	{
		return spaceTap;
	}

	/**
	 *  The getEscTap method returns the boolean value of the variable escTap.
	 *  Precondition:	None.
	 *  Postcondition:	The value of escTap is returned.
	 */
	public boolean getEscTap()
	{
		return escTap;
	}

	/**
	 *  The getWTap method returns the boolean value of the variable wTap.
	 *  Precondition:	None.
	 *  Postcondition:	The value of wTap is returned.
	 */
	public boolean getWTap()
	{
		return wTap;
	}

	/**
	 *  The getATap method returns the boolean value of the variable aTap.
	 *  Precondition:	None.
	 *  Postcondition:	The value of aTap is returned.
	 */
	public boolean getATap()
	{
		return aTap;
	}

	/**
	 *  The getSTap method returns the boolean value of the variable sTap.
	 *  Precondition:	None.
	 *  Postcondition:	The value of sTap is returned.
	 */
	public boolean getSTap()
	{
		return sTap;
	}

	/**
	 *  The getDTap method returns the boolean value of the variable dTap.
	 *  Precondition:	None.
	 *  Postcondition:	The value of dTap is returned.
	 */
	public boolean getDTap()
	{
		return dTap;
	}
	
	/**
	 *  The updateKeyStroke method checks each key used in the game to see
	 *  if it is pressed. If the key is pressed, the value will be set to
	 *  true.
	 *  Precondition:	None.
	 *  Postcondition:	up, down, left, right, enter, space, backspace, w, a, s, d may
	 *  				be set to true or false depending on the array's values.
	 */
	public void updateKeyStroke()
	{
		if(!up&&keys[KeyEvent.VK_UP])
			upTap = true;
		else
			upTap = false;
		if(!down&&keys[KeyEvent.VK_DOWN])
			downTap = true;
		else
			downTap = false;
		if(!left&&keys[KeyEvent.VK_LEFT])
			leftTap = true;
		else
			leftTap = false;
		if(!right&&keys[KeyEvent.VK_RIGHT])
			rightTap = true;
		else
			rightTap = false;
		if(!esc&&keys[KeyEvent.VK_ESCAPE])
			escTap = true;
		else
			escTap = false;
		if(!space&&keys[KeyEvent.VK_SPACE])
			spaceTap = true;
		else
			spaceTap = false;
		if(!enter&&keys[KeyEvent.VK_ENTER])
			enterTap = true;
		else
			enterTap = false;
		if(!backspace&&keys[KeyEvent.VK_BACK_SPACE])
			backspaceTap = true;
		else
			backspaceTap = false;
		if(!w&&keys[KeyEvent.VK_W])
			wTap = true;
		else
			wTap = false;
		if(!a&&keys[KeyEvent.VK_A])
			aTap = true;
		else
			aTap = false;
		if(!s&&keys[KeyEvent.VK_S])
			sTap = true;
		else
			sTap = false;
		if(!d&&keys[KeyEvent.VK_D])
			dTap = true;
		else
			dTap = false;
		
		up = keys[KeyEvent.VK_UP];
		down = keys[KeyEvent.VK_DOWN];
		left = keys[KeyEvent.VK_LEFT];
		right = keys[KeyEvent.VK_RIGHT];
		space = keys[KeyEvent.VK_SPACE];
		enter = keys[KeyEvent.VK_ENTER];
		backspace = keys[KeyEvent.VK_BACK_SPACE];
		w = keys[KeyEvent.VK_W];
		a = keys[KeyEvent.VK_A];
		s = keys[KeyEvent.VK_S];
		d = keys[KeyEvent.VK_D];
		esc = keys[KeyEvent.VK_ESCAPE];
		
	} // End of updateKeyStroke
	
	
	/**
	 * The keyPressed method will check if a key is pressed, then set the connected value
	 * in the keys array to true.
	 * Precondition:	None.
	 * Postcondition:	Pressed keys will change the boolean value of the keys array.
	 */
	public void keyPressed(KeyEvent e)
	{
		keys[e.getKeyCode()] = true;
	
	} // End of keyPressed


	/**
	 * The keyReleased method will check if a key is released, then set the connected value
	 * in the keys array to false.
	 * Precondition:	None.
	 * Postcondition:	Released keys will change the boolean value of the keys array.
	 */
	public void keyReleased(KeyEvent e)
	{
		keys[e.getKeyCode()] = false;
	
	} // End of keyReleased

	
	/** 
	 * The keyTyped method is needed for implementation, but never used.
	 */
	public void keyTyped(KeyEvent e) {}

} // End of Keyboard class
