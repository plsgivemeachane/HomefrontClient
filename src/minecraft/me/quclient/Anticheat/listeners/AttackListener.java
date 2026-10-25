package me.quclient.Anticheat.listeners;

import java.util.HashMap;
import java.util.Map;

import me.quclient.Anticheat.Anticheat;
import me.quclient.Anticheat.PlayerData;
import me.quclient.Client.Client;
import me.quclient.EventHandler.Events.ServerPacketEvent;
import me.quclient.EventHandler.Events.TickEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MathHelper;

public class AttackListener implements Listener {
	
	public float angle = 120.0f;
	public Minecraft mc = Minecraft.getMinecraft();
	
	public Map<EntityPlayer,Double> attacks = new HashMap<EntityPlayer,Double>();

	@Override
	public void onTick(TickEvent e) {
		// TODO Auto-generated method stub
		for(Object o : mc.theWorld.loadedEntityList) {
			if(o instanceof EntityPlayer) {
				if(o instanceof EntityPlayer) {
					//PlayerData data = Client.anticheat.getPlayer((EntityPlayer)o);
					check(new PlayerData((EntityPlayer)o));
				}
			}
		}
	}
	
	public void check(PlayerData data) {
		EntityPlayer p = data.p;
		EntityPlayer near = getNearEntity(p);
		
		if(near != null && p.swingProgressInt == 1) {
			double distance = p.getDistanceToEntity(near);
			System.out.println(distance);
			if(distance < 6) {
				System.out.println(p.getName() + " Tries to swing " + near.getName());
			}
			
			if(distance < 6 && near.hurtTime > 0) {
				//System.out.print(p.getName() + " : ");
				System.out.println(p.getName() + " Attack " + near.getName() + " REACH " + distance);
				if(!attacks.keySet().contains(p))
					attacks.put(p ,distance);
				else
					attacks.replace(p, distance);
			}
				//System.out.println("Check1");
//				if(p.getName() == mc.thePlayer.getName()) {
//					Client.inCombat = true;
//					Client.combatTimer.reset();
//					Client.inCombatEntity = near;
//					System.out.println("InCombat");
//				}
				//System.out.println(p.getName() + " Attack " + near.getName());
		}
	}

	@Override
	public void onServerPacket(ServerPacketEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public EntityPlayer getNearEntity(EntityPlayer source) {
		double dis = 999;
		
		EntityPlayer best = null;
		
		for(Object o : Minecraft.getMinecraft().theWorld.loadedEntityList) {
			if(o instanceof EntityPlayer && o != source) {
				EntityPlayer p = (EntityPlayer)o;
				//System.out.println("Check " + p.getName());
				if(isInFOV(source,p,angle)) {
					//System.out.println(source.getDistanceToEntity(p));
					if(source.getDistanceToEntity(p) < dis) {
						dis = source.getDistanceToEntity(p);
						best = p;
					}
				}
			}
		}
		
		return best;
	}
	
	private boolean isInFOV(EntityPlayer entity, EntityPlayer target, double angle) {
        angle *= .5D;
        double angleDiff = getAngleDifference(entity.rotationYaw, getRotations(target.posX, target.posY, target.posZ)[0]);
        return (angleDiff > 0 && angleDiff < angle) || (-angle < angleDiff && angleDiff < 0);
    }

    private float getAngleDifference(float dir, float yaw) {
        float f = Math.abs(yaw - dir) % 360F;
        float dist = f > 180F ? 360F - f : f;
        return dist;
    }
    
    private float[] getRotations(double x, double y, double z) {
        double diffX = x + .5D - mc.thePlayer.posX;
        double diffY = (y + .5D) / 2D - (mc.thePlayer.posY + mc.thePlayer.getEyeHeight());
        double diffZ = z + .5D - mc.thePlayer.posZ;

        double dist = MathHelper.sqrt_double(diffX * diffX + diffZ * diffZ);
        float yaw = (float)(Math.atan2(diffZ, diffX) * 180D / Math.PI) - 90F;
        float pitch = (float)-(Math.atan2(diffY, dist) * 180D / Math.PI);

        return new float[] { yaw, pitch };
    }

}
