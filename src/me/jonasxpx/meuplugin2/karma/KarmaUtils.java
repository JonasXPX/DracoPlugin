package me.jonasxpx.meuplugin2.karma;

import static me.jonasxpx.meuplugin2.MeuPlugin.getKarmaPlayers;

import org.bukkit.entity.Player;

public class KarmaUtils {
		
		public static Karma getKarmaByPlayer(Player player){
			for(Karma k : getKarmaPlayers()){
				if(k.getPlayer() == player){
					return k;
				}
			}
			return null;
		}
}
