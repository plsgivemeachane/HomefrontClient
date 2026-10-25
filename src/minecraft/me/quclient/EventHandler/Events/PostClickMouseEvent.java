package me.quclient.EventHandler.Events;

import net.minecraft.util.MovingObjectPosition.MovingObjectType;

public class PostClickMouseEvent extends me.quclient.EventHandler.Event<PostClickMouseEvent> {
	
	public int key;
	public MovingObjectType typeOfHit;
	
	public PostClickMouseEvent(int key, MovingObjectType typeOfHit) {
		this.key = key;
		this.typeOfHit = typeOfHit;
	}

}
