package me.jonasxpx.meuplugin2.terrenos;

import static me.jonasxpx.meuplugin2.MeuPlugin.economy;

import org.bukkit.entity.Player;

public class Sell {

	public static void sell(String rgName, double price, Player player){
		WorldGuardInformation wgi = new WorldGuardInformation(rgName, player.getWorld());
		if(!wgi.exists()){
			player.sendMessage("§cOps, parece que o terreno não existe mais. " + rgName);
			return;
		}
			
		if(!wgi.hasOwner()){
			player.sendMessage("§cOps, parece que o terreno não possui um dono.");
			return;
		}
		if(!wgi.isOwner(player.getName())){
			player.sendMessage("§cVocê não é o dono do terreno.");
			return;
		}
		if(economy == null){
			player.sendMessage("§cOps, o sistema de compras não está a funcionar.");
			return;
		}
		
		price = price / 2;
		wgi.removeOwner(player.getName());
		economy.depositPlayer(player, price);
		player.sendMessage("§bTerreno deletado, você recebeu 50% do valor do terreno devolta.");
	}
}
