package me.quclient.Client.modules;

import me.quclient.Client.Mod;
import me.quclient.EventHandler.EventListener;
import me.quclient.EventHandler.EventPriolity;
import me.quclient.EventHandler.Events.ClickMouseEvent;
import me.quclient.EventHandler.Events.PostClickMouseEvent;
import net.minecraft.util.MovingObjectPosition.MovingObjectType;

public class ModHitRegister extends Mod {

	@EventListener(EventPriolity.HIGHEST)
	public void onClick(PostClickMouseEvent e) {
		if(e.key == 0 && e.typeOfHit == MovingObjectType.MISS) {
			mc.leftClickCounter = 0; // Fix hit reg sometime flag server :)
		}
	}
	
}
