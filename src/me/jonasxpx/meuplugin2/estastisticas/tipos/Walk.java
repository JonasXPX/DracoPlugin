package me.jonasxpx.meuplugin2.estastisticas.tipos;

import me.jonasxpx.meuplugin2.estastisticas.Status;
import me.jonasxpx.meuplugin2.estastisticas.Value;

public class Walk extends Status{

	public Walk(Value value) {
		super("walk");
		setValueClass(value);
	}
	
	public long getWalk(){
		return Math.round(getValue().getValue());
	}
	
	
	public String getFormatedVersion() {
		return getWalk() + " Blocos";
	}
	
}
