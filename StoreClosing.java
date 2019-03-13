package lab5.store.events;

import lab5.simulator.Event;
import lab5.simulator.EventQueue;
import lab5.simulator.SimState;
import lab5.store.StoreState;

/**
 * @author Mikeal Granström, Erik Olausson, Amir Rakshan, Sermed Mutter
 */


/**
 *  Klassen skapar ett event för när butiken stänger. 
 *
 */
public class StoreClosing extends Event {
	
	private StoreState storeState;
	

	/**
	 * Konstruktorn kallar på event konstruktor med tid, storeState och eventQueue
	 * den lagrar en Customer, sätter namnet på eventet till Shopping och lägger till dess state.
	 * @param time  - tiden för eventet 
	 * @param storeState - simulatorn tillstånd
	 * @param Customer - kunden som tillhör  händelsen.
	 * @param eventQueue - den nuvarande händelsekön
	 * 
	 */ 
	public StoreClosing(double time, StoreState storeState, EventQueue eventQueue) {
		super(time, storeState, eventQueue);
		this.NAME = "CLOSING";
		this.storeState = storeState;

	}

	
	/**
	 *  Sätter current time, det förgående eventet och stänger butiken. 
	 */
	public void run() {
		
		storeState.setTimeCurrent(TIME);
		storeState.setCustomerPast(NAME);
		
		storeState.setOpenCurrent(false);
	}
}