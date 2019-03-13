package lab5.store.events;

import lab5.simulator.EventQueue;
import lab5.simulator.SimState;
import lab5.simulator.events.Start;
import lab5.store.StoreState;

/**
 * @author Mikeal Granström, Erik Olausson, Amir Rakshan, Sermed Mutter
 */

/** 
 *  Denna skapar ett start event för butiken
 */

public class StoreStart extends Start {
	
	private StoreState state;
	
	
	/**
	 * Konstruktorn kallar på start konstruktor med tid, storeState och eventQueue
	 * den lagrar en Customer, sätter namnet på eventet till Shopping och lägger till dess state.
	 * @param time  - tiden för eventet 
	 * @param storeState - simulatorn tillstånd
	 * @param Customer - kunden som tillhör  händelsen.
	 * @param eventQueue - den nuvarande händelsekön
	 * 
	 */ 
	public StoreStart(double time, StoreState storeState, EventQueue eventQueue) {
		super(time, storeState, eventQueue);
		this.state = storeState;
	}
	
	
	/**
	 * Run metoden sätter en start tid och skapar en första arrival event. 
	 */
	public void run() {
		state.setTimeCurrent(TIME);		// sätter tiden till 0

		state.setOpenCurrent(true);
		// Creates new arrival event and puts it in the queue.
		eventQueue.addEvent(new Arrival(state.getRandomCheckoutTime, state, eventQueue));
	}

}