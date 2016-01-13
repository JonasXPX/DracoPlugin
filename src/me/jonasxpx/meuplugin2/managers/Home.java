package me.jonasxpx.meuplugin2.managers;



import static me.jonasxpx.meuplugin2.MeuPlugin.homeSql;

import org.bukkit.Bukkit;
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
				int v;
				if((v = homeSql.update(player, name, loc)) == 0){
					homeSql.create(player, name, loc);
					player.sendMessage("�6Voc� marcou uma nova posi��o: �6" + name);
				}else
					player.sendMessage("�6Voc� remarcou uma nova posi��o: �6" + name);
			}
		}, "home - " + name + " : " + player.getName()).start();
	}
	
	public void teleport(final String name){
		new Thread(new Runnable() {
			@Override
			public void run() {
				String loc;
				if((loc = homeSql.getStringLocation(player.getName(), name)) != null){
					String locArray[] = loc.split(",");
					player.sendMessage(locArray.length + "");
					player.teleport(new Location(Bukkit.getWorld(locArray[5]), Double.parseDouble(locArray[0]), Double.parseDouble(locArray[1]), Double.parseDouble(locArray[2]), Float.parseFloat(locArray[3]), Float.parseFloat(locArray[4])));
					player.sendMessage("�bVoc� foi teleportado para �6" + name.toLowerCase());
					return;
				}
				player.sendMessage("�cVoc� n�o possui um local marcado com esse nome.");
			}
		}, "home - " + name + " : " + player.getName()).start();
	}
	
	public void listHomes(){
		new Thread(new Runnable() {
			@Override
			public void run() {
				String listhome = null;
				if((listhome = homeSql.listHomes(player.getName())) != null){
					player.sendMessage(listhome);
					return;
				}
				player.sendMessage("�cVoc� n�o tem nenhuma home marcada");
			}
		}).start();
	}
	
	public void deleteHome(final String name){
		new Thread(new Runnable() {
			@Override
			public void run() {
				if(homeSql.delete(player.getName(), name))
					player.sendMessage("�bVoc� deletou uma posi��o: �6" + name);
				else
					player.sendMessage("�bN�o foi poss�vel deletar essa posi��o.");
			}
		}, "home - " + name + " : " + player.getName()).start();
	}

}