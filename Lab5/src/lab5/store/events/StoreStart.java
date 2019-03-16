package lab5.store.events;

import lab5.simulator.EventQueue;
import lab5.Options;
import lab5.simulator.Event;
import lab5.simulator.State;
import lab5.store.Start;
import lab5.store.StoreState;

/**
 * @author Mikael Granström, Erik Olausson, Sermed Mutter, Amir Rakshan
 */

/**
 * Detta är en klass för affärens starthändelse (event).
 *
 */
public class StoreStart extends Event {
	
	private StoreState state;
	
	/**
	 * Konstruktorn har parametrarna time, storeState och eventQueue.
	 * Konstruktorn lagrar simuleringens tillstånd.
	 * 
	 * @param time - Tiden för eventet.
	 * @param storeState - Simulatorns tillstånd.
	 * @param eventQueue - Den nuvarande händelsekön.
	 */
	public StoreStart(double time, StoreState storeState, EventQueue eventQueue) {
		super(time, storeState, eventQueue);
		this.state = storeState;
	}
	
	/**
	 * Metoden run sätter en starttid (vanligtvis 0) och lägger till det första Arrival-eventet.
	 */
	public void run() {
		state.setTimeCurrent(eventTime);

		state.setStoreOpen(true);
		eventQueue.addEvent(new Arrival(state.getNewArrivalTime(), state, eventQueue));
		eventQueue.addEvent(new StoreClosing(Options.getCloseTime(), state, eventQueue));
	}

}