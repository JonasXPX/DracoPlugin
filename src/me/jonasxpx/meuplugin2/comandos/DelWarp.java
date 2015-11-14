package me.jonasxpx.meuplugin2.comandos;

import me.jonasxpx.meuplugin2.managers.WorldSetManager;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class DelWarp implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!sender.isOp()){
			sender.sendMessage("�cVoc� n�o tem permi��o");
			return true;
		}
		if(args.length >= 1){
			if(WorldSetManager.deleteLocation(args[0]))
				sender.sendMessage("�bWarp " + args[0] + " deletada");
			else
				sender.sendMessage("�bWarp n�o existe");
			return true;
		}
		sender.sendMessage("�cUse /delwarp <warp>");
		return false;
	}

}
