package systemComponents;

/**
 *  AlarmListener.java
 *  
 *  The AlarmListener interface sets up a blueprint for any class that wishes
 *  to use an Alarm. Any class implementing this needs a takeNotice method.
 */
public interface AlarmListener
{
	/**
	 *  The takeNotice method is what the alarm will invoke whenever
	 *  it's period is up. A class implementing the AlarmListener should
	 *  incorporate timing sensitive code within this method.
	 */
	public void tick();

}	//	End of AlarmListener Interface
