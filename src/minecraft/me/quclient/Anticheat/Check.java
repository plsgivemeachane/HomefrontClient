package me.quclient.Anticheat;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;

public interface Check {
	
	public final Minecraft mc = Minecraft.getMinecraft();

	public boolean runCheck(EntityPlayer e);
	public Type getType();
	public void flag(EntityPlayer e);

}
