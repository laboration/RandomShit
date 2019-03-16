package lab5.store;
import lab5.simulator.Event;
import lab5.simulator.EventQueue;
import lab5.simulator.State;

public class Stop extends Event {
	
	public Stop(double time, State state,EventQueue eventQueue) {
		super(time,state,eventQueue);
		this.NAME = "STOP";
	}
	public void run() {
		super.state.stopSim(); 
		super.state.setTimeCurrent(eventTime);
		state.setLastEvent(this.NAME);
	}
	
}