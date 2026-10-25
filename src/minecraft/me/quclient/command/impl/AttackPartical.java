package me.quclient.command.impl;

import me.quclient.Client.Client;
import me.quclient.Client.Mod;
import me.quclient.Client.modules.ModAttackPartical;
import me.quclient.command.Command;

public class AttackPartical extends Command {
	
	public AttackPartical() {
		super("AttackPartical","AttackPartical Things...",".a <1:CRITS,2:MAGIC,3:BLODD>","a","attackpartical","attackpar");
	}
	
	@Override
	public void onCommand(String[] args, String command) {
		// TODO Auto-generated method stub
		if(args.length != 1) {
			addChat(getSyntax());
			return;
		}
		
		addChat("Mod was set to " + args[0]);
		
		ModAttackPartical.MOD = Integer.parseInt(args[0]);
		
		
	}

}
