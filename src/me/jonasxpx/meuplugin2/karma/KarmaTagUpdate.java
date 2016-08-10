package me.jonasxpx.meuplugin2.karma;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import ca.wacos.nametagedit.NametagAPI;
import me.jonasxpx.meuplugin2.MeuPlugin;
import me.jonasxpx.meuplugin2.managers.TagManager;

public class KarmaTagUpdate extends BukkitRunnable{

	@Override
	public void run() {
		 for(Karma k : MeuPlugin.getKarmaPlayers()){
			 Player p = k.getPlayer();
			 String tag = TagManager.getFullTagByPlayer(p);
			 NametagAPI.setPrefix(p.getName(), tag.length() > 15 ? tag.substring(0, 15) : tag);
		 }
	}
		 
	public static void updateSingle(Karma k){
		String tag = TagManager.getFullTagByPlayer(k.getPlayer());
		NametagAPI.setPrefix(k.getPlayer().getName(), tag.length() > 15 ? tag.substring(0, 15) : tag);
	}
}
