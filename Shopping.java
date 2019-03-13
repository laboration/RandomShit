package lab5.store.events;

import lab5.store.StoreState;
import lab5.simulator.Event;
import lab5.simulator.EventQueue;
import lab5.simulator.SimState;
import lab5.store.Customer;


/**
 *@author Mikeal Granström, Erik Olausson, Amir Rakshan, Sermed Mutter  
 */

/**
 * 
 * Denna klass skapar ett shopping event
 *
 */
public class Shopping extends Event {
	private StoreState state;
	private Customer Customer;
	
	/**
	 * Konstruktorn kallar på event konstruktor med tid, storeState och eventQueue
	 * den lagrar en Customer, sätter namnet på eventet till Shopping och lägger till dess state.
	 * @param time  - tiden för eventet 
	 * @param storeState - simulatorn tillstånd
	 * @param Customer - kunden som tillhör  händelsen.
	 * @param eventQueue - den nuvarande händelsekön
	 * 
	 */ 
	public Shopping(double time, StoreState storeState, EventQueue eventQueue, Customer Customer) {
		super(time, storeState, eventQueue);
		this.Customer = Customer;
		this.NAME = "SHOPPING";
		this.state = storeState;
	}
	
	
	
	/**
	 * 
	 * Denna metod updarerar current time ,den tidigare customer och den tidigare event.
	 * Den skapar även en ny checkout event om det finns några lediga checkouts.
	 * om det inte finns någon tillgänglig checkout placerar den customer i checkout queue(kön)
	 * 
	 */
	public void run() {
		
		state.setTimeCurrent(TIME);
		state.setCustomerast(Customer);
		state.setEventPast(NAME);
		
		if (state.getCashierNr() > 0) {

			eventQueue.addEvent(new Checkout(state.getRandomCheckoutTime(), state, eventQueue, Customer));
			state.decreaseCashier();
		
		} else {
			state.getPayQueueCurrent().add(Customer);
			state.increaseQueueSizeTotal();
		}
	}
	
}