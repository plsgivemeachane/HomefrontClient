package me.quclient.Client;

import org.lwjgl.opengl.Display;

import me.quclient.EventHandler.EventListener;
import me.quclient.EventHandler.Events.TickEvent;
import net.minecraft.client.audio.SoundCategory;

public class FPSReducer extends Mod {
	
	private float currentSound = 0;
	private int currentFPS = 0;
	private boolean isSupress = false;
	
	@Override
	public void onEnable() {
		this.currentFPS = mc.gameSettings.limitFramerate;
		this.currentSound = mc.gameSettings.getSoundLevel(SoundCategory.MASTER);
	}
	
	@EventListener
	public void onEvent(TickEvent e) {
		if(Display.isActive() && isSupress == true) {
			recoverFPS();
			recoverSound();
			isSupress = false;
			
		} else if(isSupress == false){
			reduceFPS();
			stopSound();
			isSupress = true;
		}
	}
	
	
	private void reduceFPS() {
		this.currentFPS = mc.gameSettings.limitFramerate;
		mc.gameSettings.limitFramerate = 10;
		System.out.println("Suppress FPS");
	}
	
	private void stopSound() {
		this.currentSound = mc.gameSettings.getSoundLevel(SoundCategory.MASTER);
		mc.gameSettings.setSoundLevel(SoundCategory.MASTER, 0);
		System.out.println("Suppress Sound");
	}
	
	private void recoverFPS() {
		mc.gameSettings.limitFramerate = this.currentFPS;
		System.out.println("recover FPS");
	}
	
	private void recoverSound() {
		mc.gameSettings.setSoundLevel(SoundCategory.MASTER, this.currentSound);
		System.out.println("recover Sound");
	}

}
