import java.util.*;

/**
 * Tests the Filter interface type
 * @author Nicholas Gadjali
 */
public class FilterTester {
	/**
	 * Main method
	 * @param args unused
	 */
	public static void main(String[] args) {
		String[] test = {"abc", "def1", "ghi", "jkl3"};
		for (String s : test) {
			System.out.print(s + " ");
		}
		System.out.println();
		String[] filtered = FilterTester.filter(test, new Filter() {
			public boolean accept(String x) {
				final int limit = 3;
				if (x.length() > limit) return false;
				return true;
			}
		});
		for (String s : filtered) {
			System.out.print(s + " ");
		}
	}
	
	/**
	 * Filters Strings
	 * @param a Input string array
	 * @param f Filter
	 * @return String array specified by filter
	 */
	public static String[] filter(String[] a, Filter f) {
		ArrayList<String> al = new ArrayList<>();
		for (String s : a) {
			if (f.accept(s)) al.add(s);
		}
		String[] returnme = new String[al.size()];
		for (int i = 0; i < returnme.length; i++) {
			returnme[i] = al.get(i);
		}
		return returnme;
	}
}
