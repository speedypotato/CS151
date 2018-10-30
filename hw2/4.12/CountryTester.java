import java.util.*;

public class CountryTester {
	public static void main(String[] args) {
		ArrayList<Country> a = new ArrayList<>();
		a.add(new Country("A", 4));
		a.add(new Country("C", 2));
		a.add(new Country("B", 3));
		a.add(new Country("D", 1));
		System.out.println("Pre-sorted");
		for (Country c : a) {
			System.out.println(c.toString());
		}
		Collections.sort(a, Country.createComparatorByName(true));
		System.out.println("Sorted by increasing name");
		for (Country c : a) {
			System.out.println(c.toString());
		}
		Collections.sort(a, Country.createComparatorByName(false));
		System.out.println("Sorted by decreasing name");
		for (Country c : a) {
			System.out.println(c.toString());
		}
		Collections.sort(a, Country.createComparatorByArea(true));
		System.out.println("Sorted by increasing area");
		for (Country c : a) {
			System.out.println(c.toString());
		}
		Collections.sort(a, Country.createComparatorByArea(false));
		System.out.println("Sorted by decreasing area");
		for (Country c : a) {
			System.out.println(c.toString());
		}
	}
}
