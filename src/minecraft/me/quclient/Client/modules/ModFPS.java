package me.quclient.Client.modules;

import me.quclient.Client.ModDraggable;
import me.quclient.Utils.ScreenPosition;

public class ModFPS extends ModDraggable {
	
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
		font.drawStringWithShadow("FPS : " + mc.getDebugFPS(), pos.getAbsoluteX(), pos.getAbsoluteY(), 0xFF00FF00);
	}
	
	@Override
	public void renderDummy(ScreenPosition pos) {
		font.drawStringWithShadow("FPS : 200", pos.getAbsoluteX(), pos.getAbsoluteY(), 0xFF00FF00);
	}
	
	
}
