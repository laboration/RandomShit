package Simulator;

public abstract class Event implements Comparable<Event> {
	
	protected double eventTime;
	protected State state;
	protected EventQueue eventQueue;
	protected String NAME = "Event";
	
	
	
	public Event(double time, State state, EventQueue eventQueue){
		
		this.eventTime= time;
		this.state = state;
		this.eventQueue = eventQueue;
	}
	public void setTime(double eventTime) {
		this.eventTime = eventTime;	
	}
	
	public double getTime() {
		return this.eventTime;
	}
	
	public int compareTo(Event o) {
		 Double timeOne = new Double(eventTime);
		 Double timeTwo = new Double(o.eventTime);
		return timeOne.compareTo(timeTwo); 
		}
		
	public abstract void run();
	//
}	
