package me.quclient.Client.modules;

import java.util.ArrayList;
import java.util.List;

import me.quclient.Client.ModDraggable;
import me.quclient.EventHandler.EventListener;
import me.quclient.EventHandler.EventPriolity;
import me.quclient.EventHandler.Events.ClickMouseEvent;
import me.quclient.EventHandler.Events.TickEvent;
import me.quclient.Utils.ScreenPosition;
import net.minecraft.client.gui.Gui;

public class ModCPS extends ModDraggable {
	private List<Long> lclicks = new ArrayList<>();
	private List<Long> rclicks = new ArrayList<>();
	
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
		mc.fontRendererObj.drawStringWithShadow("[" + lclicks.size() + " : " + rclicks.size() + "]", pos.getAbsoluteX(), pos.getAbsoluteY(), -1);
	}
	
	@EventListener
	public void onUpdate(TickEvent event) {
		long time = System.currentTimeMillis();
		
		lclicks.removeIf(e -> e + 1000 < time);
		rclicks.removeIf(e -> e + 1000 < time);
	}
	
	@EventListener(EventPriolity.HIGH)
	public void click(ClickMouseEvent e) {
		if(e.key == 0) lclicks.add(System.currentTimeMillis());
		if(e.key == 1) rclicks.add(System.currentTimeMillis());
	}
}
