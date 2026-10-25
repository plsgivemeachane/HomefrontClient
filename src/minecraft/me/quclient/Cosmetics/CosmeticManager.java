package me.quclient.Cosmetics;

import net.minecraft.client.entity.AbstractClientPlayer;

public class CosmeticManager {
	
	public static boolean shouldRenderTopHat(AbstractClientPlayer player) {
		return true;
	}
	
	public static float[] getTopHatColor(AbstractClientPlayer player) {
		return new float[] {1, 0, 0};
	}

}
