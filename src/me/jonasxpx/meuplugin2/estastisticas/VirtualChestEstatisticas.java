package me.jonasxpx.meuplugin2.estastisticas;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.jonasxpx.meuplugin2.MeuPlugin;
import me.jonasxpx.meuplugin2.estastisticas.tipos.Dead;
import me.jonasxpx.meuplugin2.estastisticas.tipos.Kills;
import me.jonasxpx.meuplugin2.estastisticas.tipos.MobKills;
import me.jonasxpx.meuplugin2.estastisticas.tipos.Walk;

public class VirtualChestEstatisticas {
	
	
	
	public static Inventory openInventory(Player p){
		ListemPlayer s = MeuPlugin.getListemByPlayer(p.getName());
		Inventory inv = Bukkit.createInventory(null, 9, "§bEstatisticas");
		for(Type type : Type.values()){
			Status status = s.getTipo(type);
			StringBuilder sb = new StringBuilder();
			sb.append("§7§m>------------------------------------<\n");
			if(status instanceof Kills){
				sb.append("§bVocê já matou §6");
				sb.append(((Kills) status).getKills());
				sb.append(" Jogador(es)");
			} else if (status instanceof Dead){
				sb.append("§bVocê morreu §6");
				sb.append(((Dead) status).getDeads());
				sb.append(" Vez(es)");
			} else if (status instanceof Walk){
				sb.append("§bVocê já andou §6");
				sb.append(((Walk) status).getWalk());
				sb.append(" Bloco(s)");
			} else if (status instanceof MobKills){
				sb.append("§bVocê já matou §6");
				sb.append(((MobKills) status).getKills());
				sb.append(" Mob(s)");
			}
			sb.append("\n§7§m>------------------------------------<");
			ItemStack item = new ItemStack(type.material, 1);
			ItemMeta meta = item.getItemMeta();
			meta.setLore(Arrays.asList(sb.toString().split("\n")));
			meta.setDisplayName("§6" + type.nome);
			item.setItemMeta(meta);
			inv.addItem(item);
		}
		return inv;
	}

}
