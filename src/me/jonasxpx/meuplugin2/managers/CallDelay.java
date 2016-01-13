package me.jonasxpx.meuplugin2.managers;

import java.util.ArrayList;

import me.jonasxpx.meuplugin2.MeuPlugin;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public abstract class CallDelay extends BukkitRunnable{
	
	private int seconds;
	public static ArrayList<String> time = new ArrayList<String>();
	
	public CallDelay(Player player, int seconds) {
		if(time.contains(player.getName())){player.sendMessage("§6Aguarde."); return;}
		this.seconds = seconds;
		player.sendMessage("§6Aguarde "+seconds+" segundos.");
		register(player.getName());
	}
	public CallDelay(CommandSender sender, int seconds) {
		if(time.contains(sender.getName())){sender.sendMessage("§6Aguarde."); return;}
		this.seconds = seconds;sender.sendMessage("§6Aguarde "+seconds+" segundos.");
		register(sender.getName());
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
	
	
	
	/**** STATIC ****/
	private static void register(String string){
		time.add(string);
	}
	
	public static void unregister(String string){
		time.remove(string);
	}
	
}
