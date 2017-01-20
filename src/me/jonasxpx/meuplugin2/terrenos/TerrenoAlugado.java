package me.jonasxpx.meuplugin2.terrenos;

import java.io.Serializable;
import java.util.Calendar;

import org.bukkit.block.Sign;

public class TerrenoAlugado extends Terrenos implements Serializable{

	private static final long serialVersionUID = -2485720412595304743L;
	private Calendar expire;
	
	public TerrenoAlugado(String player, Sign sign) {
		super(sign);
		setOwner(player);
	}
	
	public TerrenoAlugado(String player, long time, Sign sign) {
		super(sign);
		setOwner(player);
	}
	
	public void setExpireDays(int days){
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(System.currentTimeMillis() + (days * 86400000));
		this.expire = c;
	}
	
	public String getPlayer(){
		return getOwner();
	}
	
	public boolean isExpired(){
		return Calendar.getInstance().after(expire);
	}
	
}
