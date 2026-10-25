package me.quclient.Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.logging.log4j.core.jmx.Server;

import me.quclient.EventHandler.EventListener;
import me.quclient.EventHandler.Events.ServerPacketEvent;
import me.quclient.EventHandler.Events.TickEvent;
import me.quclient.Utils.ClientChatHandler;
import me.quclient.Utils.StaticsStorage;
import me.quclient.Utils.Timers;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.network.play.server.S02PacketChat;
import net.minecraft.util.ChatComponentText;

public class CapeManager {
	
	public Minecraft mc = Minecraft.getMinecraft();
	public Timers t = new Timers();
	public static List<String> goodies = new ArrayList<>();
	public boolean loaded = false;
	
	public CapeManager() throws URISyntaxException, IOException, InterruptedException {
		Client.getInstance().getEventManager().registerMethod(this);
		
		// Create a trust manager that does not validate certificate chains like the default 

		TrustManager[] trustAllCerts = new TrustManager[]{
		        new X509TrustManager() {

		            public java.security.cert.X509Certificate[] getAcceptedIssuers()
		            {
		                return null;
		            }
		            public void checkClientTrusted(java.security.cert.X509Certificate[] certs, String authType)
		            {
		                //No need to implement.
		            }
		            public void checkServerTrusted(java.security.cert.X509Certificate[] certs, String authType)
		            {
		                //No need to implement.
		            }
		        }
		};

		// Install the all-trusting trust manager
		try 
		{
		    SSLContext sc = SSLContext.getInstance("SSL");
		    sc.init(null, trustAllCerts, new java.security.SecureRandom());
		    HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
		} 
		catch (Exception e) 
		{
		    System.out.println(e);
		}
	}
	
	Map<String, String> combineListsIntoOrderedMap (List<String> keys, List<String> values) {
	    if (keys.size() != values.size())
	        //throw new IllegalArgumentException ("Cannot combine lists with dissimilar sizes");
	    	return new HashMap<>();
	    Map<String,String> map = new HashMap<String,String>();
	    for (int i=0; i<keys.size(); i++) {
	        map.put(keys.get(i), values.get(i));
	    }
	    return map;
	}
	
	@EventListener
	public void onChat(ServerPacketEvent event) {
		if(event.p instanceof S02PacketChat) {
			S02PacketChat s = (S02PacketChat)event.p;
			
			String message = s.getChatComponent().getUnformattedText();
			String name = s.getChatComponent().getUnformattedText().split(":")[0];
			for(String names : goodies) {
				if(name.contains(names)) {
					event.cancelled = true;
					mc.thePlayer.addChatMessage(new ChatComponentText("§b" + message));
				}
			}
		}
	}
	
	private void sendRequestPlayer(String message) {
		new Thread() {
			public void run() {
				if(mc.theWorld == null) return;
				try {
					System.out.println("Send Request");
					
					URL url = new URL(message);
					URLConnection uc = url.openConnection();
					BufferedReader in = new BufferedReader(new InputStreamReader(uc.getInputStream()));
					
					String inputline;
					
					while((inputline = in.readLine()) != null) {
						System.out.println(inputline);
						
						CapeManager.goodies = Arrays.asList(inputline.split(","));
					}
					
					
					in.close();
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					for(Object o : mc.theWorld.loadedEntityList) {
						if(o instanceof AbstractClientPlayer) {
							AbstractClientPlayer p = (AbstractClientPlayer)o;
							if(goodies.contains(p.getNameClear()) && !Client.players.contains(p)) {
								Client.players.add(p);
							}
						}
					}
				} catch (Exception p) {}
			}
		}.start();
	}
	
	public static void sendRequestSetCape(String message,int capes) {
		new Thread() {
			public void run() {
				try {
					System.out.println("Send Request");
					
					URL url = new URL(message + capes);
					URLConnection uc = url.openConnection();
					BufferedReader in = new BufferedReader(new InputStreamReader(uc.getInputStream()));
					
					String inputline;
					
					while((inputline = in.readLine()) != null) {
						System.out.println(inputline);
					}
					
					ClientChatHandler.sendChat("Cape SET!");
					
					
					in.close();
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}.start();
	}
	
	private void sendRequestPlayerAdding(String message) {
		new Thread() {
			public void run() {
				try {
					System.out.println("Send Request Adding");
					
					URL url = new URL(message);
					URLConnection uc = url.openConnection();
					BufferedReader in = new BufferedReader(new InputStreamReader(uc.getInputStream()));
					
					String inputline;
					
					while((inputline = in.readLine()) != null) {
						System.out.println(inputline);
					}
					
					
					in.close();
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}.start();
	}
	
	private void sendRequestPlayerCapes(String message) {
		new Thread() {
			public void run() {
				try {
					System.out.println("Send Request Capes?");
					
					URL url = new URL(message);
					URLConnection uc = url.openConnection();
					BufferedReader in = new BufferedReader(new InputStreamReader(uc.getInputStream()));
					
					String inputline;
					
					while((inputline = in.readLine()) != null) {
						System.out.println(inputline);
						
						List<String> l1 = Arrays.asList(inputline.split(","));
						List<String> l2 = CapeManager.goodies;
						
						Client.capes = combineListsIntoOrderedMap(l2,l1);
						
						System.out.println(Client.capes);
					}
					
					
					in.close();
					
					System.out.println(Client.capes.get(mc.thePlayer.getNameClear()));
					System.out.println(StaticsStorage.CAPE);
					if(Integer.valueOf(Client.capes.get(mc.thePlayer.getNameClear())) != StaticsStorage.CAPE) {
						StaticsStorage.CAPE = Integer.valueOf(Client.capes.get(mc.thePlayer.getNameClear()));
						ClientChatHandler.sendChat("Cape Desync fix!");
					}
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (NumberFormatException e) {
					e.printStackTrace();
				}
			}
		}.start();
	}

	
	@EventListener()
	public void onUpdate(TickEvent event) throws URISyntaxException, IOException, InterruptedException{
		if(mc.theWorld == null || mc.thePlayer == null) return;
		
		if(!loaded) {
			sendRequestPlayer("https://MyZoomCloneP3.quanvndev.repl.co/");
			sendRequestPlayerAdding("https://MyZoomCloneP3.quanvndev.repl.co/add/" + mc.thePlayer.getNameClear());
			//sendRequestSetCape("https://MyZoomCloneP3.quanvndev.repl.co/namecape/" + Minecraft.getMinecraft().thePlayer.getNameClear() + "/", StaticsStorage.CAPE);
			loaded = true;
			ClientChatHandler.sendChat("Loading Request!");
		}
//		} else if(!Client.players.isEmpty()) {
//			sendRequestPlayerCapes("https://MyZoomCloneP3.quanvndev.repl.co/getallcapes");
//		}
		
		
		
		
		
		if(t.hasPassed(10000)) {
			t.reset();
			sendRequestPlayer("https://MyZoomCloneP3.quanvndev.repl.co/");
			sendRequestPlayerCapes("https://MyZoomCloneP3.quanvndev.repl.co/getallcapes");
			
			
		
		}
		
		
	}

}
