package me.jonasxpx.meuplugin2.listeners;

import static me.jonasxpx.meuplugin2.MeuPlugin.economy;
import static me.jonasxpx.meuplugin2.MeuPlugin.instance;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import br.com.devpaulo.legendchat.api.events.ChatMessageEvent;
import me.jonasxpx.meuplugin2.karma.KarmaUtils;

public class ChatEvent implements Listener{

	
	@EventHandler
	public void onChatEvent(ChatMessageEvent e){
		e.setTagValue("karma", KarmaUtils.getKarmaByPlayer(e.getSender()).getTag());
		
		//chat manager
		if(!e.getSender().hasPermission("dracoplugin.chat.trust")){
			if(instance.chatMinMoney.containsKey(e.getChannel().getName())){
				if(economy.getBalance(e.getSender()) < instance.chatMinMoney.get(e.getChannel().getName())){
					e.getSender().sendMessage("§cVocê não pode usar o chat global ainda.");
					e.setCancelled(true);
				}
			}
		}
	}
	
}
