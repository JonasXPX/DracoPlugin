package me.jonasxpx.meuplugin2.managers;

import java.io.File;

import javax.annotation.Nullable;

import me.jonasxpx.meuplugin2.MeuPlugin;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class Home {
	
	private Player player;
	
	public Home(Player player){
		this.player = player;
	}

	@Nullable
	public void setHome(String name){
		File home = new File(MeuPlugin.data + "/home/" + player.getName().toLowerCase() + ".yml");
		FileConfiguration fc = YamlConfiguration.loadConfiguration(home);
	}
}
