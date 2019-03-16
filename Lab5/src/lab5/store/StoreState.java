package lab5.store;

import lab5.random.ExponentialRandomStream;
import lab5.random.UniformRandomStream;
import lab5.simulator.State;
import lab5.Options;

/**
 * @author Mikael Alanko, Tom Hammarkvist, Rickard Holmberg, Fredrik Lindgren
 */

/**
 * Creates the multiple states that the store can have.
 *
 */
public class StoreState extends State {
	private boolean storeOpen, prevStoreOpen;
	private int availCheckouts = Options.getCashierMax(), prevAvailCheckouts = Options.getCashierMax(), maxCashiers = Options.getCashierMax(); 
	private int people = 0, prevPeople;
	private int served = 0, prevServed;
	private int missed = 0, prevMissed;
	private int nrQueued, prevNrQueued;
	private double timeQueued, checkoutsIdleTime;
	private int curQueue, prevQueue;
	private double lastEventTime;
	
	private Customer prevCustomer;
	private String prevEvent;
	private boolean lastCustomerDenied;

	private ExponentialRandomStream expGen = new ExponentialRandomStream(Options.getLambda(), Options.getSeed());
	private UniformRandomStream uniGenP = new UniformRandomStream(Options.getPlockMin(), Options.getPlockMax(), Options.getSeed());
	private UniformRandomStream uniGenC = new UniformRandomStream(Options.getPayMin(), Options.getPayMax(), Options.getSeed());
	
	private CustomerFactory factory = new CustomerFactory();
	
	private FIFO checkoutQueue = new FIFO();
	private String prevCheckoutQueue = checkoutQueue.toString();
	
	private ArrivalTimeGen arrivalTimeGen = new ArrivalTimeGen();
	private ShoppingTimeGen shoppingTimeGen = new ShoppingTimeGen();
	private CheckoutTimeGen checkoutTimeGen = new CheckoutTimeGen();

	/**
	 * Creates the store state
	 */
	
	public StoreState() {
	}
	
	/**
	 * Increase the amount of available checkouts.
	 */
	public void incAvailCheckouts() {
		if(availCheckouts < Options.getCashierMax()) {
		availCheckouts++;
		}
	}
	
	/**
	 * Decreases the current amount of checkouts available
	 */
	public void decAvailCheckouts() {
		if (availCheckouts > 0) {
		availCheckouts--;
		}
	}
	
	/**
	 * Returns the previous customer
	 * @return Returns the previous customer
	 */
	public Customer getPrevCustomer() {return prevCustomer;}
	
	/**
	 * Stores the previous customer
	 * @param prevCustomer previous customer
	 */
	public void setPrevCustomer(Customer prevCustomer) {this.prevCustomer = prevCustomer;}
	
	/**
	 * Returns the previous event.
	 * @return Returns the previous event.
	 */
	public String getPrevEvent() {return prevEvent;}
	
	/**
	 * Stores the previous event.
	 * @param prevEvent name of the previous event
	 */
	public void setPrevEvent(String prevEvent) {this.prevEvent = prevEvent;}
	
	/**
	 * Checks whether it store is open or not.
	 * @return true if open, false if closed
	 */
	public boolean isStoreOpen() {return storeOpen;}
	
	/**
	 * Stores the storeOpen variable
	 * @param storeOpen state of store
	 */
	public void setStoreOpen(boolean storeOpen) {this.storeOpen = storeOpen;}
	
	/**
	 * Returns the current amount of checkouts available
	 * @return The current amount of checkouts available
	 */
	public int getAvailCheckouts() {return availCheckouts;}
	
	/**
	 * Returns the time of the last event performed.
	 * @return The time of the last event performed.
	 */
	public double getLastEventTime() {return lastEventTime;}
	
	/**
	 * Returns the previous amount of people that queued
	 * @return The previous amount of people that queued.
	 */
	public int getPrevNrQueued() {return prevNrQueued;}
	
	/**
	 * Returns the current amount of people in the store.
	 * @return The current amount of people in the store.
	 */
	public int getPeople() {return people;}
	
	/**
	 * Returns the previous amount of people in the store.
	 * @return The previous amount of people in the store.
	 */
	public int getPrevPeople() {return prevPeople;}
	
	/**
	 * Increases the number of people in the store by one.
	 */
	public void incPeople() {people++;}
	
	/**
	 * Decreases the amount of people in the store by one.
	 */
	public void decPeople() {people--;}
	
	/**
	 * Returns the current amount of served customers.
	 * @return The current amount of served customers.
	 */
	public int getServed() {return served;}
	
	/**
	 * increases the number of customers served by one.
	 */
	public void incServed() {served++;}
	
	/**
	 * Returns the previous amount of customers that have been served.
	 * @return The previous amount of customers that have been served.
	 */
	public int getPrevServed() {return prevServed;}
	
	/**
	 * Returns the current amount of missed customers.
	 * @return The current amount of missed customers.
	 */
	public int getMissed() {return missed;}
	
	/**
	 * Increases the amount of customers missed by one.
	 */
	public void incMissed() {missed++;}
	
	/**
	 * Returns the amount of previously missed customers.
	 * @return Returns the amount of previously missed customers.
	 */
	public int getPrevMissed() {return prevMissed;}
	
	/**
	 * Return the amount of people that have queued.
	 * @return An integer with the amount of people that have queued.
	 */
	public int getNrQueued() {return nrQueued;}
	
	/**
	 * Increases the number of people that have queued.
	 */
	public void incQueued() {nrQueued++;}
	
	/**
	 * Returns the time people have queued.
	 * @return The time people have queued.
	 */
	public double getTimeQueue() {return timeQueued;}
	
	/**
	 * Returns the current size of the checkout queue.
	 * @return The current size of the checkout queue.
	 */
	public int getQueueSize() {return checkoutQueue.size();}
	
