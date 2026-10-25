package me.quclient.Utils;

import java.io.InputStream;
import java.util.Locale;

import org.apache.commons.io.IOUtils;

import me.quclient.Client.modules.ModMotionBlur;
import net.minecraft.client.resources.IResource;
import net.minecraft.client.resources.data.IMetadataSection;
import net.minecraft.util.ResourceLocation;

public class MotionBlurResource implements IResource {

	private static final String JSON = "{\"targets\":[\"swap\",\"previous\"],\"passes\":[{\"name\":\"phosphor\",\"intarget\":\"minecraft:main\",\"outtarget\":\"swap\",\"auxtargets\":[{\"name\":\"PrevSampler\",\"id\":\"previous\"}],\"uniforms\":[{\"name\":\"Phosphor\",\"values\":[%.2f, %.2f, %.2f]}]},{\"name\":\"blit\",\"intarget\":\"swap\",\"outtarget\":\"previous\"},{\"name\":\"blit\",\"intarget\":\"swap\",\"outtarget\":\"minecraft:main\"}]}";

    public MotionBlurResource() {
    }
	
	@Override
	public ResourceLocation getResourceLocation() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InputStream getInputStream() {
		//System.out.println("USED");
		double amount = 0.7 + ModMotionBlur.blurAmount / 100.0 * 3.0 - 0.01;
        return IOUtils.toInputStream(String.format(Locale.ENGLISH, "{\"targets\":[\"swap\",\"previous\"],\"passes\":[{\"name\":\"phosphor\",\"intarget\":\"minecraft:main\",\"outtarget\":\"swap\",\"auxtargets\":[{\"name\":\"PrevSampler\",\"id\":\"previous\"}],\"uniforms\":[{\"name\":\"Phosphor\",\"values\":[%.2f, %.2f, %.2f]}]},{\"name\":\"blit\",\"intarget\":\"swap\",\"outtarget\":\"previous\"},{\"name\":\"blit\",\"intarget\":\"swap\",\"outtarget\":\"minecraft:main\"}]}", amount, amount, amount));
	}

	@Override
	public boolean hasMetadata() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public <T extends IMetadataSection> T getMetadata(String p_110526_1_) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getResourcePackName() {
		// TODO Auto-generated method stub
		return null;
	}

}
