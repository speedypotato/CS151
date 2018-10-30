import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import javax.swing.Icon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * Graph View
 * @author Nicholas Gadjali
 */
public class GraphView extends JFrame implements ChangeListener {
	private static final int RECT_SPACING = 50;
	private static final int RECT_HEIGHT = 100;
	
	private Model model;
	private ArrayList<Integer> numbers;
	
	public GraphView(Model m) {
		model = m;
		numbers = model.getData();
		
		setLocation(0, 100);
		setLayout(new BorderLayout());
		
		Icon bars = new Icon() {
			@Override
			public void paintIcon(Component c, Graphics g, int x, int y) {
				Graphics2D g2 = (Graphics2D)g;
				g2.setColor(Color.black);
				
				int index = 0;
				for (int i : numbers) {
					Rectangle2D.Double rectangle = new Rectangle2D.Double
								(0, RECT_SPACING * index, i, RECT_SPACING);
					index++;
					g2.fill(rectangle);
				}
			}
			public int getIconHeight() { return RECT_HEIGHT; }
			public int getIconWidth() { return RECT_SPACING; }
		};
		
		MouseListener ml = new MouseListener() {
			public void mousePressed(MouseEvent e) {
				int x = e.getX();
				int y = e.getY();
				int index = 0;
				while (index * RECT_SPACING < y) index++; //Find bar to change
				model.update(--index, x);
			}
			public void mouseClicked(MouseEvent e) { }
			public void mouseEntered(MouseEvent e) { }
			public void mouseExited(MouseEvent e) { }
			public void mouseReleased(MouseEvent e) { }
		};
		
		JLabel barGraph = new JLabel(bars);
		barGraph.addMouseListener(ml);
		add(barGraph);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setVisible(true);
	}
	
	public void stateChanged(ChangeEvent e) {
		numbers = model.getData();
		repaint();
	}
}
