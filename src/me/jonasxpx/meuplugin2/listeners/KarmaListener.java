package me.jonasxpx.meuplugin2.listeners;

import static me.jonasxpx.meuplugin2.MeuPlugin.getKarmaPlayers;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import me.jonasxpx.meuplugin2.karma.Karma;
import me.jonasxpx.meuplugin2.karma.KarmaLevels;
import me.jonasxpx.meuplugin2.karma.KarmaTagUpdate;
import me.jonasxpx.meuplugin2.karma.KarmaUtils;

public class KarmaListener implements Listener{

	@EventHandler
	public void onLogin(PlayerJoinEvent e){
		Karma karma = new Karma(e.getPlayer());
		getKarmaPlayers().add(karma);
		KarmaTagUpdate.updateSingle(karma);
	}
	
	@EventHandler
	public void onQuit(PlayerQuitEvent e){
		Karma k = KarmaUtils.getKarmaByPlayer(e.getPlayer());
		if(k != null)getKarmaPlayers().remove(KarmaUtils.getKarmaByPlayer(e.getPlayer()));
	}
	
	@EventHandler
	public void onQuit(PlayerKickEvent e){
		Karma k = KarmaUtils.getKarmaByPlayer(e.getPlayer());
		if(k != null)getKarmaPlayers().remove(KarmaUtils.getKarmaByPlayer(e.getPlayer()));
	}
	
	@EventHandler
	public void onKill(EntityDeathEvent e){
		if(!(e.getEntity().getKiller() instanceof Player))
			return;
		
		
		if(e.getEntity().getType() != EntityType.PLAYER){
			Karma k = KarmaUtils.getKarmaByPlayer((Player)e.getEntity().getKiller());
			if(k.getKarma() >= KarmaLevels.L_5.max)
				return;
			k.addKarma(1);
			System.out.println("Debug Karma M: Update " + k.getTag() + " = " + k.getPlayer().getName() + " Karma: " + k.getKarma());
		}
	}
	@EventHandler
	public void onKillPlayer(PlayerDeathEvent e){
		if(e.getEntity().getKiller() == null)
			return;
		
		if(e.getEntity().getKiller() instanceof Player){
			Karma k = KarmaUtils.getKarmaByPlayer((Player)e.getEntity().getKiller());
			if(k.getKarma() <= KarmaLevels.L_1.level)
				return;
			k.addKarma(-1);
			System.out.println("Debug Karma P: Update " + k.getTag() + " = " + k.getPlayer().getName() + " Karma: " + k.getKarma());
		}
		
	}
	
}
