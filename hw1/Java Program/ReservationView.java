import java.util.HashMap;

public class ReservationView {
	private HashMap<Integer, String> sMap; // map column numbers to letters
	
	private static final String MAIN_PROMPT = "Add [P]assenger, Add [G]roup, [C]ancel Reservations, Print Seating [A]vailability Chart, Print [M]anifest, [Q]uit: ";
	private static final String[] P_PROMPT = {"Name: ", "Service Class: ", "Seat Preference: "};
	private static final String[] G_PROMPT = {"Group Name: ", "Names(Seperated by commas): ", "Service Class: "};
	private static final String[] C_PROMPT = {"[I]ndividual or [G]roup Cancellation: ", "Names(Seperated by commas): ", "Group Name: "};
	private static final String[] A_PROMPT = {"Service Class(First or Economy): ", "First ", "Economy "};
	private static final String[] M_PROMPT = {"Service Class(First or Economy): ", "First ", "Economy "};
	private static final String END_PROMPT = "Reservation Program has finished running.";
	
	public ReservationView() {
		this.sMap = new HashMap<Integer, String>();
		sMap.put(0, "A");
		sMap.put(1, "B");
		sMap.put(2, "C");
		sMap.put(3, "D");
		sMap.put(4, "E");
		sMap.put(5, "F");
	}
	
	public String getMainPrompt() {
		return MAIN_PROMPT;
	}
	
	public String[] getPPrompt() {
		return P_PROMPT;
	}
	
	public String[] getGPrompt() {
		return G_PROMPT;
	}
	
	public String[] getCPrompt() {
		return C_PROMPT;
	}
	
	public String[] getAPrompt() {
		return A_PROMPT;
	}
	
	public String[] getMPrompt() {
		return M_PROMPT;
	}
	
	public String getEndPrompt() {
		return END_PROMPT;
	}
	
	/**
	 * Gets available seats
	 * @param sClass The service class to get
	 * @param firstClass The current first class seats
	 * @param economyClass The current economy class seats
	 * @return A String array with available seats relative to index
	 */
	public String[] getAvailability(String sClass, Passenger[][] firstClass, Passenger[][] economyClass) {
		String[] available;
		if (sClass.toLowerCase().equals("first")) {
			available = new String[PlaneReservation.FIRST_CLASS_ROWS];
			for (int i = 0; i < PlaneReservation.FIRST_CLASS_ROWS; i++) { // search first class
				String temp = "";
				for (int j = 0; j < PlaneReservation.FIRST_CLASS_COLS; j++) {
					if (firstClass[i][j] == null) {
						temp += sMap.get(j);
					} else {
						temp += " ";
					}
				}
				available[i] = temp;
			}
		} else {
			available = new String[PlaneReservation.ECONOMY_CLASS_ROWS];
			for (int i = 0; i < PlaneReservation.ECONOMY_CLASS_ROWS; i++) { // search economy class
				String temp = "";
				for (int j = 0; j < PlaneReservation.ECONOMY_CLASS_COLS; j++) {
					if (economyClass[i][j] == null) {
						temp += sMap.get(j);
					} else {
						temp += " ";
					}
				}
				available[i] = temp;
			}
		}
		return available;
	}
	
	/**
	 * Gets reserved seats
	 * @param sClass The service class to get
	 * @param firstClass The current first class seats
	 * @param economyClass The current economy class seats
	 * @return A String array with the reserved seats relative to index
	 */
	public String[] getManifest(String sClass, Passenger[][] firstClass, Passenger[][] economyClass) {
		String[] manifest;
		if (sClass.toLowerCase().equals("first")) {
			manifest = new String[PlaneReservation.FIRST_CLASS_ROWS];
			for (int i = 0; i < PlaneReservation.FIRST_CLASS_ROWS; i++) {
				String temp = "";
				for (int j = 0; j < PlaneReservation.FIRST_CLASS_COLS; j++) {
					if (firstClass[i][j] != null) {
						temp += (i + 1) + sMap.get(j) + ":" + firstClass[i][j].getName() + " ";
					}
				}
				manifest[i] = temp;
			}
		} else {
			manifest = new String[PlaneReservation.ECONOMY_CLASS_ROWS];
			for (int i = 0; i < PlaneReservation.ECONOMY_CLASS_ROWS; i++) {
				String temp = "";
				for (int j = 0; j < PlaneReservation.ECONOMY_CLASS_COLS; j++) {
					if (economyClass[i][j] != null) {
						temp += (i + 10) + sMap.get(j) + ":" + economyClass[i][j].getName() + " ";
					}
				}
				manifest[i] = temp;
			}
		}
		return manifest;
	}
}
