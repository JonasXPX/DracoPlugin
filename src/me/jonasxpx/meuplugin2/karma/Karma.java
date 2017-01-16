package me.jonasxpx.meuplugin2.karma;

import org.bukkit.entity.Player;

public class Karma {

	private Player player;
	private KarmaCache karmaCache;
	
	public Karma(Player player){
		this.player = player;
		if(!KarmaDb.exists(getPlayer().getName())){
			KarmaDb.setKarma(getPlayer().getName(), KarmaLevels.L_3.level);
			//KarmaTagUpdate.updateSingle(this);
		}
		karmaCache = new KarmaCache(player);
	}
	
	public int getKarma(){
		return karmaCache.getKarma();
	}
	public KarmaCache getCache(){
		return karmaCache;
	}

	public void addKarma(int karma){
		karmaCache.addKarma(karma);
	}

	public String getTag(){
		for(KarmaLevels k : KarmaLevels.values()){
			if(getKarma() <= k.max && getKarma() >= k.level){
				return "[" + k.tag + "§r] ";
			}
		}
		return "";
	}
	
	public Player getPlayer(){
		return player;
	}	
}
