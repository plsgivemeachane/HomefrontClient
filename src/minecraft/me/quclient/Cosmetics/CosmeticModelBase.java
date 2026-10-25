package me.quclient.Cosmetics;

import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderPlayer;

public class CosmeticModelBase extends ModelBase {
	
	protected final ModelBiped playerModel;

	public CosmeticModelBase(RenderPlayer player) {
		this.playerModel = player.getMainModel();
	}

	public void render(AbstractClientPlayer player, float limbSwing, float limbSwingAmount, float partialTicks,
			float ageInTicks, float netHeadYaw, float headPitch, float scale) {
		// TODO Auto-generated method stub
		super.render(player, limbSwingAmount, partialTicks, ageInTicks, netHeadYaw, headPitch, scale);
	}
	
	
	
}
