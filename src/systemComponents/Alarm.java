package systemComponents;

/**
 *  Alarm.java
 *  
 *  The Alarm class allows the user to add special timings to their program.
 *  From this class a user can set a delay, and start an alarm to perform an
 *  action every time that delayed period is reached.
 * 
 */
public class Alarm extends Thread
{
	/**
	 * The AlarmListener object listener represents the object
	 * that utilizes the Alarm.
	 */
	private AlarmListener listener;

	/**
	 * The private boolean isAlarmrunning is used to determine if the timer is
	 * currently running or if it is stopped
	 */
	private boolean isAlarmRunning = false;
	
	/**
	 *  The private integer delay is used to represent the amount of
	 *  milliseconds waited before updating the takeNotice method.
	 */
	private int delay = 0;

	/**
	 * The default constructor names the thread a default title and nullifies
	 * the listener object.
	 */
	public Alarm()
	{
		super("Alarm");
		listener = null;
		
	} // End of Default Constructor

	/**
	 * This overloaded constructor names the thread a default title and 
	 * instantiates listener with an object received from the parameters.
	 */
	public Alarm(AlarmListener listener)
	{
		super("Alarm");
		this.listener = listener;
	
	} // End of Constructor

	/**
	 * This overloaded constructor names the thread a title based on the String
	 * received from the parameters and instantiates listener with an object
	 * also received from the parameters.
	 */
	public Alarm(String name, AlarmListener listener)
	{
		super(name);
		this.listener = listener;
	
	} // End of Constructor

	
	/**
	 *  The public method setPeriod returns void, and receives an integer through
	 *  the parameters. When invoked it modifies the value of the delay variable
	 *  to be equal to the value of the integer received.
	 *  Precondition:	None.
	 *  Postcondition:	delay is changed.
	 */
	public void setPeriod(int delay)
	{
		this.delay = delay;
	
	} // End of setPeriod method

	
	/**
	 *  The private method setPeriodicBeep method returns void, and receives an
	 *  integer in the parameters. When invoked this method will set the delay
	 *  equal to that of the received integer. Then, while the alarm is
	 *  instantiated, invoke the takeNotice method of its alarmListener every time
	 *  the delay is reached.
	 *  Precondition:	whoWantsToKnow is instantiated.
	 *  Postcondition:	takeNotice is invoked from listener periodically.
	 */
	private void setPeriodicBeep(int delay)
	{
		// Update the delay
		this.delay = delay;

		// As long as the program is running
		try {
			while (isAlarmRunning)
			{
				// Wait for delay milliseconds
				Thread.sleep(delay);
				
				// If listener is not null, invoke takeNotice()
				if (listener != null)
					listener.tick();
			
			} // End of while loop
		
		} // End of try block
		
		// If there is an error, print the error message
		catch(InterruptedException e) {
			e.printStackTrace();
		
		} // End of catch block
	
	} // End of setPeriodicBeep method

	
	/**
	 *  The public method run returns void. When invoked, this method will set
	 *  the alarm's periodic beep to the current delay.
	 *  Precondition:	None.
	 *  Postcondition:	setPeriodicBeep is invoked.
	 */
	public void run()
	{
		System.out.println("The " + super.getName() + " is now running.");
		isAlarmRunning = true;
		setPeriodicBeep(delay);
	
	} // End of run method

	
	/**
	 *  The public method run returns void. When invoked, this method will set
	 *  the alarm's periodic beep to the current delay.
	 *  Precondition:	None.
	 *  Postcondition:	setPeriodicBeep is invoked.
	 */
	public void pause()
	{
		System.out.println("The " + super.getName() + " is no longer running.");
		isAlarmRunning = false;
		setPeriodicBeep(delay);
	
	} // End of run method

}	//	end of class Alarm
