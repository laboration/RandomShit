package Simulator;
import Simulator.Event;
import Simulator.EventQueue;
import Simulator.State;
public class Simulator {
	
	public static void run(EventQueue eventQueue, SimState simstate) {// metod run som ska plocka ut events p� tur fr�n eventqueue och ser till att de sker genom att ropa respektive metod
		while (!(eventQueue.IsEmpty())) { //s� l�nge eventqueue inte �r tom s� k�r den
			if(!simState.nextEvent()) { // om eventqueue inte har n�gon till event p� k�
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

		
	
	
