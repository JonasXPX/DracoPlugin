package me.jonasxpx.meuplugin2.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class ToolsWere implements Listener {
	
	private static final String ANVIL = "draco.anviluse";
	
	@EventHandler
	public void onUse(PlayerInteractEvent e){
		if(e.isCancelled()){
			return;
		}
		
		if(!e.getPlayer().hasPermission(ANVIL)){
			e.setCancelled(true);
			e.getPlayer().sendMessage("§cOps... Você não pode usar.");
		}
	}
	
}
