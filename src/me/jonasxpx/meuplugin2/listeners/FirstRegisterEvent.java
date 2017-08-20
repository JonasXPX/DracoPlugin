package me.jonasxpx.meuplugin2.listeners;

import static me.jonasxpx.meuplugin2.MeuPlugin.instance;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

import fr.xephi.authme.events.LoginEvent;
import me.jonasxpx.meuplugin2.MeuPlugin;

public class FirstRegisterEvent implements Listener{
	/**
	 * datadatadatadatadatadatadata
	 * @param e
	 */

	@EventHandler(priority = EventPriority.LOWEST)
	public void onRegister2(LoginEvent e){
		Location loc = getLastLocation(e.getPlayer());
		if(loc != null && loc.getWorld().getName().equalsIgnoreCase("minerar")){
			instance.getServer().dispatchCommand(instance.getServer().getConsoleSender(), "warp spawn " + e.getPlayer().getName());
		}
		if(loc == null){
			instance.getServer().dispatchCommand(instance.getServer().getConsoleSender(), "warp inicio " + e.getPlayer().getName());
		}
	}
	
	private Location getLastLocation(Player player){
		File file = new File(MeuPlugin.ESS_FOLDER, player.getName().toLowerCase() + ".yml");
		System.out.println(file.exists() + " - " + file.getAbsolutePath());
		FileConfiguration data = YamlConfiguration.loadConfiguration(file);
		Location loc = null;
		if(data.contains("logoutlocation")) {
			loc = new Location(Bukkit.getWorld(data.getString("logoutlocation.world")),
					data.getDouble("logoutlocation.x"),
					data.getDouble("logoutlocation.y"),
					data.getDouble("logoutlocation.z"),
					Float.parseFloat(String.valueOf(data.getDouble("logoutlocation.yaw"))),
					Float.parseFloat(String.valueOf(data.getDouble("logoutlocation.pitch"))));
		}
		return loc;
	}

}
