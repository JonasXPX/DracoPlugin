package me.jonasxpx.meuplugin2.estastisticas;

import org.bukkit.entity.Player;

import me.jonasxpx.meuplugin2.MeuPlugin;

public class PlayerManager {

	public static void load(Player player){
		ListemPlayer listem = new ListemPlayer(player);
		MeuPlugin.getStatusPlayer().add(listem);
		MeuPlugin.getStatusPlayer().forEach(v -> System.out.println(v.getPlayer().getName()));
	}
	
	public static void unload(Player player){
		ListemPlayer toRemove = null;
		for(ListemPlayer l : MeuPlugin.getStatusPlayer()){
			if(l.getPlayer().getName().equalsIgnoreCase(player.getName())){
				toRemove = l;
			}
		}
		if(toRemove != null){
			toRemove.saveData();
			MeuPlugin.getStatusPlayer().remove(toRemove);
		}
	}
	
}
