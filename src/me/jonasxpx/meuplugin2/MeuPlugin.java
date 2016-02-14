package me.jonasxpx.meuplugin2;

import java.io.File;

import me.jonasxpx.meuplugin2.comandos.WorldSet;
import me.jonasxpx.meuplugin2.comandos.homes.Home;
import me.jonasxpx.meuplugin2.comandos.homes.ListHomes;
import me.jonasxpx.meuplugin2.comandos.homes.SetHome;
import me.jonasxpx.meuplugin2.comandos.warps.DelWarp;
import me.jonasxpx.meuplugin2.comandos.warps.ListWarps;
import me.jonasxpx.meuplugin2.comandos.warps.SetWarp;
import me.jonasxpx.meuplugin2.comandos.warps.WarpSet;
import me.jonasxpx.meuplugin2.listeners.PlayerInteractEvents;
import me.jonasxpx.meuplugin2.managers.HomeManagerSQL;

import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;

public class MeuPlugin extends JavaPlugin{
	
	public static MeuPlugin instance;
	public static File data;
	public static HomeManagerSQL homeSql;
	
	@Override
	public void onEnable() {
		instance = this;
		data = this.getDataFolder();
		getServer().getPluginManager().registerEvents(new PlayerInteractEvents(), this);
		getCommand("worldset").setExecutor(new WorldSet());
		getCommand("warp").setExecutor(new WarpSet());
		getCommand("delwarp").setExecutor(new DelWarp());
		getCommand("setwarp").setExecutor(new SetWarp());
		getCommand("listwarps").setExecutor(new ListWarps());
		getCommand("home").setExecutor(new Home());
		getCommand("sethome").setExecutor(new SetHome());
		getCommand("listhomes").setExecutor(new ListHomes());
		homeSql = new HomeManagerSQL("localhost", "mc_4720", "root", "", 3306);
	}
	
	@Override
	public void onDisable() {
		HandlerList.unregisterAll(this);
	}
}
