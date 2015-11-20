package me.jonasxpx.meuplugin2.managers;

import me.jonasxpx.meuplugin2.MeuPlugin;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public abstract class CallDelay extends BukkitRunnable{
	
	private int seconds;
	
	public CallDelay(Player player, int seconds) {
		this.seconds = seconds;
		player.sendMessage("§6Aguarde "+seconds+" segundos.");
	}
	public CallDelay(CommandSender sender, int seconds) {
		this.seconds = seconds;sender.sendMessage("§6Aguarde "+seconds+" segundos.");
	}
	public CallDelay() {
		
	}
	public void startAfter(){
		try {
			this.runTaskLater(MeuPlugin.instance, 20 * seconds);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
