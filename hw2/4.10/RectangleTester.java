import java.awt.geom.Rectangle2D;
import java.util.*;

public class RectangleTester {
	public static void main(String[] args) {
		ArrayList<Rectangle2D.Double> rects = new ArrayList<>();
		rects.add(new Rectangle2D.Double(0, 0, 10, 10));
		rects.add(new Rectangle2D.Double(1, 0, 10, 10));
		rects.add(new Rectangle2D.Double(0, 1, 10, 10));
		rects.add(new Rectangle2D.Double(0, 0, 10, 15));
		System.out.println("Pre-sorted");
		for (Rectangle2D.Double r : rects) {
			System.out.println(r.toString());
		}
		Collections.sort(rects, new RectangleComparator());
		System.out.println("Post-sorted");
		for (Rectangle2D.Double r : rects) {
			System.out.println(r.toString());
		}
	}
}
