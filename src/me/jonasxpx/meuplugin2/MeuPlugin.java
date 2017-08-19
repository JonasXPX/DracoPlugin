package me.jonasxpx.meuplugin2;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import com.google.common.collect.Lists;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.trc202.CombatTag.CombatTag;
import com.trc202.CombatTagApi.CombatTagApi;

import me.jonasxpx.meuplugin2.comandos.Default;
import me.jonasxpx.meuplugin2.comandos.MinerarCmd;
import me.jonasxpx.meuplugin2.comandos.MobControlCommand;
import me.jonasxpx.meuplugin2.comandos.WorldSet;
import me.jonasxpx.meuplugin2.comandos.homes.Home;
import me.jonasxpx.meuplugin2.comandos.homes.ListHomes;
import me.jonasxpx.meuplugin2.comandos.homes.SetHome;
import me.jonasxpx.meuplugin2.comandos.warps.DelWarp;
import me.jonasxpx.meuplugin2.comandos.warps.ListWarps;
import me.jonasxpx.meuplugin2.comandos.warps.SetWarp;
import me.jonasxpx.meuplugin2.comandos.warps.WarpSet;
import me.jonasxpx.meuplugin2.estastisticas.ListemPlayer;
import me.jonasxpx.meuplugin2.estastisticas.PlayerManager;
import me.jonasxpx.meuplugin2.karma.Karma;
import me.jonasxpx.meuplugin2.karma.KarmaDb;
import me.jonasxpx.meuplugin2.karma.KarmaTagUpdate;
import me.jonasxpx.meuplugin2.listeners.ChatEvent;
import me.jonasxpx.meuplugin2.listeners.EstatisticasListeners;
import me.jonasxpx.meuplugin2.listeners.GlitchFix;
import me.jonasxpx.meuplugin2.listeners.KarmaListener;
import me.jonasxpx.meuplugin2.listeners.MessagesListeners;
import me.jonasxpx.meuplugin2.listeners.PlayerInteractEvents;
import me.jonasxpx.meuplugin2.listeners.TerrenoListeners;
import me.jonasxpx.meuplugin2.listeners.ToolsWere;
import me.jonasxpx.meuplugin2.managers.HomeManagerSQL;
import me.jonasxpx.meuplugin2.managers.StatusDataBase;
import me.jonasxpx.meuplugin2.mobcontrol.MobControl;
import me.jonasxpx.meuplugin2.mobcontrol.MobLocation;
import net.milkbowl.vault.economy.Economy;

public class MeuPlugin extends JavaPlugin{
	
	public static MeuPlugin instance;
	public static File data;
	public static HomeManagerSQL homeSql;
	private KarmaDb karmaDb;
	public static StatusDataBase statusDb;
	private static ArrayList<Karma> players = Lists.newArrayList();
	private static ArrayList<ListemPlayer> statusPlayers = Lists.newArrayList();
	private static LinkedHashMap<String, String> groupTags = null;
	public Map<String, Double> chatMinMoney = null;
	public Map<Messages, String> messages = null;
	public static boolean isEnabledLegendChat = false;
	public WorldGuardPlugin worldGuard = null;
	public static Economy economy;
	public static CombatTagApi cta = null;
	public int mobControlLimit = 0;
	public int mobControlDelay = 0;
	
	@Override
	public void onEnable() {
		getConfig().options().copyDefaults(true);
		saveConfig();
		instance = this;
		data = this.getDataFolder();
		getServer().getPluginManager().registerEvents(new PlayerInteractEvents(), this);
		getServer().getPluginManager().registerEvents(new KarmaListener(), this);
		getServer().getPluginManager().registerEvents(new TerrenoListeners(), this);
		getServer().getPluginManager().registerEvents(new EstatisticasListeners(), this);
		getServer().getPluginManager().registerEvents(new ToolsWere(), this);
		getServer().getPluginManager().registerEvents(new MessagesListeners(), this);
		getServer().getPluginManager().registerEvents(new GlitchFix(), this);
		getCommand("worldset").setExecutor(new WorldSet());
		getCommand("warp").setExecutor(new WarpSet());
		getCommand("delwarp").setExecutor(new DelWarp());
		getCommand("setwarp").setExecutor(new SetWarp());
		getCommand("listwarps").setExecutor(new ListWarps());
		getCommand("home").setExecutor(new Home());
		getCommand("sethome").setExecutor(new SetHome());
		getCommand("listhomes").setExecutor(new ListHomes());
		getCommand("minerar").setExecutor(new MinerarCmd());
		getCommand("dracoplugin").setExecutor(new Default());
		getCommand("mobcontrol").setExecutor(new MobControlCommand());
		if(getServer().getPluginManager().getPlugin("Legendchat") != null)
		{
			isEnabledLegendChat = true;
			getServer().getPluginManager().registerEvents(new ChatEvent(), this);
		}
		if(getServer().getPluginManager().getPlugin("CombatTag") != null){
			cta = new CombatTagApi((CombatTag) getServer().getPluginManager().getPlugin("CombatTag"));
		}
		loadConfig();
		MobControl.initialize();
		loadDataBase();
		for(Player p : this.getServer().getOnlinePlayers())
		{
			Karma karma = new Karma(p);
			getKarmaPlayers().add(karma);
			KarmaTagUpdate.updateSingle(karma);
			PlayerManager.load(p);
		}
		new KarmaTagUpdate().runTaskTimerAsynchronously(this, 0, 20 * 30);
		if(getServer().getPluginManager().getPlugin("WorldGuard") != null){
			worldGuard = (WorldGuardPlugin) getServer().getPluginManager().getPlugin("WorldGuard");
		}
		setupEconomy();
	}
	
