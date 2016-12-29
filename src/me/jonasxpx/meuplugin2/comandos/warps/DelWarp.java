package me.jonasxpx.meuplugin2.comandos.warps;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import me.jonasxpx.meuplugin2.managers.Warp;

public class DelWarp implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!sender.isOp()){
			sender.sendMessage("§cVocê não tem permição");
			return true;
		}
		if(args.length >= 1){
			if(Warp.delWarp(args[0]))
				sender.sendMessage("§bWarp " + args[0] + " deletada");
			else
				sender.sendMessage("§bWarp não existe");
			return true;
		}
		sender.sendMessage("§cUse /delwarp <warp>");
		return false;
	}
}
