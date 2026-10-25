package me.quclient.Anticheat.Checks;

import me.quclient.Anticheat.Anticheat;
import me.quclient.Anticheat.Check;
import me.quclient.Anticheat.PlayerData;
import me.quclient.Anticheat.Type;
import me.quclient.Anticheat.Utils;
import me.quclient.Client.Client;
import me.quclient.Utils.StaticsStorage;
import net.minecraft.entity.player.EntityPlayer;

public class SpeedCheckA implements Check {
	
	public double ticks = 0;	

	@Override
	public boolean runCheck(EntityPlayer player) {
		if(mc.thePlayer == null) return false;
		if(mc.theWorld == null) return false;
		
		
		
		if(Utils.getSpeed(player) > StaticsStorage.maxSpeed && player.hurtTime < 5) {
			ticks = Utils.getSpeed(player);
			return false;
		}
		
		
		return false;
	}

	@Override
	public Type getType() {
		// TODO Auto-generated method stub
		return Type.DEFINETLY;
	}

	@Override
	public void flag(EntityPlayer p) {
		// TODO Auto-generated method stub
		Client.anticheat.flag("SPEED(A)", getType(), p, "ticks=(" + ticks + ") max=(" + StaticsStorage.maxSpeed + ")");
	}


}
