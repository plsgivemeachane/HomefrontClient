package me.quclient.Client.modules;

import me.quclient.Client.Client;
import net.minecraft.client.Minecraft;

public class ModCheats {
	
	public Minecraft mc = Minecraft.getMinecraft();
	public static float REACH = 4f;
	public static float hitbox = 1.4f;
	public static boolean isEnable;
	
	
	public ModCheats() {
		Client.getInstance().getEventManager().registerMethod(this);
	}
	
	
	
	

}
