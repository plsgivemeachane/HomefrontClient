package me.quclient.Anticheat.Checks;

import me.quclient.Anticheat.Check;
import me.quclient.Anticheat.Type;
import me.quclient.Anticheat.Utils;
import me.quclient.Client.Client;
import me.quclient.Utils.StaticsStorage;
import net.minecraft.entity.player.EntityPlayer;

public class VelocityCheckA implements Check {

	@Override
	public boolean runCheck(EntityPlayer e) {
		// TODO Auto-generated method stub
		
		if(e.beAttack) {
			if(e.attackTimers.hasPassed(500)) {
				e.attackTimers.reset();
				e.beAttack = false;
				if(Utils.getSpeed(e) < StaticsStorage.minVelocity) {
					return true;
				} else {
					return false;
				}
			}
		} else e.attackTimers.reset();
		
		if(e.hurtTime > 0) {
			// Full check
			if(e.hurtTime > 7 && !e.onGround) {
				// Being attack ticks
				e.beAttack = true;
			}
			
			if(e.beAttack && e.hurtTime < 9) {
				// Attack delay ticks
				
				if(Utils.getSpeed(e) == 0) {
					return false;
				}
			}
			
		} else {
//			e.beAttack = false;
		}
		
		return false;
	}

	@Override
	public Type getType() {
		// TODO Auto-generated method stub
		return Type.PROBILITY;
	}

	@Override
	public void flag(EntityPlayer e) {
		// TODO Auto-generated method stub
		e.VelocityAViolent++;
		Client.anticheat.flag("Velocity(A)", getType(), e, " VL #" + e.VelocityAViolent);
	}

}
