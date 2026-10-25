package me.quclient.EventHandler.Events;

import me.quclient.EventHandler.Event;
import net.minecraft.entity.Entity;

public class OnKilledEvent extends Event<OnKilledEvent> {
	public Entity entity;
	
	public OnKilledEvent(Entity targetEntity) {
		this.entity = targetEntity;
	}
}
