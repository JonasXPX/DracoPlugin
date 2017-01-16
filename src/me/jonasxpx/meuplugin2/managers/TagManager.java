package me.jonasxpx.meuplugin2.managers;

import org.bukkit.entity.Player;

import me.jonasxpx.meuplugin2.MeuPlugin;
import me.jonasxpx.meuplugin2.karma.KarmaUtils;

public class TagManager {

	
	public static String getFullTagByPlayer(Player player){
		StringBuilder build = new StringBuilder();
		build.append(KarmaUtils.getKarmaByPlayer(player).getTag());
		for(String group : MeuPlugin.getGroupTags().keySet()){
			if(player.hasPermission("dracoplugin.grouptag." + group))
				if(!player.isOp())
					build.append(MeuPlugin.getGroupTags().get(group));
		}
		return build.toString();
	}
}
