package me.quclient.Anticheat.Checks;

import me.quclient.Anticheat.Anticheat;
import me.quclient.Anticheat.Check;
import me.quclient.Anticheat.PlayerData;
import me.quclient.Anticheat.Type;
import me.quclient.Client.Client;
import me.quclient.Utils.StaticsStorage;
import net.minecraft.entity.player.EntityPlayer;

public class SpeedCheckB implements Check {
	
	public int ticks = 0;

	public float getSpeed(EntityPlayer p) {
		return (float) Math.sqrt(p.motionX * p.motionX + p.motionZ * p.motionZ);
	}
	
	@Override
	public boolean runCheck(EntityPlayer player) {
		if(mc.thePlayer == null) return false;
		if(mc.theWorld == null) return false;
		
		
		
		if(getSpeed(player) > StaticsStorage.maxSpeed) {
			ticks = (int) getSpeed(player);
			return true;
		}
		
		
		return false;
	}

	@Override
	public Type getType() {
		// TODO Auto-generated method stub
		return Type.LIKELY;
	}

	@Override
	public void flag(EntityPlayer p) {
		// TODO Auto-generated method stub
		Client.anticheat.flag("SPEED(B) : đi quá nhanh trong time", getType(), p, "ticks=(" + ticks + ") max=(" + StaticsStorage.maxSpeed + ")");
	}


}
