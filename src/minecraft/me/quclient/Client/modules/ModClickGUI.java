package me.quclient.Client.modules;

import org.lwjgl.input.Keyboard;

import me.quclient.ClickGUI.ClickGUI;
import me.quclient.Client.Client;
import me.quclient.Client.Mod;
import me.quclient.EventHandler.EventListener;
import me.quclient.EventHandler.EventPriolity;
import me.quclient.EventHandler.Events.KeyDownEvent;

public class ModClickGUI extends Mod {
	
	
	public ModClickGUI() {
		Client.getInstance().getEventManager().registerMethod(this);
	}
	
	@EventListener(EventPriolity.NORMAL)
	public void onKey(KeyDownEvent e) {
		if(e.key == Keyboard.KEY_RSHIFT) {
			// Render ClickGUI
			mc.displayGuiScreen(new ClickGUI());
		}
	}
	

}
