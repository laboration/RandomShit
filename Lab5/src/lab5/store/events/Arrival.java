package lab5.store.events;

import lab5.store.StoreState;
import lab5.simulator.Event;
import lab5.simulator.EventQueue;
import lab5.store.Customer;
/**
 * En klass som skapar specifika eventet Arrival (ankomst)
 * @author Erik Olausson, Mikael Granström, Sermed Mutter, Amir Rakshan
 *
 */
public class Arrival extends Event {
	private Customer Customer;
	private StoreState state;
	
	/**
	 * Konstruktorn kallar på konstruktorn för event, med parametrarna time, storeState och eventQueue och ger det namn och tillstånd
	 * @param time eventets tidpunkt
	 * @param storeState tillståndet just nu
	 * @param eventQueue kön just nu
	 */
	public Arrival(double time, StoreState storeState, EventQueue eventQueue) {
		super(time, storeState, eventQueue);
		this.NAME = "ARRIVAL";
		this.state = storeState;
	}
	
	/**
	 * Skapar en ny kund, sätter en ny tid, förra kund och förra event
	 * Kontrollerar om snabbköpet är öppet och läggder till kunder om så är fallet
	 * Om snabbköpet är fullt ökar customersMissed med ett.
	 * Gerenerar nästa ankomst och lägger till den i kön av event. 
	 */
	public void run() {
		Customer = state.createCustomer(); // Creates a new customer.
		state.setTimeCurrent(eventTime);
		state.setPrevCustomer(Customer);
		state.setPrevEvent(NAME);

		if (state.isStoreOpen()) {
			if (state.storeNotFull()) {
				state.incPeople();
				eventQueue.addEvent(
				new Shopping(state.getNewShoppingTime(), state, eventQueue, Customer));
				state.setlastCustomerDenied(false);

			} else {
				state.incMissed();
				state.setlastCustomerDenied(true);
			}
			eventQueue.addEvent(new Arrival(state.getNewArrivalTime(), state, eventQueue));
		}
		// Om snabbköpet är stängt händer ingenting.
	}
}