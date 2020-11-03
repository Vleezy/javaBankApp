package BankApplication;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Menu {

	/*
	 * 
	 * Instance Variables (Non-Static Class Variable)
	 * 
	 */

	Scanner keyboard = new Scanner(System.in); // Scanner import to work
	Bank bank = new Bank();// New object has list of customers
	boolean exit; // Default false

	/*
	 * 
	 * Main Method
	 * 
	 *
	 */

	public static void main(String[] args) { // Main method

		Menu menu = new Menu(); // Creating new Menu Object
		menu.runMenu(); // Telling Menu to run
	}

	public void runMenu() {
		printHeader();

		while (!exit) {
			printMenu();
			int choice = getInput();
			performAction(choice);
		}
	}

	/*
	 * 
	 * Header
	 * 
	 */

	
	private void printHeader() { // Prints out a visual aid when loaded
		System.out.println("  _________________________________________");
		System.out.println(" || *                                   * ||");
		System.out.println(" || *            Welcome To             * ||");
		System.out.println(" || *           Romero's Bank           * ||");
		System.out.println(" ||_______________________________________||");

	}

	/*
	 * 
	 * Keyboard Input/ Print Menu
	 * 
	 */
	
	private void printMenu() { // Selections Menu
		displayHeader("Select An Option");
		System.out.println("1) Create A New Account");
		System.out.println("2) Deposit");
		System.out.println("3) Withdraw");
		System.out.println("4) Account Balance");
		System.out.println("0) Exit");
	}

	
	private int getInput() { // Takes input to store into integer choice
		int choice = -1;

		// do,while loop guarantees it runs once
		do {
			System.out.print("Enter Selection: ");
			try { // Error Handling
				choice = Integer.parseInt(keyboard.nextLine()); // Takes keyboard input + converts to an integer +
																// stores it in choice
			} catch (NumberFormatException e) {
				System.out.println("Invalid, Please Select A Number Displayed!");
			}
			if (choice < 0 || choice > 4) {
				System.out.println("Invalid, Please Select A Number Displayed!");
			}
		} while (choice < 0 || choice > 4);

		return choice;
	}

	/*
	 * 
	 * Selection Methods
	 * 
	 */

	private void performAction(int choice) {
		switch (choice) {
		case 0:
			System.out.println("Tip: Here at Romero's Bank We Value Our Customers!" + "\n" + "Have A Nice Day!");
			System.exit(0); // Exits program. 0 = standard exit
			break;
		case 1:
			try {
				createAnAccount(); // clicked surround with try catch
			} catch (InvalidAccountTypeException e) {
				System.out.println("Account Was Not Created Successfully.");
			}

			break;
		case 2:
			makeADeposit();

			break;
		case 3:
			makeAWithdrawl();

			break;
		case 4:
			ListBalances();

			break;
		default:
			System.out.println("Error, Please Contact Support If Issue Is Persistent.");
		}

	}

	/*
	 * 
	 * Question / get account
	 * 
	 */

	private String askQuestion(String question, List<String> answers) { // method question
		String response = "";
		Scanner input = new Scanner(System.in);
		// ternary: if you try to enter 0 will say false
		boolean choices = ((answers == null) || answers.size() == 0) ? false : true;

		boolean firstRun = true;

		do {
			if (!firstRun) {
				System.out.println("Invalid Selection. Please Try Again!");
			}
			System.out.print(question);

			if (choices) {
				System.out.print("(");
				for (int i = 0; i < answers.size() - 1; ++i) {
					System.out.print(answers.get(i) + "/");
				}
				System.out.print(answers.get(answers.size() - 1));
				System.out.print("): ");
			}

			response = input.nextLine();
			firstRun = false;

			if (!choices) {
				break;
			}

		} while (!answers.contains(response));
		return response;
	}

	/*
	 * 
	 * Get Deposit
	 * 
	 */

	private double getDeposit(String accountType) {
		double initialDeposit = 0;
		// Getting initial deposit
		boolean valid = false;
		while (!valid) {
			System.out.println("Please Enter An Initial Deposit: ");

			// Error Handling + initial deposit
			try {
				initialDeposit = Double.parseDouble(keyboard.nextLine());
			} catch (NumberFormatException e) {
				System.out.println("Please Enter A Valid Number.");
			}
			if (accountType.equalsIgnoreCase("checking")) {
				if (initialDeposit < 100) {
					System.out.println("Checking Account Minimum Requirement: $100");
				} else {
					valid = true;
				}
			} else if (accountType.equalsIgnoreCase("savings")) {
				if (initialDeposit < 50) {
					System.out.println("Savings Account Minimum Requirement: $50");
				} else {
					valid = true;
				}
			}
		}
		return initialDeposit;
	}

	/*
	 * 
	 * Create Account
	 * 
	 */

	
	private void createAnAccount() throws InvalidAccountTypeException { // added throw declaration
		displayHeader("Create An Account");
		
		// get account information

		// String firstName, lastName, ssn; //<-- No longer needed using it below
		String accountType = askQuestion("Enter Account Type:", Arrays.asList("checking", "savings"));
		String firstName = askQuestion("Please Enter Your First Name: ", null); // instead of sysout we make it a // method; since it returns a String you can assign.
		String lastName = askQuestion("Please Enter Your Last Name: ", null); // Entering a firstName null arrayList
		String ssn = askQuestion("Please Enter Your SSN: ", null);
		// initial deposit
		double initialDeposit = getDeposit(accountType);

		// Create Account
		Account account;
		if (accountType.equalsIgnoreCase("checking")) {
			account = new Checking(initialDeposit);
		} else if (accountType.equalsIgnoreCase("savings")) {
			account = new Savings(initialDeposit);
		} else {
			throw new InvalidAccountTypeException();
		}
		// Create Customer
		Customer customer = new Customer(firstName, lastName, ssn, account);
		bank.addCustomer(customer); // Bank adds customer we create

	}

	/*
	 * 
	 * Get Amount
	 * 
	 */

	private double getDollarAmount(String question) {

		System.out.print(question);
		double amount = 0;

		// Error
		try {
			amount = Double.parseDouble(keyboard.nextLine());
		} catch (NumberFormatException e) { // If you enter a word it will default to 0 ; connects to Account.java
			amount = 0;
		}
		return amount;
	}

	/*
	 * 
	 * Make Deposit
	 * 
	 */

	private void makeADeposit() {
		displayHeader("Make A Deposit");
		int account = selectAccount();

		if (account >= 0) {
			double amount = getDollarAmount("How Much Would You Like To Deposit: ");
			bank.getCustomer(account).getAccount().deposit(amount);
		}
	}

	/*
	 * 
	 * Make Withdraw
	 * 
	 */

	private void makeAWithdrawl() {
		displayHeader("Make A Withdrawl");
		int account = selectAccount();

		if (account >= 0) {
			double amount = getDollarAmount("How Much Would You Like To Withdraw: ");
			bank.getCustomer(account).getAccount().withdraw(amount);
		}

	}

	/*
	 * 
	 * Balance
	 * 
	 */

	private void ListBalances() {
		displayHeader("Account Details");
		int account = selectAccount();

		if (account >= 0) {
			displayHeader("Account Details");
			System.out.println(bank.getCustomer(account).getAccount());
		}

	}

	/*
	 * 
	 * Display Header
	 * (Title Headers)
	 * 
	 */
	
	private void displayHeader(String message) {
		System.out.println(); // <- Prints empty line out or you can use "/n" <- new line
		StringBuilder sb = new StringBuilder(); // <- Lets you build up a string, rather then create one each time you do something
		int width = message.length() + 6; //<- display header to width
		sb.append("+");
		for(int i = 0; i < width; i++) {
			sb.append("-");
		}
		sb.append("+");
		System.out.println(sb.toString());
		System.out.println("|   " + message + "   |");
		System.out.println(sb.toString());
	}
	
	/*
	 * 
	 * Select Account
	 * 
	 */

	private int selectAccount() {
		ArrayList<Customer> customers = bank.getCustomers();

		// If no customer exists at bank
		if (customers.size() <= 0) {
			System.out.println("Invalid, No Customer Exists.");
			return -1;
		}

		System.out.println("Select An Account: ");

		// import Java ArrayList
		for (int i = 0; i < customers.size(); i++) {
			System.out.println("\t" + (i + 1) + ") " + customers.get(i).basicInfo()); // basicInfo: customer.java
		}

		int account;
		System.out.print("Please Enter Selection: ");

		// Error (If input is wrong)
		try {
			account = Integer.parseInt(keyboard.nextLine()) - 1;
		} catch (NumberFormatException e) { // If you enter a word it will default to 0 ; connects to Account.java
			account = -1;
		}

		// Making sure you select existing account
		if (account < 0 || account > customers.size()) {
			System.out.println("Invalid Account Selected.");
			account = -1;
		}
		return account;
	}

}
