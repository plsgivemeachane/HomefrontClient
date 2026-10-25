package me.quclient.Client.modules;

import me.quclient.Client.Mod;
import me.quclient.EventHandler.EventListener;
import me.quclient.EventHandler.Events.BreakEvent;
import me.quclient.EventHandler.Events.ServerPacketEvent;
import me.quclient.EventHandler.Events.TickEvent;
import net.minecraft.block.material.Material;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.C08PacketPlayerBlockPlacement;
import net.minecraft.network.play.server.S23PacketBlockChange;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;

public class ModAntiDesync extends Mod {
	
	public static BlockPos posBroke;
	public static BlockPos posPlace;
	public static EnumFacing face;
	public static boolean isBroke;
	public static boolean isPlace;
	public C08PacketPlayerBlockPlacement placePacket;
	
	@EventListener
	public void onPacket(ServerPacketEvent e) {
		if(!this.isEnable || mc.thePlayer == null || mc.theWorld == null) return;
		Packet p = e.p;
		
		if(p instanceof S23PacketBlockChange) {
			S23PacketBlockChange s23 = (S23PacketBlockChange)p;
			System.out.println("Placed " + s23.getBlockState().getBlock().getMaterial().isSolid());
			if(posBroke != null) {
				if(s23.getBlockPosition() == posBroke && s23.getBlockState().getBlock().getMaterial() == Material.air) {
					isBroke = true;
				}
			}
			
			if(s23.getBlockState().getBlock().getMaterial().isSolid()) {
				System.out.println("Placed");
				isPlace = true;
				posPlace = s23.getBlockPosition();
			}
		} else if(p instanceof C08PacketPlayerBlockPlacement) {
			C08PacketPlayerBlockPlacement c = (C08PacketPlayerBlockPlacement)p;
			placePacket = c;
		}
	}
	
	@EventListener
	public void onDestroy(BreakEvent e) {
		if(!this.isEnable || mc.thePlayer == null || mc.theWorld == null) return;
		
		
		posBroke = e.pos;
		if(isBroke) {
			// Break Success				
			e.cancelled = false;
			isBroke = false;
			posBroke = null;
			face = null;
		} else {
			System.out.println("[AntiDesync] Broke Fail Desync from server");
			//Client.notification.show(new Notification(NotificationType.ERROR, name, "Block break fail", 1));
			e.cancelled = true;
		}
	}
	
	

}
