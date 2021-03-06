
// TODO: Auto-generated Javadoc
/**
 * The Class Itinerary.
 */
public class Itinerary {
	
	/** The head. */
	private TripStopNode head;
	
	/** The tail. */
	private TripStopNode tail;
	
	/** The cursor. */
	private TripStopNode cursor;
	
	/** The dummy. */
	private TripStopNode dummy;// an empty node that only contains 
								
								/** The dummy stop. */
								//a link to the first link
	private TripStop dummyStop;
	
	/** The total stops. */
	private int totalStops = 0;
	
	/** The cursor count. */
	private int cursorCount =0;
	
	/** The total dist. */
	private int totalDist = 0;
	
	/**
	 * Instantiates a new itinerary.
	 */
	public Itinerary() {
		
		tail = null;
		cursor = null;
		dummyStop = new TripStop("",0,"");
		dummy = new TripStopNode(dummyStop);
		head = dummy;// sets head to static position, called dummy
	}

	/**
	 * Gets the stops count.
	 *
	 * @return the stops count
	 */
	public int getStopsCount() {
		return totalStops;
	}

	/**
	 * Gets the total distance.
	 *
	 * @return the total distance
	 */
	public int getTotalDistance() {
		return totalDist;
	}
	
	/**
	 * Sets the total distance.
	 *
	 * @param distance the new total distance
	 */
	public void setTotalDistance(int distance)
	{
		this.totalDist = distance;
	}
	
	/**
	 * Reset cursor to head.
	 */
	public void resetCursorToHead() {
		if (dummy != null)
			cursor = dummy.getNext();// resets cursor to node dummy points to, i.e. the first node in the list
		else
			cursor = null;
	}
	
	/**
	 * Move cursor to tail.
	 */
	public void moveCursorToTail() {
		if (tail != null)
			cursor = tail;// resets cursor to tail node
		else
			cursor = null;
	}
	
	/**
	 * Gets the cursor.
	 *
	 * @return the cursor
	 * @throws IllegalArgumentException the illegal argument exception
	 */
	public TripStopNode getCursor() throws IllegalArgumentException
	{
		if (cursor == null)
		{
			throw new IllegalArgumentException();
		}
		return cursor;
	}
	
	/**
	 * Gets the first.
	 *
	 * @return the first
	 * @throws IllegalArgumentException the illegal argument exception
	 */
	public TripStopNode getFirst() throws IllegalArgumentException// ask about this
	{
		if (dummy.getNext() == null)//if list is empty
		{
			throw new IllegalArgumentException();
		}
		return dummy.getNext();//return first node NOT the dummy Node
	}
	
	/**
	 * Cursor foward.
	 *
	 * @throws EndOfItineraryException the end of itinerary exception
	 */
	public void cursorFoward() throws EndOfItineraryException
	{
		if (cursor == tail) {
			throw new EndOfItineraryException();
		}
		cursor = cursor.getNext();
	}

	/**
	 * Cursor backward.
	 *
	 * @throws EndOfItineraryException the end of itinerary exception
	 */
	public void cursorBackward() throws EndOfItineraryException {
		if (cursor == null || cursor.getPrev() == dummy ) {
			throw new EndOfItineraryException();
		}
		cursor = cursor.getPrev();
	}
	
	/**
	 * Insert before cursor.
	 *
	 * @param newStop the new stop
	 * @throws IllegalArgumentException the illegal argument exception
	 */
	public void insertBeforeCursor(TripStop newStop) throws IllegalArgumentException
	{
	
	if (newStop == null)//make sure data is present before adding to linked list
	{
		throw new IllegalArgumentException();
	}
	TripStopNode newNode = new TripStopNode(newStop);
	if (cursor == null)
	{
		newNode.setPrev(dummy);
		tail = newNode;
		tail.setPrev(dummy);
		dummy.setNext(newNode);
		cursor = newNode;
	}
	else if (cursor == dummy)
	{
		/*
		set newStop's previous as dummy,then sets its next to dummy's next
		this ensures that the linked list is not destroyed from the insertion
		then dummy's next is set to newStop
	*/
	
		
		newNode.setPrev(dummy);
		newNode.setNext(dummy.getNext());
		
		if (dummy.getNext()!=null){
			dummy.getNext().setPrev(newNode);
		}
		else {
			dummy.setNext(newNode);
		}
		cursor = newNode;
		if (newNode.getNext() == null)
		{
			tail = newNode;
		}
	}
	
	else
	{
		/*
		 * set newStop's next to current cursor,
		 * set newStop's previous to cursorsPrevious
		 * this again ensures the linked list is not broken
		 * set newStops previous's next value to newStop. 
		 */
		
		newNode.setNext(cursor);
		newNode.setPrev(cursor.getPrev());
		newNode.getNext().setPrev(newNode);
		newNode.getPrev().setNext(newNode);
		cursor = newNode;
		if (newNode.getNext() == null)
		{
			tail = newNode;
		}
	}
	totalStops++;// increment totalStops of linked list
	totalDist += newStop.getDistance();//increment total distance
}

