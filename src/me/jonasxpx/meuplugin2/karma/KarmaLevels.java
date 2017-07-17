package me.jonasxpx.meuplugin2.karma;

import org.bukkit.ChatColor;

public enum KarmaLevels {
	L_5(ChatColor.DARK_GREEN +"§2\u271E", 1000, 1001),
	
	/**
	 * Most killed mob.
	 */
	L_4(ChatColor.GREEN + "\u271E", 750, L_5.level),
	
	/**
	 * Normal level.
	 */
	L_3(ChatColor.WHITE + "\u271E", 500, L_4.level),
	
	/**
	 * Most killed players.
	 */
	L_2(ChatColor.RED + "§c\u271E", 250, L_3.level),
	L_1(ChatColor.DARK_RED + "\u271E", 0, L_2.level);
	
	public String tag;
	public int level;
	public int max;
	
	private KarmaLevels(String tag, int level, int max) {
		this.tag = tag;
		this.level = level;
		this.max = max;
	}
}