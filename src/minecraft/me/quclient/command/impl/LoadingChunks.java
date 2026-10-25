package me.quclient.command.impl;

import me.quclient.Utils.StaticsStorage;
import me.quclient.command.Command;

public class LoadingChunks extends Command {

	public LoadingChunks() {
		super("Loading Chunks", "Set default loaded Chunks", ".chunks <mode <my,other>> <number 1 - any>", "chunks","c");
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void onCommand(String[] args, String command) {
		if(args.length != 2) {
			addChat(syntax);
			return;
		}
		
		String mode = args[0];
		int number = Integer.valueOf(args[1]);
		
		if(mode.equalsIgnoreCase("my")) {
			StaticsStorage.MAX_CHUNKS_LOAD = number;
			
			addChat("Success set my to " + args[1]);
		} else if(mode.equalsIgnoreCase("other")) {
			StaticsStorage.MAX_CHUNKS_LOAD_OTHER = number;
			
			addChat("Success set other to " + args[1]);
		} else {
			addChat(syntax);
		}
		
		
	}
	
	

}
