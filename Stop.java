package Store;
import Simulator.Event;
import Simulator.EventQueue;
import Simulator.State;

public class Stop extends Event {
	
	public Stop(double time,State state,EventQueue eventQueue) {
		super(time,state,eventQueue);
		this.NAME = "Stop";
	}
	public void run() {
		super.SimState.stop(); 
		super.SimState.setCurTime(TIME);
	}
	
}
	
