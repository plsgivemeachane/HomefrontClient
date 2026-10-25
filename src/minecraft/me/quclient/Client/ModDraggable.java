package me.quclient.Client;

import java.io.File;

import me.quclient.Utils.IRenderer;
import me.quclient.Utils.ScreenPosition;

public class ModDraggable extends Mod implements IRenderer {
	
	private ScreenPosition pos = ScreenPosition.fromRelative(0.5, 0.5);

	public ModDraggable() {
		pos = loadPositionFromFile();
	}
	
	private ScreenPosition loadPositionFromFile() {
		// TODO Auto-generated method stub
		
		ScreenPosition load = FileManager.readFromFile(new File(getFolder(), "pos.json"), ScreenPosition.class);
		
		if(load == null) {
			load = ScreenPosition.fromRelative(0.5, 0.5);
			this.pos = load;
			savePositionToFile();
		}
		
		return load;
	}
	

	private void savePositionToFile() {
		// TODO Auto-generated method stub
		FileManager.writeJsonToFile(new File(getFolder(), "pos.json"), pos);
	}

	public final int getLineOffset(ScreenPosition pos,int lineNum) {
		return pos.getAbsoluteY() + getLineOffset(lineNum);
	}
	
	private int getLineOffset(int lineNum) {
		return (font.FONT_HEIGHT + 3) * lineNum;
	}

	@Override
	public int getWidth() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getHeight() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void save(ScreenPosition pos) {
		// TODO Auto-generated method stub
		this.pos = pos;
		savePositionToFile();
	}

	@Override
	public ScreenPosition load() {
		// TODO Auto-generated method stub
		return pos;
	}

	@Override
	public void render(ScreenPosition pos) {
		// TODO Auto-generated method stub
		
	}
		
	
}
