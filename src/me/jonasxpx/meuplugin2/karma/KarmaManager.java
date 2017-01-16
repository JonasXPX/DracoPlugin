package me.jonasxpx.meuplugin2.karma;

import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class KarmaManager {

	public static void addPlayerEffect(Player player, PotionEffectType type, int duração, int amplificacao){
		if(player.hasPotionEffect(type)){
			return;
		}
		player.addPotionEffect(new PotionEffect(type, duração, amplificacao));
	}
}
