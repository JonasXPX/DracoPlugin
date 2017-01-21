package me.jonasxpx.meuplugin2.terrenos;

import static me.jonasxpx.meuplugin2.MeuPlugin.economy;

import org.bukkit.entity.Player;

public class Buy {

	
	public static void buyRegion(String rgName, double price, Player player){
		WorldGuardInformation wgi = new WorldGuardInformation(rgName, player.getWorld());
		if(!wgi.exists()){
			player.sendMessage("§cOps, parece que o terreno não existe mais. " + rgName);
			return;
		}
			
		if(wgi.hasOwner()){
			player.sendMessage("§cOps, parece que o terreno já possui um dono.");
			return;
		}
		if(economy == null){
			player.sendMessage("§cOps, o sistema de compras não está a funcionar.");
			return;
		}
		if(economy.getBalance(player) < price){
			player.sendMessage("§cOps, você não tem dinheiro para comprar.");
			return;
		}
		
		economy.withdrawPlayer(player, price);
		wgi.addOwner(player.getName());
		
		player.sendMessage("§bTerreno adquirido com sucesso!");
	}
	
}
