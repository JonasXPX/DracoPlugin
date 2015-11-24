package me.jonasxpx.meuplugin2.managers;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class Utils {

	public static boolean checkPerm(Player player, String perm){
		return player.hasPermission(perm);
	}
	
	public static boolean checkOnline(String player){
		if(Bukkit.getPlayer(player) == null)
			return false;
		return true;
	}

	
	
}
