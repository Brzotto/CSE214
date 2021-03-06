

/**
 * The Class TripStopNode.
 */
public class TripStopNode {
	
	private TripStop data;
		private TripStopNode next;
	private TripStopNode prev;
	/**
	 * Instantiates a new trip stop node.
	 *
	 * @param initData the init data
	 * @throws IllegalArgumentException if the initial data is null
	 */
	public TripStopNode(TripStop initData)throws IllegalArgumentException
	{
		if (initData == null)
		{
			throw new IllegalArgumentException();
		}
		data = initData;
		prev = null;
		next = null;
	}
	
	/**
	 * Gets the data of the tripStop.
	 *
	 * @return the data
	 */
	public TripStop getData()
	{
		return data;
	}
	
	/**
	 * Sets the data of the tripStop.
	 *
	 * @param newData the new data
	 * @throws IllegalArgumentException if the data is null.
	 */
	public void setData(TripStop newData)throws IllegalArgumentException
	{
		if (newData == null)
		{
			throw new IllegalArgumentException();
		}
		data = newData;
	}
	
	/**
	 * Gets the next tripStopNode.
	 *
	 * @return the next tripStopNode.
	 */
	public TripStopNode getNext()
	{
		return next;
	}
	
	/**
	 * Sets the next tripStopNode.
	 *
	 * @param newNext the new next tripStopNode
	 */
	public void setNext(TripStopNode newNext)
	{
		next = newNext;
	}
	
	/**
	 * Gets the previous TripStopNode.
	 *
	 * @return the prev
	 */
	public TripStopNode getPrev()
	{
		return prev;
	}
	
	/**
	 * Sets the previous tripStopNode.
	 *
	 * @param newPrev the new tripStopNode set previous to the current node.
	 */
	public void setPrev(TripStopNode newPrev)
	{
		prev = newPrev;
	}
	/**
	 * Prints out tripStopNode in nice format
	 * 
	 * @return String version of object.
	 */
	public String toString()
	{
		return data.getLocation() +  "      " + data.getActivity() + "\t\t\t" + data.getDistance();
	}
}
