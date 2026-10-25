package me.quclient.ClickGUI;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.lwjgl.opengl.GL11;

import me.quclient.Client.Client;
import me.quclient.Client.Mod;
import me.quclient.Utils.Animations;
import me.quclient.Utils.RenderUtils;
import me.quclient.Utils.ScreenPosition;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.audio.SoundHandler;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;

public class ClickGUI extends GuiScreen {
	
	private Animations a = new Animations(20);
	private Animations fade = new Animations(100,true);
	private Map<Integer,Animations> fades = new HashMap<>();
	
	private class GUIBUTTON {
		public ScreenPosition pos;
		public Mod m;
		public int page;
		
		public GUIBUTTON(ScreenPosition pos, Mod m, int page) {
			this.pos = pos;
			this.m = m;
			this.page = page;
		}
	}
	
	public int delay = 6;
	public int page = 0;
	public int maxPage = 1;
	
	public HashMap<ScreenPosition, Mod> scs = new HashMap<ScreenPosition, Mod>();
	public List<GUIBUTTON> buttons = new ArrayList<GUIBUTTON>();
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		a.StartOrTickAnimations();
		ScreenPosition pos = ScreenPosition.fromRelative(0.9, 0.9);
		ScreenPosition pos2 = ScreenPosition.fromRelative(0.1, 0.1);
		ScreenPosition pos3 = ScreenPosition.fromRelative(0.87, 0.1);
		ScreenPosition textpos = ScreenPosition.fromRelative(0.425, 0.15);
		FontRenderer fr = mc.fontRendererObj;
		
		RenderUtils.drawRoundedRect(pos2.getAbsoluteX(),pos2.getAbsoluteY() - a.getDown(1,200),pos.getAbsoluteX(),pos.getAbsoluteY() - a.getDown(1,200),10,Client.BACKGROUND_COLOR,true);
		
		RenderUtils.drawRoundedRect(pos2.getAbsoluteX() + 0,pos2.getAbsoluteY() - a.getDown(1,200),pos2.getAbsoluteX() + 15,pos.getAbsoluteY() - a.getDown(1,200),5,Client.BACKGROUND_COLOR_2,true);
		RenderUtils.drawRoundedRect(pos3.getAbsoluteX() + 0,pos3.getAbsoluteY() - a.getDown(1,200),pos3.getAbsoluteX() + 15,pos.getAbsoluteY() - a.getDown(1,200),5,Client.BACKGROUND_COLOR_2,true);
		if(!a.isDone()) return;
		
		float stringWidth = fr.getStringWidth(Client.name);
		float x = textpos.getAbsoluteX() - (int)(stringWidth / 2)+ 40;
		float y = textpos.getAbsoluteY() - (int) (fr.FONT_HEIGHT) + 10;
		RenderUtils.drawRoundedRect(x - 5, y - 5,x + stringWidth + 5,y + 5 + fr.FONT_HEIGHT,5, 0xFF000000);
		fr.drawString(Client.name, textpos.getAbsoluteX() + 40 - (int)(fr.getStringWidth(Client.name) / 2), textpos.getAbsoluteY() - (int) (fr.FONT_HEIGHT) + 10, 0xFF00a37d);
		
		int i = 0;
		int j = 0;
		
		for(Mod m : Client.getInstance().getMods()) {
			if(j < 3) {
//				System.out.println(m.getClass().getSimpleName());
//				System.out.println(j);
//				System.out.println(i);
				drawModule(m, j, i);
				j += 1;
			} else {
				j = 0;
				i += 1;
			}
		}
		
