package me.jonasxpx.meuplugin2.comandos;

import static me.jonasxpx.meuplugin2.managers.Utils.checkPerm;
import static me.jonasxpx.meuplugin2.managers.Warp.getWarp;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class WarpSet implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String arg2, String[] args) {
		if(args.length == 1){
			if(!checkPerm((Player)sender, "meuplugin.warps." + args[0])){
				sender.sendMessage("§cVocê não pode usar essa warp.");
				return true;
			}
			Location warp = getWarp(args[0]);
			if(warp == null){
				sender.sendMessage("§cWarp não encontrada");
				return true;
			}
			((Player)sender).teleport(warp);
			sender.sendMessage("§b» Teleportado para §e" + args[0].toLowerCase());
			return true;
		}
		return true;
	}
}
