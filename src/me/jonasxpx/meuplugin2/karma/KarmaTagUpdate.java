package me.jonasxpx.meuplugin2.karma;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import com.nametagedit.plugin.NametagEdit;

import ca.wacos.nametagedit.NametagAPI;
import me.jonasxpx.meuplugin2.MeuPlugin;
import me.jonasxpx.meuplugin2.managers.TagManager;

public class KarmaTagUpdate extends BukkitRunnable{

	@Override
	public void run() {
		 for(Karma k : MeuPlugin.getKarmaPlayers()){
			 Player p = k.getPlayer();
			 String tag = TagManager.getFullTagByPlayer(p);
			 if(!checkVersionTagAPIisUP())
				 NametagAPI.setPrefix(p.getName(), tag.length() > 15 ? tag.substring(0, 15) : tag);
			 else
				 NametagEdit.getApi().setPrefix(p, tag.length() > 15 ? tag.substring(0, 15) : tag);
		 }
	}
		 
	public static void updateSingle(Karma k){
		String tag = TagManager.getFullTagByPlayer(k.getPlayer());
		if(!checkVersionTagAPIisUP())
			NametagAPI.setPrefix(k.getPlayer().getName(), tag.length() > 15 ? tag.substring(0, 15) : tag);
		else 
			NametagEdit.getApi().setPrefix(k.getPlayer(), tag.length() > 15 ? tag.substring(0, 15) : tag);
	}
	
	private static boolean checkVersionTagAPIisUP(){
		try{
			Class.forName("com.nametagedit.plugin.NametagEdit");
			return true;
		}catch (ClassNotFoundException e) {
			return false;
		}
	}
}
