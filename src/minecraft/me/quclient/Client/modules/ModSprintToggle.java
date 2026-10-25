package me.quclient.Client.modules;

import org.lwjgl.input.Keyboard;

import com.google.common.base.Ticker;

import me.quclient.Client.ModDraggable;
import me.quclient.EventHandler.EventListener;
import me.quclient.EventHandler.EventPriolity;
import me.quclient.EventHandler.Events.KeyDownEvent;
import me.quclient.EventHandler.Events.TickEvent;
import me.quclient.Utils.ScreenPosition;

public class ModSprintToggle extends ModDraggable {
	

	private boolean Sprint = false;
	private int delay = 6;
	
	@Override
	public int getWidth() {
		// TODO Auto-generated method stub
		return 64;
	}
	
	@Override
	public int getHeight() {
		// TODO Auto-generated method stub
		return font.FONT_HEIGHT;
	}
	
	
	@Override
	public void render(ScreenPosition pos) {
		font.drawString("Sprint [(" + Sprint + ")]", pos.getAbsoluteX(), pos.getAbsoluteY(), 0xFF00FF00);
	}
	
	@EventListener(EventPriolity.NORMAL)
	public void onTicks(TickEvent e) {
		if(mc.thePlayer == null) return;
		
		if(!mc.thePlayer.isSprinting() && Sprint) {
			if(!mc.thePlayer.isCollidedHorizontally && mc.thePlayer.moveForward > 0 && !mc.thePlayer.isInWater() && !mc.thePlayer.isInLava() && !mc.thePlayer.isBlocking() && !mc.thePlayer.isSneaking()) {
				mc.gameSettings.keyBindSprint.pressed = true;
			}
		}
		delay--;
		if(delay < 0) delay = 0;
	}
	
	@EventListener(EventPriolity.NORMAL)
	public void onPress(KeyDownEvent e) {
		if(e.key == Keyboard.KEY_LCONTROL && delay == 0) {
			Sprint = !Sprint;
			delay = 6;
		}
	}
	
	

}
