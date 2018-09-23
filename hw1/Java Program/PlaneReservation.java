import java.util.ArrayList;
import java.util.HashMap;

public class PlaneReservation {
	// DO NOT CHANGE: NOT YET FULLY SCALABLE
	public static final int FIRST_CLASS_ROWS = 2;
	public static final int FIRST_CLASS_COLS = 4;
	public static final int ECONOMY_CLASS_ROWS = 20;
	public static final int ECONOMY_CLASS_COLS = 6;

	private Passenger[][] firstClass;
	private Passenger[][] economyClass;
	private ArrayList<Group> groupList;
	private HashMap<Integer, String> sMap; // map column numbers to letters
	
	public PlaneReservation() {
		this.firstClass = new Passenger[FIRST_CLASS_ROWS][FIRST_CLASS_COLS];
		this.economyClass = new Passenger[ECONOMY_CLASS_ROWS][ECONOMY_CLASS_COLS];
		this.groupList = new ArrayList<Group>();
		this.sMap = new HashMap<Integer, String>();
		sMap.put(0, "A");
		sMap.put(1, "B");
		sMap.put(2, "C");
		sMap.put(3, "D");
		sMap.put(4, "E");
		sMap.put(5, "F");
	}

	/**
	 * Gets a copy of the first class
	 * @return The first class seating
	 */
	public Passenger[][] getFirstClass() {
		return firstClass.clone();
	}
	
	/**
	 * Gets a copy of the economy class
	 * @return The economy class seating
	 */
	public Passenger[][] getEconomyClass() {
		return economyClass.clone();
	}
	
	/**
	 * Gets a copy of the group list
	 * @return The group list
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<Group> getGroupList() {
		return (ArrayList<Group>) groupList.clone();
	}
	
	/**
	 * Reserves a seat in the PlaneReservation model
	 * @param service The service class
	 * @param a First index
	 * @param b Second index
	 * @param p Passenger to reserve
	 * @return If reservation was successful
	 */
	public boolean reserveSeat(String service, int a, int b, Passenger p) {
		if (service.equals("first")) firstClass[a][b] = p;
		else economyClass[a][b] = p;
		return true;
	}
	
	/**
	 * Adds the group to the list : DOES NOT RESERVE GROUP SEATS
	 * @param g The group to add to the list
	 * @return If group adding was successful
	 */
	public boolean addGroupToList(Group g) {
		return groupList.add(g);
	}
	
	/**
	 * Adds a passenger
	 * @param name The name of the passenger
	 * @param sClass The service class
	 * @param sPreference The seat preference
	 */
	public boolean addPassenger(String name, String sClass, char sPreference) {
		if (sClass.equals("first")) {
			if (sPreference == 'c') { // no center seats in first class
				System.out.println("Request Failed.");
				return false;
			} else if (sPreference == 'w') { // window seat
				for (int i = 0; i < FIRST_CLASS_ROWS; i++) {
					if (firstClass[i][0] == null) {
						firstClass[i][0] = new Passenger(name);
						System.out.println(name + " has reserved seat " + (i + 1) + sMap.get(0));
						return true;
					} else if (firstClass[i][3] == null) {
						firstClass[i][3] = new Passenger(name);
						System.out.println(name + " has reserved seat " + (i + 1) + sMap.get(3));
						return true;
					}
				}
			} else if (sPreference == 'a') { // aisle seat
				for (int i = 0; i < FIRST_CLASS_ROWS; i++) {
					if (firstClass[i][1] == null) {
						firstClass[i][1] = new Passenger(name);
						System.out.println(name + " has reserved seat " + (i + 1) + sMap.get(1));
						return true;
					} else if (firstClass[i][2] == null) {
						firstClass[i][2] = new Passenger(name);
						System.out.println(name + " has reserved seat " + (i + 1) + sMap.get(2));
						return true;
					}
				}
			}
		} else if (sClass.equals("economy")){
			if (sPreference == 'c') { // center seat
				for (int i = 0; i < ECONOMY_CLASS_ROWS; i++) {
					if (economyClass[i][1] == null) {
						economyClass[i][1] = new Passenger(name);
						System.out.println(name + " has reserved seat " + (i + 10) + sMap.get(1));
						return true;
					} else if (economyClass[i][4] == null) {
						economyClass[i][4] = new Passenger(name);
						System.out.println(name + " has reserved seat " + (i + 10) + sMap.get(4));
						return true;
					}
				}
			} else if (sPreference == 'w') { // window seat
				for (int i = 0; i < ECONOMY_CLASS_ROWS; i++) {
					if (economyClass[i][0] == null) {
						economyClass[i][0] = new Passenger(name);
						System.out.println(name + " has reserved seat " + (i + 10) + sMap.get(0));
						return true;
					} else if (economyClass[i][5] == null) {
						economyClass[i][5] = new Passenger(name);
						System.out.println(name + " has reserved seat " + (i + 10) + sMap.get(5));
						return true;
					}
				}
			} else if (sPreference == 'a') { // aisle seat
				for (int i = 0; i < ECONOMY_CLASS_ROWS; i++) {
					if (economyClass[i][2] == null) {
						economyClass[i][2] = new Passenger(name);
						System.out.println(name + " has reserved seat " + (i + 10) + sMap.get(2));
						return true;
					} else if (economyClass[i][3] == null) {
						economyClass[i][3] = new Passenger(name);
						System.out.println(name + " has reserved seat " + (i + 10) + sMap.get(3));
						return true;
					}
				}
			}
		}
		// did not find the requested seat
		System.out.println("Request Failed.");
		return false;
	}
	
