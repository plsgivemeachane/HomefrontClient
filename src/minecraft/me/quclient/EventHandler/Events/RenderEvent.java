package me.quclient.EventHandler.Events;

import me.quclient.EventHandler.Event;

public class RenderEvent extends Event<RenderEvent> {
	
	public int particalTicks;
	
	public RenderEvent(int particalTicks) {
		this.particalTicks = particalTicks;
	}

}
