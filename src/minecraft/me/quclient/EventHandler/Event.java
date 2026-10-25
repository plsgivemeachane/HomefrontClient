package me.quclient.EventHandler;

public class Event<T> {
	
	private EventType type;
	private EventPriolity pri = EventPriolity.NORMAL;
	
	public Event() {}
	
	public EventType getType() {return this.type;}
	public void setType(EventType type) {this.type = type;}
	

}
