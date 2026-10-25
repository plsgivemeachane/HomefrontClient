package me.quclient.EventHandler.Events;

import me.quclient.EventHandler.Event;

public class KeyDownEvent extends Event<KeyDownEvent> {
	
	public int key;
	
	public KeyDownEvent(int key) {
		this.key = key;
	}
	

}
