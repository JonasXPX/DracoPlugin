package me.jonasxpx.meuplugin2.managers;

import java.util.ArrayList;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import me.jonasxpx.meuplugin2.MeuPlugin;

public abstract class CallDelay extends BukkitRunnable {

	private int seconds;
	public static ArrayList<String> time = new ArrayList<String>();

	public CallDelay(Player player, int seconds) {
		if (time.contains(player.getName())) {
			player.sendMessage("§6Aguarde.");
			return;
		}
		this.seconds = seconds;
		if (this.seconds != 0)
			player.sendMessage("§6Aguarde " + seconds + " segundos.");
		register(player.getName());
	}

	public CallDelay(CommandSender sender, int seconds) {
		if (time.contains(sender.getName())) {
			sender.sendMessage("§6Aguarde.");
			return;
		}
		this.seconds = seconds;
		if (this.seconds != 0)
			sender.sendMessage("§6Aguarde " + seconds + " segundos.");
		register(sender.getName());
	}

	public CallDelay() {

	}

	public void startAfter() {
		try {
			this.runTaskLater(MeuPlugin.instance, seconds == 0 ? seconds : 20 * seconds);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**** STATIC ****/
	private static void register(String string) {
		time.add(string);
	}

	public static void unregister(String string) {
		time.remove(string);
	}

	public static boolean isRegistred(String string) {
		return time.contains(string);
	}

}
