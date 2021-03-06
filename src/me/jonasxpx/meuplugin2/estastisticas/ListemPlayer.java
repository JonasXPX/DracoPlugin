package me.jonasxpx.meuplugin2.estastisticas;

import java.util.ArrayList;
import java.util.Arrays;

import org.bukkit.entity.Player;
import org.json.JSONObject;

import me.jonasxpx.meuplugin2.MeuPlugin;
import me.jonasxpx.meuplugin2.estastisticas.tipos.Dead;
import me.jonasxpx.meuplugin2.estastisticas.tipos.Draco;
import me.jonasxpx.meuplugin2.estastisticas.tipos.Farm;
import me.jonasxpx.meuplugin2.estastisticas.tipos.Kills;
import me.jonasxpx.meuplugin2.estastisticas.tipos.MobKills;
import me.jonasxpx.meuplugin2.estastisticas.tipos.Walk;

public class ListemPlayer {

	private ArrayList<Status> estatisticas;
	private final Player player;
	private JSONObject json;
	
	public ListemPlayer(Player player) {
		this.player = player;
		this.estatisticas = new ArrayList<>();
		String jsonData = MeuPlugin.statusDb.getDataPlayer(player.getName());
		if(jsonData == null){
			json = new JSONObject();
		} else {
			json = new JSONObject(jsonData);
		}
		configurePlayer();
		estatisticas.add(new Dead(new Value(json.has(Type.DEAD.value) ? json.getJSONArray(Type.DEAD.value).getDouble(0) : 0)));
		estatisticas.add(new Kills(new Value(json.has(Type.KILLS.value) ? json.getJSONArray(Type.KILLS.value).getDouble(0) : 0)));
		estatisticas.add(new MobKills(new Value(json.has(Type.MOBKILLS.value) ? json.getJSONArray(Type.MOBKILLS.value).getDouble(0) : 0)));
		estatisticas.add(new Walk(new Value(json.has(Type.WALK.value) ? json.getJSONArray(Type.WALK.value).getDouble(0) : 0)));
		estatisticas.add(new Farm(new Value(json.has(Type.FARM.value) ? json.getJSONArray(Type.FARM.value).getDouble(0) : 0)));
		estatisticas.add(new Draco(new Value(json.has(Type.DRACO.value) ? json.getJSONArray(Type.DRACO.value).getDouble(0) : 0)));
		
	}
	
	public Player getPlayer(){
		return this.player;
	}
	
	public Status getTipo(Type type){
		for(Status s : estatisticas){
			if(type.value.equalsIgnoreCase(s.getName())){
				return s;
			}
		}
		return null;
	}
	
	private void configurePlayer(){
		Arrays.asList(Type.values()).forEach(values -> {
			if(!json.has(values.value)){
				json.append(values.value, 0L);
			}
		});
	}
	
	public void saveData(){
		json = new JSONObject();
		for(Status s : estatisticas){
			json.append(s.getName(), s.getValue().getValue());
		}
		MeuPlugin.statusDb.savePlayer(player.getName(), json.toString());
	}
	
	@Override
	public String toString() {
		StringBuilder out = new StringBuilder();
		for(Status s : estatisticas){
			out.append(s.getName());
			out.append(": ");
			out.append(s.getValue().getValue());
			out.append("\n");
		}
		return out.toString();
	}
	
	
}
