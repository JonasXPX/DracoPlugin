package me.jonasxpx.meuplugin2.terrenos;

import org.bukkit.block.Sign;
import org.bukkit.entity.Player;

public class CreateTerreno {

	public static void createAlugado(Player player, Sign sign){
		TerrenoAlugado t = new TerrenoAlugado(player.getName().toLowerCase(), sign);
	}
	
	
	private static String getDaysFromSign(String s){
		s = s.substring(s.indexOf(": ")+1, s.indexOf("Dias"));
		return s.trim();
	}
	
}
