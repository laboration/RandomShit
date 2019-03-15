 
package lab5;

import java.util.Random;
import lab5.simulator.EventQueue;
import lab5.store.Start;
import lab5.store.Stop;
import lab5.store.StoreState;
import lab5.store.StoreView;
import lab5.Options; //???
import lab5.simulator.Simulator;

public class Optimize {
	private static int missed;

	public static void main(String[]args) {
		
		//metod 2
		int klar = metod_2();
		System.out.println("bästa antalet kassor som minskade är: " + "(" + missed + ")" + " " +klar);
		
		//metod 3
//		int klar = metod_3(1001010);
//		System.out.println("Bästa antalet kassor som minskade är: " + "(" + missed + ")" + " " +klar);
//		
		// sim med optimala antal kassor.
//		Options.setCashierMax(klar);
//		StoreState storeState = new StoreState();;
//		EventQueue eventQueue=  new EventQueue();		
//		StoreView storeView = new StoreView(storeState);
//		eventQueue.addEvent(new Start(Options.getStartTime(), storeState, eventQueue));
//		eventQueue.addEvent(new Stop(Options.getStopTime(), storeState, eventQueue));
//		storeState.flag = true;
//		new Simulator(eventQueue, storeState);

	}
	
	/**
	 * metod_1() kör en simulering där alla parametrar är fixerade.
	 * simuleringen är densamma som huvudprogrammet runsim
	 * @return Sluttillståndet state.
	 */
	public static StoreState metod_1() {
		StoreState state = new StoreState();
		EventQueue eventQueue=  new EventQueue();	
		eventQueue.addEvent(new Start(Options.getStartTime(), state, eventQueue));
		eventQueue.addEvent(new Stop(Options.getStopTime(),state,eventQueue));
		
		state.flag = true;
		new Simulator(eventQueue, state);
	
		return state;
	}

	/**
	 * Metod_2() ska genom flera körda simuleringar med hjälp av antal kassor minimera antalet missed 
	 * antal missade är en avtagande funktion i antal kassor
	 * @return optimerad antal kassor.
	 */
	public static int metod_2() {
		int antalkassor=0;//börjar med att sätta antal kassor = 0
		int optimized = Integer.MAX_VALUE; // lägsta antal missade kunder.

		for (int i = 1; i<=Options.getPeopleMax(); i++) { // om max antal människor i affären => i
			Options.setCashierMax(i); // ändrar max antalet kassor till i
			StoreState state = metod_1();  
			if (state.getMissed() < optimized ) { // om antalet missade kunder < längsta antal missade kunder
				optimized = state.getMissed();
				antalkassor = i;
			}else{
				continue;
			}
		}
		return antalkassor;
	}
	 /**
	 * Metoden runnar metod_2() försöker ta reda på bästa möjliga antal kassor mha slumpmässigt genererade frö .
	 * @param seed för slumpmässig generering.
	 * @return bästa möjliga antal kassor.
	 */
	public static int metod_3(long seed) { // tar frö som argument
		Random fro = new Random(seed); //variabel av typen random
		int calculator = 0; // loopar tills calc är 100
		int maxantal = 0; //maxantalet
		

		while (true) {
			if (calculator == 100) { // bryt loopen när calc når 100
				break;
			}
			Options.setSeed(fro.nextInt());// psuedorandom genererad int
			int antal = metod_2();	

			if(antal > maxantal) { //så länge antal > maxantalet resetar vi calc till 0
				maxantal = antal;
				calculator = 0;
			}else{
				calculator++; // annars +1 tills vi når 100
			}
		}
		return maxantal;	// return maxantalet
	}
}
