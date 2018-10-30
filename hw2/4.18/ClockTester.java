import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class ClockTester {
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setLayout(new FlowLayout());
		
		JLabel clock = new JLabel(new ClockIcon(250));
		frame.add(clock);
		ActionListener listener = new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				clock.repaint();
			}
		};
		Timer t = new Timer(1000, listener);
		t.start();
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}
}
