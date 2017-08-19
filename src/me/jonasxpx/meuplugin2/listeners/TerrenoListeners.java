package me.jonasxpx.meuplugin2.listeners;

import java.util.List;

import org.bukkit.Material;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.jonasxpx.meuplugin2.terrenos.Buy;
import me.jonasxpx.meuplugin2.terrenos.Chest;
import me.jonasxpx.meuplugin2.terrenos.Sell;
import me.jonasxpx.meuplugin2.terrenos.WorldGuardInformation;

public class TerrenoListeners implements Listener {
	
	
	@EventHandler
	public void onBuy(PlayerInteractEvent e){
		if(e.getAction() != Action.RIGHT_CLICK_BLOCK){
			return;
		}

		if(e.getClickedBlock().getState() instanceof Sign){
			Sign sign = (Sign)e.getClickedBlock().getState();
			if(!sign.getLine(3).equalsIgnoreCase("§cClique aqui.")){
				return;
			}
			if(sign.getLine(0).trim().equalsIgnoreCase("") || sign.getLine(1).trim().equalsIgnoreCase("")){
				return;
			}
			
			String rgName = sign.getLine(0).trim();
			double price = Double.parseDouble(sign.getLine(1).trim());
			e.getPlayer().openInventory(Chest.openChest(rgName, price, e.getPlayer()));
		}
	}
	
	@EventHandler
	public void onInventoryClick(InventoryClickEvent e){
		if(e.getInventory().getName().equalsIgnoreCase("§bTerreno Info."))
		{
			e.setCancelled(true);
			if(getType(e.getCurrentItem()) == TypeClick.COMPRA){
				String[] data = getFormatedItens(e.getCurrentItem());
				if(data != null){
					Buy.buyRegion(data[0], Double.parseDouble(data[1]), (Player)e.getWhoClicked());
					e.getView().close();
				}
			} else if (getType(e.getCurrentItem()) == TypeClick.VENDA){
				String[] data = getFormatedItens(e.getCurrentItem());
				if(data != null){
					Sell.sell(data[0], Double.parseDouble(data[1]), (Player)e.getWhoClicked());
					e.getView().close();
				}
			} else if (getType(e.getCurrentItem()) == TypeClick.INFO){
				///
			}
		}
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onCreate(SignChangeEvent e){
		if(e.getLine(3).equalsIgnoreCase("&cClique Aqui")){
			if(!e.getPlayer().hasPermission("draco.createsign")){
				e.getBlock().breakNaturally();
				e.getPlayer().sendMessage("§cVocê não pode criar um placa desse tipo.");
				return;
			}
			WorldGuardInformation wgi = new WorldGuardInformation(e.getLine(0).trim(), e.getPlayer().getWorld());
			if(!wgi.exists()){
				e.getBlock().breakNaturally();
				e.getPlayer().sendMessage("§cRegion não encontrada.");
				return;
			}
			e.setLine(3, "§cClique Aqui.");
		}
	}
	
	public TypeClick getType(ItemStack i){
		if(i.hasItemMeta()){
			if(i.getItemMeta().hasDisplayName()){
				ItemMeta m = i.getItemMeta();
				if(m.getDisplayName().contains("Dono"))
					return TypeClick.INFO;
				if(m.getDisplayName().contains("Comprar"))
					return TypeClick.COMPRA;
				if(m.getDisplayName().contains("Deletar"))
					return TypeClick.VENDA;
			}
		}
		return null;
	}
	
	public String[] getFormatedItens(ItemStack compraItem){
		if(!compraItem.hasItemMeta())
			return null;
		
		ItemMeta meta = compraItem.getItemMeta();
		if(!meta.hasLore())
			return null;
		
		List<String> lore = meta.getLore();
		
		if(!lore.get(0).contains("Valor"))
			return null;
		
		String price = lore.get(0).split("§c")[1];
		String region = lore.get(1).split("§c")[1];
		
		return new String[]{region.trim(), price.trim()};
	}
	
	public enum TypeClick {
		COMPRA,
		VENDA,
		INFO;
	}
}
