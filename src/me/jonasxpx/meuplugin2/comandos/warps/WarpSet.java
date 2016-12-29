package me.jonasxpx.meuplugin2.comandos.warps;

import static me.jonasxpx.meuplugin2.managers.Utils.checkPerm;
import static me.jonasxpx.meuplugin2.managers.Warp.getWarp;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.jonasxpx.meuplugin2.managers.CallDelay;

public class WarpSet implements CommandExecutor{

	
	@Override
	public boolean onCommand(final CommandSender sender, Command cmd, String arg2, final String[] args) {
		if(args.length == 1){
			if(!checkPerm((Player)sender, "draco.warps." + args[0])){
				sender.sendMessage("§cVocê não pode usar essa warp.");
				return true;
			}
			final Location warp = getWarp(args[0]);
			if(warp == null){
				sender.sendMessage("§cWarp não encontrada");
				return true;
			}
			if(CallDelay.isRegistred(sender.getName())){
				sender.sendMessage("§6Você já esta aguardando.");
				return true;
			}
			new CallDelay(sender, sender.hasPermission("draco.delay") ? 0 : 3) {
				@Override
				public void run() {
					((Player)sender).teleport(warp);
					sender.sendMessage("§b» Teleportado para §e" + args[0].toLowerCase());
					CallDelay.unregister(sender.getName());
				}
			}.startAfter();
 			return true;
		}
		sender.sendMessage("§c Use /warp <nome>");
		return true;
	}
}
