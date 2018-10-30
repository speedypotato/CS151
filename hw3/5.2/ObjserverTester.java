import java.util.*;

/**
 * Test the Observer implementation
 * @author Nicholas Gadjali
 */
public class ObjserverTester {
	public static void main(String[] args) {
		ArrayList<Integer> numbers = new ArrayList<>();
		
		//add test numbers
		numbers.add(10);
		numbers.add(30);
		numbers.add(60);
		numbers.add(100);
		
		Model m = new Model(numbers);
		TextFrame text = new TextFrame(m);
		GraphView bars = new GraphView(m);
		
		m.attach(bars);
		m.attach(text);
	}
}
