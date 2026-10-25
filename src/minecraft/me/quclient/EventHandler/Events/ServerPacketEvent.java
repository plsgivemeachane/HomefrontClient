package me.quclient.EventHandler.Events;

import me.quclient.EventHandler.Event;
import me.quclient.EventHandler.EventCancelable;
import net.minecraft.network.Packet;

public class ServerPacketEvent extends EventCancelable {
	
	public Packet p;
	
	public ServerPacketEvent(Packet p) {
		this.p = p;
	}

}
