package me.jonasxpx.meuplugin2.listeners;

import org.bukkit.Material;
import org.bukkit.entity.EnderDragon;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.meta.ItemMeta;

import br.com.devpaulo.legendchat.api.events.ChatMessageEvent;
import me.jonasxpx.meuplugin2.MeuPlugin;
import me.jonasxpx.meuplugin2.estastisticas.ListemPlayer;
import me.jonasxpx.meuplugin2.estastisticas.PlayerManager;
import me.jonasxpx.meuplugin2.estastisticas.Status;
import me.jonasxpx.meuplugin2.estastisticas.Type;
import me.jonasxpx.meuplugin2.estastisticas.VirtualChestEstatisticas;
import me.jonasxpx.meuplugin2.managers.Utils;

public class EstatisticasListeners implements Listener{
	
	@EventHandler
	public void eventDeadAndKill(PlayerDeathEvent e){
		ListemPlayer status = MeuPlugin.getListemByPlayer(e.getEntity().getName());
		Status dead = status.getTipo(Type.DEAD);
		dead.getValue().increase();
		if(e.getEntity().getKiller() != null){
			status = MeuPlugin.getListemByPlayer(e.getEntity().getKiller().getName());
			Status kill = status.getTipo(Type.KILLS);
			kill.getValue().increase();
		}
	}
	
	@EventHandler
	public void onLogin(PlayerJoinEvent e){
		PlayerManager.load(e.getPlayer());
	}
	
	@EventHandler
	public void onQuit(PlayerQuitEvent e){
		PlayerManager.unload(e.getPlayer());
	}
	
	@EventHandler
	public void onMobKills(EntityDeathEvent e){
		if(e.getEntity().getKiller() == null){
			return;
		}
		ListemPlayer status = MeuPlugin.getListemByPlayer(e.getEntity().getKiller().getName());
		status.getTipo(Type.MOBKILLS).getValue().increase();
		if(e.getEntity() instanceof EnderDragon){
			status.getTipo(Type.DRACO).getValue().increase();
		}
	}
	
	@EventHandler
	public void onPlayerMove(PlayerMoveEvent e){
		if(e.getFrom().getBlockX() != e.getTo().getBlockX() || e.getFrom().getBlockZ() != e.getTo().getBlockZ()){
			ListemPlayer status = MeuPlugin.getListemByPlayer(e.getPlayer().getName());
			status.getTipo(Type.WALK).getValue().increase();
		}
	}
	
	@EventHandler
	public void onInteract(PlayerInteractEvent e){
		if(e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK){
			if(e.getPlayer().getItemInHand().getType() == Material.BOOK){
				if(e.getPlayer().getItemInHand().hasItemMeta()){
					ItemMeta m = e.getPlayer().getItemInHand().getItemMeta();
					if(m.hasDisplayName() && m.getDisplayName().equalsIgnoreCase("Estatisticas")){
						e.setCancelled(true);
						e.getPlayer().openInventory(VirtualChestEstatisticas.openInventory(e.getPlayer()));
					}
				}
			}
		}
	}
	
	@EventHandler
	public void inventoryClickEvent(InventoryClickEvent e){
		if(e.getInventory().getName().equalsIgnoreCase("§bEstatisticas")){
			e.setCancelled(true);
		}
	}
	
	@EventHandler(priority = EventPriority.LOWEST)
	public void blockBreakEvent(BlockBreakEvent e){
		if(e.isCancelled()){
			return;
		}
		if(Utils.isBlockedSpan(e.getBlock().getLocation(), true)){
			return;
		}
		if(Utils.isToFarm(e.getBlock())){
			ListemPlayer lp = MeuPlugin.getListemByPlayer(e.getPlayer().getName());
			lp.getTipo(Type.FARM).getValue().increase();
		}
	}
	
	@EventHandler(priority = EventPriority.LOWEST)
	public void blockPlaceEvent(BlockPlaceEvent e){
		if(!e.isCancelled())
			Utils.isBlockedSpan(e.getBlock().getLocation(), false);
	}
	
	@EventHandler
	public void chatManager(ChatMessageEvent e){

	}
}
