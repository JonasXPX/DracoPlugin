package me.jonasxpx.meuplugin2.managers;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import me.jonasxpx.meuplugin2.MeuPlugin;

public class Utils {

	public static HashMap<Location, Long> antiSpan = new HashMap<>();
	
	public static boolean checkPerm(Player player, String perm){
		return player.hasPermission(perm);
	}
	
	public static boolean checkOnline(String player){
		if(Bukkit.getPlayer(player) == null)
			return false;
		return true;
	}

	public static boolean isInCombat(Player player){
		return MeuPlugin.getCombatTag().isInCombat(player);
	}
	
	public static boolean isBlockedSpan(Location block, boolean isBreak){
		if(!antiSpan.containsKey(block) && !isBreak){
			antiSpan.put(block, (System.currentTimeMillis() / 1000) + 30);
			return false;
		}
		if(isBreak && antiSpan.containsKey(block)){
			if(System.currentTimeMillis() / 1000 < antiSpan.get(block)){
				return true;
			}
			antiSpan.remove(block);
		}
		return false;
	}
	
	public static boolean isToFarm(Block block){
		switch(block.getType()){
		case RED_ROSE:
			return true;
		case YELLOW_FLOWER:
			return true;
		case SUGAR_CANE:
			return true;
		case NETHER_WARTS:
		{
			if(block.getData() != 1 && block.getData() != 0)
				return true;
			else return false;
		}
		default:
			return false;
			
		}
	}
	
	
}
