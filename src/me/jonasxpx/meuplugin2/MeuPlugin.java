package me.jonasxpx.meuplugin2;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import com.google.common.collect.Lists;

import me.jonasxpx.meuplugin2.comandos.Default;
import me.jonasxpx.meuplugin2.comandos.MinerarCmd;
import me.jonasxpx.meuplugin2.comandos.WorldSet;
import me.jonasxpx.meuplugin2.comandos.homes.Home;
import me.jonasxpx.meuplugin2.comandos.homes.ListHomes;
import me.jonasxpx.meuplugin2.comandos.homes.SetHome;
import me.jonasxpx.meuplugin2.comandos.warps.DelWarp;
import me.jonasxpx.meuplugin2.comandos.warps.ListWarps;
import me.jonasxpx.meuplugin2.comandos.warps.SetWarp;
import me.jonasxpx.meuplugin2.comandos.warps.WarpSet;
import me.jonasxpx.meuplugin2.karma.Karma;
import me.jonasxpx.meuplugin2.karma.KarmaDb;
import me.jonasxpx.meuplugin2.karma.KarmaTagUpdate;
import me.jonasxpx.meuplugin2.listeners.KarmaChatEvent;
import me.jonasxpx.meuplugin2.listeners.KarmaListener;
import me.jonasxpx.meuplugin2.listeners.PlayerInteractEvents;
import me.jonasxpx.meuplugin2.listeners.TerrenoEvent;
import me.jonasxpx.meuplugin2.managers.HomeManagerSQL;

public class MeuPlugin extends JavaPlugin{
	
	public static MeuPlugin instance;
	public static File data;
	public static HomeManagerSQL homeSql;
	private KarmaDb karmaDb;
	private static ArrayList<Karma> players = Lists.newArrayList();
	private static LinkedHashMap<String, String> groupTags = null;
	private static HashMap<String, ItemStack> itemChest = null;
	public static boolean isEnabledLegendChat = false;
	
	@Override
	public void onEnable() {
		getConfig().options().copyDefaults(true);
		saveConfig();
		instance = this;
		data = this.getDataFolder();
		getServer().getPluginManager().registerEvents(new PlayerInteractEvents(), this);
		getServer().getPluginManager().registerEvents(new KarmaListener(), this);
		getServer().getPluginManager().registerEvents(new TerrenoEvent(), this);
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
		if(getServer().getPluginManager().getPlugin("Legendchat") != null)
		{
			isEnabledLegendChat = true;
			getServer().getPluginManager().registerEvents(new KarmaChatEvent(), this);
		}
		loadConfig();
		loadDataBase();
		configureItensChest();
		for(Player p : this.getServer().getOnlinePlayers())
		{
			Karma karma = new Karma(p);
			getKarmaPlayers().add(karma);
			KarmaTagUpdate.updateSingle(karma);
		}
		new KarmaTagUpdate().runTaskTimerAsynchronously(this, 0, 20 * 30);
	}
	
	@Override
	public void onDisable() {
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
	}
	
	private void configureItensChest(){
		itemChest = new HashMap<>();
		ItemStack buy = new ItemStack(Material.DIAMOND);
		ItemStack rent = new ItemStack(Material.SIGN);
		
		ItemMeta meta_buy = buy.getItemMeta();
		meta_buy.setDisplayName("§bComprar Terreno");
		meta_buy.setLore(Arrays.asList("§bValor:§f " + getConfig().getDouble("Terrenos.Valores.Compra")));
		buy.setItemMeta(meta_buy);
		
		ItemMeta meta_rent = rent.getItemMeta();
		meta_rent.setDisplayName("§bAlugar Terreno");
		meta_rent.setLore(Arrays.asList("§bValor:§f " + getConfig().getDouble("Terrenos.Valores.Aluguel") + " p/Dia"));
		rent.setItemMeta(meta_rent);
		
		itemChest.put("buy", buy);
		itemChest.put("rent", rent);
	}
	
	
	public static ArrayList<Karma> getKarmaPlayers(){
		return players;
	}
	public static LinkedHashMap<String, String> getGroupTags(){
		return groupTags;
	}
	public static HashMap getItensChest(){
		return itemChest;
	}
}
