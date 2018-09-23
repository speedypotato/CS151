/**
 * Groups passengers
 */
public class Group {
	private Passenger[] group;
	private String groupName;

	/**
	 * Creates a Group of passengers
	 */
	public Group(String gName, String people) {
		groupName = gName;
		String[] temp = people.split(",");
		group = new Passenger[temp.length];
		for (int i = 0; i < temp.length; i++) {
			group[i] = new Passenger(temp[i]);
		}
	}

	/**
	 * Gets the passengers in the group
	 * 
	 * @return A String array with the group passenger names
	 */
	public String[] getMembers() {
		String[] passengers = new String[group.length];
		for (int i = 0; i < group.length; i++) {
			passengers[i] = group[i].getName();
		}
		return passengers;
	}

	/**
	 * Gets the group's name
	 * 
	 * @return The name of the group
	 */
	public String getName() {
		return groupName;
	}
	
	/**
	 * Gets an array of group passengers
	 * @return An array of group passengers
	 */
	public Passenger[] getGroupPassengers() {
		return group;
	}
}
