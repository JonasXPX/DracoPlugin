package me.jonasxpx.meuplugin2.comandos.homes;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.jonasxpx.meuplugin2.managers.CallDelay;

public class Home implements CommandExecutor{

	@Override
	public boolean onCommand(final CommandSender sender, Command cmd, String label, final String[] args) {
		if(!(sender instanceof Player)){
			sender.sendMessage("§bJogador invalido!");
			return true;
		}
		final me.jonasxpx.meuplugin2.managers.Home home = new me.jonasxpx.meuplugin2.managers.Home((Player)sender);
		if(args.length == 0){
			if(CallDelay.isRegistred(sender.getName())){
				sender.sendMessage("§6Você já esta aguardando.");
				return true;
			}
			new CallDelay(sender, sender.hasPermission("draco.delay") ? 0 : 3) {
				@Override
				public void run() {
					home.teleport("default");
					CallDelay.unregister(sender.getName());
				}
			}.startAfter();
			return true;
		}
		if(CallDelay.isRegistred(sender.getName())){
			sender.sendMessage("§6Você já esta aguardando.");
			return true;
		}
		new CallDelay(sender, sender.hasPermission("draco.delay") ? 0 : 3) {
			@Override
			public void run() {
				home.teleport(args[0]);
				CallDelay.unregister(sender.getName());
			}
		}.startAfter();
		return false;
	}

}
