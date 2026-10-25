package me.quclient.Utils;

import java.awt.Color;

public class ColorUtils {
	public static int getRanbow(int seconds, float sat, float bright) {
		float hue = (System.currentTimeMillis() & 1000 * seconds) / (float) (1000*seconds);
		int color = Color.HSBtoRGB(hue, sat, bright);
		
		
		return color;
	}
	
	public static Color getRanbowColor(int seconds, float sat, float bright) {
		float hue = (System.currentTimeMillis() & 1000 * seconds) / (float) (1000*seconds);
		int color = Color.HSBtoRGB(hue, sat, bright);
		
		
		return new Color(color);
	}
	
	
	
	public static int getRanbow(int seconds, float sat, float bright, long Index) {
		float hue = ((System.currentTimeMillis() + Index) & 1000 * seconds) / (float) (1000*seconds);
		int color = Color.HSBtoRGB(hue, sat, bright);
		
		return color;
	}
	
	public static Color getRanbowColor(int seconds, float sat, float bright, long Index) {
		float hue = ((System.currentTimeMillis() + Index) & 1000 * seconds) / (float) (1000*seconds);
		int color = Color.HSBtoRGB(hue, sat, bright);
		
		return new Color(color);
	}
}
