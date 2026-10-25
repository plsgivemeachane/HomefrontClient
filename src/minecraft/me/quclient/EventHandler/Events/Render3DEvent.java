package me.quclient.EventHandler.Events;

import me.quclient.EventHandler.Event;

public class Render3DEvent extends Event<Render3DEvent> {
	
	public int particalTicks;
	
	public Render3DEvent(int particalTicks) {
		this.particalTicks = particalTicks;
	}

}
