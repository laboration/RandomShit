package Store;
import java.util.ArrayList;
import java.util.List;

import java.util.NoSuchElementException;

import Store.Customer;


public class FIFO {




	private List<Customer>Queue =new ArrayList<Customer>(); // skapar en Customer queue (k�)
	
	private static int maxSize=0;
	
			
	

	public void add(Customer arg0) {  // L�gger in ny customer 
		Queue.add(arg0);
		
		if (Queue.size()< maxSize()) {
			 maxSize = Queue.size();
			
			
		}
	}	
	

	
	/** Denna returnerar f�rsta objektet i FIFO-Queue
	 */
	public Object first() throws NoSuchElementException {
		if(Queue.isEmpty()) {
			throw new NoSuchElementException("FIFO-Queue �r tom");
		}else{
			return Queue.get(0);     //Retunerear f�rsta elementet i Queue
		}
		
		
	}

	
	public boolean isEmpty() {		//Kollar om FIFO-Queue �r tom eller inte
	 if (Queue.isEmpty()) {
		 return true ;
	 }else{
		 return false; }
	}
	

	
	public int maxSize() {
		return maxSize;   
		
	}

	
	public void removeFirst() throws NoSuchElementException { //Tar bort f�rsta item av Queue
		if (Queue.isEmpty()){
			throw new NoSuchElementException("FIFO-Queue �r tom");
		}else {
			Queue.remove(0);			
		}
		
	}


	public int size() {
		return Queue.size();
	}
	 
	
	
	

 /**
  * Returnerar ID av Customer i Queue i form av Str�ngar.
  */
	public String toString() {
		String a = "";
		for (int i=0; i< Queue.size();i++) {
			a+= "(" + String.valueOf(Queue.get(i)) + ") "; //konverterar v�ra values till strings
			
		}
		return "Queue: " + a;

	
	}
	}




