import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * Number column
 * @author Nicholas Gadjali
 */
public class TextFrame extends JFrame implements ChangeListener {
	private Model model;
	private JTextField[] textFields;
	
	public TextFrame(Model m) {
		model = m;
		
		Container contentPane = this.getContentPane();
		setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS)); //top-down list
		
		ArrayList<Integer> numbers = model.getData();
		textFields = new JTextField[numbers.size()]; //creates list of editable text fields
		
		//Listener for textfields
		ActionListener al = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JTextField f = (JTextField)e.getSource(); //Get field with event
				int index = 0;
				for (int i = 0; i < textFields.length; i++) { //Get field index
					if (textFields[i] == f) {
						index = i;
						break;
					}
				}
				int number = Integer.parseInt(f.getText()); //Get new number
				model.update(index, number); //Update number in model
			}
		};
		
		for (int i = 0; i < numbers.size(); i++) {
			JTextField textField = new JTextField(numbers.get(i).toString(), 10); //10=width
			textField.addActionListener(al);
			add(textField); //Add to JFrame
			textFields[i] = textField; //Update textfield
		}
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setVisible(true);
	}
	
	public void stateChanged(ChangeEvent e) {
		ArrayList<Integer> updated = model.getData();
		int index = 0;
		for (JTextField j : textFields) {
			j.setText(updated.get(index).toString());
			index++;
		}
	}
}
