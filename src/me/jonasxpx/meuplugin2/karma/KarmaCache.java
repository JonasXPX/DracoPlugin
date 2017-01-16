package me.jonasxpx.meuplugin2.karma;

import org.bukkit.entity.Player;

public class KarmaCache {
	
	private Player player;
	private int k = -1;
	
	public KarmaCache(Player player){
		this.player = player;
	}
	
	public int getKarma(){
		if(k == -1){
			k = KarmaDb.getKarma(player.getName().toLowerCase());
		}
		return k;
	}
	
	public void addKarma(int km){
		if(km < 0)
			k = k - Math.abs(km);
		else
			k = k + km;
	}
	
	public void saveCache(){
		KarmaDb.updateKarma(player.getName().toLowerCase(), k);
	}
	
}
