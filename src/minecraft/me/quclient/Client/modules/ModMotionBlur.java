package me.quclient.Client.modules;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Map;

import me.quclient.Client.Mod;
import me.quclient.EventHandler.EventListener;
import me.quclient.EventHandler.EventPriolity;
import me.quclient.EventHandler.Events.ServerPacketEvent;
import me.quclient.EventHandler.Events.TickEvent;
import me.quclient.EventHandler.Events.WorldEvent;
import me.quclient.Utils.MotionBlurResourceManager;
import me.quclient.Utils.Timers;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.resources.SimpleReloadableResourceManager;
import net.minecraft.network.play.server.S08PacketPlayerPosLook;
import net.minecraft.util.ResourceLocation;

public class ModMotionBlur extends Mod {
	
	private Map domainResourceManagers;
	public static double blurAmount = 3;
	public static boolean enabled;
	private Timers t = new Timers();
	private Method method;
	
	@Override
	public void onEnable() {
		enabled = false;
		super.onEnable();
	}
	
	@EventListener(EventPriolity.HIGHEST)
	public void onWorld(WorldEvent e) {
		enabled = false;
		System.out.println("RECV");
	}
	
	@EventListener
	public void onPacket(ServerPacketEvent e) {
		if(e.p instanceof S08PacketPlayerPosLook) {
			// Tp reset
			//enabled = false;
			//System.out.println("RECV");
		}
	}
	
	@EventListener
	public void onClientTicks(TickEvent e) {
		if(this.domainResourceManagers == null && mc.getResourceManager() != null) {
			try {
                Field[] var2 = SimpleReloadableResourceManager.class.getDeclaredFields();
                int var3 = var2.length;

                for(int var4 = 0; var4 < var3; ++var4) {
                    Field field = var2[var4];
                    if (field.getType() == Map.class) {
                        field.setAccessible(true);
                        this.domainResourceManagers = (Map)field.get(Minecraft.getMinecraft().getResourceManager());
                        System.out.println(this.domainResourceManagers.getClass().getName());
                        break;
                    }
                }
            } catch (Exception var6) {
                throw new RuntimeException(var6);
            }
		}
		
		if(!this.domainResourceManagers.containsKey("motionblur")) {
			this.domainResourceManagers.put("motionblur", new MotionBlurResourceManager());
			
			//Method method = ReflectionHelper.findMethod(EntityRenderer.class, this.mc.field_71460_t, new String[]{"loadShader", "func_175069_a"}, new Class[]{ResourceLocation.class});
        	if(method == null) {
	        	//System.out.println("Finding Class");
	        	Class clz = EntityRenderer.class;
	        	for(Method m : clz.getDeclaredMethods()) {
	        		if(m.getName().contains("loadShader")) {
	        			m.setAccessible(true);
	        			method = m;
	        		}
	        	}
        	}
        	
        	
		}
		
		
	
		if (this.mc.thePlayer != null && isEnable && t.hasPassed(500) && !enabled) {
            try {
            	if(method == null) return;
            	method.invoke(this.mc.entityRenderer, new ResourceLocation("motionblur", "motionblur"));
            	enabled = true;
            	System.out.println("Enabled : " + method.toString());
                this.mc.entityRenderer.getShaderGroup().createBindFramebuffers(mc.displayWidth, mc.displayHeight);
                
            } catch (Exception var3) {
            	throw new RuntimeException(var3);
            }
        }
		
		
	}
	
	
	
	
	

}
