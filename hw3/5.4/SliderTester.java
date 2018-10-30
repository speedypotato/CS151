/**
 * Class for testing slider
 * @author Nicholas Gadjali
 */
public class SliderTester {
	/**
	 * Main method
	 * @param args unused
	 */
	public static void main(String[] args) {
		CarModel car = new CarModel(1, 25, 100);
		CarFrame frame = new CarFrame(car);
		
		car.attach(frame);
	}
}
