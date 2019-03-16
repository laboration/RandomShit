package lab5.simulator;

import lab5.simulator.Event;
import lab5.simulator.EventQueue;
import lab5.simulator.State;

public class Simulator {
	
	public Simulator(EventQueue eventQueue, State state) {// metod run som ska plocka ut events på tur från eventqueue och ser till att de sker genom att ropa respektive metod
		while (!(eventQueue.isEmpty())) { //så länge eventqueue inte är tom så kör den
			Event event = eventQueue.grab();
			if (!state.flag) { // om eventqueue inte har någon till event på kö
				break; // break
		   
			}if(event.NAME == "START") {
			   event.run();
		    
			}else if(event.NAME == "STOP") {
			   event.run();
			   state.update();
		
		    }else {
			   event.run();
			   state.update();
		    }
		}	
	}
}	