package me.jonasxpx.meuplugin2.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import br.com.devpaulo.legendchat.api.events.ChatMessageEvent;
import me.jonasxpx.meuplugin2.karma.KarmaUtils;

public class KarmaChatEvent implements Listener{

	
	@EventHandler
	public void onChatEvent(ChatMessageEvent e){
		e.setTagValue("karma", KarmaUtils.getKarmaByPlayer(e.getSender()).getTag());
	}
	
}
