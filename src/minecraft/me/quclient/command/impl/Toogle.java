package me.quclient.command.impl;

import me.quclient.Client.Client;
import me.quclient.Client.Mod;
import me.quclient.command.Command;

public class Toogle extends Command {
	
	public Toogle() {
		super("Toggle","Toggle Things...",".t <name>","t");
	}
	
	@Override
	public void onCommand(String[] args, String command) {
		// TODO Auto-generated method stub
		if(args.length != 1) {
			addChat(getSyntax());
			return;
		}
		
		for(Mod m : Client.getMods()) {
			if(m.getClass().getSimpleName().contains(args[0])) {
				m.setEnable(!m.isEnable);
				addChat(m.getClass().getSimpleName() + " " + ((m.isEnable) ? "Enabled" : "Disabled"));
			}
		}
		
		
	}

}
