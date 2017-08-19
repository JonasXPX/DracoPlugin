package me.jonasxpx.meuplugin2.listeners;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.vehicle.VehicleEnterEvent;

public class GlitchFix implements Listener{
	
	private static final String[] blocked = {"?", "help", "ess", "essentials"};
	
	@EventHandler
	public void placeBlock(BlockPlaceEvent e){
		if(e.getBlock().getType() == Material.JUKEBOX){
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void placeVeicle(VehicleEnterEvent e){
		e.setCancelled(true);
	}
	
	@EventHandler
	public void interact(PlayerInteractEvent e){
		if(e.getClickedBlock() == null)
			return;
		if(e.getClickedBlock().getType() == Material.ANVIL){
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void command(PlayerCommandPreprocessEvent e){
		if(e.getPlayer().isOp()){
			return;
		}
		
		for(String i : blocked){
			if(e.getMessage().replaceFirst("/","").equalsIgnoreCase(i)){
				e.setCancelled(true);
				break;
			}
		
		}
	}
}
