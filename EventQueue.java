package Simulator;
import java.util.PriorityQueue;;
public class EventQueue {
	private PriorityQueue<Event>eventQueue;
	
	public void addEvent(Event event) {
		if(event == null) {
			throw new IllegalArgumentException();		}
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
