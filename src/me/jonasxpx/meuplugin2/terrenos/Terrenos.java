package me.jonasxpx.meuplugin2.terrenos;

import org.bukkit.block.Sign;

public class Terrenos {

	private Sign sign;
	private boolean ocuped;
	private String owner;
	
	
	public Terrenos(Sign sign) {
		this.sign = sign;
	}

	public Sign getSign() {
		return sign;
	}

	public boolean isOcuped() {
		return ocuped;
	}


	public void setOcuped(boolean ocuped) {
		this.ocuped = ocuped;
	}


	public String getOwner() {
		return owner;
	}


	public void setOwner(String owner) {
		this.owner = owner;
	}
	
	
	
	
	
	
}
