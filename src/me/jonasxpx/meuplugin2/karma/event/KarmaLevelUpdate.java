package me.jonasxpx.meuplugin2.karma.event;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import me.jonasxpx.meuplugin2.karma.Karma;

public class KarmaLevelUpdate extends Event implements Cancellable{

	private static HandlerList handler = new HandlerList();
	private boolean cancelled = false;
	private final Player player;
	private final Karma karma;
	
	
	public KarmaLevelUpdate(Player player, Karma karma) {
		this.player = player;
		this.karma = karma;
		
	}
	
	public Player getPlayer(){
		return player;
	}
	public Karma getKarma(){
		return karma;
	}
	
	@Override
	public void setCancelled(boolean cancelled) {
		this.cancelled = cancelled;
	}
	
	@Override
	public boolean isCancelled() {
		return cancelled;
	}
	
	@Override
	public HandlerList getHandlers() {
		return handler;
	}

	
}
