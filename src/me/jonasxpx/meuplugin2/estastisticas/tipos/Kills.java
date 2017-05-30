package me.jonasxpx.meuplugin2.estastisticas.tipos;

import me.jonasxpx.meuplugin2.estastisticas.Status;
import me.jonasxpx.meuplugin2.estastisticas.Value;

public class Kills extends Status{

	public Kills(Value value) {
		super("Kills");
		this.setValueClass(value);
	}
	
	public long getKills(){
		return Math.round(getValue().getValue());
	}
	
	
	public String getFormatedVersion(){
		return getKills() + " Kills";
	}
}
