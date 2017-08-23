package me.jonasxpx.meuplugin2.comandos;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.jonasxpx.meuplugin2.managers.CallDelay;
import me.jonasxpx.meuplugin2.managers.Utils;
import me.jonasxpx.meuplugin2.managers.Warp;

public class MinerarCmd implements CommandExecutor{

	@Override
	public boolean onCommand(final CommandSender sender, Command arg1, String label, String[] args) {
		if(CallDelay.isRegistred(sender.getName())){
			sender.sendMessage("§6Você já esta aguardando.");
			return true;
		}
		new CallDelay(((Player)sender), sender.hasPermission("draco.delay") ? 0 : 3) {
			@Override
			public void run() {
				if(Utils.isInCombat((Player)sender)){
					sender.sendMessage("§cNão foi possível teleportar, você está em combate.");
					return;
				}
				sender.sendMessage(
						  "§c---------------------------------------------\n"
						+ "§4§lAtenção: §cEste mundo reseta ao reiniciar\n"
						+ "§4Qualquer construção aqui será perdida após reiniciar\n"
						+ "§c---------------------------------------------");
				((Player)sender).teleport(Warp.getWarp("minerar"));
				CallDelay.unregister(sender.getName());
			}
		}.startAfter();
		return true;
	}
}
