package me.jonasxpx.meuplugin2.managers;



import static me.jonasxpx.meuplugin2.MeuPlugin.homeSql;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public class Home {
	
	private Player player;
	
	public Home(Player player){
		this.player = player;
	}

	public void setHome(final String name){
		final Location loc = player.getLocation();
		new Thread(new Runnable() {
			@Override
			public void run() {
				if(homeSql.update(player, name, loc) == -1){
					homeSql.create(player, name, loc);
					player.sendMessage("§bVocê marcou uma nova posição");
				}else
					player.sendMessage("§bVocê remarcou uma nova posição");
			}
		}, "home - " + name + " : " + player.getName()).start();
	}
	public void deleteHome(final String name){
		new Thread(new Runnable() {
			@Override
			public void run() {
				if(homeSql.delete(player.getName(), name))
					player.sendMessage("§bVocê deletou uma posição: §6" + name);
				else
					player.sendMessage("§bNão foi possível deletar essa posição.");
			}
		}).start();
	}
}
