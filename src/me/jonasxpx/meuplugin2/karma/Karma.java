package me.jonasxpx.meuplugin2.karma;

import org.bukkit.entity.Player;

public class Karma {

	private Player player;
	
	public Karma(Player player){
		this.player = player;
		if(!KarmaDb.exists(getPlayer().getName())){
			KarmaDb.setKarma(getPlayer().getName(), KarmaLevels.L_3.level);
			//KarmaTagUpdate.updateSingle(this);
		}
	}
	
	public int getKarma(){
		return KarmaDb.getKarma(player.getName().toLowerCase());
	}

	public void addKarma(int karma){
		int k = getKarma();
		if(karma < 0){
			KarmaDb.updateKarma(player.getName().toLowerCase(), k - Math.abs(karma));
		} else if (karma > 0){
			KarmaDb.updateKarma(player.getName().toLowerCase(), k + karma);
		}
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
