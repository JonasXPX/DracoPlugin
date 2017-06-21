package me.jonasxpx.meuplugin2.estastisticas;

import org.bukkit.Material;

public enum Type {
	DEAD("dead", Material.YELLOW_FLOWER, "Morreu"),
	KILLS("kills", Material.SKULL, "Kill(s)"),
	MOBKILLS("mobkills", Material.MOB_SPAWNER, "Matou quantos Mobs"),
	WALK("walk", Material.LEATHER_BOOTS, "Andou"),
	FARM("farm", Material.WOOD_HOE, "Colheu"),
	DRACO("draco", Material.EYE_OF_ENDER, "Matou o dragão");
	
	public String value;
	public String nome;
	public Material material;
	
	private Type(String s, Material itemID, String nome) {
		this.value = s;
		this.nome = nome;
		this.material = itemID;
	}
}
