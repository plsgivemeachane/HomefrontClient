package me.quclient.EventHandler.Events;

import me.quclient.EventHandler.Event;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.MovingObjectPosition.MovingObjectType;

public class ClickMouseEvent extends Event<ClickMouseEvent> {
	
	public int key;
	public MovingObjectType typeOfHit;
	
	public ClickMouseEvent(int key, MovingObjectType typeOfHit) {
		this.key = key;
		this.typeOfHit = typeOfHit;
	}

}
