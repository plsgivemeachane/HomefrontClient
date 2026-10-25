package me.quclient.EventHandler.Events;

import me.quclient.EventHandler.Event;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;

public class AttackEvent extends Event<AttackEvent> {
	
	public Entity entity;
	
	public AttackEvent(Entity targetEntity) {
		this.entity = targetEntity;
	}

}
