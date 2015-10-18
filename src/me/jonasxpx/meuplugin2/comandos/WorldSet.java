package me.jonasxpx.meuplugin2.comandos;

import me.jonasxpx.meuplugin2.managers.WorldSetManager;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class WorldSet implements CommandExecutor{

	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		if(args.length >= 1){
			WorldSetManager.saveLocation(args[0], ((Player)sender).getLocation());
			sender.sendMessage("§c"+args[0]+" marcada!.");
			return true;
		}
		return false;
	}
}
