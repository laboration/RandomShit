//package lab5.store;
//
//import lab5.Options;
//import lab5.store.Customer;
//import lab5.simulator.State;
//import lab5.random.*;
//
//
///**
// *En klass som beskriver tillståndet för simuleringen "Snabbköp"
// * @author Erik Olausson, Mikael Granström, Sermed Mutter, Amir Rakshan
// *
// */
//public class StoreStateDålig extends State {
//	//Variabler
//	private boolean openCurrent, openPast;
//	private int queueCurrent, queuePast;
//	private double timeInQueue, timeEmptyCashier;
//	private double timeEventPast;
//	private int cashierNrCurrent = Options.getCashierMax(), cashierNrPast = Options.getCashierMax();
//	private int customersCurrent = 0;
//	private int customersDoneCurrent = 0, customersDonePast;
//	private int customersMissedCurrent = 0, customersMissedPast;
//	private int queueSizeTotal, customerQueueSizePast;
//	
//	//Objekt av olika typer
//	private ExponentialRandomStream randExp = new ExponentialRandomStream(Options.getLambda(), Options.getSeed());
//	private UniformRandomStream randUniPlock= new UniformRandomStream(Options.getPlockMin(), Options.getPlockMax(), Options.getSeed());
//	private UniformRandomStream randUniPay = new UniformRandomStream(Options.getPayMin(), Options.getPayMax(), Options.getSeed());
//	
//	private int customerPast;
//	private String eventPast;
//	private boolean lastCustomerDenied;
//	
//	private customerConstructor constructor = new customerConstructor(); //Ge mig Customer så att jag kan skriva inre klassen
//	
//	private FIFO payQueueCurrent = new FIFO();
//	private String payQueuePast = payQueueCurrent.toString();
//	
//	private constructRandomArrivalTime timeArrivalRandom = new constructRandomArrivalTime();
//	private constructRandomShoppingTime timeShoppingRandom = new constructRandomShoppingTime();
//	private constructRandomCheckoutTime timeCheckoutRandom= new constructRandomCheckoutTime();
//	
//	/**
//	 * Tom metod för att skapa tillståndet
//	 */
//	public StoreStateDålig() { //Behöver EventQueue för att fungera. Allt i filen bör fungera om alla andra klasser finns med
//	}
//	
//	/**
//	 * Ökar antalet kassor
//	 */
//	public void increaseCashier() {
//		if (cashierNrCurrent < Options.getCashierMax()) {
//			cashierNrCurrent++;
//		}
//	}
//	
//	/**
//	 * Minskar antalet kassor
//	 */
//	public void decreaseCashier() {
//		if (cashierNrCurrent > 0) {
//			cashierNrCurrent--;
//		}
//	}
//	
//	/**
//	 * Returnerar den förra kunden
//	 * @return
//	 */
//	public int getCustomerPast() {
//		return customerPast;
//	}
//	
//	/**
//	 * "Lagrar/Sparar" den förra kunden
//	 * @param customerPast ID för förra kunden
//	 */
//	public void setCustomerPast(int customerPast) {
//		this.customerPast = customerPast;
//	}
//	
//	/**
//	 * Returnerar det förra eventet
//	 * @return
//	 */
//	public String getEventPast() {
//		return eventPast;
//	}
//	
//	/**
//	 * "Lagrar/Sparar" det förra eventet
//	 * @param eventPast namn för förra eventet
//	 */
//	public void setEventPast(String eventPast) {
//		this.eventPast = eventPast;
//	}
//	
//	/**
//	 * Kontrollerar om snabbköpet är öppet eller ej (true eller false)
//	 * @return
//	 */
//	public boolean checkOpenCurrent() {
//		return openCurrent;
//	}
//	
//	/**
//	 * Sparar värdet på parametern i openCurrent
//	 * @param flag tillståndet som snabbköpet har (öppet eller stängt / true eller flase)
//	 */
//	public void setOpenCurrent(boolean flag) {
//		this.openCurrent = flag;
//	}
//	
//	/**
//	 * Returnerar antalet kassor
//	 * @return
//	 */
//	public int getCashierNr() {
//		return cashierNrCurrent;
//	}
//	
//	/**
//	 * Returnerar tiden då det förra eventet skedde
//	 * @return
//	 */
//	public double getTimeEventPast() {
//		return timeEventPast;
//	}
//	
//	/**
//	 * Returnerar mängden kunder som köade "förut"
//	 * @return
//	 */
//	public int getCustomerQueueSizePast() {
//		return customerQueueSizePast;
//	}
//	
//	/**
//	 * Returnerar mängden kunder i snabbköpet just nu
//	 * @return
//	 */
//	public int getCustomersCurrent() {
//		return customersCurrent;
//	}
//	
//	/**
//	 * Returnerar mängden kunder i snabbköpet "förut"
//	 * @return
//	 */
//	public int getCustomersPast() {
//		return customerPast;
//	}
//	
//	/**
//	 * Lägger till en kund i snabbköpet
//	 */
//	public void increaseCustomers() {
//		customersCurrent++;
//	}
//	
//	/**
//	 * Tar bort en kund ur snabbköpet
//	 */
//	public void decreaseCustomers() {
//		customersCurrent--;
//	}
//	
//	/**
//	 * Returnerar mängden kunder som är klara (gått genom kassan)
//	 * @return
//	 */
//	public int getCustomersDoneCurrent() {
//		return customersDoneCurrent;
//	}
//	
//	/**
//	 * Ökar antalet kunder som är klara (gått genom kassan) 
//	 */
//	public void increaseDone() {
//		customersDoneCurrent++;
//	}
//	
//	/**
//	 * Returnerar antalet kunder som var klara "förut" (innan senaste)
//	 * @return
//	 */
//	public int getCustomersDonePast() {
//		return customersDonePast;
//	}
//	
//	/**
//	 * Returnerar hur många kunder man har förlorat i nuläget
//	 * @return
//	 */
//	public int getCustomersMissedCurrent() {
//		return customersMissedCurrent;
//	}
//	
//	/**
//	 * Ökar antalet förlorade kunder
//	 */
//	public void increaseMissed() {
//		customersMissedCurrent++;
//	}
//	
//	/**
//	 * Returnerar hur många kunder man i förlorat "förut" (innan den senaste)
//	 * @return
//	 */
//	public int getCustomersMissedPast() {
//		return customersMissedPast;
//	}
//	
//	/**
//	 * Returnerar den totala mängden kunder som stått i kö
//	 * @return
//	 */
//	public int getQueueSizeTotal() {
//		return queueSizeTotal;
//	}
//	
//	/**
//	 * Ökar det totala antalet kunder som stått i kö
//	 */
//	public void increaseQueueSizeTotal() {
//		queueSizeTotal++;
//	}
//	
//	
//	
//	/**
//	 * Returnerar tiden som kunder har stått i kö
//	 * @return
//	 */
//	public double getTimeInQueue() {
//		return timeInQueue;
//	}
//	
//	/**
//	 * Returnerar mängden kunder i kö just nu
//	 * @return
//	 */
//	public int getQueueSizeCurrent() {
//		return payQueueCurrent.size(); //Måste använda köräknaren från FIFO
//	}
//	
//	/**
//	 * Returnerar ett svar på om snabbköpet var öppet tidigare
//	 * @return
//	 */
//	public boolean getOpenPast() {
//		return openPast;
//	}
//	
//	/**
//	 * Returnerar den förra storleken på kön (innan senaste)
//	 * @return
//	 */
//	public int getQueueSizePast() {
//		return queuePast;
//	}
//	
//	/**
//	 * Returnerar det förra antalet kassor som var öppna (innan det senaste antalet)
//	 * @return
//	 */
//	public int getCashierNrPast() {
//		return cashierNrPast;
//	}
//	
//	/**
//	 * Returnerar tiden som kassor stått utan kunder
//	 * @return
//	 */
//	public double getTimeEmptyCashier() {
//		return timeEmptyCashier;
//	}
//	
//	/**
//	 * Returnerar kön (kan det vara så att det finns en kö in till affären och en kö till kassan? Blir förvirrad av alla olika köer
//	 * @return
//	 */
//	public int getQueueCurrent() {
//		return queueCurrent;
//	}
//	
//	/**
//	 * Returnerar kassakön
//	 * @return
//	 */
//	public FIFO getPayQueueCurrent() {
//		return payQueueCurrent;
//	}
//	
//	/**
//	 * Returnerar den förra kassakön (innan senaste)
//	 * @return
//	 */
//	public String getPayQueuePast() {
//		return payQueuePast;
//	}
//	
//	/**
//	 * Skapar en ny kund i konstruktorn och returnerar denne
//	 * @return
//	 */
//	public Customer newCustomer() {
//		return constructor.newCustomer(); //Ska lösas om man har Customer. newCustomer finns i denna fil, inget fel på den
//	}
//	
//	/**
//	 * Ökar tidsmängden i kötidsräknaren för varje kund som står i kö
//	 */
//	public void increaseTimeQueue() {
//		timeInQueue += (getTimeCurrent()-getTimePast()) * getQueueSizeTotal();
//	}
//	
//	/**
//	 * Ökar tidsmängden i räknaren för kassor som inte har kunder
//	 */
//	public void increaseTimeEmptyCashier() {
//		timeEmptyCashier = timeEmptyCashier + (getTimeCurrent()-getTimePast()) * getCashierNrPast();
//	}
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	/**
//	 * Returnerar en ankomsttid från metodens egna generator (constructRandomArrivalTime)
//	 * @return
//	 */
//	public double getRandomArrivalTime() {
//		return timeArrivalRandom.newRandomArrivalTime();
//	}
//	
//	/**
//	 * Returnerar en ankomsttid från metodens egna generator (constructRandomCheckoutTime)
//	 * @return
//	 */
//	public double getRandomCheckoutTime() {
//		return timeCheckoutRandom.newRandomCheckoutTime();
//	}
//	
//	/**
//	 * Returnerar en ankomsttid från metodens egna generator (constructRandomShoppingTime)
//	 * @return
//	 */
//	public double getRandomShoppingTime() {
//		return timeShoppingRandom.newRandomShoppingTime();
//	}
//	
//	public boolean getlastCustomerDenied() {
//		return lastCustomerDenied;
//	}
//	
//	public void setlastCustomerDenied(boolean status) {
//		this.lastCustomerDenied = status;
//	}
//	
//	/**
//	 * Returnerar ett svar på om snabbköpet har nått sitt man i antal kunder eller inte
//	 * @return
//	 */
//	public boolean checkCustomersNotMax() {
//		if (customersCurrent >= Options.getCustomersMax()) {
//			return false;
//		}
//		else {
//			return true;
//		}
//	}
//	
//	public void updateEvent() {
//		if (flag != true) {
//		}
////		System.out.println(eventPast + " " + lastCustomerDenied + " " + Options.getCustomersMax());
//		if (eventPast == "ARRIVAL" && lastCustomerDenied) {
//			
//		}else {
//			if (!openCurrent && eventPast == "ARRIVAL" && customerPast== 0) {
//				// Specialfall: vill inte uppdatera tider här
//				setChanged();
//				notifyObservers();
//			}
//		
//		else {
//			increaseTimeEmptyCashier();
//			increaseTimeQueue();
//			setChanged();
//			notifyObservers();
//			timeEventPast = getTimeCurrent();
//		}
//		}
//		//Sparar dessa värden inför nästa event
//		payQueuePast = payQueueCurrent.toString();
//		openPast = openCurrent;
//		customerQueueSizePast = payQueueCurrent.size();
//		customerPast = customersCurrent;
//		customersDonePast = customersDoneCurrent;
//		customersMissedPast = customersMissedCurrent;
//		queuePast = queueCurrent;
//		cashierNrPast = cashierNrCurrent; //Kan finnas fel här, vetefan vilka variabler som hör till vad
//	}
//	
//	/**
//	 * En inbyggd klass som gör nya customers med egna ID:n
//	 * @author Erik Olausson, Mikael Granström, Sermed Mutter, Amir Rakshan
//	 *
//	 */
//	private class customerConstructor {
//		public int customerID;
//		
//		/**
//		 * Returnerar en ny customer med ett specifikt ID
//		 * @return
//		 */
//		public Customer newCustomer() {
//			Customer customer = new Customer(customerID);
//			customerID++;
//			return customer;
//		}
//	}
//	
//	/**
//	 * En inbyggd klass som genererar randomiserade ankomsttider
//	 * @author Erik Olausson, Mikael Granström, Sermed Mutter, Amir Rakshan
//	 *
//	 */
//	private class constructRandomArrivalTime{
//		private double arrivalTimeCurrent = 0.0;
//		private double arrivalTimePast;
//		
//		/**
//		 * Returnerar en ny ankomsttid
//		 * @return
//		 */
//		public double newRandomArrivalTime() {
//			arrivalTimeCurrent = arrivalTimePast + randExp.next();
//			arrivalTimePast = arrivalTimeCurrent;
//			return arrivalTimeCurrent;
//		}
//	}
//	
//	/**
//	 * En inbyggd klass som genererar randomiserade checkouttider
//	 * @author Erik Olausson, Mikael Granström, Sermed Mutter, Amir Rakshan
//	 *
//	 */
//	private class constructRandomCheckoutTime{
//		private double checkoutTimeCurrent = 0.0;
//		
//		/**
//		 * Returnerar en ny checkouttid
//		 * @return
//		 */
//		public double newRandomCheckoutTime() {
//			checkoutTimeCurrent = getTimeCurrent() + randUniPay.next();
//			return checkoutTimeCurrent;
//		}
//	}
//	
//	/**
//	 * En inbyggd klass som genererar randomiserade shoppingtider
//	 * @author Erik Olausson, Mikael Granström, Sermed Mutter, Amir Rakshan
//	 */
//	private class constructRandomShoppingTime{
//		private double shoppingTimeCurrent = 0.0;
//		
//		/**
//		 * Returnerar en ny shoppingtid
//		 * @return
//		 */
//		public double newRandomShoppingTime() {
//			shoppingTimeCurrent = getTimeCurrent() + randUniPlock.next(); //Dessa finns nog i andra klasser
//			return shoppingTimeCurrent;
//		}
//	}
//}