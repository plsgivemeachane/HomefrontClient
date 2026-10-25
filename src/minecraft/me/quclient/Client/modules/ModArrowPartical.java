package me.quclient.Client.modules;

import me.quclient.Client.Mod;
import me.quclient.EventHandler.EventListener;
import me.quclient.EventHandler.Events.TickEvent;
import me.quclient.Utils.Timers;
import net.minecraft.entity.Entity;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.util.EnumParticleTypes;

public class ModArrowPartical extends Mod {
	
	public Timers t = new Timers();
	
	@EventListener
	public void onTick(TickEvent e) {
		if(mc.thePlayer == null || mc.theWorld == null) return;
		if(!t.hasPassed(100)) return;
		for(Object o : mc.theWorld.loadedEntityList) {
			if(o instanceof EntityArrow) {
				if(((Entity)o).isCollided) continue;
				if(((Entity)o).motionY == 0) continue;
				if(((Entity)o).posY == ((Entity)o).lastTickPosY) continue;
				mc.effectRenderer.emitParticleAtEntity((Entity)o, EnumParticleTypes.CRIT_MAGIC);
			}
		}
		
		t.reset();
	}

}
