package me.quclient.Client.modules;

import java.awt.Color;

import org.lwjgl.opengl.GL11;

import me.quclient.Client.ModDraggable;
import me.quclient.Utils.ScreenPosition;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.settings.KeyBinding;

public class ModKeyStroke extends ModDraggable {
	
	public static enum KeystrokeMode {
		
		WASD(Key.W, Key.A, Key.S, Key.D),
		WASD_MOUSE(Key.W, Key.A, Key.S, Key.D, Key.LMB, Key.RMB);
		
		private final Key[] keys;
		private int width;
		private int height;
		
		private KeystrokeMode(Key... keysIn) {
			this.keys = keysIn;
			
			for(Key key : keys) {
				this.width = Math.max(this.width, key.getX() + key.getWidth());
				this.height = Math.max(this.height, key.getY() + key.getHeight());
			}
			
		}

		public Key[] getKeys() {
			return keys;
		}

		public int getWidth() {
			return width;
		}

		public int getHeight() {
			return height;
		}
		
		
	}
	
	private static class Key {
		
		private static final Key W = new Key("W", Minecraft.getMinecraft().gameSettings.keyBindForward,21,1 ,18,18);
		private static final Key A = new Key("A", Minecraft.getMinecraft().gameSettings.keyBindLeft   ,1 ,21,18,18);
		private static final Key S = new Key("S", Minecraft.getMinecraft().gameSettings.keyBindBack  ,21,21,18,18);
		private static final Key D = new Key("D", Minecraft.getMinecraft().gameSettings.keyBindRight   ,41,21,18,18);
		
		private static final Key LMB = new Key("LMB", Minecraft.getMinecraft().gameSettings.keyBindAttack,1,41,28,18);
		private static final Key RMB = new Key("RMB", Minecraft.getMinecraft().gameSettings.keyBindUseItem,31,41,28,18);
		
		private final String name;
		private final KeyBinding keyBind;
		private final int x;
		private final int y;
		private final int w;
		private final int h;
		
		public Key(String name, KeyBinding keyBind,int x,int y,int w,int h) {
			this.name = name;
			this.keyBind = keyBind;
			this.x = x;
			this.y = y;
			this.w = w;
			this.h = h;
		}
		
		public boolean isDown() {
			return keyBind.isKeyDown();
		}

		public String getName() {
			return name;
		}

		public KeyBinding getKeyBind() {
			return keyBind;
		}

		public int getX() {
			return x;
		}

		public int getY() {
			return y;
		}

		public int getWidth() {
			return w;
		}

		public int getHeight() {
			return h;
		}
		
		
		
	}
	private KeystrokeMode mode = KeystrokeMode.WASD_MOUSE;
	
	@Override
	public int getWidth() {
		return mode.getWidth();
	}
	
	@Override
	public int getHeight() {
		return mode.getHeight();
	}
	
	@Override
	public void render(ScreenPosition pos) {
		
//		System.out.println(pos.getRelativeX());
//		System.out.println(pos.getRelativeY());
		
		GL11.glPushMatrix();
		
		boolean blend = GL11.glIsEnabled(GL11.GL_BLEND);
				
		GL11.glDisable(GL11.GL_BLEND);
		
		for(Key key : mode.getKeys()) {
			
			int textWidth = font.getStringWidth(key.name);
			
			Gui.drawRect(
				pos.getAbsoluteX() + key.getX(),
				pos.getAbsoluteY() + key.getY(),
				pos.getAbsoluteX() + key.getX() + key.getWidth(),
				pos.getAbsoluteY() + key.getY() + key.getHeight(),
				key.isDown() ? new Color(255, 255, 255, 102).getRGB() : new Color(0, 0, 0, 102).getRGB()
			);
			
			font.drawString(
				key.name,
				pos.getAbsoluteX() + key.getX() + key.getWidth() / 2 - textWidth / 2,
				pos.getAbsoluteY() + key.getY() + key.getHeight() / 2 - 4,
				key.isDown() ? Color.black.getRGB() : Color.white.getRGB() 
			);
			
		}
		
		GL11.glPopMatrix();
		
	}
	
	@Override
	public void renderDummy(ScreenPosition pos) {
		
		
	}

}
