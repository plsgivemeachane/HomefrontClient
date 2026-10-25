package me.quclient.Anticheat;

import net.minecraft.entity.player.EntityPlayer;

public class Utils {
	
	public static double getSpeed(EntityPlayer p) {
		return Math.sqrt(p.motionX * p.motionX + p.motionZ * p.motionZ);
	}

	public static boolean isMoving(EntityPlayer p) {
		return (p.moveForward != 0 || p.moveStrafing != 0);
	}
	
	public static boolean isOnGround(EntityPlayer p) {
		return (p.isCollided && p.onGround);
	}
	
	

}
