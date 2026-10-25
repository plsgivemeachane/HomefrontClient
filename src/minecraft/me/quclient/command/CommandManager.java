package me.quclient.command;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import me.quclient.Client.Client;
import me.quclient.EventHandler.EventListener;
import me.quclient.EventHandler.Events.ChatEvent;
import me.quclient.Utils.ClientChatHandler;
import me.quclient.command.impl.AttackPartical;
import me.quclient.command.impl.Capes;
import me.quclient.command.impl.LoadingChunks;
import me.quclient.command.impl.MotionBlur;
import me.quclient.command.impl.NameHider;
import me.quclient.command.impl.Time;
import me.quclient.command.impl.Toogle;

public class CommandManager {

	public List<Command> commands = new ArrayList<Command>();
	public String prefix = ".";
	
	public CommandManager() {
		Client.getInstance().getEventManager().registerMethod(this);
		
		commands.add(new Toogle());
		commands.add(new MotionBlur());
		commands.add(new AttackPartical());
		commands.add(new LoadingChunks());
		commands.add(new Capes());
		commands.add(new NameHider());
		commands.add(new Time());
	}
	
//	Client.getInstance().getModule

	
	@EventListener
	public void onChat(ChatEvent e) {
		System.out.println("Handled " + e.message);
		String message = e.message;
		
		if(!message.startsWith(prefix)) return;
		
		e.cancelled = true;
		
		message = message.substring(prefix.length());
		
		if(message.split(" ").length > 0) {
			
			String commandName = message.split(" ")[0];
			
			System.out.println(commandName);
			
			boolean checked = false;
			
			for(Command c : commands) {
				if(c.aliases.contains(commandName)) {
					c.onCommand(Arrays.copyOfRange(message.split(" "), 1, message.split(" ").length), commandName);
					checked = true;
				}
			}
			
			if(!checked) {
				ClientChatHandler.sendChat("Command not found.Please try these commands");
				for(Command c : commands) {
					ClientChatHandler.sendChat(c.name + " : " + c.desc + " ; " + c.syntax);
				}
			}
			
		}
	}
	
}
