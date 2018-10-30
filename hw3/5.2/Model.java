import java.util.*;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Model {
	private ArrayList<Integer> numbers;
	private ArrayList<ChangeListener> listeners;
	
	/**
	 * Constructs a new model
	 * @param numbers
	 */
	public Model(ArrayList<Integer> numbers) {
		this.numbers = numbers;
		this.listeners = new ArrayList<>();
	}
	
	/**
	 * Attaches a ChangeListener to the model
	 * @param cl The listener to attach
	 */
	public void attach(ChangeListener cl) {
		listeners.add(cl);
	}
	
	/**
	 * Gets the data
	 * @return the data
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<Integer> getData() {
		return (ArrayList<Integer>)numbers.clone();
	}
	
	/**
	 * Change the data and let the listeners know
	 * @param loc index to change
	 * @param val value to set
	 */
	public void update(int loc, int val) {
		numbers.set(loc, val);
		for (ChangeListener c : listeners) c.stateChanged(new ChangeEvent(this));
	}
}
