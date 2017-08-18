package me.jonasxpx.meuplugin2.listeners;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import me.jonasxpx.meuplugin2.Messages;
import me.jonasxpx.meuplugin2.MeuPlugin;

public class MessagesListeners implements Listener{

	@EventHandler
	public void onLogin(PlayerJoinEvent e){
		e.setJoinMessage(ChatColor.translateAlternateColorCodes('&', MeuPlugin.instance.messages.get(Messages.LOGIN)));
	}
	
	@EventHandler
	public void onLeave(PlayerQuitEvent e){
		e.setQuitMessage(ChatColor.translateAlternateColorCodes('&', MeuPlugin.instance.messages.get(Messages.LOGOUT)));
	}
	
	@EventHandler
	public void onDead(PlayerDeathEvent e){
		e.setDeathMessage(ChatColor.translateAlternateColorCodes('&', MeuPlugin.instance.messages.get(Messages.DEADBYPLAYER)));
	}
	
}
