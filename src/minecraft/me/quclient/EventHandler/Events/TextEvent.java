package me.quclient.EventHandler.Events;

import me.quclient.EventHandler.EventCancelable;
import net.minecraft.util.IChatComponent;

public class TextEvent extends EventCancelable {

	public String message;
	
	public TextEvent(String message) {
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
}
