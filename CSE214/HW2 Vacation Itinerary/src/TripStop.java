
/**
 * The Class TripStop.
 */
public class TripStop {
	
	private String Location;
	private int Distance;
	private String Activity;
	
	/**
	 * Instantiates a new trip stop.
	 */
	public TripStop() 
	{
		Location = "";
		Distance = 0;
		Activity = "";		
	}
	
	/**
	 * Instantiates a new trip stop.
	 *
	 * @param Location the location
	 * @param Distance the distance
	 * @param Activity the activity
	 * @throws IllegalArgumentException if distance is being set to negative value.
	 */
	public TripStop(String Location, int Distance, String Activity) throws IllegalArgumentException
	{
		if (Distance < 0)
		{
			throw new IllegalArgumentException();
		}
		this.Location = Location;
		this.Distance = Distance;
		this.Activity = Activity;
	}
	
	/**
	 * Gets the location.
	 *
	 * @return the location
	 */
	public String getLocation()
	{
		return Location;
	}
	
	/**
	 * Gets the distance.
	 *
	 * @return the distance
	 */
	public int getDistance()
	{
		return Distance;
	}
	
	/**
	 * Gets the activity.
	 *
	 * @return the activity
	 */
	public String getActivity()
	{
		return Activity;
	}
	
	/**
	 * Sets the location.
	 *
	 * @param newLoc the new location
	 */
	public void setLocation(String newLoc)
	{
		Location = newLoc;
	}
	
	/**
	 * Sets the distance.
	 *
	 * @param newDis the new distance
	 */
	public void setDistance(int newDis)
	{
		Distance = newDis;
	}
	
	/**
	 * Sets the activity.
	 *
	 * @param newAct the new activity
	 */
	public void setActivity(String newAct)
	{
		Activity = newAct;
	}
	
	/**
	 * Clone, creates a new object of tripStop with this instance's current values.
	 *
	 * @return the tripStop object.
	 */
	public Object Clone()
	{
		TripStop clonedStop = new TripStop(this.Location,this.Distance,this.Activity);
		return clonedStop;
	}
}
