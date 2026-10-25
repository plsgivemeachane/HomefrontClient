package me.quclient.Anticheat;

import java.util.ArrayList;
import java.util.List;

import me.quclient.Anticheat.Checks.FlyCheckA;
import me.quclient.Anticheat.Checks.FlyCheckB;
import me.quclient.Anticheat.Checks.ReachCheck;
import me.quclient.Anticheat.Checks.SpeedCheckA;
import me.quclient.Anticheat.Checks.VelocityCheckA;
import me.quclient.Anticheat.listeners.AttackListener;
import me.quclient.Anticheat.listeners.JoinLeaveListener;
import me.quclient.Client.Client;
import me.quclient.Client.modules.ModAnticheat;
import me.quclient.EventHandler.EventListener;
import me.quclient.EventHandler.Events.ServerPacketEvent;
import me.quclient.EventHandler.Events.TickEvent;
import me.quclient.Utils.ClientChatHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;

public class Anticheat {
	
	//public static Anticheat instance;
	
	public List<Check> checks = new ArrayList<>();
	public JoinLeaveListener jll = new JoinLeaveListener();
	public AttackListener attack = new AttackListener();

	public Minecraft mc = Minecraft.getMinecraft();
	
	public Anticheat() {
		Client.getInstance().getEventManager().registerMethod(this);
		
		registerCheck();
	}
	
	
	public void registerCheck() {
		checks.add(new ReachCheck());
		checks.add(new FlyCheckA());
		checks.add(new FlyCheckB());
		checks.add(new SpeedCheckA());
		checks.add(new VelocityCheckA());
	}
	
	
	public List<PlayerData> getPlayers(){
		return jll.players;
	}
	
	public PlayerData getPlayer(EntityPlayer p) {
		if(jll.has.indexOf(p) != -1)
			return jll.players.get(jll.has.indexOf(p));
		return null;
	}
	
	public void flag(String m, Type t, EntityPlayer p, String log) {
		ClientChatHandler.sendChat("§c§l[Anti-cheat]§r§9 " + p.getName() + " §chas §lCRINGE§r§b " + m + " §aTYPE §5" + t.toString() + " §7" + log);
	}
	
	@EventListener
	public void onTick(TickEvent e) {
		if(mc.thePlayer == null) return;
		if(mc.theWorld == null) return;
		//System.out.println("CHECK");
		for(Object o : mc.theWorld.loadedEntityList) {
			if(!(o instanceof EntityPlayer)) continue;
			for(Check c : this.checks) {
				EntityPlayer p = (EntityPlayer)o;
				if(c.runCheck(p) && Client.getInstance().getModule(ModAnticheat.class).isEnable) {
					c.flag(p);
				}
			}
		}
		
		jll.onTick(e);
		attack.onTick(e);
	}
	
	@EventListener
	public void onServerPacket(ServerPacketEvent e) {
		jll.onServerPacket(e);
		attack.onServerPacket(e);
	}
	
	
	

}
