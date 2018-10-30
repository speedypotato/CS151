import java.util.*;
import java.awt.*;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

import javax.swing.Icon;

public class ClockIcon implements Icon {
	private int radius;
	private Point2D.Double clockCenter;

	/**
	 * Constructs a new clock
	 * @param radius The clock's radius
	 */
	public ClockIcon(int radius) {
		this.radius = radius;
	}
	
	@Override
	/**
	 * Gets the clock's height
	 * @return The height of the clock
	 */
	public int getIconHeight() {
		return radius * 2;
	}

	@Override
	/**
	 * Gets the clock's width
	 * @return The width of the clock
	 */
	public int getIconWidth() {
		return radius * 2;
	}

	@Override
	/**
	 * Paints the clock with the current time
	 */
	public void paintIcon(Component c, Graphics g, int x, int y) {
		Graphics2D g2 = (Graphics2D)g;
		// setup clockface
		Ellipse2D.Double clockFace = new Ellipse2D.Double(x, y, this.getIconHeight(), this.getIconWidth());
		g2.setColor(Color.WHITE);
		g2.fill(clockFace);
		clockCenter = new Point2D.Double(x + radius, y + radius);
		// setup numbers
		Point2D.Double twelve = pointPlacer(0.95, 0);
		Point2D.Double one = pointPlacer(0.95, 5);
		Point2D.Double two = pointPlacer(0.95, 10);
		Point2D.Double three = pointPlacer(0.95, 15);
		Point2D.Double four = pointPlacer(0.95, 20);
		Point2D.Double five = pointPlacer(0.95, 25);
		Point2D.Double six = pointPlacer(0.95, 30);
		Point2D.Double seven = pointPlacer(0.95, 35);
		Point2D.Double eight = pointPlacer(0.95, 40);
		Point2D.Double nine = pointPlacer(0.95, 45);
		Point2D.Double ten = pointPlacer(0.95, 50);
		Point2D.Double eleven = pointPlacer(0.95, 55);
		g2.setColor(Color.BLACK);
		g2.drawString("12", (float)twelve.getX(), (float)twelve.getY());
		g2.drawString("1", (float)one.getX(), (float)one.getY());
		g2.drawString("2", (float)two.getX(), (float)two.getY());
		g2.drawString("3", (float)three.getX(), (float)three.getY());
		g2.drawString("4", (float)four.getX(), (float)four.getY());
		g2.drawString("5", (float)five.getX(), (float)five.getY());
		g2.drawString("6", (float)six.getX(), (float)six.getY());
		g2.drawString("7", (float)seven.getX(), (float)seven.getY());
		g2.drawString("8", (float)eight.getX(), (float)eight.getY());
		g2.drawString("9", (float)nine.getX(), (float)nine.getY());
		g2.drawString("10", (float)ten.getX(), (float)ten.getY());
		g2.drawString("11", (float)eleven.getX(), (float)eleven.getY());
		// setup hands
		GregorianCalendar time = new GregorianCalendar();
		int hour = time.get(Calendar.HOUR) % 12;
		int min = time.get(Calendar.MINUTE);
		int sec = time.get(Calendar.SECOND);
		Line2D.Double hourHand = new Line2D.Double(clockCenter, pointPlacer(0.5, hour * 5));
		Line2D.Double minHand = new Line2D.Double(clockCenter, pointPlacer(0.7, min));
		Line2D.Double secHand = new Line2D.Double(clockCenter, pointPlacer(0.9, sec));
		
		g2.draw(hourHand);
		g2.draw(minHand);
		g2.draw(secHand);
	}
	
	/**
	 * Gets a point for the proper placement
	 * @param ratio How far out to place the point
	 * @param placement Where to place the point in minute measurements
	 * @return Point2D.Double
	 */
	private Point2D.Double pointPlacer(double ratio, double placement) {
		double rad = placement / 30 * Math.PI;
		double dist = ratio * radius;
		double x = clockCenter.getX() + dist * Math.sin(rad);
		double y = clockCenter.getY() - dist * Math.cos(rad);
		return new Point2D.Double(x, y);
	}
}
