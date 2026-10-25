package me.quclient.Cosmetics.impl;

import org.lwjgl.opengl.GL11;

import me.quclient.Cosmetics.CosmeticBase;
import me.quclient.Cosmetics.CosmeticManager;
import me.quclient.Cosmetics.CosmeticModelBase;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderEntity;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.util.ResourceLocation;

public class CosmeticTopHat extends CosmeticBase {
	
	public CosmeticTopHat(RenderPlayer player) {
		super(player);
		modelTopHat = new ModelTopHat(player);
	}
	
	private final ModelTopHat modelTopHat;
	private static final ResourceLocation TEXTURE = new ResourceLocation("quclient/hat.png");
	

	@Override
	public void render(AbstractClientPlayer player, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
		if(CosmeticManager.shouldRenderTopHat(player)) {
			GlStateManager.pushMatrix();
			playerRenderer.bindTexture(TEXTURE);
			
			if(player.isSneaking()) {
				GL11.glTranslated(0, 0.225D, 0);
			}
			
			float[] color = CosmeticManager.getTopHatColor(player);
			GL11.glColor3f(color[0], color[1], color[2]);
			modelTopHat.render(player, limbSwingAmount, partialTicks, ageInTicks, netHeadYaw, headPitch, scale);
			GL11.glColor3f(1, 1, 1);
			GL11.glPopMatrix();
		}
	}
	
	private class ModelTopHat extends CosmeticModelBase {

		private ModelRenderer rim;
		private ModelRenderer pointy;
		
		public ModelTopHat(RenderPlayer player) {
			super(player);
			rim = new ModelRenderer(playerModel, 0, 0);
			rim.addBox(-5.5f, -9f, -5.5f, 11, 2, 11);
			pointy = new ModelRenderer(playerModel, 0, 13);
			pointy.addBox(-3.5f, -17f, -3.5f, 7, 8, 7);
		}


		@Override
		public void render(AbstractClientPlayer player, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
			
			rim.rotateAngleX = playerModel.bipedHead.rotateAngleX;
			rim.rotateAngleY = playerModel.bipedHead.rotateAngleY;
			rim.rotationPointX = 0.0f;
			rim.rotationPointY = 0.0f;
			rim.render(scale);
			
			rim.rotateAngleX = playerModel.bipedHead.rotateAngleX;
			rim.rotateAngleY = playerModel.bipedHead.rotateAngleY;
			rim.rotationPointX = 0.0f;
			rim.rotationPointY = 0.0f;
			rim.render(scale);			
			
		}
	}

}
