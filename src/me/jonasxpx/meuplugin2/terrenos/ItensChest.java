package me.jonasxpx.meuplugin2.terrenos;

import org.bukkit.inventory.ItemStack;

import me.jonasxpx.meuplugin2.MeuPlugin;

public enum ItensChest {
	
	
	
	BUY((ItemStack)MeuPlugin.getItensChest().get("buy") ),
	RENT((ItemStack)MeuPlugin.getItensChest().get("rent"));
	
	private ItemStack item;
	
	private ItensChest(ItemStack t) {
		this.item = t;
	
	}
	
	public ItemStack getItem(){
		return item;
	}
	

}
