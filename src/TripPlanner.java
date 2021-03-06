import java.util.Scanner;

public class TripPlanner {

	public static void main(String[] args) {
		String choice;
		Scanner sc = new Scanner(System.in);
		Scanner input = new Scanner(System.in);
		Itinerary Vacation1 = new Itinerary();// I dont like typing Itinerary
		Itinerary Vacation2 = new Itinerary();
		Itinerary CurrentVacation = Vacation1;
		String location = "", activity = "";
		int dist = 0;
		System.out.println("Welcome to Zotto's TripPlanner!\n");
		while (true)// infinite loop
		{
			System.out.println("Select an option from the Menu:");
			System.out.println("F-Cursor forward");
			System.out.println("B-Cursor backward");
			System.out.println("I-Insert before cursor");
			System.out.println("A-Append to tail");
			System.out.println("D-Delete and move cursor forward");
			System.out.println("H-Cursor to Head");
			System.out.println("T-Cursor to Tail");
			System.out.println("E-edit cursor");
			System.out.println("S-Switch itinerary");
			System.out.println("O-Insert cursor from other itinerary before cursor from this itinerary");
			System.out.println("R-Replace this itinerary with a copy of the other itinerary");
			System.out.println("P-Print current itinerary, including summary");
			System.out.println("C-Clear current itinerary");
			System.out.println("Q-Quit");
			System.out.print("Select an Operation: ");

			choice = sc.nextLine();

			switch (choice.toUpperCase()) {
			case "F":
			{
				try {
				CurrentVacation.cursorFoward();
				System.out.println("Cursor moved forward");
				}catch(EndOfItineraryException e)
				{
					System.out.println("Cursor is at end of list.");
				}
				break;
			}
			case "B":
			{
				try{
				CurrentVacation.cursorBackward();
				System.out.println("Cursor moved back");
				}catch(EndOfItineraryException e)
				{
					System.out.println("Cursor is already at begining of list.");
				}
				break;
			}
			case "I":
			{
				try{
				System.out.println("Enter Location: ");
				location = input.nextLine();
				System.out.println("Enter Activity: ");
				activity = input.nextLine();
				System.out.println("Enter Distance: ");
				dist= input.nextInt();
				System.out.println("Added.");
				input.nextLine();
				TripStop newStop = new TripStop(location,dist,activity);
				CurrentVacation.insertBeforeCursor(newStop);
				}catch(IllegalArgumentException e)
				{
					System.out.println("Distance is invalid -- enter value above or equal to 0");
				}
				break;
			}
			case "A":
			{
				try{
				System.out.println("Enter Location: ");
				location = input.nextLine();
				System.out.println("Enter Activity: ");
				activity = input.nextLine();
				System.out.println("Enter Distance: ");
				dist= input.nextInt();
				System.out.println("Added.");
				input.nextLine();
				TripStop newStop = new TripStop(location,dist,activity);
				CurrentVacation.appendToTail(newStop);
				}catch(IllegalArgumentException e)
				{
					System.out.println("Value is invalid");
				}
				break;
			}
			case "D":
			{
				try {
				CurrentVacation.removeCursor();
				System.out.println("Deleted cursor.");
				CurrentVacation.cursorFoward();
				}catch (EndOfListException e)
				{
					System.out.println("End of list.");
				} catch (EndOfItineraryException e) {
					System.out.println("End of Itinerary.");
				}
				
				break;
			}
			
			case "P":
			{
				CurrentVacation.PrintAllStops();
				break;
			}
			case "R":
			{
				Itinerary tempVacation = new Itinerary();
				tempVacation = CurrentVacation;
				if (tempVacation == Vacation1)
				{
					tempVacation = (Itinerary) Vacation2.Clone();
					Vacation1 = tempVacation;
				}
				else if (tempVacation == Vacation2)
				{
					tempVacation = (Itinerary) Vacation1.Clone();
					Vacation2 = tempVacation;
				}
				CurrentVacation = tempVacation;
				System.out.println("Itinerary Copied.");
				
				break;
			}
			case "S":
			{
				Itinerary tempVacation = new Itinerary();
				tempVacation = CurrentVacation;// tempVacation is used, because issues arose with overwriting on the currentVacation pointer, 
												//leading to issues with printing the linked list.
				if (tempVacation == Vacation1)
				{
					tempVacation = Vacation2;
				}
				else if (tempVacation == Vacation2)
				{
					tempVacation = Vacation1;
				}
				CurrentVacation = tempVacation;
				
				System.out.println("Itinerary Switched.");
				break;
				
				
			}
			case "O":
			{
				try{
				Itinerary tempVacation = new Itinerary();
				tempVacation = CurrentVacation;
				TripStopNode tempNode;
				if (tempVacation == Vacation1 && Vacation2.getFirst() !=null)//Ensures that values are not being added to other itinerary when they cant be
				{
					tempNode = (TripStopNode) Vacation2.getCursor();
					tempVacation.insertBeforeCursor((TripStop)tempNode.getData().Clone());
				}
				else if (tempVacation == Vacation2 && Vacation1.getFirst()!=null)
				{
					tempNode = (TripStopNode) Vacation1.getCursor();
					tempVacation.insertBeforeCursor((TripStop)tempNode.getData().Clone());
				}
				CurrentVacation = tempVacation;
				System.out.println("Added from other Itinerary.");
			}
			catch(IllegalArgumentException e){
				System.out.println("Other Itinerary is empty");
			}
				break;	
			
			}
			case "C":
			{
				if (CurrentVacation == Vacation1)
				{
					Vacation1 = new Itinerary();
					CurrentVacation = Vacation1;
				}
				else 
				{
					Vacation2 = new Itinerary();
					CurrentVacation = Vacation2;
				}
				break;
				
			}
			case "E":// ask about get Cursor method
			{
				try{
					TripStopNode newCursor = CurrentVacation.getCursor();
					System.out.println("Edit Location, or press enter without typing anything to keep: " + newCursor.getData().getLocation());
					location = input.nextLine();
					System.out.println("Edit Distance, or press -1 without typing anything to keep: " + newCursor.getData().getDistance()); 
					dist = input.nextInt();
					input.nextLine();
					System.out.println("Edit Activity, or press enter without typing anything to keep: " + newCursor.getData().getActivity());
					activity = input.nextLine();
					if (!(location.equals("")))
					{
							newCursor.getData().setLocation(location);
					}
					if (!(dist == -1))
					{
						int oldDist = CurrentVacation.getCursor().getData().getDistance();//edits for summary of Itinerary
						int newDist = CurrentVacation.getTotalDistance() - oldDist + dist;// replaces old dist with new dist
						CurrentVacation.setTotalDistance(newDist);
						newCursor.getData().setDistance(dist);
					}
					if (!(activity.equals("")))
						{
							newCursor.getData().setActivity(activity);
						}
					System.out.println("Edits applied");
					}catch(IllegalArgumentException e)
					{
						System.out.println("List is empty");
					}
				
				break;
					
			}
			case "T":
			{
				System.out.println("Cursor moved to tail.");
				CurrentVacation.moveCursorToTail();
				break;
			}
			case "H":
			{
				System.out.println("Cursor moved to head.");
				CurrentVacation.resetCursorToHead();
				break;
			}
			case "Q":
			{
				System.out.println("Quitting.");
				System.exit(0);
				break;
			}
			default:
			{
				System.out.println("Option doesnt exist!");
				break;
			}
			}
		}
	}
}
