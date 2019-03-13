package Simulator;
import Simulator.Event;
import Simulator.EventQueue;
import Simulator.State;
public class Simulator {
	
	public static void run(EventQueue eventQueue, SimState simstate) {// metod run som ska plocka ut events på tur från eventqueue och ser till att de sker genom att ropa respektive metod
		while (!(eventQueue.IsEmpty())) { //så länge eventqueue inte är tom så kör den
			if(!simState.nextEvent()) { // om eventqueue inte har någon till event på kö
				break; // break
		   
			}if(event.NAME == "Start") {
			   event.run();
		    
			}else if(event.NAME == "Stop") {
			   event.run();
		
		    }else {
			   event.run();
			   simState.update();
		    }
		}	
	}
}	

		
	
	
