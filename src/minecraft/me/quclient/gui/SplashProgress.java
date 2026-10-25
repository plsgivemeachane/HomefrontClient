package me.quclient.gui;

import java.awt.Color;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.shader.Framebuffer;
import net.minecraft.util.ResourceLocation;

public class SplashProgress {
	
	private static final int MAX = 10;
	private static int PROGRESS = 0;
	private static String CURRENT = "";
	private static ResourceLocation splash;
	private static FontRenderer fr;
	private static double preCall = 0;
	private static double moveving = 0;
	

	public static void update() {
		if(Minecraft.getMinecraft() == null || Minecraft.getMinecraft().getLanguageManager() == null){
			return;
		}
		
		drawSplash(Minecraft.getMinecraft().getTextureManager());
	}
	
	public static void setProgress(int givenProcess, String givenText) {
		PROGRESS = givenProcess;
		CURRENT = givenText;
		update();
	}
	
	public static void drawSplash(TextureManager tm) {
		
		ScaledResolution sr = new ScaledResolution(Minecraft.getMinecraft());
		int scaleFactor = sr.getScaleFactor();
		
		Framebuffer framebuffer = new Framebuffer(sr.getScaledWidth() * scaleFactor, sr.getScaledHeight() * scaleFactor, true);
		framebuffer.bindFramebuffer(false);
		
		GlStateManager.matrixMode(GL11.GL_PROJECTION);
		GlStateManager.loadIdentity();
		GlStateManager.ortho(0.0, sr.getScaledWidth(), sr.getScaledHeight(), 0.0, 1000.0, 3000.0);
		GlStateManager.matrixMode(GL11.GL_MODELVIEW);
		GlStateManager.loadIdentity();
		GlStateManager.translate(0.0f, 0.0f, -2000f);
		GlStateManager.disableLighting();
		GlStateManager.disableFog();
		GlStateManager.disableDepth();
		GlStateManager.enableTexture2D();
		
		if(splash == null) {
			splash = new ResourceLocation("quclient/splash.jpg");
			
		}
		
		tm.bindTexture(splash);
		
		GlStateManager.resetColor();
		GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
		
		Gui.drawScaledCustomSizeModalRect(0, 0, 0, 0, 1920, 1080, sr.getScaledWidth(), sr.getScaledHeight(), 1920, 1080);
		drawProgress();
		framebuffer.unbindFramebuffer();
		framebuffer.framebufferRender(sr.getScaledWidth() * scaleFactor, sr.getScaledHeight() * scaleFactor);
		
		GlStateManager.enableAlpha();
		GlStateManager.alphaFunc(516, 0.1f);
		
		
		Minecraft.getMinecraft().updateDisplay();
		
		
	}
	
	public static void drawProgress() {
		if(Minecraft.getMinecraft().gameSettings == null || Minecraft.getMinecraft().getTextureManager() == null || Minecraft.getMinecraft().fontRendererObj == null) return;
		
		if(fr == null) {
			fr = Minecraft.getMinecraft().fontRendererObj;
		}
		
		ScaledResolution sr = new ScaledResolution(Minecraft.getMinecraft());
		
		double nProgress = PROGRESS;
		double calc = (nProgress / MAX) * sr.getScaledWidth();
		
		if((int)(calc) != (int)(preCall)) {
			moveving = 0;
		}
		
		moveving++;
		
		Gui.drawRect(0, sr.getScaledHeight() - 35, sr.getScaledWidth(), sr.getScaledHeight(), new Color(0, 0, 0, 100).getRGB());
		
		GlStateManager.resetColor();
		resetTextureState();
		
		fr.drawString(CURRENT, 20, sr.getScaledHeight() - 25, 0xFFFFFFFF);
		
		String s = PROGRESS + "/" + MAX;
		fr.drawString(s, sr.getScaledWidth() - 20 - fr.getStringWidth(s), sr.getScaledHeight() - 25, 0xFFe1e1e1);
		
		GlStateManager.resetColor();
		resetTextureState();
		
		Gui.drawRect(0, sr.getScaledHeight() - 10, sr.getScaledWidth(), sr.getScaledHeight(), new Color(0, 0, 0, 100).getRGB());
		Gui.drawRect(0, sr.getScaledHeight() - 10, calc + moveving, sr.getScaledHeight(), 0xFFFFFFFF);
		
		SplashProgress.preCall = calc;
	}
	
	public static void resetTextureState() {
		//Sound engine started
		GlStateManager.textureState[GlStateManager.activeTextureUnit].textureName = -1;
	}
	
}
