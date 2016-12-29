package me.jonasxpx.meuplugin2.listeners;

import static me.jonasxpx.meuplugin2.MeuPlugin.getKarmaPlayers;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.potion.PotionEffectType;

import me.jonasxpx.meuplugin2.karma.Karma;
import me.jonasxpx.meuplugin2.karma.KarmaLevels;
import me.jonasxpx.meuplugin2.karma.KarmaManager;
import me.jonasxpx.meuplugin2.karma.KarmaTagUpdate;
import me.jonasxpx.meuplugin2.karma.KarmaUtils;
import me.jonasxpx.meuplugin2.karma.event.KarmaLevelUpdate;

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
	
	/**
	 * Mob kill Event.
	 * @param e evento.
	 */
	@EventHandler
	public void onKill(EntityDeathEvent e){
		if(!(e.getEntity().getKiller() instanceof Player))
			return;
		
		if(e.getEntity().getType() != EntityType.PLAYER){
			Karma k = KarmaUtils.getKarmaByPlayer((Player)e.getEntity().getKiller());
			KarmaLevelUpdate kEvent = new KarmaLevelUpdate((Player)e.getEntity().getKiller(), k);
			if(kEvent.isCancelled()){
				return;
			}
			if(k.getKarma() >= KarmaLevels.L_5.max)
				return;
			k.addKarma(1);
		}
	}
	
	/**
	 * Player kill event.
	 * @param e evento.
	 */
	@EventHandler
	public void onKillPlayer(PlayerDeathEvent e){
		if(e.getEntity().getKiller() == null)
			return;
		
		if(e.getEntity().getKiller() instanceof Player){
			Karma k = KarmaUtils.getKarmaByPlayer((Player)e.getEntity().getKiller());
			KarmaLevelUpdate kEvent = new KarmaLevelUpdate((Player)e.getEntity().getKiller(), k);
			if(kEvent.isCancelled()){
				return;
			}
			if(k.getKarma() <= KarmaLevels.L_1.level)
				return;
			k.addKarma(-1);	
		}
	}
	
	@EventHandler
	public void onHitPlayer(EntityDamageByEntityEvent e){
		if(!(e.getDamager() instanceof Player)){
			return;
		}
		Karma k = KarmaUtils.getKarmaByPlayer((Player)e.getDamager());
		if(k.getKarma() <= KarmaLevels.L_1.level){
			KarmaManager.addPlayerEffect((Player)e.getDamager(), PotionEffectType.INCREASE_DAMAGE, 20 * 30, 1);
		}
		if(k.getKarma() <= KarmaLevels.L_4.max && k.getKarma() >= KarmaLevels.L_4.level){
			KarmaManager.addPlayerEffect((Player)e.getDamager(), PotionEffectType.SPEED, 20 * 30, 1);
		}
	}
	
}
