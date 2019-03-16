package lab5.store;

import lab5.simulator.View;

import java.util.Observable;
import java.util.Observer;

import lab5.Options;
import lab5.store.StoreState;

import java.text.DecimalFormat;
public class StoreView extends View implements Observer {
	
	private StoreState state;
	private DecimalFormat decFormat1 = new DecimalFormat("0.00");
	private DecimalFormat decFormat2 = new DecimalFormat("0.0");
	
	public StoreView(StoreState state) {
		super(state);
		this.state = state;
		state.addObserver(this);
		printStart();
	}

	public void update(Observable arg0, Object arg1) {	
		printEvents();
	}
	
	//Parametrar
	public void printStart() {
			System.out.println("PARAMETRAR");
			System.out.println("==========");
			System.out.println("Antal kassor, N.......: "+Options.getCashierMax());
			System.out.println("Max som rymms, M.......: "+Options.getPeopleMax());
			System.out.println("Ankomsthastighet, lambda......: "+decFormat2.format(Options.getLambda()));
			System.out.println("Plocktider[P_min..Pmax] ["+Options.getPlockMin()+".."+Options.getPlockMax()+"]");
			System.out.println("Betaltider[K_min..Kmax] ["+Options.getPayMin()+".."+Options.getPayMax()+"]");
			System.out.println("Frö,f  "+Options.getSeed());
			System.out.println("");
			System.out.println("FÖRLOPP  ");
			System.out.println("=======");
			System.out.println("Tid\tHändelse \tKund\t?\tled\tledT\tKunder\tTot\tMissade\tköat\tKöatT\tköar\t[Kassakö..]");
			System.out.println(decFormat1.format(Options.getStartTime())+"  "+"\t START");
		}		

		
	//Alla event	
	public void printEvents() {
		if(state.getPrevEvent() == "CLOSING") {
			System.out.println(
					decFormat1.format(state.getTimeCurrent())+ 
					"\t"+state.getPrevEvent()+"  "+
					"\t"+"-"+
					"\t"+state.getPrevStoreOpen()+
					"\t"+state.getPrevAvailCheckouts()+
					"\t"+decFormat1.format(state.getCheckoutsIdleTime())+"  "+
					"\t"+state.getPrevPeople()+
					"\t"+state.getPrevServed()+
					"\t"+state.getPrevMissed()+
					"\t"+state.getPrevNrQueued()+
					"\t"+decFormat1.format(state.getTimeQueue())+
					"\t"+state.getPrevQueueSize()+
					"\t["+state.getPrevCheckoutQueue().toString()+"]"
				);
			
		} else if(state.getLastEvent() == "STOP") {
			System.out.println(
					decFormat1.format(state.getTimeCurrent())+ 
					"\t"+state.getLastEvent()
					);
			
			printSummary();
		}
		
		else if (state.getPrevEvent() != null){
		
		System.out.println(
				decFormat1.format(state.getTimeCurrent())+ 
				"\t"+state.getPrevEvent()+"  "+
				"\t"+state.getPrevCustomer().findCustomer()+
				"\t"+state.isStoreOpen()+
				"\t"+state.getPrevAvailCheckouts()+
				"\t"+decFormat1.format(state.getCheckoutsIdleTime())+"  "+
				"\t"+state.getPrevPeople()+
				"\t"+state.getPrevServed()+
				"\t"+state.getPrevMissed()+
				"\t"+state.getPrevNrQueued()+
				"\t"+decFormat1.format(state.getTimeQueue())+
				"\t"+state.getPrevQueueSize()+
				"\t["+state.getPrevCheckoutQueue()+"]"
			);
		}		
		
	}
	
	public void printClosing() {
		System.out.println("TID  "+"HÄNDELSE  ");
	}
	//Resultat		
	public void printSummary() {
		System.out.println("");
		System.out.println("RESULTAT");
		System.out.println("========");
		System.out.println(
		"1) Av "+
		//totalt antal kunder
		(state.getServed()+state.getMissed())
		+" kunder handlade "
		//antal kunder som handlat
		+state.getServed()
		+" medan "
		//antal kunder som missades
		+state.getMissed()
		+" missades."
				);
		System.out.println("");
		System.out.println(
		"2) Total tid "
		//antal kassor lediga
		+Options.getCashierMax()
		+" kassor varit lediga: "
		//total tid dom kassor varit lediga
		+decFormat1.format(state.getCheckoutsIdleTime())
		+" te. \n"
		+"   Genomsnittlig ledig kassatid: "
		//Genomsnittlig tid
		+decFormat1.format(state.getCheckoutsIdleTime()/state.getAvailCheckouts())
		+" te (dvs "
		+decFormat1.format(100*(state.getCheckoutsIdleTime()/Options.getCashierMax()/state.getLastEventTime()))
		+"% av tiden från öppning tills sista kunden betalat). \n"
				);
		System.out.println(
		"3) Total tid "
		+state.getNrQueued()
		+" kunder tvingats köa: "
		+decFormat1.format(state.getTimeQueue())
		+" te. \n"
		+"   Genomsnittlig kötid: "
		+decFormat1.format(state.getTimeQueue()/state.getNrQueued())
		+" te."
		
				);	
		
	}
	
}
