package me.jonasxpx.meuplugin2;

import me.jonasxpx.meuplugin2.comandos.WarpSet;
import me.jonasxpx.meuplugin2.comandos.WorldSet;
import me.jonasxpx.meuplugin2.listeners.PlayerInteractEvents;
import me.jonasxpx.meuplugin2.managers.WorldReset;

import org.bukkit.Bukkit;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;

public class MeuPlugin extends JavaPlugin{

	
	public static MeuPlugin instance;
	
	@Override
	public void onEnable() {
		instance = this;
		getServer().getPluginManager().registerEvents(new PlayerInteractEvents(), this);
		getCommand("worldset").setExecutor(new WorldSet());
		getCommand("warp").setExecutor(new WarpSet());
	}
	
	@Override
	public void onDisable() {
		HandlerList.unregisterAll(this);
		WorldReset.resetRegions(Bukkit.getWorld("Minerar"));
	}
	
}
