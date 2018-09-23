import java.util.Scanner;

public class ReservationSystem {
	public static void main(String[] args) {
		PlaneReservation model = new PlaneReservation();
		ReservationView view = new ReservationView();
		ReservationController controller = new ReservationController(model, view);
		Scanner s = new Scanner(System.in);
		
		if (args.length < 1) {
			System.out.println("No Filename Input!  Defaulting to data.txt!");
			args = new String[]{"data.txt"};
		}
		controller.loadFile(args[0]);
		boolean keepRunning = true;
		while (keepRunning) {
			System.out.print(view.getMainPrompt());
			switch(s.next().toLowerCase().charAt(0)) {
				case 'p':
					controller.passengerOption(s);
					break;
				case 'g':
					controller.groupOption(s);
					break;
				case 'c':
					controller.cancelOption(s);
					break;
				case 'a':
					controller.availabilityOption(s);
					break;
				case 'm':
					controller.manifestOption(s);
					break;
				case 'q':
					controller.saveFile(args[0]);
					keepRunning = false;
					break;
			}
			System.out.println();
		}
		s.close();
		System.out.println(view.getEndPrompt());
	}
}
