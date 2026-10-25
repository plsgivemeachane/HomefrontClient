package me.quclient.Client.modules;

import me.quclient.Client.ModDraggable;
import me.quclient.Utils.ScreenPosition;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;

public class ModHUDPlayer extends ModDraggable {
	
	@Override
	public int getWidth() {
		// TODO Auto-generated method stub
		return 50;
	}
	
	@Override
	public int getHeight() {
		// TODO Auto-generated method stub
		return font.FONT_HEIGHT * 2;
	}
	
	@Override
	public void render(ScreenPosition pos) {
		Entity entities = mc.pointedEntity;
		if(entities != null && entities instanceof EntityLivingBase) {
			EntityLivingBase entity = (EntityLivingBase) entities;
			int health = (int) entity.getHealth();
			int maxHealth = (int) entity.getMaxHealth();
			font.drawString(entity.getDisplayName().getFormattedText(),pos.getAbsoluteX(),pos.getAbsoluteY(),-1);
			font.drawString("Health:" + health + "/" + maxHealth,pos.getAbsoluteX(),pos.getAbsoluteY() + font.FONT_HEIGHT + 1,-1);
		}
	}
}
