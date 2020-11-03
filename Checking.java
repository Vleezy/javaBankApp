package BankApplication;

public class Checking extends Account{
	private static String accountType = "Checking";
	
	/*
	 * 
	 * Constructor
	 * 
	 */
	
	Checking(double initialDeposit){
		this.setBalance(initialDeposit);
		
		this.checkInterest(0); //Doing the same as bottom (Account.java)
		
//		if(initialDeposit > 10000) {
//			this.setInterest(0.05); //Setting interest
//		}
//		else {
//			this.setInterest(0.02); //Setting interest
//		}
	}
	
	@Override
	public String toString() {
	return "Account Type: " + accountType + " Account\n" +
	"Account Number: " + this.getAccountNumber() + "\n" +
	"Balance: " + this.getBalance() + "\n" +
	"Interest Rate: " + this.getInterest() + "%\n";
	}
}
