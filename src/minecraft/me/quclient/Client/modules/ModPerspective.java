package me.quclient.Client.modules;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;

import me.quclient.Client.ModDraggable;
import me.quclient.EventHandler.EventListener;
import me.quclient.EventHandler.Events.KeyDownEvent;
import me.quclient.EventHandler.Events.TickEvent;

public class ModPerspective extends ModDraggable {
	
	public int delay = 0;
	
	private boolean returnOnRelease = true;
	private boolean perspectiveToggled = false;
	
	private float cameraYaw;
	private float cameraPitch;
	private int previousPerspective = 0;
	
	@EventListener
	public void onKeyDown(KeyDownEvent e) {
		if(e.key == Keyboard.KEY_G && delay == 0) {
			if(Keyboard.getEventKeyState()) {
				perspectiveToggled = !perspectiveToggled;
				
				cameraYaw = mc.thePlayer.rotationYaw;
				cameraPitch = mc.thePlayer.rotationPitch;
				
				if(perspectiveToggled) {
					previousPerspective = mc.gameSettings.thirdPersonView;
					mc.gameSettings.thirdPersonView = 1;
				} else {
					mc.gameSettings.thirdPersonView = previousPerspective;
				}
			} else {
//				
			}
		}
		
		if(Keyboard.getEventKey() == mc.gameSettings.keyBindTogglePerspective.getKeyCode()) {
			perspectiveToggled = false;
		}
	}
	
	@EventListener
	public void onUpdate(TickEvent e) {
		if(delay > 0) delay--;
	}
	
	public float getCameraYaw() {
		return perspectiveToggled ? cameraYaw : mc.thePlayer.rotationYaw;
	}
	
	public float getCameraPitch() {
		return perspectiveToggled ? cameraPitch : mc.thePlayer.rotationPitch;
	}
	
	public boolean overrideMouse() {
		if(mc.inGameHasFocus && Display.isActive()) {
			
			if(!perspectiveToggled) return true;
			
			mc.mouseHelper.mouseXYChange();
			float f1 = (float) (mc.gameSettings.mouseSensitivity * 0.1);
			float f2 = f1 * 3 * 8.0f;
			float f3 = mc.mouseHelper.deltaX * f2;
			float f4 = mc.mouseHelper.deltaY * f2;
			
			cameraYaw += f3 * 0.15f;
			cameraPitch -= f4 * 0.15f;
			
			
//			if(cameraPitch > 90) cameraPitch = 90;
//			if(cameraYaw < -90) cameraYaw = -90;
			
		}
		
		return false;
	}
	
	

}
