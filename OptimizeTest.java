package lab5;
import java.util.Random;

import lab5.simulator.EventQueue;
import lab5.simulator.Simulator;
import lab5.simulator.events.Stop;
import lab5.store.StoreState;
import lab5.store.events.StoreClosing;
import lab5.store.events.StoreStart;
import lab5.Settings;


/**
 * Optimizes the number of cashiers, prints result. Always uses worst seed (random generator).
 * @author Mikael Alanko, Tom Hammarkvist, Rickard Holmberg, Fredrik Lindgren
 */

public class Optimize {

	private static int missed;
	/**
	 * Main method
	 * @param args - Not used
	 */
	public static void main(String[] args) {

		int result = simulation_2();
		int result2 = simulation_3((long)1000);		
		System.out.println("Minsta antal kassor som ger minimalt antal missade ("+missed+"): "+result);	
	}
	
	/**
	 * Normal simulation without printing anything.
	 * @return The state, at the end of the simulation. 
	 */
	public static StoreState simulation_1() {
		EventQueue eventQueue= new EventQueue();
		StoreState state = new StoreState();
		eventQueue.put(new StoreStart(Settings.getSTARTTIME(), state, eventQueue));
		eventQueue.put(new StoreClosing(Settings.getCLOSINGTIME(), state, eventQueue));
		eventQueue.put(new Stop(Settings.getSTOPTIME(), state, eventQueue)); // Creates a stop event and put it in the eventqueue.
		
		state.isRunning = true;
		new Simulator(eventQueue, state);
		
		return state;
		
	}
	
	/**
	 * This method runs the simulation with different numbers of cashiers 
	 * to find the optimal number of cashiers for a specific seed. 
	 * @return Optimal number of cashiers. 
	 */
	public static int simulation_2() {
		int bestResult = Integer.MAX_VALUE;
		int optNrOfCheckouts = 0;
		
		for (int i = 1; i<=Settings.getMAXPEOPLE(); i++) {
			Settings.MAXCHECKOUTS =  i;
			StoreState state = simulation_1();

			if (state.getMissed() == 0) {

				optNrOfCheckouts = i;
				missed = state.getMissed();
				break;
			} else if ( state.getMissed() < bestResult ) {
				
				bestResult = state.getMissed();
				optNrOfCheckouts = i;
				missed = state.getMissed();
			}
		}
		return optNrOfCheckouts;
	}
		
	/**
	 * This method runs the simulation_2 method with different random generated seeds to find the optimal number of cashiers.
	 * @param seed - Seed for random generation.
	 * @return Optimal number of cashiers.
	 */
	public static int simulation_3(long seed) {
		Random gen = new Random(seed);
		int counter = 0;
		int res = 0;
		int maxres = 0;
		int worstSeed = 0;
		
		while (true) {
			if (counter == 99) {
				break;
			}
			Settings.SEED = gen.nextInt(10000);
			res = simulation_2();	
			
			if (res > maxres) {
				maxres = res;
				counter = 0;
				 worstSeed = Settings.SEED;
			} else {
				counter++;
			}
		}
		System.out.println("The worst possible seed was: "+worstSeed);
		return maxres;	
	}
}
