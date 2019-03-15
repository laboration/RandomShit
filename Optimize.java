 
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
		System.out.println("b�sta antalet kassor som minskade �r: " + "(" + missed + ")" + " " +klar);
		
		//metod 3
//		int klar = metod_3(1001010);
//		System.out.println("B�sta antalet kassor som minskade �r: " + "(" + missed + ")" + " " +klar);
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
	 * metod_1() k�r en simulering d�r alla parametrar �r fixerade.
	 * simuleringen �r densamma som huvudprogrammet runsim
	 * @return Sluttillst�ndet state.
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
	 * Metod_2() ska genom flera k�rda simuleringar med hj�lp av antal kassor minimera antalet missed 
	 * antal missade �r en avtagande funktion i antal kassor
	 * @return optimerad antal kassor.
	 */
	public static int metod_2() {
		int antalkassor=0;//b�rjar med att s�tta antal kassor = 0
		int optimized = Integer.MAX_VALUE; // l�gsta antal missade kunder.

		for (int i = 1; i<=Options.getPeopleMax(); i++) { // om max antal m�nniskor i aff�ren => i
			Options.setCashierMax(i); // �ndrar max antalet kassor till i
			StoreState state = metod_1();  
			if (state.getMissed() < optimized ) { // om antalet missade kunder < l�ngsta antal missade kunder
				optimized = state.getMissed();
				antalkassor = i;
			}else{
				continue;
			}
		}
		return antalkassor;
	}
	 /**
	 * Metoden runnar metod_2() f�rs�ker ta reda p� b�sta m�jliga antal kassor mha slumpm�ssigt genererade fr� .
	 * @param seed f�r slumpm�ssig generering.
	 * @return b�sta m�jliga antal kassor.
	 */
	public static int metod_3(long seed) { // tar fr� som argument
		Random fro = new Random(seed); //variabel av typen random
		int calculator = 0; // loopar tills calc �r 100
		int maxantal = 0; //maxantalet
		

		while (true) {
			if (calculator == 100) { // bryt loopen n�r calc n�r 100
				break;
			}
			Options.setSeed(fro.nextInt());// psuedorandom genererad int
			int antal = metod_2();	

			if(antal > maxantal) { //s� l�nge antal > maxantalet resetar vi calc till 0
				maxantal = antal;
				calculator = 0;
			}else{
				calculator++; // annars +1 tills vi n�r 100
			}
		}
		return maxantal;	// return maxantalet
	}
}
