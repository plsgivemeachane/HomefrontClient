package me.quclient.Anticheat.Checks;

import me.quclient.Anticheat.Anticheat;
import me.quclient.Anticheat.Check;
import me.quclient.Anticheat.PlayerData;
import me.quclient.Anticheat.Type;
import me.quclient.Anticheat.Utils;
import me.quclient.Client.Client;
import me.quclient.Utils.StaticsStorage;
import net.minecraft.entity.player.EntityPlayer;

public class FlyCheckB implements Check {
	
	public int ticks = 0;	

	@Override
	public boolean runCheck(EntityPlayer player) {
		if(mc.thePlayer == null) return false;
		if(mc.theWorld == null) return false;
		if(player.capabilities.isCreativeMode) return false;
		if(player.isSpectator()) return false;
		if(player.capabilities.allowFlying) return false;
		
		if(!Utils.isOnGround(player) && player.motionY == 0 && Utils.isMoving(player)) {
			player.flyBflags++;
			if(player.flyBflags > StaticsStorage.MAX_FLAG) {
				ticks = player.flyBflags;
				player.flyBflags = 0;
				return true;
			}
		} else {
			player.flyBflags = 0;
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
		Client.anticheat.flag("FLY(B)", getType(), p, "ticks=(" + ticks + ") max=(" + StaticsStorage.MAX_FLAG + ")");
	}


}
