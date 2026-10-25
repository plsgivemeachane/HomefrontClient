package me.quclient.Utils;

public interface IRenderer extends IRenderComfig{
	
	int getWidth();
	int getHeight();
	
	void render(ScreenPosition pos);
	
	default void renderDummy(ScreenPosition pos) {
		render(pos);
	}
	
	public default boolean isEnable() {
		return true;
	}
	
}
