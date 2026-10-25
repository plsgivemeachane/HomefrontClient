package me.quclient.Utils;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentStyle;
import net.minecraft.util.ChatComponentText;

public class ClientChatHandler {
	
	public static void sendChat(String message) {
		Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(message));
	}

}
