package me.quclient.command;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import me.quclient.Utils.ClientChatHandler;


public class Command {

	public String name, desc, syntax;
	public List<String> aliases = new ArrayList<>();
	
	public Command(String name, String desc, String syntax, String... aliases) {
		this.name = name;
		this.desc = desc;
		this.syntax = syntax;
		this.aliases = Arrays.asList(aliases); 
	}
	
	public void onCommand(String[] args, String command) {
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getSyntax() {
		return syntax;
	}

	public void setSyntax(String syntax) {
		this.syntax = syntax;
	}

	public List<String> getAliases() {
		return aliases;
	}

	public void setAliases(List<String> aliases) {
		this.aliases = aliases;
	}
	
	public void addChat(String message) {
		ClientChatHandler.sendChat(message);
	}
	
	
}
