package me.quclient.gui;

import java.awt.Color;
import java.io.IOException;

import me.quclient.Client.Client;
import me.quclient.Utils.Animations;
import me.quclient.Utils.MathUtils;
import me.quclient.Utils.RenderUtils;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiMultiplayer;
import net.minecraft.client.gui.GuiOptions;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiSelectWorld;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;

public class MainMenu extends GuiScreen {
	
	public Animations ani = new Animations(20);
	
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		ani.StartOrTickAnimations();
		int animation = ani.getDown(0,100);
		mc.getTextureManager().bindTexture(new ResourceLocation("quclient/splash.jpg"));
		this.drawModalRectWithCustomSizedTexture(0, 0, 0, 0, this.width, this.height, this.width, this.height);
		RenderUtils.drawRoundedRect(width / 2 - 90, height - 50 + animation, width / 2 + 100, height - 5 + animation, 10, new Color(255, 255, 255, 170).getRGB());
		if(ani.isDone())
			super.drawScreen(mouseX, mouseY, partialTicks);
	}
	
	@Override
	public void initGui() {
		ani.enable();
		//this.buttonList.add(new GuiButton(1, 10, height / 2 - 40, "SinglePlayer"));
		this.buttonList.add(new SinglePlayerGuiButton(1, width / 2 - 85, height - 48));
		this.buttonList.add(new MultiPlayerGuiButton(2, width / 2 - 40, height - 48));
		this.buttonList.add(new SettingGuiButton(3, width / 2 + 5, height - 48));
		this.buttonList.add(new QuitGuiButton(4, width / 2 + 55, height - 48));
//		this.buttonList.add(new MultiPlayerGuiButton(2, 10, height / 2 - 15, "Multiplayer"));
//		this.buttonList.add(new Gui(3, 10, height / 2 + 10, "Setting"));
//		this.buttonList.add(new GuiButton(4, 10, height / 2 + 35, "Quit"))
	}
	
	@Override
	protected void actionPerformed(GuiButton button) throws IOException {
		// TODO Auto-generated method stub
		if(button.id == 1) {
			mc.displayGuiScreen(new GuiSelectWorld(this));
		}
		if(button.id == 2) {
			mc.displayGuiScreen(new GuiMultiplayer(this));
		}
		if(button.id == 3) {
			mc.displayGuiScreen(new GuiOptions(this, mc.gameSettings));
		}
		if(button.id == 4) {
			mc.shutdown();
		}
		
		super.actionPerformed(button);
	}

}
