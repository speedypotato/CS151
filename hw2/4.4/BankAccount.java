/**
 * Simulates a bank account
 * @author Nicholas Gadjali
 */
public class BankAccount implements Comparable<BankAccount> {
	private double balance;
	private String owner;
	
	/**
	 * Creates a bank account
	 * @param owner The bank account owner's name
	 */
	public BankAccount(String owner) {
		this.balance = 0;
	}
	
	/**
	 * Creates a bank account
	 * @param owner The bank account owner's name
	 * @param balance The starting balance
	 */
	public BankAccount(String owner, double balance) {
		this.owner = owner;
		this.balance = balance;
	}
	
	/**
	 * Owner setter method
	 * @param owner The name of the account owner
	 * @return If operation was successful
	 */
	public boolean setOwner(String owner) {
		this.owner = owner;
		return true;
	}
	
	/**
	 * Compares bank account balances
	 */
	public int compareTo(BankAccount that) {
		if (this.balance == that.balance) return 0;
		else return (int)(this.balance - that.balance);
	}
	
	/**
	 * Gets bank account info
	 * @return The owner name and balance
	 */
	public String getInfo() {
		return this.owner + ": " + this.balance;
	}
}
