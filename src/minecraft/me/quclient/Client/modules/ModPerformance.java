package me.quclient.Client.modules;

import com.jcraft.jorbis.Block;

import me.quclient.Client.Mod;
import me.quclient.EventHandler.EventListener;
import me.quclient.EventHandler.EventPriolity;
import me.quclient.EventHandler.Events.BlockRenderEvent;
import me.quclient.EventHandler.Events.ServerPacketEvent;
import me.quclient.EventHandler.Events.TickEvent;
import me.quclient.EventHandler.Events.WorldEvent;
import me.quclient.Utils.ClientChatHandler;
import me.quclient.Utils.StaticsStorage;
import me.quclient.Utils.Timers;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.network.play.server.S08PacketPlayerPosLook;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.MovingObjectPosition.MovingObjectType;

public class ModPerformance extends Mod {
	
	public Timers delay = new Timers();
	public int rendered;
	public boolean isMaxed = false;
	public static int WaitTime = 500;
	public boolean isPause = false;
	
	public int BlockCancelled = 6;
	
	public ModPerformance() {
		// Config Performance here
		rendered = 0;
		isPause = false;
		mc.gameSettings.fancyGraphics = false;
		mc.gameSettings.enableVsync = false;
		mc.gameSettings.useVbo = true;
		mc.gameSettings.limitFramerate = 10000;
		//mc.gameSettings.particleSetting = 2;
		mc.gameSettings.ambientOcclusion = 0;
		mc.gameSettings.clouds = 0;
		mc.gameSettings.allowBlockAlternatives = false;
		mc.gameSettings.pauseOnLostFocus = true;
		mc.gameSettings.mipmapLevels = 0;
		mc.gameSettings.ofLazyChunkLoading = true;
		//mc.gameSettings.chatScale = 0.5f;
		mc.gameSettings.ofFastMath = true;
		mc.gameSettings.ofChunkUpdatesDynamic = false;
	}
	
	@EventListener
	public void onWorld(WorldEvent e) {
		//ClientChatHandler.sendChat("Pre World Rendering...");
		BlockCancelled = 100;
		WaitTime = 1000;
		//mc.renderGlobal.resumeChunkUpdates();
	}
	
	@EventListener
	public void onTele(ServerPacketEvent e) {
		if(e.p instanceof S08PacketPlayerPosLook) {
			BlockCancelled = 50;
			WaitTime = 500;
		}
	}
	
	@EventListener
	public void onBlock(BlockRenderEvent e) {
		MovingObjectPosition ray = mc.theWorld.rayTraceBlocks(mc.thePlayer.getPositionVector().addVector(0, mc.thePlayer.getEyeHeight(), 0), e.pos.getVector().subtract(-1, -1, -1));
		double dis = e.pos.distanceSq(mc.thePlayer.posX, mc.thePlayer.posY + mc.thePlayer.getEyeHeight(), mc.thePlayer.posZ);
		if(ray != null && ray.typeOfHit == MovingObjectType.BLOCK && dis > 1000) {
			//System.out.println(dis);
			BlockCancelled = 0;
			//e.cancelled = true;
		}
		
		
		
		
//		//System.out.println(dis);
//		if(dis > 1 && BlockCancelled > 0) {
//			BlockCancelled--;
//			e.cancelled = true;
//			//.out.println("Cannceled");
//			if(BlockCancelled == 0) {
//				//ClientChatHandler.sendChat("Pre World Rendered");
//			}
//		}
	}
	
	
	@EventListener
	public void onTick(TickEvent e) {
		if(!this.isEnable) return;
		if(mc.thePlayer == null) return;
		if(mc.theWorld == null) return;
		if(mc.renderGlobal.hasNoChunkUpdates()) return;
		if(!mc.renderGlobal.chunksToUpdateForced.isEmpty()) return;
		if(!isPause && mc.thePlayer.moveStrafing == 0 && mc.thePlayer.moveForward == 0) {
			isPause = true;
		} else if(isPause && mc.thePlayer.moveStrafing != 0 && mc.thePlayer.moveForward != 0 ) {
			//mc.renderGlobal.resumeChunkUpdates();
			isPause = false;
			//mc.renderGlobal.resetClouds();
		}
	}

}
