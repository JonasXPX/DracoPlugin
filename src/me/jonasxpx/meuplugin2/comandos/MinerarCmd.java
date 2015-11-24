package me.jonasxpx.meuplugin2.comandos;

import me.jonasxpx.meuplugin2.managers.CallDelay;
import me.jonasxpx.meuplugin2.managers.Warp;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MinerarCmd implements CommandExecutor{

	@Override
	public boolean onCommand(final CommandSender sender, Command arg1, String label, String[] args) {
		new CallDelay(((Player)sender), 3) {
			
			@Override
			public void run() {
				((Player)sender).teleport(Warp.getWarp("minerar"));
				CallDelay.unregister(sender.getName());
			}
		}.startAfter();;
		return true;
	}
}
