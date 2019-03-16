package lab5.simulator;

import java.util.Observable;

/**
 * En klass som beskriver tillståndet "just nu" i en simulering
 * @author Erik Olausson, Mikael Granström, Sermed Mutter, Amir Rakshan
 *
 */
public abstract class State extends Observable {
	private double timeCurrent, timePast;
	private String lastEvent;
	public boolean flag;
	private EventQueue queue;
	
	/**
	 * Konstruktor. Skapar ett objekt av typen State med parametrarna time, flag och queue
	 * @param time
	 * @param flag
	 * @param queue
	 */
	public State() {
	}
	
	public void stopSim() {
		flag = false;
	}
	
	/**
	 * Returnerar den nuvarande tiden för tillståndet
	 * @return
	 */
	public double getTimeCurrent() {
		return timeCurrent;
	}
	
	/**
	 * Slår fast tiden för tillståndet
	 * @param setTime
	 */
	public void setTimeCurrent(double setTime) {
		timePast = timeCurrent;
		timeCurrent = setTime;
	}
	
	public double getTimePast() {
		return timePast;
	}
	
	/**
	 * Returnerar kön av event för tillståndet
	 * @return
	 */
	public EventQueue getEventQueue() {
		return this.queue;
	}
	
	abstract protected void updateEvent();
	
	protected void update() {
		updateEvent();
	}
	
	/**
	 * @return the lastEvent
	 */
	public String getLastEvent() {
		return lastEvent;
	}

	/**
	 * @param lastEvent the lastEvent to set
	 */
	public void setLastEvent(String lastEvent) {
		this.lastEvent = lastEvent;
	}
}	