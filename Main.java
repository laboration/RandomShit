package lab5;
import lab5.store.events.StoreClosing;
import lab5.store.events.StoreStart;
import lab5.simulator.Simulator;
import lab5.simulator.EventQueue;
import lab5.store.*;
import lab5.simulator.events.*;

/**
 * @author Mikael Granström, Erik Olausson, Sermed Mutter, Amir Rakshan
 *
 */
/**
 * Denna klass är huvudklassen. Den kör simuleringen och skapar dess instanser.
 *
 */
public class RunSim {

	public static void main(String[] args) {
	
		
		EventQueue eventQueue= new EventQueue();
		StoreState storeState = new StoreState();
		StoreView storeView = new StoreView(state);
		eventQueue.addEvent(new StoreStart(Options.getSTARTTIME(), storeState, eventQueue));
		eventQueue.addEvent(new StoreClosing(Options.getCLOSINGTIME(), storeState, eventQueue));
		eventQueue.addEvent(new Stop(Options.getSTOPTIME(), storeState, eventQueue));
		storeState.isRunning = true;
		storeView.printFirst();
		new Simulator(eventQueue, storeState);
		storeView.printEnd();
		
	}
	
}
