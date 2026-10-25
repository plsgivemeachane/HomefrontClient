package me.quclient.Client;

import java.io.File;

import me.quclient.Utils.ScreenPosition;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;

public class Mod {
	
	public boolean isEnable;
	public final Minecraft mc;
	public final FontRenderer font;
	public final Client client;

	public Mod() {
		this.mc = Minecraft.getMinecraft();
		this.font = mc.fontRendererObj;
		this.client = Client.getInstance();
		this.setEnable(loadStateFromFile());
		
	}
	
	
	private boolean loadStateFromFile() {
		// TODO Auto-generated method stub
		boolean load;
		if(FileManager.readFromFile(new File(getFolder(), "bool.json"), boolean.class) == null) {
			load = true;
			saveStateFromFile();
		} else {
			load = FileManager.readFromFile(new File(getFolder(), "bool.json"), boolean.class);
		}
		return load;
	}


	protected File getFolder() {
		File folder = new File(FileManager.getModsDirectory(), this.getClass().getSimpleName());
		folder.mkdirs();
		return folder;
	}

	private void saveStateFromFile() {
		// TODO Auto-generated method stub
		FileManager.writeJsonToFile(new File(getFolder(), "bool.json"), isEnable);
		System.out.println("Saved " + isEnable);
	}

	public boolean isEnable() {
		return isEnable;
	}

	public void setEnable(boolean isEnable) {
		this.isEnable = isEnable;
		if(this.isEnable) onEnable();
		else onDisable();
		saveStateFromFile();
	}
	
	public void onEnable() {
		Client.getInstance().getEventManager().registerMethod(this);
	}
	
	public void onDisable() {
		Client.getInstance().getEventManager().unregisterMethod(this);
	}
	
	
	
	

}