	/**
	 * Append to tail.
	 *
	 * @param newStop the new stop
	 * @throws IllegalArgumentException the illegal argument exception
	 */
	public void appendToTail(TripStop newStop) throws IllegalArgumentException 
	{
		if (newStop == null) {
			throw new IllegalArgumentException();
		}

		TripStopNode newNode = new TripStopNode(newStop);

		if (tail == null || tail == dummy) //ensures that cursor is instantiated
		{
			dummy.setNext(newNode);
			newNode.setPrev(dummy);
			tail = newNode;
			cursor = newNode;
		} else 
		{
			tail.setNext(newNode);
			newNode.setPrev(tail);
			tail = newNode;
		}
		totalStops++;// increment totalStops of linked list
		totalDist += newStop.getDistance();// increment total distance
	}
	
	/**
	 * Removes the cursor.
	 *
	 * @return the trip stop
	 * @throws EndOfListException the end of list exception
	 */
	public TripStop removeCursor() throws EndOfListException
	{
		TripStopNode removedNode = null;
		if (dummy.getNext() == null)
		{
			throw new EndOfListException();
		}
		if (cursor == dummy)
		{
			cursor = dummy;
			return null;
		}
		if (cursor == tail)
		{
			removedNode = tail;
			tail.getPrev().setNext(null);
			tail = tail.getPrev();
			cursor = cursor.getPrev();
		}
		else
		{
			/*
			 * set removedNode to current cursor
			 * cursors previous and next values must now be connected
			 * while the value cursor was holding is left for garbage collection
			 * cursor now references previous link
			 */
			removedNode = cursor;
			cursor.getNext().setPrev(cursor.getPrev());
			cursor.getPrev().setNext(cursor.getNext());
			if (cursor.getPrev() !=dummy){
				cursor = cursor.getPrev();
			}
		}
		totalStops--;//decrease list totalStops
		totalDist -= removedNode.getData().getDistance();
		return removedNode.getData();
		
	}
	
	/**
	 * Clone.
	 *
	 * @return the object
	 */
	public Object Clone ()
	{
		Itinerary clonedIt = new Itinerary();
		TripStopNode nodePtr = dummy.getNext();
		while (nodePtr != null)// 
			{
			TripStop clonedStop = (TripStop) nodePtr.getData().Clone();
			clonedIt.appendToTail(clonedStop);
			nodePtr = nodePtr.getNext();
		}
		return clonedIt;
	}
	
	/**
	 * Prints the all stops.
	 */
	public void PrintAllStops()
	{
		TripStopNode nodePtr = dummy.getNext();
		int reference = 0; // help keep track of where cursor is in the list
		while(nodePtr != null)
		{
			if (cursor == null)// this ensures that there is a still a cursor pointer even if there are only append to tail function calls, which does not affect the cursor pointer
			{
				cursor = nodePtr;
			}
			if (cursor == nodePtr)
			{
				System.out.print(">");
				System.out.println(nodePtr);
				cursorCount = reference;
			}
			else{
			System.out.println(nodePtr);
			reference++;
			}
			nodePtr = nodePtr.getNext();
			
		}
		System.out.println("Summary: This trip has " + getStopsCount() + " stops, totaling " + getTotalDistance() + " miles. " + "There are " + cursorCount + 
				" stops before the cursor and " + (totalStops-cursorCount-1) + " stops after the cursor" );
	}
	
	
}