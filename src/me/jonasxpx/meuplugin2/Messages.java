package me.jonasxpx.meuplugin2;

public enum Messages {

	LOGIN("login"),
	LOGOUT("logout"),
	DEADBYPLAYER("deadbyplayer");
	
	public String configName;
	
	private Messages(String string) {
		this.configName = string;
	}
}
