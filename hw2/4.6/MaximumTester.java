import java.util.*;

public class MaximumTester {
	/**
	 * Test the static method "maximum"
	 */
	public static void main(String[] args) {
		ArrayList<String> a = new ArrayList<>();
		a.add("a");
		a.add("aba");
		a.add("acaa");
		a.add("adada");
		a.add("aeabea");
		a.add("abcde");
		a.add("aeiou");
		
		System.out.println(maximum(a, new TestComparator()));
	}
	
	/**
	 * 
	 * @param a ArrayList of Strings
	 * @param c Comparator to sort by
	 * @return the largest string
	 */
	public static String maximum(ArrayList<String> a, Comparator<String> c) {
		if (a.size() == 0) return null;
		String max = a.get(0);
		for (String temp : a) {
			if (c.compare(temp, max) > 0) max = temp;
		}
		return max;
	}
	
	/**
	 * This particular comparator sorts by the vowel count
	 */
	private static class TestComparator implements Comparator<String> {
		@Override
		public int compare(String s1, String s2) {
			int s1v = 0;
			int s2v = 0;
			HashSet<Character> vowels = new HashSet<Character>();
			vowels.add('a');
			vowels.add('e');
			vowels.add('i');
			vowels.add('o');
			vowels.add('u');
			char[] cArray = s1.toCharArray();
			for (char temp : cArray) {
				if (vowels.contains(temp)) s1v++;
			}
			cArray = s2.toCharArray();
			for (char temp : cArray) {
				if (vowels.contains(temp)) s2v++;
			}
			return s1v - s2v;
		}
	}
}
