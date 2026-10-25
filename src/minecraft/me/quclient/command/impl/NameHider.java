package me.quclient.command.impl;

import me.quclient.Client.modules.ModNameHider;
import me.quclient.command.Command;

public class NameHider extends Command {

	public NameHider() {
		super("NameHider Module Setting", "Set the name of NameHider", ".n <name>", "n", "namehider ");
	}
	
	@Override
	public void onCommand(String[] args, String command) {
		if(args.length != 1) {
			addChat(syntax);
		}
		
		ModNameHider.name = args[0];
	}

}
