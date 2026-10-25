package me.quclient.Client.modules;

import me.quclient.Client.Mod;
import me.quclient.EventHandler.EventListener;
import me.quclient.EventHandler.Events.AttackEvent;
import me.quclient.EventHandler.Events.TickEvent;
import me.quclient.Utils.Timers;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.GameSettings;
import quclient.util.PlayerUlt;

public class ModAutoClicker extends Mod {
	
	private Timers delay = new Timers();
	
	private int CPS = 8;
	
	//Jitter Click
	private int clicks = PlayerUlt.getRandom(CPS - 1, CPS + 1);
			
	
	@EventListener
	public void onTick(TickEvent e) {
		if(mc.thePlayer == null || mc.theWorld == null) return;
		
		if(mc.currentScreen != null) return;
		
		if(GameSettings.isKeyDown(mc.gameSettings.keyBindAttack) && delay.hasPassed(1000 / clicks)) {
			clicks = PlayerUlt.getRandom(CPS - 1, CPS);
			delay.reset();
			//mc.leftClickCounter = 0;
			mc.clickMouse();
		}
	}

}
