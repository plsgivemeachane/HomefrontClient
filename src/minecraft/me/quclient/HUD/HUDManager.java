package me.quclient.HUD;

import java.util.Collection;
import java.util.Set;

import org.lwjgl.input.Keyboard;

import com.google.common.base.Ticker;
import com.google.common.collect.Sets;

import me.quclient.Client.Client;
import me.quclient.EventHandler.EventListener;
import me.quclient.EventHandler.EventPriolity;
import me.quclient.EventHandler.Events.KeyDownEvent;
import me.quclient.EventHandler.Events.RenderEvent;
import me.quclient.EventHandler.Events.TickEvent;
import me.quclient.Utils.IRenderer;
import me.quclient.Utils.ScreenPosition;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiChat;

public class HUDManager{
	
	private static HUDManager instance = null;

	private HUDManager() {}
	
	public static HUDManager getInstance() {
		
		if(instance != null) {
			return instance;
		}
		
		instance = new HUDManager();
		Client.getInstance().getEventManager().registerMethod(instance);
			
		return instance;
	}
	
	private Set<IRenderer> registeredRenderers = Sets.newHashSet();
	private Minecraft mc = Minecraft.getMinecraft();
	
	public void register(IRenderer... renderes) {
		for(IRenderer render : renderes) {
			this.registeredRenderers.add(render);
		}
	}
	
	public void unRegister(IRenderer... renderes) {
		for(IRenderer render : renderes) {
			this.registeredRenderers.remove(render);
		}
	}
	
	public Collection<IRenderer> getRegisteredRenderers() {
		return Sets.newHashSet(registeredRenderers);
	}
	
	public void openConfigScreen() {
		mc.displayGuiScreen(new HUDConfigScreen(this));
	}
	
	@EventListener(EventPriolity.HIGHEST)
	public void onRender2D(RenderEvent event) {
		//System.out.println("RENDERE");
		if((mc.currentScreen == null) || mc.currentScreen instanceof GuiChat) {
			for(IRenderer renderer : registeredRenderers) {
				callRenderer(renderer);
			}
		}
	}
	
	@EventListener(EventPriolity.HIGHEST)
	public void onKey(KeyDownEvent e) {
		if(e.key == Keyboard.KEY_Y) {
			this.openConfigScreen();
		}
	}

	private void callRenderer(IRenderer renderer) {
		if(!renderer.isEnable()) return;
		
		ScreenPosition pos = renderer.load();
		
		if(pos == null) {
			pos = ScreenPosition.fromRelative(0.5, 0.5);
		}
		
		renderer.render(pos);
	}
	
	
}
