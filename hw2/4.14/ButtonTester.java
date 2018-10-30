import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;

import javax.swing.*;

public class ButtonTester {
	/**
	 * Main running method
	 * @param args unused
	 */
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setLayout(new FlowLayout());
		
		ButtonTester bt = new ButtonTester();
		ColorCircle cc = bt.new ColorCircle();
		JLabel circle = new JLabel(cc);
		
		JButton rButton = new JButton("Red");
		rButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				cc.setColor(Color.RED);
				circle.repaint();
			}
		});
		JButton gButton = new JButton("Green");
		gButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				cc.setColor(Color.GREEN);
				circle.repaint();
			}
		});
		JButton bButton = new JButton("Blue");
		bButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				cc.setColor(Color.BLUE);
				circle.repaint();
			}
		});

		frame.add(rButton);
		frame.add(gButton);
		frame.add(bButton);
		frame.add(circle);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}
	
	/**
	 * Handles the colored circle
	 * @author Nicholas Gadjali
	 */
	private class ColorCircle implements Icon {
		private final int dimension = 50;
		private Color c;
		
		/**
		 * Gets icon height
		 */
		public int getIconHeight() {
			return dimension;
		}
		
		/**
		 * Gets icon width
		 */
		public int getIconWidth() {
			return dimension;
		}
		
		/**
		 * Sets icon color
		 * @param c The color to change to
		 * @return If successful
		 */
		public boolean setColor(Color c) {
			this.c = c;
			return true;
		}
		
		/**
		 * Paints the icon
		 */
		public void paintIcon(Component c, Graphics g, int x, int y) {
			Graphics2D g2 = (Graphics2D)g;
			Ellipse2D.Double circle = new Ellipse2D.Double(0, 0, dimension, dimension);
			g2.setColor(this.c);
			g2.fill(circle);
		}
	}
}
