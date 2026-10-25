package me.quclient.Utils;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import net.minecraft.client.resources.IResource;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.util.ResourceLocation;

public class MotionBlurResourceManager implements IResourceManager {

	public MotionBlurResourceManager() {
		
	}

	@Override
	public Set<String> getResourceDomains() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IResource getResource(ResourceLocation location) throws IOException {
		// TODO Auto-generated method stub
		return new MotionBlurResource();
	}

	@Override
	public List<IResource> getAllResources(ResourceLocation location) throws IOException {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
}
