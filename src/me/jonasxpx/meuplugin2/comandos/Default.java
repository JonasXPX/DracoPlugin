package me.jonasxpx.meuplugin2.comandos;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import me.jonasxpx.meuplugin2.MeuPlugin;

public class Default implements CommandExecutor{

	
	@Override
	public boolean onCommand(CommandSender sender, Command arg1, String arg2, String[] args) {
		if(!sender.isOp() || !sender.hasPermission("dracoplugin.adm")){
			sender.sendMessage("§cVocê não possui permissão para usar este comando.");
			return true;
		}
		if(args.length == 1){
			if(args[0].equalsIgnoreCase("reload")){
				MeuPlugin.instance.reload();
				sender.sendMessage("§cDracoPlugin recarregado.");
				return true;
			}
		}
		return false;
	}
}
