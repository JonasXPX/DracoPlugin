package me.jonasxpx.meuplugin2.managers;

import java.util.Arrays;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

import me.jonasxpx.meuplugin2.MeuPlugin;
import me.jonasxpx.meuplugin2.estastisticas.ListemPlayer;
import me.jonasxpx.meuplugin2.estastisticas.ListemPlayer.Type;
import me.jonasxpx.meuplugin2.estastisticas.tipos.Dead;
import me.jonasxpx.meuplugin2.estastisticas.tipos.Kills;
import me.jonasxpx.meuplugin2.estastisticas.tipos.MobKills;
import me.jonasxpx.meuplugin2.estastisticas.tipos.Walk;
import me.jonasxpx.meuplugin2.estastisticas.Status;

public class TimerEstatisticas extends BukkitRunnable{
	private Random rd = new Random();
	@Override
	public void run() {
		Arrays.asList(Bukkit.getOnlinePlayers()).forEach(p -> {
			ListemPlayer s = MeuPlugin.getListemByPlayer(p.getName());
			Status status = s.getTipo(Type.values()[rd.nextInt(Type.values().length)]);
			StringBuilder sb = new StringBuilder();
			sb.append("�7�m>------------------------------------<\n");
			if(status instanceof Kills){
				sb.append("�bVoc� j� matou �6");
				sb.append(((Kills) status).getKills());
				sb.append(" Jogador(es)");
			} else if (status instanceof Dead){
				sb.append("�bVoc� morreu �6");
				sb.append(((Dead) status).getDeads());
				sb.append(" Vez(es)");
			} else if (status instanceof Walk){
				sb.append("�bVoc� j� andou �6");
				sb.append(((Walk) status).getWalk());
				sb.append(" Bloco(s)");
			} else if (status instanceof MobKills){
				sb.append("�bVoc� j� matou �6");
				sb.append(((MobKills) status).getKills());
				sb.append(" Mob(s)");
			}
			sb.append("\n�7�m>------------------------------------<");
			p.sendMessage(sb.toString());
		});
	}

	
	
	
}
