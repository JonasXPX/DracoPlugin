package me.jonasxpx.meuplugin2.comandos.homes;

import me.jonasxpx.meuplugin2.managers.Home;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetHome implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!(sender instanceof Player)){
			sender.sendMessage("§bVocê não é um jogador");
			return true;
		}
		me.jonasxpx.meuplugin2.managers.Home home = new Home((Player)sender);
		if(args.length == 0){
			home.setHome("default");
			return true;
		}
		home.setHome(args[0]);
		return false;
	}
	
	
}
