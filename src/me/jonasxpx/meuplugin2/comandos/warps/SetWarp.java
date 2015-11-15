package me.jonasxpx.meuplugin2.comandos.warps;

import me.jonasxpx.meuplugin2.managers.Warp;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class SetWarp implements CommandExecutor{

	
	@Override
	public boolean onCommand(CommandSender sender, Command arg1, String arg2, String[] args) {
		if(!sender.isOp() || !sender.hasPermission("draco.setwarp")){
			sender.sendMessage("§c Você não pode marcar warps");
			return true;
		}
		if(Warp.delWarp(args[0]))
			sender.sendMessage("§bWarp "+ args[0] +" deletada");
		else
		 sender.sendMessage("§cWarp não encontrada");
		return false;
	}
}
