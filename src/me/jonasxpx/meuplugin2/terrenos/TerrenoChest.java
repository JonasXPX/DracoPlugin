package me.jonasxpx.meuplugin2.terrenos;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class TerrenoChest {

	
	public static void openInven(Player player){
		Inventory inv = Bukkit.createInventory(null, 54, "Terreno");
		inv.setItem(3, ItensChest.BUY.getItem());
		inv.setItem(5, ItensChest.RENT.getItem());
		player.openInventory(inv);
	}
	
	public static void alugar(Player player){
		
	}
	
	
}
