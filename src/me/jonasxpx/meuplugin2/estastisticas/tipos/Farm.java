package me.jonasxpx.meuplugin2.estastisticas.tipos;

import me.jonasxpx.meuplugin2.estastisticas.Status;
import me.jonasxpx.meuplugin2.estastisticas.Value;

public class Farm extends Status{
	
	public Farm(Value value) {
		super("farm");
		this.setValueClass(value);
	}
	
	public long getFarmed(){
		return Math.round(this.getValue().getValue());
	}
}
