package BankApplication;

public class InvalidAccountTypeException extends Exception {

		//created an exception
	public InvalidAccountTypeException() {
		super("Invalid Account Type Selected");
	}
}
