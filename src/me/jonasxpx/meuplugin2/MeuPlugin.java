package me.jonasxpx.meuplugin2;

import java.io.File;
import java.util.ArrayList;

import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;

import com.google.common.collect.Lists;

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
import me.jonasxpx.meuplugin2.listeners.KarmaListener;
import me.jonasxpx.meuplugin2.listeners.PlayerInteractEvents;
import me.jonasxpx.meuplugin2.managers.HomeManagerSQL;
import me.jonasxpx.meuplugin2.managers.KarmaTagUpdate;

public class MeuPlugin extends JavaPlugin{
	
	public static MeuPlugin instance;
	public static File data;
	public static HomeManagerSQL homeSql;
	private static KarmaDb karmaDb;
	protected static ArrayList<Karma> players = Lists.newArrayList();
	
	@Override
	public void onEnable() {
		getConfig().options().copyDefaults(true);
		saveConfig();
		instance = this;
		data = this.getDataFolder();
		getServer().getPluginManager().registerEvents(new PlayerInteractEvents(), this);
		getServer().getPluginManager().registerEvents(new KarmaListener(), this);
		getCommand("worldset").setExecutor(new WorldSet());
		getCommand("warp").setExecutor(new WarpSet());
		getCommand("delwarp").setExecutor(new DelWarp());
		getCommand("setwarp").setExecutor(new SetWarp());
		getCommand("listwarps").setExecutor(new ListWarps());
		getCommand("home").setExecutor(new Home());
		getCommand("sethome").setExecutor(new SetHome());
		getCommand("listhomes").setExecutor(new ListHomes());
		getCommand("minerar").setExecutor(new MinerarCmd());
		loadConfig();
		new KarmaTagUpdate().runTaskTimer(this, 0, 20 * 30);
	}
	
	@Override
	public void onDisable() {
		HandlerList.unregisterAll(this);
	}
	
	protected void loadConfig(){
		String ipDb = getConfig().getString("Db.Host");
		String Db = getConfig().getString("Db.DataBase");
		String user = getConfig().getString("Db.User");
		String pass = getConfig().getString("Db.Password");
		int port = getConfig().getInt("Db.Port");
		homeSql = new HomeManagerSQL(ipDb, Db, user, pass, port);
		karmaDb = new KarmaDb(ipDb, Db, user, pass, port);
	}
	
	public static ArrayList<Karma> getKarmaPlayers(){
		return players;
	}
}
