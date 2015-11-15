package me.jonasxpx.meuplugin2.comandos;

import static me.jonasxpx.meuplugin2.managers.Utils.checkPerm;
import static me.jonasxpx.meuplugin2.managers.Warp.getWarp;
import me.jonasxpx.meuplugin2.managers.CallDelay;
import me.jonasxpx.meuplugin2.managers.Utils;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class WarpSet implements CommandExecutor{

	
	@Override
	public boolean onCommand(final CommandSender sender, Command cmd, String arg2, final String[] args) {
		if(args.length == 1){
			if(!checkPerm((Player)sender, "draco.warps." + args[0])){
				sender.sendMessage("�cVoc� n�o pode usar essa warp.");
				return true;
			}
			final Location warp = getWarp(args[0]);
			if(warp == null){
				sender.sendMessage("�cWarp n�o encontrada");
				return true;
			}
			new CallDelay(sender, sender.hasPermission("draco.delay") ? 0 : 3) {
				@Override
				public void run() {
					((Player)sender).teleport(warp);
					sender.sendMessage("�b� Teleportado para �e" + args[0].toLowerCase());
				}
			}.startAfter();
			return true;
		}
		sender.sendMessage("�c Use /warp <nome>");
		return true;
	}
}