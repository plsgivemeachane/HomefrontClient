package me.quclient.Client;

import java.util.ArrayList;
import java.util.List;

import me.quclient.EventHandler.EventListener;
import me.quclient.EventHandler.EventPriolity;
import me.quclient.EventHandler.Events.AttackEvent;
import me.quclient.EventHandler.Events.OnKilledEvent;
import me.quclient.EventHandler.Events.TickEvent;
import me.quclient.EventHandler.Events.WorldEvent;
import me.quclient.Utils.Timers;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class CombatManager {
	private Minecraft mc;
	
	private Timers lastAttackTimer = new Timers();
	
	private boolean inCombat; 
	
	private EntityLivingBase target;
	
	private List<EntityLivingBase> attackedEntityList = new ArrayList<>();
	private List<EntityPlayer> focusedPlayerList = new ArrayList<>();
	
	public CombatManager() {
		mc = Minecraft.getMinecraft();
		Client.getInstance().getEventManager().registerMethod(this);
	}
	
	public boolean isInCombat() {
		return inCombat;
	}
	
	@EventListener(EventPriolity.HIGHEST)
	public void onUpdate(TickEvent event) {
		if(mc.thePlayer == null) return;
		
		attackedEntityList.forEach(it -> {
			if(it.isDead || it.getHealth() == 0) {
				System.out.println("Killed");
				Client.getInstance().getEventManager().callEvent(new OnKilledEvent(it));
				attackedEntityList.remove(it);
			}
		});
		
		inCombat = false;

        if (!lastAttackTimer.hasPassed(1000)) {
            inCombat = true;
            return;
        }

        if (target != null) {
            if (mc.thePlayer.getDistanceToEntity(target) > 7 || !inCombat || target.isDead) {
                target = null;
            } else {
                inCombat = true;
            }
        }
	}
	
	@EventListener
	public void onWorld(WorldEvent e) {
		inCombat = false;
		target = null;
		attackedEntityList.clear();
		focusedPlayerList.clear();
	}
	
	@EventListener
	public void onAttack(AttackEvent e) {
		Entity entity = e.entity;
		if(entity instanceof EntityLivingBase && !isInTeam((EntityLivingBase) entity)) {
			this.target = (EntityLivingBase) entity;
			System.out.println("Attacked" + target.getDisplayName().getFormattedText());
            if (!attackedEntityList.contains(target)) {
                attackedEntityList.add(target);
            }
		}
		
		lastAttackTimer.reset();
	}
	
	private boolean isInTeam(EntityLivingBase eb) {
		if(!(eb instanceof EntityPlayer)) return false;
		if(eb == mc.thePlayer) return true;
		
		EntityPlayer e = (EntityPlayer) eb;
		
		String myname = mc.thePlayer.getDisplayName().getUnformattedText().replaceAll(mc.thePlayer.getDisplayName().getFormattedText(), "");
		String otname = mc.thePlayer.getDisplayName().getUnformattedText().replaceAll(mc.thePlayer.getDisplayName().getFormattedText(), "");
		
		if(myname == otname) return true;
		
		if(mc.thePlayer.isOnSameTeam(e)) return true;
		
		ItemStack myHead = mc.thePlayer.inventory.armorInventory[3];
		ItemArmor myItemArmor = (ItemArmor) myHead.getItem();

		ItemStack entityHead = e.inventory.armorInventory[3];
        ItemArmor entityItemArmor = (ItemArmor) myHead.getItem();
        
        if (myItemArmor.getColor(myHead) == entityItemArmor.getColor(entityHead)) {
            return true;
        }
		
		return false;
	}

}
