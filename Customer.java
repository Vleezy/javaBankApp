package BankApplication;

public class Customer {


	/*
	 * 
	 * Fields
	 * 
	 */

	private final String firstName;
	private final String lastName;
	private final String ssn;
	private final Account account;	


	/*
	 * 
	 * Constructor
	 * 
	 */

	public Customer(String firstName, String lastName, String ssn, Account account) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.ssn = ssn;
		this.account = account;

	}

	/*
	 * 
	 * Annotation
	 * 
	 */

	@Override
	public String toString() {
		return "\nCustomer Information\n" + 
				"First Name: " + firstName + "\n" +
				"Last Name: " + lastName + "\n" +
				"SSN: " + ssn + "\n" +
				account;
	}


	public String basicInfo() { 	// returns account info
		return " Account Number: " + account.getAccountNumber() + " - Name: " + firstName + " " + lastName;
//				" SSN: " + ssn +
	}

	/*
	 * 
	 * Getter
	 * 
	 */

	Account getAccount() {
		return account;
	}
}
