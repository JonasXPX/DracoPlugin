package me.jonasxpx.meuplugin2.comandos.homes;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.jonasxpx.meuplugin2.managers.Home;

public class ListHomes implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Home home = new Home((Player)sender);
		if(sender.isOp()){
			if(args.length == 0){
				home.listHomes();
				return true;
			}
			return true;
		}else{
			home.listHomes();
			return true;
		}
	}
}
