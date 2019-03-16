package lab5.simulator;

import java.util.PriorityQueue;

public class EventQueue {
	private PriorityQueue<Event>eventQueue;
	
	public EventQueue() {
		eventQueue = new PriorityQueue<Event>();
	}
	
	public void addEvent(Event event) {
		eventQueue.add(event);
	}
	public boolean isEmpty() {
		return this.eventQueue.isEmpty();
	}
	public Event grab() {//
        if(eventQueue.isEmpty()) { 
        	return null; }
        return eventQueue.poll();
	}
	
	public int size() {
		return this.eventQueue.size();
	}
}