	/**
	 * Adds a group
	 * @param gName The group's name
	 * @param members The members of the group
	 * @param sClass The service class
	 * @return If adding the group was successful
	 */
	public boolean addGroup(String gName, String members, String sClass) {
		Group g = new Group(gName, members);
		if (sClass.equals("first")) {
			int availSeats = 0; // count available seats
			for (int i = 0; i < firstClass.length; i++) {
				for (int j = 0; j < firstClass[0].length; j++) {
					if (firstClass[i][j] == null) availSeats++;
				}
			}
			int groupSize = g.getGroupPassengers().length;
			if (availSeats < groupSize) {
				System.out.println("Request failed.");
				return false;
			}
			groupList.add(g);
			Passenger[] toSeat = g.getGroupPassengers();
			int seated = 0;
			while (seated < groupSize) { // keep going until whole group is seated
				int[][] adjacentTracker = new int[firstClass.length][2]; // [][0] = # of rows; [][1] = first index of free seat
				for (int i = 0; i < firstClass.length; i++) { // find the max adjacent seats in each row
					int maxAdjacent = 0;
					for (int j = 0; j < firstClass[0].length; j++) {
						if (firstClass[i][j] == null) {
							maxAdjacent++;
							if (j == (firstClass[0].length - 1) && maxAdjacent > adjacentTracker[i][0]) { // reached end of row but has new rows to save
								adjacentTracker[i][0] = maxAdjacent;
								adjacentTracker[i][1] = j - maxAdjacent + 1;
							}
						} else {
							if (maxAdjacent > adjacentTracker[i][0]) {
								adjacentTracker[i][0] = maxAdjacent;
								adjacentTracker[i][1] = j - maxAdjacent;
							}
							maxAdjacent = 0;
						}
					}
				}
				int biggestRow = 0; // find the longest row
				for (int i = 0; i < adjacentTracker.length; i++) {
					if (adjacentTracker[i][0] == groupSize) { // found a big enough row for the whole group
						biggestRow = i;
						break;
					} else if (adjacentTracker[biggestRow][0] < adjacentTracker[i][0]) biggestRow = i;
				}
				int index = adjacentTracker[biggestRow][1];
				for (int i = 0; i < adjacentTracker[biggestRow][0]; i++) { // add the passengers to row
					firstClass[biggestRow][index + i] = toSeat[seated];
					System.out.println(toSeat[seated].getName() + " has reserved seat " + (biggestRow + 1) + sMap.get(index + i));
					seated++;
					if (seated == groupSize) break;
				}
			}
		} else if (sClass.equals("economy")) {
			int availSeats = 0; // count available seats
			for (int i = 0; i < economyClass.length; i++) {
				for (int j = 0; j < economyClass[0].length; j++) {
					if (economyClass[i][j] == null) availSeats++;
				}
			}
			int groupSize = g.getGroupPassengers().length;
			if (availSeats < groupSize) {
				System.out.println("Request failed.");
				return false;
			}
			groupList.add(g);
			Passenger[] toSeat = g.getGroupPassengers();
			int seated = 0;
			while (seated < groupSize) { // keep going until whole group is seated
				int[][] adjacentTracker = new int[economyClass.length][2]; // [][0] = # of rows; [][1] = first index of free seat
				for (int i = 0; i < economyClass.length; i++) { // find the max adjacent seats in each row
					int maxAdjacent = 0;
					for (int j = 0; j < economyClass[0].length; j++) {
						if (economyClass[i][j] == null) {
							maxAdjacent++;
							if (j == (economyClass[0].length - 1) && maxAdjacent > adjacentTracker[i][0]) { // reached end of row but has new rows to save
								adjacentTracker[i][0] = maxAdjacent;
								adjacentTracker[i][1] = j - maxAdjacent + 1;
							}
						} else {
							if (maxAdjacent > adjacentTracker[i][0]) {
								adjacentTracker[i][0] = maxAdjacent;
								adjacentTracker[i][1] = j - maxAdjacent;
							}
							maxAdjacent = 0;
						}
					}
				}
				int biggestRow = 0; // find the longest row
				for (int i = 0; i < adjacentTracker.length; i++) {
					if (adjacentTracker[i][0] == groupSize) { // found a big enough row for the whole group
						biggestRow = i;
						break;
					} else if (adjacentTracker[biggestRow][0] < adjacentTracker[i][0]) biggestRow = i;
				}
				int index = adjacentTracker[biggestRow][1];
				for (int i = 0; i < adjacentTracker[biggestRow][0]; i++) { // add the passengers to row
					economyClass[biggestRow][index + i] = toSeat[seated];
					System.out.println(toSeat[seated].getName() + " has reserved seat " + (biggestRow + 10) + sMap.get(index + i));
					seated++;
					if (seated == groupSize) break;
				}
			}
		}
		return true;
	}
	
