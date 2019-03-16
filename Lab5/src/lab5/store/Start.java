package lab5.store;
import lab5.Options;
import lab5.simulator.Event;
import lab5.simulator.EventQueue;
import lab5.simulator.State;
import lab5.store.events.StoreStart;

public class Start extends Event {
	
//	public Start(double time, StoreState state, EventQueue eventQueue) {
//		super(time, state, eventQueue);
//		this.NAME = "START";
//	}
//
//}
	public Start(double time, State state, EventQueue eventQueue) {
		super(time,state,eventQueue);
		this.NAME = "START";
	}
	public void run() {
		StoreState storeState = (StoreState) state;
		eventQueue.addEvent(new StoreStart(Options.getStartTime(), storeState, eventQueue));
	}
	
}