package me.jonasxpx.meuplugin2.terrenos;

import org.bukkit.ChatColor;
import org.bukkit.World;

import com.sk89q.worldguard.domains.DefaultDomain;
import com.sk89q.worldguard.protection.databases.ProtectionDatabaseException;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;

import me.jonasxpx.meuplugin2.MeuPlugin;

public class WorldGuardInformation {

	private String rgName;
	private World world;
	
	public WorldGuardInformation(String rgName, World world){
		this.rgName = rgName;
		this.world = world;
	}
	
	public boolean isOwner(String player){
		ProtectedRegion rg = MeuPlugin.instance.worldGuard.getRegionManager(world).getRegionExact(rgName);
		if(!exists() || !hasOwner()){
			return false;
		}
		return rg.isOwner(player.toLowerCase());
	}
	
	public String getOwner(){
		ProtectedRegion rg = MeuPlugin.instance.worldGuard.getRegionManager(world).getRegionExact(rgName);
		if(rg == null){
			return null;
		}
		StringBuilder s = new StringBuilder();
		
		if(rg.getOwners().size() > 1){
			rg.getOwners().getPlayers().forEach(owner -> {
				s.append(owner);
				s.append(ChatColor.WHITE);
				s.append(", ");
				s.append(ChatColor.YELLOW);
			});
		} else if(rg.getOwners().size() == 1) {
			s.append(rg.getOwners().getPlayers().iterator().next());
		}
		return s.toString();
	}
	
	public void removeOwner(String player){
		RegionManager rm = MeuPlugin.instance.worldGuard.getRegionManager(world);
		ProtectedRegion rg = rm.getRegionExact(rgName);
		DefaultDomain dd = rg.getOwners();
		dd.removePlayer(player.toLowerCase());
		try {
			rm.save();
		} catch (ProtectionDatabaseException e) {e.printStackTrace();}
	}
	
	public void addOwner(String player){
		RegionManager rm = MeuPlugin.instance.worldGuard.getRegionManager(world);
		ProtectedRegion rg = rm.getRegionExact(rgName);
		DefaultDomain dd;
		if(hasOwner()){
			dd = rg.getOwners();
		} else {
			dd = new DefaultDomain();
		}
		dd.addPlayer(player.toLowerCase());
		rg.setOwners(dd);
		try {
			rm.save();
		} catch (ProtectionDatabaseException e) {e.printStackTrace();}
	}
	
	public boolean hasOwner(){
		ProtectedRegion rg = MeuPlugin.instance.worldGuard.getRegionManager(world).getRegionExact(rgName);
		if(rg.getOwners().size() != 0)
			return true;
		return false;
	}
	
	public boolean exists(){
		ProtectedRegion rg = MeuPlugin.instance.worldGuard.getRegionManager(world).getRegionExact(rgName);
		System.out.println("Checked region: " + rgName);
		if(rg == null){
			System.out.println("is null.");
			return false;
		}
		return true;
	}
}
