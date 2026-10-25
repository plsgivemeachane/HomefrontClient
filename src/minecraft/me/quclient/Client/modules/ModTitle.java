package me.quclient.Client.modules;

import org.lwjgl.opengl.GL11;

import me.quclient.Client.Client;
import me.quclient.Client.Mod;
import me.quclient.Client.ModDraggable;
import me.quclient.EventHandler.EventListener;
import me.quclient.EventHandler.Events.RenderEvent;
import me.quclient.Utils.ScreenPosition;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.Render;

public class ModTitle extends ModDraggable {
	private double scale = 1;
	
	private String text;
	
	
	
	@Override
	public int getHeight() {
		return (int) (font.FONT_HEIGHT*scale);
	}
	
	@Override
	public int getWidth() {
		return (int) (font.getStringWidth(Client.name + " " + Client.version)*scale);
	}
	
	@Override
	public void renderDummy(ScreenPosition pos) {
		text = Client.name + " " + Client.version + " | " + mc.thePlayer.getDisplayName().getFormattedText() + ((mc.getCurrentServerData() != null) ? " | " + mc.getCurrentServerData().serverIP : "");
		GL11.glPushMatrix();
		GL11.glScaled(scale, scale, scale);
		font.drawString(text, pos.getAbsoluteX() + 1, pos.getAbsoluteY() + 1, 0xFF00FF00);
		GL11.glPopMatrix();
	}
	
	@Override
	public void render(ScreenPosition pos) {
		text = Client.name + " " + Client.version + " | " + mc.thePlayer.getDisplayName().getFormattedText() + ((mc.getCurrentServerData() != null) ? " | " + mc.getCurrentServerData().serverIP : "");
		GL11.glPushMatrix();
		GL11.glScaled(scale, scale, scale);
		font.drawString(text, pos.getAbsoluteX() + 1, pos.getAbsoluteY() + 1, 0xFF00FF00);
		GL11.glPopMatrix();
	}
}
