package Store;

public class Customer {
	private int customerid;
	Customer(int id){   // skapar en ny kund och s�tter en id med parameter
		customerid =id;  // D�r id numret f�r kunden, allts� t.ex kund nummer 1 
	}
	public int findCustomer() {
		return customerid;
	}



}
