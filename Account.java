package BankApplication;

public class Account {

	/*
	 * 
	 * Variables
	 * 
	 */

	private double balance = 0; //initial balance
	private double interest = 0.2; //initial 2% 
	private int accountNumber;
	private static int numberOfAccounts = 10001;

	/*
	 * 
	 * Constructor
	 * 
	 */

	public Account() {
		accountNumber = numberOfAccounts++;
	}

	/*
	 * 
	 * Getters & Setters
	 * 
	 */

	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public double getInterest() {
		return interest * 100; // to get percent number
	}
	public void setInterest(double interest) {
		this.interest = interest;
	}
	public int getAccountNumber() { //only get not set because we shouldn't be able to set an account number
		return accountNumber;
	}

	/*
	 * 
	 * Withdraw Method
	 * 
	 */

	public void withdraw(double amount) {
		if(amount + 5 > balance) {
			System.out.println("Insufficient Funds");
			return;
		}
		balance -= amount + 5;
		checkInterest(0);
		System.out.println("ATM Fee: You Have Withdrawn $" + amount + " And Incurred A $5 Fee.");
		System.out.println("Current Balance: $" + balance);
	}

	/*
	 * 
	 * Deposit Method
	 * 
	 */

	public void deposit(double amount) {
		if(amount <= 0) {
			System.out.println("Please Deposit Valid Amount.");
			return;
		}

		checkInterest(amount); //Each time you deposit/withdraw it resets interest rate

		amount = amount + amount * interest;
		balance += amount;
		System.out.println("Deposit: You Have Deposited $" + amount + " With An Interest Rate Of " + (interest*100) + "%"); //interest is a double so you have to multiply by 100 to convert to percent
		System.out.println("Current Balance: $" + balance);
	}

	public void checkInterest(double amount) {
		if(balance + amount > 10000) {
			interest = 0.05;
		}
		else {
			interest = 0.02;
		}
	}
}
