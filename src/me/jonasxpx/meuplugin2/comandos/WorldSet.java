package me.jonasxpx.meuplugin2.comandos;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.jonasxpx.meuplugin2.managers.WorldSetManager;

public class WorldSet implements CommandExecutor{

	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		if(!sender.isOp()){
			return true;
		}
		if(args.length >= 1){
			WorldSetManager.saveLocation(args[0], ((Player)sender).getLocation());
			sender.sendMessage("§c"+args[0]+" marcada!.");
			return true;
		}
		sender.sendMessage("§c/worldset <name>");
		return false;
	}
}
