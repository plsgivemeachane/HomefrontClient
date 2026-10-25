package me.quclient.gui;

import java.awt.Color;

import me.quclient.Utils.Animations;
import me.quclient.Utils.RenderUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;

public class SettingGuiButton extends GuiButton {
	public Animations fade = new Animations(5,true);
	public SettingGuiButton(int buttonId, int x, int y) {
		super(buttonId, x, y, 40, 40, "SinglePlayer");
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void drawButton(Minecraft mc, int mouseX, int mouseY) {
		if (this.visible)
        {
			fade.StartOrTickAnimations();
			GlStateManager.color(1, 1, 1, 1);
			boolean flag = mouseX >= this.xPosition && mouseY >= this.yPosition && mouseX < this.xPosition + this.width && mouseY < this.yPosition + this.height;
			
			int i = 106;
			
			if(flag) {
				i += height;
				fade.enable();
				RenderUtils.drawRoundedRect(xPosition, yPosition, xPosition + width, yPosition + height, 10, new Color(255,255,255,fade.getUp(100, 255)).getRGB());
			} else fade.reset();
			
			
			GlStateManager.color(1, 1, 1, 1);
			mc.getTextureManager().bindTexture(new ResourceLocation("quclient/settings.png"));
			this.drawModalRectWithCustomSizedTexture(xPosition, yPosition, 0, 0, width, height, width, height);
        }
	}

}