	/**
	 * Returns whether the store was previously open or not.
	 * @return Returns whether the store was previously open or not.
	 */
	public boolean getPrevStoreOpen() {return prevStoreOpen;}
	
	/**
	 * Returns the previous queue size.
	 * @return Returns the previous queue size.
	 */
	public int getPrevQueueSize() {return prevQueue;}
	
	/**
	 * Returns the previously available checkouts
	 * @return Returns the previously available checkouts
	 */
	public int getPrevAvailCheckouts() {return prevAvailCheckouts;}
	
	/**
	 * Returns the time that the checkout station has been idle.
	 * @return Returns the time that the checkout station has been idle.
	 */
	public double getCheckoutsIdleTime() {return checkoutsIdleTime;}
	
	/**
	 * Returns the current queue.
 	* @return Returns the current queue.
 	*/
	public int getCurQueue() {return curQueue;}
	
	/**
	 * Returns the current checkout queue.
	 * @return Returns the current checkout queue.
	 */
	public FIFO getCheckoutQueue() {return checkoutQueue;}
	
	/**
	 * Returns the previous checkout queue.
	 * @return Returns the previous checkout queue.
	 */
	public String getPrevCheckoutQueue() {return prevCheckoutQueue;}
	
	/**
	 * Creates and returns a new customer.
	 * @return Creates and returns a new customer.
	 */
	public Customer createCustomer() {return factory.createCustomer();}
	
	
	// Difference in time from lastevent * the queue size
	/**
	 * Increasing the queue time.
	 */
	public void incQueueTime()	{
			//System.out.println(getTimeCurrent() + " " + getTimePast() + " " + getPrevQueueSize());
			timeQueued += (getTimeCurrent()-getTimePast()) * getPrevQueueSize();
	}
	
	
	// Difference in time from lastevent * the unused checkouts
	/**
	 * Increases the idle time for the checkout station.
	 */
	public void incCheckoutsIdleTime()	{checkoutsIdleTime += (getTimeCurrent()-getTimePast()) * getPrevAvailCheckouts();}
	

	
	
	/*-------------Time Generators------------------- */
	
	
	/**
	 * Returns a new arrival time
	 * @return Returns a new arrival time
	 */
	public double getNewArrivalTime() {return arrivalTimeGen.getNewArrivalTime();}
	
	/**	
	 * Returns a new shopping time
 	 * @return Returns a new shopping time
 	 */
	public double getNewShoppingTime() {return shoppingTimeGen.getNewShoppingTime();}
	
	/**
	 * Returns a new checkout time.
 	 * @return Returns a new checkout time.
 	 */
	public double getNewCheckoutTime() {return checkoutTimeGen.getCheckoutTime();}
	
	
	/**	
	 * Checks whether the store is full or not.
 	 * @return Checks whether the store is full or not.
 	 */
	
	public boolean getlastCustomerDenied() {
		return lastCustomerDenied;
	}
	
	public void setlastCustomerDenied(boolean status) {
		this.lastCustomerDenied = status;
	}
	
	public boolean storeNotFull() {
		if(people == Options.getPeopleMax()) {
			return false;
		} else {
			return true;
		}
	}
	/**
	* Updates important values if the store is still open.
	* Stores important value before the next event occurs
	*/
	public void updateEvent() {
		

		
		if(!flag){
			
			//Don not update if stop is flagged
		} 
		
		if (prevEvent == "ARRIVAL" && lastCustomerDenied) {
			incQueueTime();
			incCheckoutsIdleTime();
			lastEventTime= getTimeCurrent();
			
		}else { 
			if(!storeOpen && prevEvent == "ARRIVAL" && prevPeople == 0) {
				// Special case where you don't want to update queue time and idle time.
				setChanged();
				notifyObservers();
				
			} else {
			
				incCheckoutsIdleTime();
				incQueueTime();
				setChanged();
				notifyObservers();
				lastEventTime = getTimeCurrent();
			}
		}
		
		//Saves important values before next event.
		prevCheckoutQueue = checkoutQueue.toString();
		prevStoreOpen = storeOpen;
		prevNrQueued = nrQueued;
		prevPeople = people;
		prevServed = served;
		prevMissed = missed;
		prevQueue = checkoutQueue.size();
		prevAvailCheckouts = availCheckouts;

	}
	
	/**
	 * Creates a new customer and returns it, with a new id.
	 */
	private class CustomerFactory {

		public int idCounter;

		public Customer createCustomer() {
			Customer customer = new Customer(idCounter);
			idCounter = idCounter + 1;
			return customer;
		}
	}
	/**
	 * Internal class for generating time for arrival.
	 */
	private class ArrivalTimeGen {

		private double arrivalTime = 0.0;
		private double lastArrivalTime;
		
		/**
		 * @return returns the new arrival time.
		 */
		public double getNewArrivalTime() {
			arrivalTime = lastArrivalTime + expGen.next();
			lastArrivalTime = arrivalTime;
			return arrivalTime;
		}
	}
	
	/**
	 * Internal class for generating time for shopping.
	 */
	private class ShoppingTimeGen {

		private double shoppingTime = 0.0;
		
		/**
		 * @return Return the new shopping time.
		 */
		public double getNewShoppingTime() {
			
			shoppingTime = getTimeCurrent() + uniGenP.next();
			return shoppingTime;
		}
	}
	
	/**
 	* Internal class for generating time for checkout.
 	*/
	private class CheckoutTimeGen {

		private double checkoutTime = 0.0;
		
		/**
		 * @return Returns the checkout time.
		 */
		public double getCheckoutTime() {
			
			checkoutTime = getTimeCurrent() + uniGenC.next();
			return checkoutTime;
		}
	}

}
