package me.jonasxpx.meuplugin2.estastisticas.tipos;

import me.jonasxpx.meuplugin2.estastisticas.Status;
import me.jonasxpx.meuplugin2.estastisticas.Value;

public class Draco extends Status{

	public Draco(Value value) {
		super("draco");
		this.setValueClass(value);
	}
	
	public long getKills(){
		return Math.round(this.getValue().getValue());
	}
}