	/**
	 * Cancels a Reservation
	 * @param input The group or individual names
	 * @param typeOfCancel [g]roup or [i]ndividuals
	 * @return If cancellation was successful
	 */
	public boolean cancelReservation(String input, char typeOfCancel) {
		if (input == null) return false;
		input = input.trim();
		if (typeOfCancel == 'i') {
			if (input.charAt(input.length() - 1) == ',') input = input.substring(0, input.length() - 1); // remove comma at the end if it exists
			int index = input.lastIndexOf(",");
			if (index == -1) { // only 1 name
				for (int i = 0; i < FIRST_CLASS_ROWS; i++) { // search first class
					for (int j = 0; j < FIRST_CLASS_COLS; j++) {
						if (firstClass[i][j] != null && firstClass[i][j].getName().equals(input)) {
							System.out.println(firstClass[i][j].getName() + "'s reservation for seat " + (i + 1) + sMap.get(j) + " is now cancelled.");
							firstClass[i][j] = null;
							return true;
						}
					}
				}
				for (int i = 0; i < ECONOMY_CLASS_ROWS; i++) { // search economy class
					for (int j = 0; j < ECONOMY_CLASS_COLS; j++) {
						if (economyClass[i][j] != null && economyClass[i][j].getName().equals(input)) {
							System.out.println(economyClass[i][j].getName() + "'s reservation for seat " + (i + 10) + sMap.get(j) + " is now cancelled.");
							economyClass[i][j] = null;
							return true;
						}
					}
				}
				System.out.println("Request Failed.");
				return false;
			}
			
			while (index != -1) { // Separate names and cancel reservations
				String removeP = input.substring(index + 1);
				input = input.substring(0, index);
				for (int i = 0; i < FIRST_CLASS_ROWS; i++) { // search first class
					for (int j = 0; j < FIRST_CLASS_COLS; j++) {
						if (firstClass[i][j] != null && firstClass[i][j].getName().equals(removeP)) {
							System.out.println(firstClass[i][j].getName() + "'s reservation for seat " + (i + 1) + sMap.get(j) + " is now cancelled.");
							firstClass[i][j] = null;
						}
					}
				}
				for (int i = 0; i < ECONOMY_CLASS_ROWS; i++) { // search economy class
					for (int j = 0; j < ECONOMY_CLASS_COLS; j++) {
						if (economyClass[i][j] != null && economyClass[i][j].getName().equals(removeP)) {
							System.out.println(economyClass[i][j].getName() + "'s reservation for seat " + (i + 10) + sMap.get(j) + " is now cancelled.");
							economyClass[i][j] = null;
						}
					}
				}
				index = input.lastIndexOf(",");
			}
			
			// One more name to cancel
			for (int i = 0; i < FIRST_CLASS_ROWS; i++) { // search first class
				for (int j = 0; j < FIRST_CLASS_COLS; j++) {
					if (firstClass[i][j] != null && firstClass[i][j].getName().equals(input)) {
						System.out.println(firstClass[i][j].getName() + "'s reservation for seat " + (i + 1) + sMap.get(j) + " is now cancelled.");
						firstClass[i][j] = null;
					}
				}
			}
			for (int i = 0; i < ECONOMY_CLASS_ROWS; i++) { // search economy class
				for (int j = 0; j < ECONOMY_CLASS_COLS; j++) {
					if (economyClass[i][j] != null && economyClass[i][j].getName().equals(input)) {
						System.out.println(economyClass[i][j].getName() + "'s reservation for seat " + (i + 10) + sMap.get(j) + " is now cancelled.");
						economyClass[i][j] = null;
					}
				}
			}
			return true;
		} else if (typeOfCancel == 'g') {
			Group g = findGroup(input);
			if (g == null) { // If group is not found
				System.out.println("Request Failed.");
				return false;
			}
			String[] groupMembers = g.getMembers();
			for (String s : groupMembers) { // Remove each member one by one using individual reservation cancellation method
				cancelReservation(s, 'i');
			}
			for (int i = 0; i < groupList.size(); i++) { // Remove the group from the list
				if (groupList.get(i).getName().equals(g.getName())) {
					groupList.remove(i);
					break;
				}
			}
			return true;
		}
		System.out.println("Request Failed.");
		return false;
	}
	
	/**
	 * Finds the group in the groupList
	 * @param s The group name
	 * @return The group object or null
	 */
	private Group findGroup(String s) {
		for (int i = 0; i < groupList.size(); i++) {
			Group g = groupList.get(i);
			if (g != null && g.getName().equals(s)) return g;
		}
		return null;
	}
}
