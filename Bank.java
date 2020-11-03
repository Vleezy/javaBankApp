package BankApplication;

import java.util.ArrayList;

public class Bank {

	ArrayList<Customer> customers = new ArrayList<Customer>(); //Import ArrayList 

	public void addCustomer(Customer customer) {
		customers.add(customer);

	}

	Customer getCustomer(int account) {
		return customers.get(account); //will return customer
	}

	ArrayList<Customer> getCustomers(){
		return customers;
	}

}
