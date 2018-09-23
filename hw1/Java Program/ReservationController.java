import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class ReservationController {
	private PlaneReservation model;
	private ReservationView view;

	/**
	 * Creates a new Reservation Controller
	 */
	public ReservationController(PlaneReservation model, ReservationView view) {
		this.model = model;
		this.view = view;
	}
	
	/**
	 * Handles passenger adding
	 * @param s Scanner
	 */
	public void passengerOption(Scanner s) {
		s.nextLine(); // flush input stream
		String[] prompt = view.getPPrompt();
		System.out.print(prompt[0]);
		String name = s.nextLine();
		System.out.print(prompt[1]);
		String sClassP = s.next().toLowerCase();
		System.out.print(prompt[2]);
		char sPreference = s.next().toLowerCase().charAt(0);
		model.addPassenger(name, sClassP, sPreference);
	}
	
	/**
	 * Handles group adding
	 * @param s Scanner
	 */
	public void groupOption(Scanner s) {
		s.nextLine(); // flush input stream
		String[] prompt = view.getGPrompt();
		System.out.print(prompt[0]);
		String gName = s.next();
		s.nextLine(); // flush input stream
		System.out.print(prompt[1]);
		String members = s.nextLine();
		System.out.print(prompt[2]);
		String sClassG = s.next().toLowerCase();
		model.addGroup(gName, members, sClassG);
	}
	
	/**
	 * Handles cancelling
	 * @param s Scanner
	 */
	public void cancelOption(Scanner s) {
		s.nextLine(); // flush input stream
		String[] prompt = view.getCPrompt();
		System.out.print(prompt[0]);
		char cType = s.next().toLowerCase().charAt(0);
		s.nextLine(); // flush input stream
		if (cType == 'i') {
			System.out.print(prompt[1]);
			model.cancelReservation(s.nextLine(), cType);
		} else if (cType == 'g') {
			System.out.print(prompt[2]);
			model.cancelReservation(s.next(), cType);
		}
	}
	
	/**
	 * Gets availability list
	 * @param s Scanner
	 */
	public void availabilityOption(Scanner s) {
		s.nextLine(); // flush input stream
		String[] prompt = view.getAPrompt();
		System.out.print(prompt[0]);
		String sClassA = s.next().toLowerCase();
		String[] availability = view.getAvailability(sClassA, model.getFirstClass(), model.getEconomyClass());
		if (sClassA.equals("first")) {
			System.out.println(prompt[1]);
			for (int i = 0; i < availability.length; i++) {
				System.out.print((i + 1) + ": ");
				System.out.println(availability[i]);
			}
		} else if (sClassA.equals("economy")) {
			System.out.println(prompt[2]);
			for (int i = 0; i < availability.length; i++) {
				System.out.print((i + 10) + ": ");
				System.out.println(availability[i]);
			}
		}
	}
	
	/**
	 * Handles manifest
	 * @param s Scanner
	 */
	public void manifestOption(Scanner s) {
		s.nextLine(); // flush input stream
		String[] prompt = view.getMPrompt();
		System.out.print(prompt[0]);
		String sClassM = s.next().toLowerCase();
		String[] manifest = view.getManifest(sClassM, model.getFirstClass(), model.getEconomyClass());
		if (sClassM.equals("first")) {
			System.out.println(prompt[1]);
		} else if (sClassM.equals("economy")) {
			System.out.println(prompt[2]);
		}
		for (int i = 0; i < manifest.length; i++) {
			if (manifest[i] != "") {
				System.out.println(manifest[i]);
			}
		}
	}
	
	/**
	 * Handles quitting
	 * @param s Scanner
	 * @return If file saving was successful
	 */
	public boolean saveFile(String filename) {
		Passenger[][] firstClass = model.getFirstClass();
		Passenger[][] economyClass = model.getEconomyClass();
		// save to file
		String filePath = new File("").getAbsolutePath();
		filePath += "\\" + filename;
		File saveFile = new File(filePath);
		try {
			FileWriter fw = new FileWriter(saveFile, false); // true to append-false to overwrite
			BufferedWriter bw = new BufferedWriter(fw);
			for (int i = 0; i < PlaneReservation.FIRST_CLASS_ROWS; i++) { // save first class
				for (int j = 0; j < PlaneReservation.FIRST_CLASS_COLS; j++) {
					String tempName;
					Passenger tester = firstClass[i][j];
					if (tester == null) tempName = "";
					else tempName = firstClass[i][j].getName();
					String temp = i + "," + j + "," + tempName;
					bw.write(temp);
					bw.newLine();
				}
			}
			for (int i = 0; i < PlaneReservation.ECONOMY_CLASS_ROWS; i++) { // save economy class
				for (int j = 0; j < PlaneReservation.ECONOMY_CLASS_COLS; j++) {
					String tempName;
					Passenger tester = economyClass[i][j];
					if (tester == null) tempName = "";
					else tempName = economyClass[i][j].getName();
					String temp = i + "," + j + "," + tempName;
					bw.write(temp);
					bw.newLine();
				}
			}
			ArrayList<Group> groupList = model.getGroupList();
			// save groups
			String numOfGroups = groupList.size() + "";
			bw.write(numOfGroups); // number of groups
			bw.newLine();
			for (Group g : groupList) {
				String groupInfo = g.getName();	// groupName
				bw.write(groupInfo);			// member1,member2,member3,
				bw.newLine();
				String memberList = "";
				for (Passenger p : g.getGroupPassengers()) {
					memberList += p.getName() + ",";
				}
				bw.write(memberList);
				bw.newLine();
			}
			
			bw.close();
			fw.close();
			System.out.println("File saved");
			return true;
		} catch(IOException x) {
			System.out.println("Error: " + x.getMessage());
			return false;
		}
	}
	
	/**
	 * Loads a file
	 * @param filename The file's name
	 * @return If file loading was successful
	 */
	public boolean loadFile(String filename) {
		String filePath = new File("").getAbsolutePath();
		filePath += "\\" + filename;
		File loadFile = new File(filePath);
		if (loadFile.exists()) {
			try {
				BufferedReader br = new BufferedReader(new FileReader(filePath));
				for (int i = 0; i < PlaneReservation.FIRST_CLASS_ROWS; i++) { // load first class
					for (int j = 0; j < PlaneReservation.FIRST_CLASS_COLS; j++) {
						String temp = br.readLine();
						String tempRead[] = temp.split(","); // row,col,name
						loadPassenger(tempRead, "first");
					}
				}
				for (int i = 0; i < PlaneReservation.ECONOMY_CLASS_ROWS; i++) { // load economy class
					for (int j = 0; j < PlaneReservation.ECONOMY_CLASS_COLS; j++) {
						String temp = br.readLine();
						String tempRead[] = temp.split(","); // row,col,name
						loadPassenger(tempRead, "economy");
					}
				}
				// begin group importing
				int numOfGroups = Integer.parseInt(br.readLine()); // Get number of groups
				for (int i = 0; i < numOfGroups; i++) { // import group name and members one by one
					String groupName = br.readLine();
					String members = br.readLine();
					loadGroup(groupName, members);
				}
				br.close();
			} catch (IOException x) {
				System.out.println("Error: " + x.getMessage());
			}
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Loads a passenger into the array
	 * @param input Data array retrieved from file [rows,col,name]
	 * @param sClass The service class of the passenger
	 * @return If a passenger was loaded or not
	 */
	private boolean loadPassenger(String[] input, String sClass) {
		if (input.length < 3) return false; // empty seat
		Passenger p = new Passenger(input[2]);
		if (sClass.equals("first")) {
			model.reserveSeat("first", Integer.parseInt(input[0]), Integer.parseInt(input[1]), p);
			return true;
		}
		else if (sClass.equals("economy")) {
			model.reserveSeat("economy", Integer.parseInt(input[0]), Integer.parseInt(input[1]), p);
			return true;
		}
		return false;
	}
	
	/**
	 * Loads a group into the array
	 * @return If the group was loaded or not
	 */
	private boolean loadGroup(String gName, String members) {
		Group g = new Group(gName, members);
		return model.addGroupToList(g);
	}
}
