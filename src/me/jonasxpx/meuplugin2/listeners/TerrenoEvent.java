package me.jonasxpx.meuplugin2.listeners;

import org.bukkit.Material;
import org.bukkit.block.Sign;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import me.jonasxpx.meuplugin2.terrenos.TerrenoChest;

public class TerrenoEvent implements Listener{
	
	
	@EventHandler
	public void onSignClick(PlayerInteractEvent e){
		if(e.getAction() != Action.RIGHT_CLICK_BLOCK){
			return;
		}
		
		if(e.getClickedBlock().getType() != Material.SIGN_POST){
			System.out.println(e.getClickedBlock().getType().name());
			return;
		}
		
		Sign s = (Sign)e.getClickedBlock().getState();
		System.out.println("Debug TerrenoEvent");
		if(s.getLine(0).equalsIgnoreCase("§f§lTerreno")){
			TerrenoChest.openInven(e.getPlayer());
		}
	}

	
	@EventHandler
	public void onInventoryClick(InventoryClickEvent e){
		if(!e.getInventory().getName().equalsIgnoreCase("Terreno")){
			return;
		}
		e.setCancelled(true);
	}
}
