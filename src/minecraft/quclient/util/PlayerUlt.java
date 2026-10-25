package quclient.util;

import java.util.Random;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.network.play.client.C03PacketPlayer.C04PacketPlayerPosition;
import net.minecraft.network.play.client.C0BPacketEntityAction;

public class PlayerUlt {
	public static boolean aacdamage = false;
	public static double aacdamagevalue;
	public static Random rand = new Random();
	public static Minecraft mc = Minecraft.getMinecraft();
	
	public static EntityPlayerSP thePlayer() {
		return Minecraft.getMinecraft().thePlayer;
	}
	
	public static void damage(double value) {
		aacdamage = true;
		aacdamagevalue = value + 3.165D;
		thePlayer().moveForward++;
		thePlayer().moveForward--;
		thePlayer().moveStrafing--;
		thePlayer().moveStrafing++;
		thePlayer().sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(thePlayer().posX, thePlayer().posY + aacdamagevalue,thePlayer().posZ , false));
		thePlayer().sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(thePlayer().posX, thePlayer().posY,thePlayer().posZ , false));
		thePlayer().sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(thePlayer().posX, thePlayer().posY,thePlayer().posZ , true));
//		thePlayer().sendQueue.addToSendQueue(new C04PacketPlayerPosition(mc.thePlayer.posX, mc.thePlayer.posY + 3.05, mc.thePlayer.posZ, false));
//		thePlayer().sendQueue.addToSendQueue(new C04PacketPlayerPosition(mc.thePlayer.posX, mc.thePlayer.posY, mc.thePlayer.posZ, false));
//		thePlayer().sendQueue.addToSendQueue(new C04PacketPlayerPosition(mc.thePlayer.posX, mc.thePlayer.posY + 0.41999998688697815, mc.thePlayer.posZ, true));
//		thePlayer().sendQueue.addToSendQueue(new C04PacketPlayerPosition(mc.thePlayer.posX, mc.thePlayer.posY + 3.35, mc.thePlayer.posZ, false));
//		thePlayer().sendQueue.addToSendQueue(new C04PacketPlayerPosition(mc.thePlayer.posX, mc.thePlayer.posY, mc.thePlayer.posZ, false));
//		thePlayer().sendQueue.addToSendQueue(new C04PacketPlayerPosition(mc.thePlayer.posX, mc.thePlayer.posY, mc.thePlayer.posZ, true));
		
	}
	
	public static void StartShift() {
		Minecraft.getMinecraft().thePlayer.setSneaking(true);
		Minecraft.getMinecraft().thePlayer.sendQueue.addToSendQueue(new C0BPacketEntityAction(Minecraft.getMinecraft().thePlayer,C0BPacketEntityAction.Action.START_SNEAKING));
	}
	
	public static void StopShift() {
		Minecraft.getMinecraft().thePlayer.setSneaking(false);
		Minecraft.getMinecraft().thePlayer.sendQueue.addToSendQueue(new C0BPacketEntityAction(Minecraft.getMinecraft().thePlayer,C0BPacketEntityAction.Action.STOP_SNEAKING));
	}
	
	public static int getRandom(int min,int max) {
		int Uppper = max - min;
		return rand.nextInt(Uppper) + min;
	}
	
}
