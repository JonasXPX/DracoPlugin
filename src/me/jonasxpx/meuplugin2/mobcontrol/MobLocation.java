package me.jonasxpx.meuplugin2.mobcontrol;

import org.bukkit.Location;
import org.bukkit.entity.EntityType;

public class MobLocation {

	private Location loc;
	private EntityType entity;
	public MobLocation(Location loc, EntityType entity) {
		this.loc = loc;
		this.entity = entity;
	}
	
	@Override
	public String toString() {
		return loc.getWorld().getName() + " "
				+ loc.getBlockX() + " " 
				+ loc.getBlockY() + " "
				+ loc.getBlockZ() + " "
				+ entity.getName();
	}
	
	public void spawn(int moblimit){
		for(int x = 0; x <= moblimit; x++)
			loc.getWorld().spawnEntity(loc, entity);
	}
}
