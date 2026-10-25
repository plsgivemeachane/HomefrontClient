package me.quclient.command.impl;

import me.quclient.Client.CapeManager;
import me.quclient.Client.modules.ModAttackPartical;
import me.quclient.Utils.StaticsStorage;
import me.quclient.command.Command;
import net.minecraft.client.Minecraft;

public class Capes extends Command {
	
	public Capes() {
		super("Capes","Change the cape...",".capes <id>","capes");
	}	
	
	@Override
	public void onCommand(String[] args, String command) {
		// TODO Auto-generated method stub
		if(args.length != 1) {
			addChat(getSyntax());
			return;
		}
		
		addChat("Capes was set to " + args[0]);
		
		StaticsStorage.CAPE = Integer.parseInt(args[0]);
		
		CapeManager.sendRequestSetCape("https://MyZoomCloneP3.quanvndev.repl.co/namecape/" + Minecraft.getMinecraft().thePlayer.getNameClear() + "/", StaticsStorage.CAPE);
		
	}

}
