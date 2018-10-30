import java.util.Comparator;

/**
A country with a name and area.
*/
public class Country implements Comparable<Country> {
	/**
	 * Comparing for sorting by name
	 * @param increasing Sort by increasing order if true
	 * @return Comparator as specified
	 */
	public static Comparator<Country> createComparatorByName(final boolean increasing) {
		return new Comparator<Country>() {
			public int compare(Country c1, Country c2) {
				if (increasing) return c1.getName().compareTo(c2.getName());
				else return c2.getName().compareTo(c1.getName());
			}
		};
	}

	/**
	 * Comparator for sorting by area
	 * @param increasing Sort by increasing order if true
	 * @return Comparator as specified
	 */
	public static Comparator<Country> createComparatorByArea(final boolean increasing) {
		return new Comparator<Country>() {
			public int compare(Country c1, Country c2) {
				if (increasing) return (int)(c1.getArea() - c2.getArea());
				else return (int)(c2.getArea() - c1.getArea());
			}
		};
	}
	
	/**
	 * Simple toString for easier printing
	 */
	@Override
	public String toString() {
		return this.getName() + " " + this.getArea();
	}

	/**
	 * Constructs a country.
	 * 
	 * @param aName
	 *            the name of the country
	 * @param anArea
	 *            the area of the country
	 */
	public Country(String aName, double anArea) {
		name = aName;
		area = anArea;
	}

	/**
	 * Gets the name of the country.
	 * 
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Gets the area of the country.
	 * 
	 * @return the area
	 */
	public double getArea() {
		return area;
	}

	/**
	 * Compares two countries by area.
	 * 
	 * @param other
	 *            the other country
	 * @return a negative number if this country has a smaller area than
	 *         otherCountry, 0 if the areas are the same, a positive number
	 *         otherwise
	 */
	public int compareTo(Country other) {
		if (area < other.area)
			return -1;
		if (area > other.area)
			return 1;
		return 0;
	}

	private String name;
	private double area;
}

