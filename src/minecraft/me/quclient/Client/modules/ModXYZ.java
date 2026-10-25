package me.quclient.Client.modules;

import me.quclient.Client.ModDraggable;
import me.quclient.Utils.ScreenPosition;

public class ModXYZ extends ModDraggable {
	
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
		int offset = font.FONT_HEIGHT;
		font.drawStringWithShadow("X : " + (int) mc.thePlayer.posX, pos.getAbsoluteX(), pos.getAbsoluteY(), 0xFF00FFAA);
		font.drawStringWithShadow("Y : " + (int) mc.thePlayer.posY, pos.getAbsoluteX(), pos.getAbsoluteY() + offset, 0xFF00FFAA);
		font.drawStringWithShadow("Z : " + (int) mc.thePlayer.posZ, pos.getAbsoluteX(), pos.getAbsoluteY() + offset*2, 0xFF00FFAA);
	}
	
	@Override
	public void renderDummy(ScreenPosition pos) {
		int offset = font.FONT_HEIGHT;
		font.drawStringWithShadow("X : 0", pos.getAbsoluteX(), pos.getAbsoluteY(), 0xFF00FF00);
		font.drawStringWithShadow("Y : 0", pos.getAbsoluteX(), pos.getAbsoluteY() + offset, 0xFF00FF00);
		font.drawStringWithShadow("Z : 0", pos.getAbsoluteX(), pos.getAbsoluteY() + offset*2, 0xFF00FF00);
	}
	
	
}
