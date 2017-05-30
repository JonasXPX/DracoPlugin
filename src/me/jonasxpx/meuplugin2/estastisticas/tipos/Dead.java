package me.jonasxpx.meuplugin2.estastisticas.tipos;

import me.jonasxpx.meuplugin2.estastisticas.Status;
import me.jonasxpx.meuplugin2.estastisticas.Value;

public class Dead extends Status{

	public Dead(Value value) {
		super("dead");
		this.setValueClass(value);
	}
	
	public long getDeads(){
		return Math.round(this.getValue().getValue());
	}
	
	public String getFormatedVersion(){
		return Long.toString(getDeads()) + " Mortes";
	}
}
