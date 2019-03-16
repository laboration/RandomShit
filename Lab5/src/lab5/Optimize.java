 
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
	private int missed;

	public static void main(String[]args) {
		Optimize test = new Optimize();
		//metod 2
		int klar = test.metod_2();
		
		//metod 3
//		int klar = test.metod_3(1001010);

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
	 * metod_1() k√∂r en simulering d√§r alla parametrar √§r fixerade.
	 * simuleringen √§r densamma som huvudprogrammet runsim
	 * @return Sluttillst√•ndet state.
	 */
	public StoreState metod_1() {
		StoreState state = new StoreState();
		EventQueue eventQueue=  new EventQueue();	
		eventQueue.addEvent(new Start(Options.getStartTime(), state, eventQueue));
		eventQueue.addEvent(new Stop(Options.getStopTime(),state,eventQueue));
		
		state.flag = true;
		new Simulator(eventQueue, state);
	
		return state;
	}

	/**
	 * Metod_2() ska genom flera k√∂rda simuleringar med hj√§lp av antal kassor minimera antalet missed 
	 * antal missade √§r en avtagande funktion i antal kassor
	 * @return optimerad antal kassor.
	 */
	public int metod_2() {
		int antalkassor=0;//b√∂rjar med att s√§tta antal kassor = 0
		int optimized = Integer.MAX_VALUE; // l‰gsta antal missade kunder.

		for (int i = 1; i<=Options.getPeopleMax(); i++) { // om st√∂rsta antalet m√§nniskor i aff√§ren => i
			Options.setCashierMax(i); // lika m√•nga kassor som maximalt ryms m√§nniskor
			StoreState state = metod_1(); // anv√§nder metod 1 
			if (state.getMissed() < optimized ) {
				optimized = state.getMissed();
				this.missed = optimized;
				antalkassor = i;
			}else{
				continue;
			}
			
		}
		System.out.println("Bästa antalet kassor som minskar missade är: " + "(" + optimized + ")" + " " +antalkassor);
		return antalkassor;
	}
	 /**
	 * Metoden runnar metod_2() f√∂r att ta reda p√• b√§sta m√∂jliga antal kassor mha slumpm√§ssigt genererade tal .
	 * @param seed f√∂r slumpm√§ssig generering.
	 * @return b√§sta m√∂jliga antal kassor.
	 */
	public int metod_3(long seed) { // tar fr√∂ som argument
		Random fro = new Random(seed); //variabel av typen random
		int calculator = 0; // loopar tills calc / antalet kassor √§r 1
		int maxantal = 0; //maxantalet
		

		while (true) {
			if (calculator == 100) { // bryt loopen n√§r vi n√•r 100
				break;
			}
			Options.setSeed(fro.nextInt());// psuedorandom genererad int
			int antal = metod_2();	//metod 2 k√∂rs

			if(antal > maxantal) { //s√• l√§nge antalet √§r st√∂rre √§n maxantalet resetar vi calc till 0
				maxantal = antal;
				calculator = 0;
			}else{
				calculator++; // annars +1 tills vi n√•r 100
			}
			
		}
		System.out.println("Bästa antalet kassor som minskar missade är: " + "(" + this.missed + ")" + " " +maxantal);
	
		return maxantal;	// return maxantalet
	}
}
