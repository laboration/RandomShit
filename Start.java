package Store;
import Simulator.Event;
import Simulator.EventQueue;
import Simulator.State;

public abstract class Start extends Event {
	
	public Start(double time, State state,EventQueue eventQueue) {
		super(time,state,eventQueue);
		this.NAME = "start";
	}
	public abstract void run();

}
 