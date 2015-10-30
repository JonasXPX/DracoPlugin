package me.jonasxpx.meuplugin2.managers;

import java.io.File;
import java.io.IOException;

import me.jonasxpx.meuplugin2.MeuPlugin;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class Warp {

	public static void makeWarp(Location loc, String warp) {
		File file = new File(MeuPlugin.instance.getDataFolder() + "/warps/" + warp + ".warp");
		file.mkdir();
		if(!file.exists()){
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		FileConfiguration fc = YamlConfiguration.loadConfiguration(file);
		fc.set("world", loc.getWorld().getName());
		fc.set("x", loc.getX());
		fc.set("y", loc.getY());
		fc.get("z", loc.getZ());
		fc.get("yaw", loc.getYaw());
		fc.get("pitch", loc.getPitch());
		try {
			fc.save(file);
		} catch (IOException e) {e.printStackTrace();		}
	}
	public static Location getWarp(String name){
		File file = new File(MeuPlugin.instance.getDataFolder() + "/warps/" + name + ".warp");
		if(!file.exists()){return null;}
		FileConfiguration fc = YamlConfiguration.loadConfiguration(file);
		return new Location(
				Bukkit.getWorld(fc.getString("world")), 
				fc.getDouble("x"),
				fc.getDouble("y"),
				fc.getDouble("z"),
				Float.parseFloat(fc.getString("yaw")),
				Float.parseFloat(fc.getString("pitch")));
	}
}
