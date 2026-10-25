package me.quclient.Client.modules;

import me.quclient.Client.ModDraggable;
import me.quclient.EventHandler.EventListener;
import me.quclient.EventHandler.Events.AttackEvent;
import me.quclient.EventHandler.Events.TickEvent;
import me.quclient.Utils.ScreenPosition;

public class ModComboDisplay extends ModDraggable {
	private int combo;
	
	@EventListener
	public void onAttack(AttackEvent e) {
		combo++;
	}
	
	@EventListener
	public void onUpdate(TickEvent e) {
		if(mc.thePlayer == null) return;
		
		if(mc.thePlayer.hurtTime > 0 && mc.thePlayer.hurtTime != 0) {
			combo = 0;
		}
	}

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
		font.drawStringWithShadow("Combo : " + combo, pos.getAbsoluteX(), pos.getAbsoluteY(), 0xFF00FF00);
	}
	
	@Override
	public void renderDummy(ScreenPosition pos) {
		font.drawStringWithShadow("Combo : 0", pos.getAbsoluteX(), pos.getAbsoluteY(), 0xFF00FF00);
	}
	
	

}
