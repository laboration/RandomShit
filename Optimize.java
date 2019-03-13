package Main;

import java.util.Random;
import Simulator.EventQueue;
import Store.StoreState;
import Store.StoreView;
import Main.Stats;
import Simulator.Simulator;

public class Optimize {
	private static int missed;

	public static void main(String[]args) {
	
		int klar = metod_2();
		System.out.println("B�sta antalet kassor som minskar missade �r" +missed +klar);
	}
	
	/**
	 * metod_1() k�r en simulering d�r alla parametrar �r fixerade.
	 * simuleringen �r densamma som huvudprogrammet runsim
	 * @return Sluttillst�ndet state.
	 */
	public static int metod_1() {
		StoreState state = new StoreState();
		StoreView view = new StoreView(state);
		EventQueue eventQueue=  new EventQueue();		
		
		state.isRunning = true;
		view.printStart();
		new Simulator(eventQueue, state);
		view.printSummary();
		
		return state;
	}

	/**
	 * Metod_2() ska genom flera k�rda simuleringar med hj�lp av antal kassor minimera antalet missed 
	 * antal missade �r en avtagande funktion i antal kassor
	 * @return optimerad antal kassor.
	 */
	public static int metod_2() {
		int antalkassor = 0; // b�rjar med att s�tta antal kassor = 0
		int optimized = Integer.MAX_VALUE; // b�sta antalet kassor

		for (int i = 1; i<=Settings.getMAXPEOPLE(); i++) { // om st�rsta antalet m�nniskor i aff�ren => i
			Settings.MAXCHECKOUTS =  i; // lika m�nga kassor som maximalt ryms m�nniskor
			StoreState state = simulation_1(); // anv�nder metod 1 

			if(state.getMissed() == 0) { // om ingen kund missas

				antalkassor = i; 
				missed = state.getMissed();
				break;
			}else if (state.getMissed() < optimized ) {

				optimized = state.getMissed();
				antalkassor = i;
				missed = state.getMissed();
			}
		}
		return antalkassor;
	}
	 /**
	 * Metoden runnar metod_2() f�r att ta reda p� b�sta m�jliga antal kassor mha slumpm�ssigt genererade tal .
	 * @param seed f�r slumpm�ssig generering.
	 * @return b�sta m�jliga antal kassor.
	 */
	public static int metod_3(long seed) { // tar fr� som argument
		Random fro = new Random(seed); //variabel av typen random
		int calculator = 0; // loopar tills calc / antalet kassor �r 1
		int antal = 0; //antalet
		int maxantal = 0; //maxantalet
		

		while (true) {
			if (calculator == 100) { // bryt loopen n�r vi n�r 100
				break;
			}
			Settings.SEED = fro.nextInt(10000); // psuedorandom genererad int
			antal = metod_2();	//metod 2 k�rs

			if(antal > maxantal) { //s� l�nge antalet �r st�rre �n maxantalet resetar vi calc till 0
				maxantal = antal;
				calculator = 0;
			}else{
				calculator++; // annars +1 tills vi n�r 100
			}
		}
		return maxantal;	// return maxantalet
	}
}



