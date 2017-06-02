package me.jonasxpx.meuplugin2.mobcontrol;

import java.util.List;

import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.EntityType;
import org.bukkit.scheduler.BukkitRunnable;

import com.google.common.collect.Lists;

import me.jonasxpx.meuplugin2.MeuPlugin;

public class MobControl {
	
	private static List<MobLocation> spawners = Lists.newArrayList();
	
	public static void initialize(){
		new BukkitRunnable() {
			@Override
			public void run() {
				System.out.println(spawners.size());
				for(MobLocation b : spawners)
					b.spawn(MeuPlugin.instance.mobControlLimit);
			}
		}.runTaskTimer(MeuPlugin.instance, 0, MeuPlugin.instance.mobControlDelay * 20);
	}
	
	public static void register(MobLocation mobLoc){
		spawners.add(mobLoc);
	}
	
	public static String toStaticString() {
		StringBuilder b = new StringBuilder();
		int x = 1;
		for(MobLocation loc : spawners){
			b.append(x);
			b.append(". §c");
			b.append(loc.toString());
			b.append("\n");
			x++;
		}
		return b.toString();
	}
	
	public static void delete(int arrayPos){
		spawners.remove(arrayPos - 1);
		FileConfiguration file = MeuPlugin.instance.getConfig();
		List<String> list = file.getStringList("mobcontrol.spawners");
		list.remove(arrayPos - 1);
		file.set("mobcontrol.spawners", list);
		MeuPlugin.instance.saveConfig();
	}
	public static void save(EntityType entity, Location loc){
		FileConfiguration file = MeuPlugin.instance.getConfig();
		List<String> list = file.contains("mobcontrol.spawners") ? file.getStringList("mobcontrol.spawners") : Lists.newArrayList();
		list.add(entity.getName() + ";"
				+ loc.getWorld().getName() + ";"
				+ loc.getBlockX() + ";"
				+ loc.getBlockY() + ";"
				+ loc.getBlockZ());
		file.set("mobcontrol.spawners", list);
		register(new MobLocation(loc, entity));
		MeuPlugin.instance.saveConfig();
	}

}
