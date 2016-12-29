package me.jonasxpx.meuplugin2.listeners;

import org.bukkit.Material;
import org.bukkit.block.Sign;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import me.jonasxpx.meuplugin2.managers.WorldSetManager;

public class PlayerInteractEvents implements Listener{

	@EventHandler
	public void signInteractEvent(PlayerInteractEvent e){
		if(e.getAction() == Action.RIGHT_CLICK_BLOCK){
			if(e.getClickedBlock().getType() == Material.WALL_SIGN || e.getClickedBlock().getType() == Material.SIGN_POST){
				Sign sign = (Sign) e.getClickedBlock().getState();
				String[] line = sign.getLines();
				if(line[0].equalsIgnoreCase("§2§2")){
					if(!WorldSetManager.containsLocation(line[1].substring(2).replaceAll(" ", "_"))){
						e.getPlayer().sendMessage("§6Local não encontrado.");
						return;
					}
					e.getPlayer().teleport(WorldSetManager.getLocation(line[1].substring(2).replaceAll(" ", "_")));
					e.getPlayer().sendMessage("§6Você foi teleportado para o §b" + line[1].substring(2));
				}
			}
		}
	}
	
}
