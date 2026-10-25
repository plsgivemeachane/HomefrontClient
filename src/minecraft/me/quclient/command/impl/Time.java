package me.quclient.command.impl;

import me.quclient.Client.CapeManager;
import me.quclient.Client.modules.ModAttackPartical;
import me.quclient.Client.modules.ModFullBright;
import me.quclient.Utils.StaticsStorage;
import me.quclient.command.Command;
import net.minecraft.client.Minecraft;

public class Time extends Command {
	
	public Time() {
		super("Time","Change World time...",".time <time>","time");
	}	
	
	@Override
	public void onCommand(String[] args, String command) {
		// TODO Auto-generated method stub
		if(args.length != 1) {
			addChat(getSyntax());
			return;
		}
		
		addChat("Time was set to " + args[0]);
		
		//StaticsStorage.CAPE = Integer.parseInt(args[0]);
		ModFullBright.WORLDTIME = Integer.parseInt(args[0]);
		
		//CapeManager.sendRequestSetCape("https://MyZoomCloneP3.quanvndev.repl.co/namecape/" + Minecraft.getMinecraft().thePlayer.getNameClear() + "/", StaticsStorage.CAPE);
		
	}

}
