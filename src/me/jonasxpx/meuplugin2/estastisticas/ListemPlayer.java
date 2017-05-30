package me.jonasxpx.meuplugin2.estastisticas;

import java.util.ArrayList;
import java.util.Arrays;

import org.bukkit.entity.Player;
import org.json.JSONObject;

import me.jonasxpx.meuplugin2.MeuPlugin;
import me.jonasxpx.meuplugin2.estastisticas.tipos.Dead;
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
		System.out.println(json.toString());
		estatisticas.add(new Dead(new Value(json.getJSONArray(Type.DEAD.value).getDouble(0))));
		estatisticas.add(new Kills(new Value(json.getJSONArray(Type.KILLS.value).getDouble(0))));
		estatisticas.add(new MobKills(new Value(json.getJSONArray(Type.MOBKILLS.value).getDouble(0))));
		estatisticas.add(new Walk(new Value(json.getJSONArray(Type.WALK.value).getDouble(0))));
		
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
		int ret = MeuPlugin.statusDb.savePlayer(player.getName(), json.toString());
		System.out.println("Save data returned " + ret);
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
	
	
	public enum Type {
		DEAD("dead"),
		KILLS("kills"),
		MOBKILLS("mobkills"),
		WALK("walk");
		
		String value;
		
		private Type(String s) {
			this.value = s;
		}
	}
	
	
}
