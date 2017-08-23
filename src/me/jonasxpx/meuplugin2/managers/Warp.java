package me.jonasxpx.meuplugin2.managers;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import me.jonasxpx.meuplugin2.MeuPlugin;

public class Warp {
	
	public static SimpleCache<String, String> warpCache = new SimpleCache<>();

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
			List<String> warps = MeuPlugin.instance.getConfig().getStringList("warp_itens");
			warps.add(warp);
			MeuPlugin.instance.getConfig().set("warp_itens", warps);
			MeuPlugin.instance.saveConfig();
			MeuPlugin.warpItens.add(warp);
			if(warpCache.containsKey(warp.toLowerCase())){
				StringBuilder l = new StringBuilder();
				l.append(fc.getString("world"));
				l.append(";");
				l.append(fc.getDouble("x"));
				l.append(";");
				l.append(fc.getDouble("y"));
				l.append(";");
				l.append(fc.getDouble("z"));
				l.append(";");
				l.append(fc.getString("yaw"));
				l.append(";");
				l.append(fc.getString("pitch"));
				warpCache.updateCache(warp, l.toString());
			}
		} catch (IOException e) {e.printStackTrace();}
	}
	
	
	public static Location getWarp(String name){
		FileConfiguration fc = null;
		if(!warpCache.containsKey(name.toLowerCase())){
			File file = new File(MeuPlugin.instance.getDataFolder() + "/warps/" + name.toLowerCase() + ".warp");
			if(!file.exists()){return null;}
			fc = YamlConfiguration.loadConfiguration(file);
			StringBuilder l = new StringBuilder();
			l.append(fc.getString("world"));
			l.append(";");
			l.append(fc.getDouble("x"));
			l.append(";");
			l.append(fc.getDouble("y"));
			l.append(";");
			l.append(fc.getDouble("z"));
			l.append(";");
			l.append(fc.getString("yaw"));
			l.append(";");
			l.append(fc.getString("pitch"));
			warpCache.createCache(name.toLowerCase(), l.toString());
		}
		String[] data = warpCache.getCache(name.toLowerCase()).split(";");
		return new Location(
				Bukkit.getWorld(data[0]),
				Double.parseDouble(data[1]),
				Double.parseDouble(data[2]),
				Double.parseDouble(data[3]),
				Float.parseFloat(data[4]),
				Float.parseFloat(data[5]));
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
		if(warpCache.containsKey(warp.toLowerCase())){
			warpCache.remove(warp.toLowerCase());
		}
		return true;
	}
	

}
