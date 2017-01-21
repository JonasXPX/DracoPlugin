package me.jonasxpx.meuplugin2.terrenos;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Chest {

	
	public static Inventory openChest(String region, double price, Player player){
		Inventory inv = Bukkit.createInventory(null, 9, "§bTerreno Info.");
		ItemStack[] itens = getFormatedItens(region, player.getWorld(), price);
		WorldGuardInformation rgInfo = new WorldGuardInformation(region, player.getWorld());
		if(rgInfo.hasOwner()){
			inv.setItem(3, itens[0]);
			if(rgInfo.isOwner(player.getName())){
				inv.setItem(5, itens[2]);
			}
		} else {
			inv.setItem(3, itens[1]);
		}
		return inv;
	}

	/**
	 * 
	 * @param region
	 * @param world
	 * @return
	 *  [0] = Dono do terreno;
	 *  [1] = Compra do terreno;
	 *  [2] = Venda do terreno;
	 */
	public static ItemStack[] getFormatedItens(String region, World world, double price){
		WorldGuardInformation rgInfo = new WorldGuardInformation(region, world);
		ItemMeta meta;
		
		ItemStack dono = new ItemStack(Material.WOOL, 1, (short)14);
		meta = dono.getItemMeta();
		meta.setDisplayName("§bDono(s): §6" + rgInfo.getOwner());
		dono.setItemMeta(meta);
		
		ItemStack comprar = new ItemStack(Material.WOOL, 1, (short)4);
		meta = comprar.getItemMeta();
		meta.setDisplayName("§bComprar terreno");
		meta.setLore(Arrays.asList("§6Valor: §c" + price, "§6Nome: §c" + region));
		comprar.setItemMeta(meta);

		ItemStack vender = new ItemStack(Material.WOOL, 1, (short)15);
		meta = vender.getItemMeta();
		meta.setDisplayName("§cDeletar terreno");
		meta.setLore(Arrays.asList("§6Valor: §c" + price, "§6Nome: §c" + region,"§bQuando deletado, é retornado apenas §c50% §bdo valor do terreno."));
		vender.setItemMeta(meta);
		
		ItemStack[] itens = {dono, comprar, vender};
		return itens;
	}
	
}
