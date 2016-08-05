package me.jonasxpx.meuplugin2.karma;

public enum KarmaLevels {
	L_5("§2\u2020", 1000, 1001),
	L_4("§a\u2020", 750, L_5.level),
	L_3("§f\u2020", 500, L_4.level),
	L_2("§c\u2020", 250, L_3.level),
	L_1("§4\u2020", 0, L_2.level);
	
	
	public String tag;
	public int level;
	public int max;
	
	
	
	private KarmaLevels(String tag, int level, int max) {
		this.tag = tag;
		this.level = level;
		this.max = max;
	}
}
