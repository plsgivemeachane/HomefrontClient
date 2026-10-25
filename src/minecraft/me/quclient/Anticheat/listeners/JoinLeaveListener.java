package me.quclient.Anticheat.listeners;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.apache.commons.lang3.tuple.Pair;

import com.google.common.collect.Lists;

import me.quclient.Anticheat.PlayerData;
import me.quclient.EventHandler.Events.ServerPacketEvent;
import me.quclient.EventHandler.Events.TickEvent;
import me.quclient.Utils.Timers;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;

public class JoinLeaveListener implements Listener {
	
	public Minecraft mc = Minecraft.getMinecraft();

	public List<PlayerData> players = Lists.newArrayList();
	public List<EntityPlayer> has = Lists.newArrayList();
	public List<EntityPlayer> removePlayer = Lists.newArrayList();
	public List<PlayerData> removeData = Lists.newArrayList();
	
	public Timers t = new Timers();

	
	@Override
	public void onTick(TickEvent e) {
		if(!t.hasPassed(500)) {
			return;
		}
		
		t.reset();
		
		// Join
		for(Object o : mc.theWorld.loadedEntityList) {
			if(o instanceof EntityPlayer) {
				EntityPlayer p = (EntityPlayer)o;
				if(!has.contains(p)) {
					has.add(p);
					players.add(new PlayerData(p));
					System.out.println(p.getName() + " Joined");
				}
			}
		}
		
		
		
		// Leave
		removeData.clear();
		removePlayer.clear();
		for(EntityPlayer hp : has) {
			boolean isHas = false; 
			for(Object o : mc.theWorld.loadedEntityList) {
				if(o instanceof EntityPlayer) {
					EntityPlayer p = (EntityPlayer)o;
					
					if(hp == p) {
						isHas = true;
					}
					
				}
			}
			if(!isHas) {
				//System.out.println(hp.getName() + " 'Leaved");
				//Player LEFT
				removePlayer.add(hp);
				removeData.add(new PlayerData(hp));
			}
		}
		
		for(EntityPlayer remove : removePlayer) {
			has.remove(remove);
		}
		
		for(PlayerData remove : removeData) {
			players.remove(remove);
		}
	}

	@Override
	public void onServerPacket(ServerPacketEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	

}
