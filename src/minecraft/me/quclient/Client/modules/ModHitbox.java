package me.quclient.Client.modules;

import com.google.common.base.Ticker;

import me.quclient.Client.Mod;
import me.quclient.EventHandler.EventListener;
import me.quclient.EventHandler.EventPriolity;
import me.quclient.EventHandler.Events.Render3DEvent;
import me.quclient.EventHandler.Events.ServerPacketEvent;
import me.quclient.EventHandler.Events.TickEvent;
import me.quclient.Utils.RenderUtils;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.network.play.server.S03PacketTimeUpdate;

public class ModHitbox extends Mod {
	
	@EventListener
	public void onRender(Render3DEvent event) {
		if(!this.isEnable) return;
		for(Object o : mc.theWorld.loadedEntityList) {
			if(o instanceof EntityLivingBase) {
				EntityLivingBase e = (EntityLivingBase)o;
				RenderUtils.drawOutlinedEntityESP(e.posX, e.posY, e.posZ, e.width, e.height, 1, 1, 1, 1);
			}
		}
	}
	
}
