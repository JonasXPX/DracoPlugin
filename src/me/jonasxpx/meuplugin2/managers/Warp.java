package me.jonasxpx.meuplugin2.managers;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import me.jonasxpx.meuplugin2.MeuPlugin;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class Warp {

	public static void makeWarp(Location loc, String warp) {
		File file = new File(MeuPlugin.instance.getDataFolder() + "/warps/" + warp.toLowerCase() + ".warp");
		file.getParentFile().mkdir();
		try {
			if(!file.exists()){
				file.createNewFile();
			}
			FileConfiguration fc = YamlConfiguration.loadConfiguration(file);
			fc.set("world", loc.getWorld().getName());
			fc.set("x", loc.getX());
			fc.set("y", loc.getY());
			fc.set("z", loc.getZ());
			fc.set("yaw", loc.getYaw());
			fc.set("pitch", loc.getPitch());
			fc.save(file);
		} catch (IOException e) {e.printStackTrace();}
	}
	public static Location getWarp(String name){
		File file = new File(MeuPlugin.instance.getDataFolder() + "/warps/" + name.toLowerCase() + ".warp");
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
	public static Collection<String> getWarps(){
		Collection<String> list = new ArrayList<String>();
		File file = new File(MeuPlugin.instance.getDataFolder() + "/warps/");
		for(String st : file.list()){
			if(st.matches(".*.warp$"))
			list.add(st.replace(".warp", ""));
		}
		return list;
	}
	
	public static boolean delWarp(String warp){
		File file = new File(MeuPlugin.instance.getDataFolder() + "/warps/" + warp.toLowerCase() + ".warp");
		if(!file.exists()){
			return false;
		}
		file.delete();
		return true;
	}
	

}
