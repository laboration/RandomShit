package lab5.store.events;

import lab5.simulator.Event;
import lab5.simulator.EventQueue;
import lab5.store.Customer;
import lab5.store.StoreState;

/**
 * @author Mikael Granström, Erik Olausson, Sermed Mutter, Amir Rakshan
 */

/**
 * Detta är en klass för det specifika eventet Checkout.
 * 
 */

public class Checkout extends Event {

	private StoreState state;
	private Customer Customer;

	/**
	 * Konstruktorn lagrar en kund, sätter namnet till "Checkout" och anger tillståndet.
	 * @param time - Tiden för eventet.
	 * @param storeState - Simulatorns tillstånd.
	 * @param eventQueue - Den nuvarande händelsekön.
	 * @param customer - Kunden som tillhör eventet.
	 */
	public Checkout(double time, StoreState storeState, EventQueue eventQueue, Customer Customer) {
		super(time, storeState, eventQueue);
		this.Customer = Customer;
		this.NAME = "CHECKOUT";
		this.state = storeState;
	}

	/**
	 * Metoden run uppdaterar följande: tid, föregående kund och föregående event. 
	 * Den ökar antalet färdiga kassaköp och minskar antalet kunder i affären.
	 * 
	 * Dessutom kollar den om kassakön är tom, om kön inte är tom så skapar den ett Checkout-event för kunden som är först i kön.
	 * Om kön är tom så ökas antalet tillgängliga kassor.
	 */
	public void run() {
		state.setTimeCurrent(eventTime);
		state.setPrevCustomer(Customer);
		state.setPrevEvent(NAME);
		
		state.decPeople();
		state.incServed();

		if (!state.getCheckoutQueue().isEmpty()) {
			
			Customer = state.getCheckoutQueue().first();
			state.getCheckoutQueue().removeFirst();
			eventQueue.addEvent(new Checkout(state.getNewCheckoutTime(), state, eventQueue, Customer));

		} else { state.incAvailCheckouts(); }

	}

}