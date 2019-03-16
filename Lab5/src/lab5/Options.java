package lab5;

/**
 * @author Erik Olausson, Mikael Granstr√∂m, Sermed Mutter, Amir Rakshan
 *
 */
public class Options {
	static int cashierMax = 2; //optimize ‰ndrar 
	static int peopleMax = 7; //M
	
	// Tid mellan nya customers
	static double lambda = 2.0; //L
	
	// Tid att plocka varor
	static double plockMax = 1.0; //HIGH_COLLECTION_TIME
	static double plockMin = 0.5; //LOW_COLLECTION_TIME
	
	// Tid att betala
	static double payMax = 3.0;  //HIGH_PAYMENT_TIME
	static double payMin = 2.0;  //LOW_PAYMENT_TIME
	
	// Seed
	static int seed = 1234; //SEED
	
	// Time
	static double stopTime = 999;
	static double closeTime = 10.0;   //END_TIME
	static double startTime = 0;
	
	/**
	 * Returnerar det maximala antalet kassor
	 * @return
	 */
	public static int getCashierMax(){
		return cashierMax;
	}
	
	public static void setCashierMax(int cashier) {
		cashierMax = cashier;
	}
	
	/**
	 * Returnerar "fr√∂t"
	 * @return
	 */
	public static int getSeed() {
		return seed;
	}
	
	public static void setSeed(int newSeed) {
		seed = newSeed;
	}
	
	/**
	 * Returnerar den exponeltiala slumpm√§ssiga parametern lambda
	 * @return
	 */
	public static double getLambda() {
		return lambda;
	}
	
	/**
	 * Returnerar stopptiden
	 * @return
	 */
	public static double getStopTime() {
		return stopTime;
	}
	
	/**
	 * Returnerar st√§ngningstiden f√∂r snabbk√∂pet
	 * @return
	 */
	public static double getCloseTime(){
		return closeTime;
	}
	
	/**
	 * Returnerar starttiden
	 * @return
	 */
	public static double getStartTime() {
		return startTime;
	}
	
	/**
	 * Returnerar den maximala tiden att plocka varor
	 * @return
	 */
	public static double getPlockMax() {
		return plockMax;
	}
	
	/**
	 * Returnerar den minimala tiden att plocka varor
	 * @return
	 */
	public static double getPlockMin() {
		return plockMin;
	}
	
	/**
	 * Returnerar den maximala tiden att betala
	 * @return
	 */
	public static double getPayMax() {
		return payMax;
	}
	
	/**
	 * Returnerar den minimala tiden att betala
	 * @return
	 */
	public static double getPayMin() {
		return payMin;
	}
	
	/**
	 * Returnerar den maximala m√§ngden till√•tna kunder
	 * @return
	 */
	public static int getPeopleMax() {
		return peopleMax;
	}

}