package me.jonasxpx.meuplugin2.comandos;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.jonasxpx.meuplugin2.managers.CallDelay;
import me.jonasxpx.meuplugin2.managers.Warp;

public class MinerarCmd implements CommandExecutor{

	@Override
	public boolean onCommand(final CommandSender sender, Command arg1, String label, String[] args) {
		if(CallDelay.isRegistred(sender.getName())){
			sender.sendMessage("�6Voc� j� esta aguardando.");
			return true;
		}
		new CallDelay(((Player)sender), 3) {
			@Override
			public void run() {
				((Player)sender).teleport(Warp.getWarp("minerar"));
				CallDelay.unregister(sender.getName());
			}
		}.startAfter();
		return true;
	}
}
