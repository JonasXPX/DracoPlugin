package me.jonasxpx.meuplugin2.listeners;

import static me.jonasxpx.meuplugin2.MeuPlugin.instance;

import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import fr.xephi.authme.api.API;
import fr.xephi.authme.events.LoginEvent;

public class FirstRegisterEvent implements Listener{
	/**
	 * datadatadatadatadatadatadata
	 * @param e
	 */

	@EventHandler
	public void onRegister2(LoginEvent e){
		System.out.println("\n\n\n\nasd\n\n\n");
		Location loc = API.getLastLocation(e.getPlayer());
		if(loc!=null && loc.getWorld().getName().equalsIgnoreCase("minerar")){
			instance.getServer().dispatchCommand(instance.getServer().getConsoleSender(), "warp spawn " + e.getPlayer().getName());
			System.out.println("jogador estava no /minerar \"" + e.getPlayer().getName() + "\"");
		}
		if(loc == null || loc.getWorld() == null || loc.getWorld().getName().equalsIgnoreCase("null")){
			instance.getServer().dispatchCommand(instance.getServer().getConsoleSender(), "warp inicio " + e.getPlayer().getName());
			System.out.println("jogador novato teleportado para o inicio \"" + e.getPlayer().getName() + "\"");
		}
	}

}