		maxPage = i;
		
		
	}
	
	@Override
	public void initGui() {
		fades.clear();
		a.enable();
		int i = 0;
		int j = 0;
		
		for(Mod m : Client.getInstance().getMods()) {
			if(j < 3) {
//				System.out.println(m.getClass().getSimpleName());
//				System.out.println(j);
//				System.out.println(i);
				fades.put(j + i * 100, new Animations(60,true));
				j += 1;
			} else {
				j = 0;
				i += 1;
			}
		}
	}
	
	public void drawModule(Mod mod, int place, int pages) {
		if(fades.isEmpty()) return;
		Animations curr = fades.get(place + pages * 100);
		curr.StartOrTickAnimations();
		ScreenPosition modPos = ScreenPosition.fromRelative(0.14 + (0.14 * place + 0.1 * place), 0.4);
		ScreenPosition textPos = ScreenPosition.fromRelative(0.22 + (0.14 * place + 0.1 * place), 0.3);
		FontRenderer fr = mc.fontRendererObj;
		
		if(page != pages) {
			// Not in page!
			return;
		}
		
		
		int color = (!mod.isEnable) ? Client.MAIN_COLOR : Client.SECOND_COLOR;
		
		if(mod.isEnable) {
			curr.enable();
		} else curr.reset();
		
		
		GL11.glPushMatrix();
		GL11.glPushAttrib(color);
		RenderUtils.drawRoundedRect(modPos.getAbsoluteX(), modPos.getAbsoluteY(), modPos.getAbsoluteX() + 100, modPos.getAbsoluteY() + 100, 10, color, true);
		RenderUtils.drawRoundedRect(modPos.getAbsoluteX(), modPos.getAbsoluteY() + 70, modPos.getAbsoluteX() + 100, modPos.getAbsoluteY() + 100, 10, 0xFF000000, true);
		RenderUtils.drawRoundedRect(modPos.getAbsoluteX() + 5, modPos.getAbsoluteY() + 75, modPos.getAbsoluteX() + 95, modPos.getAbsoluteY() + 95, 10, 0xDDFFFFFF, true);
		if(!mod.isEnable)
			RenderUtils.drawRoundedRect(modPos.getAbsoluteX() + 10, modPos.getAbsoluteY() + 76, modPos.getAbsoluteX() + 30, modPos.getAbsoluteY() + 94, 10, 0xDDFF0000, true);
		else
			RenderUtils.drawRoundedRect(modPos.getAbsoluteX() + 10, modPos.getAbsoluteY() + 76, modPos.getAbsoluteX() + 90 - curr.getDown(), modPos.getAbsoluteY() + 94, 10, 0xDD00FF00, true);
		
		GL11.glColor4f(1, 1, 1, 1);
		fr.drawString(mod.getClass().getSimpleName(), textPos.getAbsoluteX() - (fr.getStringWidth(mod.getClass().getSimpleName()) / 2), textPos.getAbsoluteY(),-1);
		GL11.glPopAttrib();
		GL11.glPopMatrix();
		
		if(!(scs.keySet().contains(modPos))) {
			scs.put(modPos, mod);
		}
		
		GUIBUTTON button = new GUIBUTTON(modPos, mod, pages);
		
		if(!buttons.contains(button)){
			buttons.add(button);
		}
		
		if(delay > 0) delay--;
	}
	
	@Override
	protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
		
		
		// For Module Toggle
		for(GUIBUTTON button : buttons) {
			// TODO get the clicked Item
			ScreenPosition pos = button.pos;
			if(mouseX >= pos.getAbsoluteX() && mouseX <= pos.getAbsoluteX() + 100) {
				if(mouseY >= pos.getAbsoluteY() && mouseY <= pos.getAbsoluteY() + 100) {
					// TODO Action boiiiiii
					if(mouseButton == 0 && delay == 0 && button.page == page) {
						playPressSound(mc.getSoundHandler());
						//Toggle Module
						button.m.setEnable(!button.m.isEnable);
						delay = 6;
					}
				}
			}
		}
		
		// for page
		ScreenPosition pos = ScreenPosition.fromRelative(0.9, 0.9);
		ScreenPosition pos2 = ScreenPosition.fromRelative(0.1, 0.1);
		ScreenPosition pos3 = ScreenPosition.fromRelative(0.87, 0.1);
		
		//Left
		if(mouseX >= pos2.getAbsoluteX() && mouseX <= pos2.getAbsoluteX() + 15) {
			if(mouseY >= pos2.getAbsoluteY() && mouseY <= pos.getAbsoluteY()) {
				if(mouseButton == 0 && delay == 0) {
					playPressSound(mc.getSoundHandler());
					// Page decrease incline
					if(--page < 0) page = 0;
				}
			}
		}
		
		//Right
		if(mouseX >= pos3.getAbsoluteX() && mouseX <= pos3.getAbsoluteX() + 15) {
			if(mouseY >= pos3.getAbsoluteY() && mouseY <= pos.getAbsoluteY()) {
				if(mouseButton == 0 && delay == 0) {
					playPressSound(mc.getSoundHandler());
					// Page increase incline
					if(++page > maxPage) page = maxPage;
				}
			}
		}
	}
	
	public void playPressSound(SoundHandler soundHandlerIn)
    {
        soundHandlerIn.playSound(PositionedSoundRecord.create(new ResourceLocation("gui.button.press"), 1.0F));
    }
	
	
	
}
