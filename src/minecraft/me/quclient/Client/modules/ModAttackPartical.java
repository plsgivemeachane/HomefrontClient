package me.quclient.Client.modules;

import org.apache.commons.lang3.RandomUtils;

import me.quclient.Client.Mod;
import me.quclient.EventHandler.EventListener;
import me.quclient.EventHandler.EventPriolity;
import me.quclient.EventHandler.Events.AttackEvent;
import me.quclient.EventHandler.Events.OnKilledEvent;
import net.minecraft.block.Block;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.init.Blocks;
import net.minecraft.network.play.server.S2CPacketSpawnGlobalEntity;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;

public class ModAttackPartical extends Mod {
	
	public static int MOD = 1;
	public static final int blockState = Block.getStateId(Blocks.redstone_block.getDefaultState());
	public static final int blockState2 = Block.getStateId(Blocks.snow.getDefaultState());
	
	
	@EventListener(EventPriolity.HIGHEST)
	public void onAttack(AttackEvent e) {
		int particalCount = 1;
		Entity entity = e.entity;
		System.out.println("Attack");
		for(int i = 0;i < particalCount;i++) {
			switch(MOD) {
			case 1:mc.effectRenderer.emitParticleAtEntity(e.entity, EnumParticleTypes.CRIT); break;
			case 2:mc.effectRenderer.emitParticleAtEntity(e.entity, EnumParticleTypes.CRIT_MAGIC);break;
			case 3:
				for(int i1 = 0;i1 < 20;i1++) {
					mc.effectRenderer.spawnEffectParticle(EnumParticleTypes.BLOCK_CRACK.getParticleID(), entity.posX, entity.posY + entity.height / 2, entity.posZ,entity.motionX + RandomUtils.nextFloat(0, 40f) - 20, entity.motionY + RandomUtils.nextFloat(0f, 40f) - 20, entity.motionZ + RandomUtils.nextFloat(0f, 40f) - 20, blockState);
		        }	
				break;
			}
		}
	}
	
	@EventListener
	public void onKilled(OnKilledEvent e) {
		Entity entity = e.entity;
		 
		
		 mc.getSoundHandler().playSound(PositionedSoundRecord.create(new ResourceLocation("random.explode"), 1.0f));
         mc.getSoundHandler().playSound(PositionedSoundRecord.create(new ResourceLocation("ambient.weather.thunder"), 1.0f));
         //mc.getNetHandler().handleSpawnGlobalEntity(new S2CPacketSpawnGlobalEntity(new EntityLightningBolt(mc.theWorld, entity.posX, entity.posY, entity.posZ))); 
         for(int i = 0;i < 500;i++) {
        	 int range = 10;
        	 mc.effectRenderer.spawnEffectParticle(EnumParticleTypes.BLOCK_CRACK.getParticleID(), entity.posX + RandomUtils.nextFloat(0, range) - range / 2, entity.posY + entity.height + RandomUtils.nextFloat(0, range) - range / 2, entity.posZ + RandomUtils.nextFloat(0, range) - range / 2,entity.motionX, entity.motionY, entity.motionZ, blockState2);
         }	
	}
	
	

}
