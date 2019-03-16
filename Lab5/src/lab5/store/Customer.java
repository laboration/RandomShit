package lab5.store;

public class Customer {
	private int customerid;
	Customer(int id){   // skapar en ny kund och sätter en id med parameter
		customerid =id;  // Där id numret för kunden, alltså t.ex kund nummer 1 
	}
	public int findCustomer() {
		return customerid;
	}
}