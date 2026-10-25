package me.quclient.Client.modules;

import me.quclient.Client.ModDraggable;
import me.quclient.EventHandler.EventListener;
import me.quclient.EventHandler.Events.AttackEvent;
import me.quclient.EventHandler.Events.TickEvent;
import me.quclient.Utils.ScreenPosition;

public class ModSpeedDisplay extends ModDraggable {
	private double speed;
	
	@EventListener
	public void onMove(TickEvent e) {
		if(mc.thePlayer == null) return;
		
		speed = Math.sqrt(mc.thePlayer.motionX * mc.thePlayer.motionX + mc.thePlayer.motionZ * mc.thePlayer.motionZ);
	}
	
//	@EventListener
//	public void onUpdate(TickEvent e) {
//		if(mc.thePlayer == null) return;
//		
//		if(mc.thePlayer.hurtTime == mc.thePlayer.maxHurtTime) {
//			combo = 0;
//		}
//	}

	@Override
	public int getWidth() {
		// TODO Auto-generated method stub
		return 50;
	}
	
	@Override
	public int getHeight() {
		// TODO Auto-generated method stub
		return font.FONT_HEIGHT;
	}
	
	@Override
	public void render(ScreenPosition pos) {
		font.drawStringWithShadow("Speed : " + (float)((int)(speed * 100)) / 100, pos.getAbsoluteX(), pos.getAbsoluteY(), 0xFF00FF00);
	}
	
	@Override
	public void renderDummy(ScreenPosition pos) {
		font.drawStringWithShadow("Speed : 0", pos.getAbsoluteX(), pos.getAbsoluteY(), 0xFF00FF00);
	}
	
	
}
