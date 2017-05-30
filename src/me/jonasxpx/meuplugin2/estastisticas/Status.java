package me.jonasxpx.meuplugin2.estastisticas;

public class Status {

	private final String name;
	private Value value;
	
	public Status(String name) {
		this.name = name;
	}
	
	public Value getValue() {
		return value;
	}
	
	public void setValueClass(Value value) {
		this.value = value;
	}
	
	public String getName() {
		return name;
	}
	
	
}
