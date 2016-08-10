package me.jonasxpx.meuplugin2.managers;

import org.bukkit.entity.Player;

import me.jonasxpx.meuplugin2.MeuPlugin;
import me.jonasxpx.meuplugin2.karma.KarmaUtils;

public class TagManager {

	
	public static String getFullTagByPlayer(Player player){
		StringBuilder build = new StringBuilder();
		build.append(MeuPlugin.getKarmaPlayers().get(MeuPlugin.getKarmaPlayers().indexOf(KarmaUtils.getKarmaByPlayer(player))).getTag());
		for(String group : MeuPlugin.getGroupTags().keySet()){
			if(player.hasPermission("dracoplugin.grouptag." + group) && !player.isOp())
				build.append(MeuPlugin.getGroupTags().get(group));
		}
		System.out.println("[Debug-DP] Tag update " + build.toString() + " " + player.getName());
		return build.toString();
	}
}
