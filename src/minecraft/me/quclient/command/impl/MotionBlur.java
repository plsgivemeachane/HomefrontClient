package me.quclient.command.impl;

import me.quclient.Client.Client;
import me.quclient.Client.modules.ModMotionBlur;
import me.quclient.command.Command;

public class MotionBlur extends Command {
	public MotionBlur() {
		super("MotionBlur","Change Motion Blur Settings",".m <1 - 10>","m","motionblur");
	}
	
	
	
	@Override
	public void onCommand(String[] args, String command) {
		// TODO Auto-generated method stub
		if(args.length != 1) {
			addChat(getSyntax());
			return;
		}
		
		String str = args[0];
		
		double i = Double.parseDouble(str);
		
		ModMotionBlur.blurAmount = i;
		
		ModMotionBlur.enabled = false;
		
		addChat("Set Motion Blur Value to " + i);
		
		
	}
}
