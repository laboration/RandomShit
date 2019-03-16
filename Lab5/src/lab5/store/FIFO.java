package lab5.store;
import java.util.ArrayList;
import java.util.List;

import java.util.NoSuchElementException;

import lab5.store.Customer;


public class FIFO {


	private List<Customer>Queue =new ArrayList<Customer>(); // skapar en Customer queue (kö)
	
	private static int maxSize=0;
	
	/** Denna returnerar första objektet i FIFO-Queue
	 */
	public Customer first() throws NoSuchElementException {
		if(Queue.isEmpty()) {
			throw new NoSuchElementException("FIFO-Queue är tom");
		}else{
			return Queue.get(0);     //Retunerear första elementet i Queue
		}
	}	

	public void add(Customer arg0) {  // Lägger in ny customer 
		Queue.add(arg0);
		
		if (Queue.size()< maxSize()) {
			 maxSize = Queue.size();
			
			
		}
	}	

	
	public boolean isEmpty() {		//Kollar om FIFO-Queue är tom eller inte
	 if (Queue.isEmpty()) {
		 return true ;
	 }else{
		 return false; }
	}
	

	
	public int maxSize() {
		return maxSize;   
		
	}

	
	public void removeFirst() throws NoSuchElementException { //Tar bort första item av Queue
		if (Queue.isEmpty()){
			throw new NoSuchElementException("FIFO-Queue är tom");
		}else {
			Queue.remove(0);			
		}
		
	}


	public int size() {
		return Queue.size();
	}
	 
	
	
	

 /**
  * Returnerar ID av Customer i Queue i form av Strängar.
  */
	public String toString() {
		int length = Queue.size();
		String QueueString = "";
		Customer Element;
		int ID;
		for (int i = 0; i < length; i++) {
			Element = Queue.get(i);
			ID = Element.findCustomer();
			QueueString = QueueString + String.valueOf(ID);
			if (i + 1 < length) {
				QueueString += ", ";
			}
		}
		
		return QueueString;
	}
}