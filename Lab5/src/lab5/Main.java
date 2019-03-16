package lab5;
import lab5.store.events.StoreClosing;
import lab5.store.events.StoreStart;
import lab5.simulator.Simulator;
import lab5.simulator.State;
import lab5.simulator.EventQueue;
import lab5.store.*;

/**
 * @author Erik Olausson, Mikael Granström, Sermed Mutter, Amir Rakshan
 *
 */
/**
 * Denna klass är huvudklassen. Den kör simuleringen och skapar dess instanser.
 *
 */
public class Main {

	public static void main(String[] args) {
	
		
		EventQueue eventQueue= new EventQueue();
		StoreState storeState = new StoreState();
		StoreView storeView = new StoreView(storeState);
		eventQueue.addEvent(new Start(Options.getStartTime(), storeState, eventQueue));
		eventQueue.addEvent(new Stop(Options.getStopTime(), storeState, eventQueue));
		
		storeState.flag = true;
		new Simulator(eventQueue, storeState);
		
	}
	
}