	@Override
	public void onDisable() {
		Arrays.asList(getServer().getOnlinePlayers()).forEach(p -> {
			PlayerManager.unload(p);
		});
		HandlerList.unregisterAll(this);
	}
	
	protected void loadConfig(){
		groupTags = new LinkedHashMap<String, String>();
		getConfig().getConfigurationSection("GroupTag").getKeys(true).forEach(g ->{
			groupTags.put(g, ChatColor.translateAlternateColorCodes('&', getConfig().getString("GroupTag." + g)));
		});
		if(!getConfig().contains("Terrenos.Valores")){
			getConfig().options().header("#####");
			getConfig().addDefault("Terrenos.Valores.Compra", 5000000.0);
			getConfig().addDefault("Terrenos.Valores.Aluguel", 200000.0);
			saveConfig();
		}
		if(!getConfig().contains("mobcontrol")){
			getConfig().addDefault("mobcontrol.delay", 120);
			getConfig().addDefault("mobcontrol.limit", 5);
			saveConfig();
		}
		if(!getConfig().contains("chat")){
			getConfig().addDefault("chat.global.minmoney", 50000.0);
			saveConfig();
		}
		if(!getConfig().contains("messages")){
			getConfig().addDefault("messages.login", "");
			getConfig().addDefault("messages.logout", "");
			getConfig().addDefault("messages.deadbyplayer", "");
			saveConfig();
		}
		
		this.mobControlDelay = getConfig().getInt("mobcontrol.delay");
		this.mobControlLimit = getConfig().getInt("mobcontrol.limit");
		chatMinMoney = new HashMap<>();
		for(String key : getConfig().getConfigurationSection("chat").getKeys(true)){
			chatMinMoney.put(key, getConfig().getDouble("chat." + key + ".minmoney"));
		}
		messages = new HashMap<>();
		messages.put(Messages.LOGIN, getConfig().getString("messages." + Messages.LOGIN.configName));
		messages.put(Messages.LOGOUT, getConfig().getString("messages." + Messages.LOGOUT.configName));
		messages.put(Messages.DEADBYPLAYER, getConfig().getString("messages." + Messages.DEADBYPLAYER.configName));
		for(String s : getConfig().getStringList("mobcontrol.spawners")){
			String[] split = s.split(";");
			EntityType type = EntityType.fromName(split[0]);
			MobControl.register(new MobLocation(new Location(getServer().getWorld(split[1]),
					Double.parseDouble(split[2]),
					Double.parseDouble(split[3]),
					Double.parseDouble(split[4])),
					type));
		}
		
	}
	
	public void reload(){
		reloadConfig();
		saveConfig();
		loadConfig();
	}
	
	public void loadDataBase(){
		String ipDb = getConfig().getString("Db.Host");
		String Db = getConfig().getString("Db.DataBase");
		String user = getConfig().getString("Db.User");
		String pass = getConfig().getString("Db.Password");
		int port = getConfig().getInt("Db.Port");
		homeSql = new HomeManagerSQL(ipDb, Db, user, pass, port);
		karmaDb = new KarmaDb(ipDb, Db, user, pass, port);
		statusDb = new StatusDataBase(ipDb, Db, user, pass);
	}
	
	public static ArrayList<Karma> getKarmaPlayers(){
		return players;
	}
	public static ArrayList<ListemPlayer> getStatusPlayer(){
		return statusPlayers;
	}
	
	public static ListemPlayer getListemByPlayer(String player){
		for(ListemPlayer l : statusPlayers){
			if(l.getPlayer().getName().equalsIgnoreCase(player)){
				return l;
			}
		}
		return null;
	}
	public static LinkedHashMap<String, String> getGroupTags(){
		return groupTags;
	}
	
	public static CombatTagApi getCombatTag(){
		return cta;
	}
	
	private boolean setupEconomy()
    {
        RegisteredServiceProvider<Economy> economyProvider = getServer().getServicesManager().getRegistration(net.milkbowl.vault.economy.Economy.class);
        if (economyProvider != null) {
            economy = economyProvider.getProvider();
        }

        return (economy != null);
    }
}
