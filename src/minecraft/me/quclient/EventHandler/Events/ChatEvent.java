package me.quclient.EventHandler.Events;

import me.quclient.EventHandler.Event;
import me.quclient.EventHandler.EventCancelable;

public class ChatEvent extends EventCancelable {

	public String message;
	
	public ChatEvent(String message) {
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
}
