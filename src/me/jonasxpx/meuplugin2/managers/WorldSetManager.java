package me.jonasxpx.meuplugin2.managers;

import me.jonasxpx.meuplugin2.MeuPlugin;

import org.bukkit.Bukkit;
import org.bukkit.Location;

public class WorldSetManager {

	public static void saveLocation(String name, Location loc){
		MeuPlugin ins = MeuPlugin.instance;
		ins.getConfig().set("LocationSign." + name.toLowerCase(), loc.getWorld().getName() + ";" + loc.getX() + ";" + loc.getY() + ";" + loc.getZ());
		ins.saveConfig();
	}
	
	public static boolean containsLocation(String name){
		return MeuPlugin.instance.getConfig().contains("LocationSign." + name.toLowerCase());
	}
	
	public static Location getLocation(String name){
		MeuPlugin ins = MeuPlugin.instance;
		String[] loc = ins.getConfig().getString("LocationSign." + name.toLowerCase()).split(";");
		return new Location(Bukkit.getWorld(loc[0]), Double.parseDouble(loc[1]), Double.parseDouble(loc[2]), Double.parseDouble(loc[3]));
	}
	
	public static boolean deleteLocation(String name){
		MeuPlugin ins = MeuPlugin.instance;
		if(!ins.getConfig().contains("LocationSign." + name.toLowerCase()))
			return false;
		else{
			ins.getConfig().set("LocationSign." + name.toLowerCase(), null);
			return true;
		}
	}
}
