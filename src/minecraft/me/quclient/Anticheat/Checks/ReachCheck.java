package me.quclient.Anticheat.Checks;

import me.quclient.Anticheat.Anticheat;
import me.quclient.Anticheat.Check;
import me.quclient.Anticheat.PlayerData;
import me.quclient.Anticheat.Type;
import me.quclient.Client.Client;
import me.quclient.Utils.StaticsStorage;
import net.minecraft.entity.player.EntityPlayer;

public class ReachCheck implements Check {
	
	public double lastReach = 0;

	@Override
	public boolean runCheck(EntityPlayer player) {
		if(Client.anticheat.attack.attacks.get(player) == null) return false; 
		double reciver = Client.anticheat.attack.attacks.get(player);
		//System.out.pritln("Distancef : " + reciver);
		
		if(reciver > StaticsStorage.maxReach && lastReach != reciver) {
			lastReach = reciver;
			Client.anticheat.attack.attacks.remove(player);
			return true;
		}
		
		
		
		return false;
	}

	@Override
	public Type getType() {
		// TODO Auto-generated method stub
		return Type.PROBILITY;
	}

	@Override
	public void flag(EntityPlayer p) {
		// TODO Auto-generated method stub
		Client.anticheat.flag("Reach(A)", getType(), p, "reach=(" + lastReach + ") max=(" + StaticsStorage.maxReach + ")");
	}


}
