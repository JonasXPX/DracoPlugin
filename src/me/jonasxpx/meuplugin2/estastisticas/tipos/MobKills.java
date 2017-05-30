package me.jonasxpx.meuplugin2.estastisticas.tipos;

import me.jonasxpx.meuplugin2.estastisticas.Status;
import me.jonasxpx.meuplugin2.estastisticas.Value;

public class MobKills extends Status{

	public MobKills(Value value) {
		super("mobkills");
		setValueClass(value);
	}
	
	public long getKills(){
		return Math.round(getValue().getValue());
	}
	
	public String getFormatedVersion() {
		return getKills() + " Mobs";
	}
	
}
