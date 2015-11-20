package me.jonasxpx.meuplugin2.comandos.warps;

import me.jonasxpx.meuplugin2.managers.Warp;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetWarp implements CommandExecutor{

	
	@Override
	public boolean onCommand(CommandSender sender, Command arg1, String arg2, String[] args) {
		if(!sender.isOp() || !sender.hasPermission("draco.setwarp")){
			sender.sendMessage("§c Você não pode marcar warps");
			return true;
		}
		if(args.length >= 1){
			Warp.makeWarp(((Player)sender).getLocation(), args[0]);
			sender.sendMessage("§6" + args[0] + " Marcada!.");
			return true;
		}
		sender.sendMessage("§cUse /setwarp <nome>");
		return false;
	}
}
