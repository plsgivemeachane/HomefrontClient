package me.quclient.Client.modules;

import me.quclient.Client.Mod;
import me.quclient.EventHandler.EventListener;
import me.quclient.EventHandler.Events.ServerPacketEvent;
import me.quclient.EventHandler.Events.TextEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.network.play.server.S02PacketChat;
import net.minecraft.util.ChatComponentText;

public class ModNameHider extends Mod {
	
	public static String name = "HomeFrontUser";
	
	@EventListener
	public void onChat(TextEvent e) {
		if(!this.isEnable) return; 
		if(mc.thePlayer == null || mc.theWorld == null) return;
		//System.out.println("HERE");
		String chat = e.message;
		
		String playername = Minecraft.getMinecraft().thePlayer.getCommandSenderName();
		//System.out.println(playername);
		//System.out.println(name);
		chat.replace(playername,name);
		//System.out.println(chat);
		
		//s.chatComponent = new ChatComponentText(chat.replaceAll(playername, name));
		e.message = chat.replaceAll(playername, name);
		
	}

}
