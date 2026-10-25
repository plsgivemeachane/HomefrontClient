package me.quclient.Anticheat.listeners;

import me.quclient.EventHandler.Events.ServerPacketEvent;
import me.quclient.EventHandler.Events.TickEvent;

public interface Listener {
	
	public void onTick(TickEvent e);
	public void onServerPacket(ServerPacketEvent e);

}
