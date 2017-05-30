package me.jonasxpx.meuplugin2.estastisticas;

public class Value {
	
	private double value;
	
	public Value() {
	}
	
	public Value(double value) {
		this.value = value;
	}

	public double getValue() {
		return value;
	}
	
	public void setValue(double value) {
		this.value = value;
	}
	
	public void increase(){
		this.value++;
	}
	
	public void increaseBy(int value){
		this.value = this.value + value;
	}
	
}
