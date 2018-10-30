import java.awt.geom.Rectangle2D;
import java.util.*;

public class RectangleComparator implements Comparator<Rectangle2D.Double> {
	/**
	 * Total ordering
	 */
	@Override
	public int compare(Rectangle2D.Double r1, Rectangle2D.Double r2) {
		double r1x = r1.getX();
		double r2x = r2.getX();
		if (r1x != r2x) return (int)(r1x - r2x);
		double r1y = r1.getY();
		double r2y = r2.getY();
		if (r1y != r2y) return (int)(r1y - r2y);
		double r1w = r1.getWidth();
		double r2w = r2.getWidth();
		if (r1w != r2w) return (int)(r1w = r2w);
		double r1h = r1.getHeight();
		double r2h = r2.getHeight();
		return (int)(r1h - r2h);
	}
}
