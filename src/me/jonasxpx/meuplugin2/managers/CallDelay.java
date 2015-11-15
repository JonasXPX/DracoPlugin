package me.jonasxpx.meuplugin2.managers;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public abstract class CallDelay implements Runnable{
	
	private int seconds;
	
	public CallDelay(Player player, int seconds) {
		this.seconds = seconds;
		player.sendMessage("§6Aguarde 3 segundos.");
	}
	public CallDelay(CommandSender sender, int seconds) {
		this.seconds = seconds;sender.sendMessage("§6Aguarde 3 segundos.");
	}
	public CallDelay() {
		
	}
	public void startAfter(){
		try {
			Thread.sleep(seconds * 1000);
			new Thread(this).start();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
