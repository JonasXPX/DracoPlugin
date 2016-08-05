package me.jonasxpx.meuplugin2.managers;

import org.bukkit.scheduler.BukkitRunnable;

import ca.wacos.nametagedit.NametagAPI;
import me.jonasxpx.meuplugin2.MeuPlugin;
import me.jonasxpx.meuplugin2.karma.Karma;

public class KarmaTagUpdate extends BukkitRunnable{

		 @Override
		public void run() {
			 for(Karma k : MeuPlugin.getKarmaPlayers()){
				 System.out.println("Debug Karma: Update " + k.getTag() + " = " + k.getPlayer().getName() + " Karma: " + k.getKarma());
				 NametagAPI.setPrefix(k.getPlayer().getName(), k.getTag());
			 }
		}
		 
	public static void updateSingle(Karma k){
		NametagAPI.setPrefix(k.getPlayer().getName(), k.getTag());
	}
}
