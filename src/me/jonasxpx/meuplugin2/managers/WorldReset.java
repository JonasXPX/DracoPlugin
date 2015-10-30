package me.jonasxpx.meuplugin2.managers;

import java.io.File;

import org.bukkit.World;

public class WorldReset {

	public static void resetRegions(World world){
		System.out.println(world.getWorldFolder().getPath());
		File f = new File(world.getWorldFolder().getAbsolutePath() + "/region");
		for(File fx : f.listFiles()){
			fx.delete();
			System.out.println(fx.getAbsolutePath() + " foi deletado!");
		}
		f.delete();
		System.out.println(f.getAbsolutePath() + " foi deletado!");
	}
}
