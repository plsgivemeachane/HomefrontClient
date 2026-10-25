package me.quclient.Client;

import java.awt.Color;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import me.quclient.Anticheat.Anticheat;
import me.quclient.Client.modules.ModAntiDesync;
import me.quclient.Client.modules.ModAnticheat;
import me.quclient.Client.modules.ModArmorStatus;
import me.quclient.Client.modules.ModArrowPartical;
import me.quclient.Client.modules.ModAttackPartical;
import me.quclient.Client.modules.ModBlockOverlay;
import me.quclient.Client.modules.ModCPS;
import me.quclient.Client.modules.ModClickGUI;
import me.quclient.Client.modules.ModComboDisplay;
import me.quclient.Client.modules.ModFPS;
import me.quclient.Client.modules.ModFullBright;
import me.quclient.Client.modules.ModGlintColor;
import me.quclient.Client.modules.ModHUDPlayer;
import me.quclient.Client.modules.ModHitRegister;
import me.quclient.Client.modules.ModHitbox;
import me.quclient.Client.modules.ModKeyStroke;
import me.quclient.Client.modules.ModMotionBlur;
import me.quclient.Client.modules.ModNameHider;
import me.quclient.Client.modules.ModNoFOV;
import me.quclient.Client.modules.ModPerformance;
import me.quclient.Client.modules.ModPerspective;
import me.quclient.Client.modules.ModPhysicItem;
import me.quclient.Client.modules.ModPingDesyncFix;
import me.quclient.Client.modules.ModPotionStatus;
import me.quclient.Client.modules.ModReachDisplay;
import me.quclient.Client.modules.ModSpeedDisplay;
import me.quclient.Client.modules.ModSprintToggle;
import me.quclient.Client.modules.ModTitle;
import me.quclient.Client.modules.ModXYZ;
import me.quclient.EventHandler.EventManager;
import me.quclient.HUD.HUDManager;
import me.quclient.command.CommandManager;
import me.quclient.gui.SplashProgress;
import net.minecraft.client.entity.AbstractClientPlayer;

public class Client {
	
	public Client() {}
	
	public static Anticheat anticheat;
	
	public static int MAIN_COLOR = new Color(158, 228, 147).getRGB();
	public static int SECOND_COLOR = new Color(58, 79, 65).getRGB();
	public static int BACKGROUND_COLOR = new Color(61, 59, 142, 110).getRGB();
	public static int BACKGROUND_COLOR_2 = new Color(104, 131, 186).getRGB();
	
	public static String name = "HomeFrontClient";
	public static String version = "7.0.5 RELEASE";
	public static String author = "Quanvndev";
	
	private static Client instance;
	private static List<Mod> mods = new ArrayList<Mod>();
	private EventManager eventManager;
	private HUDManager hudManager;
	private ModClickGUI click;
	private CapeManager cp;
	private CombatManager cm;
	private CommandManager cmng;
	
	public static List<AbstractClientPlayer> players;
	public static Map<String, String> capes = new HashMap<>();

	public void setup() {
		
		FileManager.init();
		
		players = new ArrayList<AbstractClientPlayer>();
		//SplashProgress.setProgress(1, "Client - Initialising All the things...");
		
		System.out.println("Setting up things...");
		
		eventManager = new EventManager(getInstance());
		hudManager = HUDManager.getInstance();
		eventManager.registerMethod(hudManager);
		click = new ModClickGUI();
		cm = new CombatManager();
		cmng = new CommandManager();
		
		try {
			cp = new CapeManager();
		} catch (URISyntaxException | IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		anticheat = new Anticheat();
		
		update();
		
		//eventManager.callEvent(new RenderEvent(1));
		System.out.println("OK...");
	}
	
	public CombatManager getCm() {
		return cm;
	}
	
	public void registerMod(ModDraggable m, HUDManager api) { 
		//eventManager.registerMethod(m.getClass());
		mods.add(m);
		api.register(m);
	}
	
	public void registerMod(Mod m) {
		mods.add(m);
	}
	

	
	
	
	public void update() {
		
		
		registerMod(new ModTitle(), hudManager);
		registerMod(new ModArmorStatus(), hudManager);
		registerMod(new ModKeyStroke(), hudManager);
		registerMod(new ModFPS(), hudManager);
		registerMod(new ModXYZ(), hudManager);
		registerMod(new ModHUDPlayer(), hudManager);
		registerMod(new ModSprintToggle(), hudManager);
		registerMod(new ModCPS(), hudManager);
		registerMod(new ModReachDisplay(), hudManager);
		registerMod(new ModComboDisplay(), hudManager);
		registerMod(new ModSpeedDisplay(), hudManager);
		registerMod(new ModPotionStatus(), hudManager);
		registerMod(new ModBlockOverlay());
		registerMod(new ModNameHider());
		registerMod(new ModMotionBlur());
		registerMod(new ModPerformance());
		registerMod(new ModArrowPartical());
		registerMod(new ModBlockOverlay());
		registerMod(new ModAnticheat());
		registerMod(new ModGlintColor());
		registerMod(new ModAttackPartical());
		registerMod(new ModPhysicItem());
		registerMod(new ModNoFOV());
		registerMod(new ModFullBright());
		registerMod(new ModPerspective());
		registerMod(new ModAntiDesync());
		registerMod(new ModHitRegister());	
		registerMod(new FPSReducer());
		registerMod(new ModPingDesyncFix());
		registerMod(new ModHitbox());
		//registerMod(new ModSneakAtEdge());
		//registerMod(new ModFastPlace());
		//registerMod(new ModAutoClicker());
		
	}
	
	public static List<Mod> getMods() {
		return mods;
	}
	
	public <T> T getModule(Class<T> clazz) {
		
		for(Mod m : Client.getInstance().mods) {
			if(m.getClass() == clazz) {
				return (T) m;
			}
		}
		
		return null;
	}
	
	public EventManager getEventManager() {
		return eventManager;
	}
	
	public static Client getInstance() {
		if(instance == null) {
			instance = new Client();
			return instance;
		}
		
		return instance;
	}
	
}
