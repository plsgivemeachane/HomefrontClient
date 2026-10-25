package me.quclient.Client.modules;

import com.google.common.base.Ticker;

import me.quclient.Client.Mod;
import me.quclient.EventHandler.EventListener;
import me.quclient.EventHandler.EventPriolity;
import me.quclient.EventHandler.Events.ServerPacketEvent;
import me.quclient.EventHandler.Events.TickEvent;
import net.minecraft.network.play.server.S03PacketTimeUpdate;

public class ModFullBright extends Mod {
	
	public static int WORLDTIME = 6000;

	@EventListener(EventPriolity.HIGHEST)
	public void onTicks(TickEvent e) {
		mc.gameSettings.gammaSetting = 100;
		if(mc.theWorld == null) return;
		mc.theWorld.setWorldTime(WORLDTIME);
	}
	
	@EventListener(EventPriolity.HIGHEST)
	public void onPacket(ServerPacketEvent e) {
		if(e.p instanceof S03PacketTimeUpdate) {
			e.cancelled = true;
		}
		
		
	}
	
	
	@Override
	public void onDisable() {
		mc.gameSettings.gammaSetting = 1;
		super.onDisable();
	}
	
}
