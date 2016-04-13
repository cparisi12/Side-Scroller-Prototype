package systemComponents;

import java.awt.event.*;

/**
 * Window.java
 *
 * The Window class is used to allow the window to close when the X is clicked..
 * The following can be done from within the Window class:
 * 
 * @author Kile DaSilva
 */
public class Window extends WindowAdapter
{
	/**
	 *  The windowClosing method ends the program.
	 *  PRECONDITION:		None.
	 *  POSTCONDITION:		The window is closed.
	 */
	public void windowClosing(WindowEvent event)
	{
		// Constants declared to eliminate the need of literals
		final int ZERO = 0;
		
		// Terminates the program
		System.exit(ZERO);
		
	} // End of windowClosing method

} // End of Window class
