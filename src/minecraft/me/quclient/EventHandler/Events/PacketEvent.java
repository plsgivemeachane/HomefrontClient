package me.quclient.EventHandler.Events;

import me.quclient.EventHandler.EventCancelable;
import net.minecraft.network.Packet;

public class PacketEvent extends EventCancelable {
	
	public Packet packet;
	
	public PacketEvent(Packet p) {
		this.packet = p;
	}

}
