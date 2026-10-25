package me.quclient.Client.modules;

import java.awt.Color;

import me.quclient.Client.Mod;

public class ModGlintColor extends Mod {

	private final Color DEFAULT = new Color(128, 64, 204); 
	
	private Color customColor = new Color(204, 255, 255, 110);
	
	public Color getColor() {
		if(this.isEnable) return customColor;
		else return DEFAULT;
	}
	
}
