package me.jonasxpx.meuplugin2.comandos.warps;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.util.ChatPaginator;
import org.bukkit.util.ChatPaginator.ChatPage;

import me.jonasxpx.meuplugin2.MeuPlugin;
import me.jonasxpx.meuplugin2.listeners.WarpInventoryListener;
import me.jonasxpx.meuplugin2.managers.StringUtils;
import me.jonasxpx.meuplugin2.managers.Warp;

public class ListWarps implements CommandExecutor{

	
	@Override
	public boolean onCommand(CommandSender sender, Command arg1, String arg2, String[] args) {
		if(args.length >= 1 && args[0].equalsIgnoreCase("list")){
			StringBuilder sb = new StringBuilder();
			sb.append("§6Todas as Warps: ");
			Warp.getWarps().forEach(warps -> {
				if(sender.hasPermission("draco.warps." + warps))
					sb.append("§f" + warps + "§6, ");
			});
			sb.append(".");
			if(args.length == 1){
				ChatPage cp = ChatPaginator.paginate(sb.toString(), 1);
				Arrays.asList(cp.getLines()).forEach(lines -> sender.sendMessage(lines));
				sender.sendMessage("§6Página 1 de " + cp.getTotalPages());
				return true;
			}
			try{
				ChatPage cp = ChatPaginator.paginate(sb.toString(), Integer.parseInt(args[1]));
				 sender.sendMessage(StringUtils.parceString(cp.getLines()) + "\n§6Página " + args[1] + " de " + cp.getTotalPages());
			}catch(NumberFormatException e){}
			return true;
		}
		if(!(sender instanceof Player)){
			return true;
		}
		Player p = (Player) sender;
		p.openInventory(getWarpInventory(p));
		return true;
	}
	
		
	private Inventory getWarpInventory(Player player){
		List<String> itens = MeuPlugin.warpItens;
		List<ItemStack> temp = new ArrayList<>();
		ItemStack item;
		for(String s : itens){
			String[] data = s.split(";");
			String warp_name = ChatColor.translateAlternateColorCodes('&', data[0]);
			String perm = warp_name.replaceAll(WarpInventoryListener.regex.pattern(), "");
			if(!player.hasPermission("draco.warps." + perm)){
				continue;
			}
			int warp_item = data.length < 2 ? Material.COMPASS.getId() : Integer.parseInt(data[1]);
			item = new ItemStack(warp_item, 1);
			ItemMeta meta = item.getItemMeta();
			meta.setDisplayName(warp_name);
			item.setItemMeta(meta);
			temp.add(item);
		}
		int size = round(temp.size());
		Inventory inventory = Bukkit.createInventory(null, size > 54 ? 54 : size, "Teleportes");
		temp.forEach(i -> inventory.addItem(i));
		return inventory;
	}
		
	
	public static int round(int n){
	    return (n%9==0) ? n : round(++n);
	}
	
}
