package me.jonasxpx.meuplugin2.listeners;

import java.util.regex.Pattern;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import me.jonasxpx.meuplugin2.managers.CallDelay;
import me.jonasxpx.meuplugin2.managers.Utils;
import me.jonasxpx.meuplugin2.managers.Warp;

public class WarpInventoryListener implements Listener{
	
	public static final Pattern regex = Pattern.compile("(?i)§[0-9A-Fa-flkmo]");

	@EventHandler
	public void onClickEvent(InventoryClickEvent e){
		if(!e.getInventory().getTitle().equalsIgnoreCase("Teleportes")){
			return;
		}
		e.setCancelled(true);
		ItemStack item = e.getCurrentItem();
		if(item == null || !item.hasItemMeta() || !item.getItemMeta().hasDisplayName())
			return;
		String name = item.getItemMeta().getDisplayName();
		name = name.replaceAll(regex.pattern(), "");
		Location loc = Warp.getWarp(name);
		if(loc == null){
			((Player)e.getWhoClicked()).sendMessage("§cLocal inválido.");
			return;
		}
		if(CallDelay.isRegistred(e.getWhoClicked().getName())){
			((Player)e.getWhoClicked()).sendMessage("§6Você já esta aguardando.");
			return;
		}
		new CallDelay(((Player)e.getWhoClicked()), ((Player)e.getWhoClicked()).hasPermission("draco.delay") ? 0 : 3) {
			@Override
			public void run() {
				if(Utils.isInCombat((Player)e.getWhoClicked())){
					((Player)e.getWhoClicked()).sendMessage("§cNão foi possível teleportar, você está em combate.");
					return;
				}
				((Player)e.getWhoClicked()).teleport(loc);
				CallDelay.unregister(e.getWhoClicked().getName());
			}
		}.startAfter();
		e.getWhoClicked().closeInventory();
	}
}
