package me.jonasxpx.meuplugin2.managers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.bukkit.Location;
import org.bukkit.entity.Player;


public class HomeManagerSQL {
	
	private Connection conn;
	private Statement stat;
	
	public HomeManagerSQL(String IP, String database, String username, String passw, int port){
		try {
			System.out.println("Conectando...");
			conn = DriverManager.getConnection("jdbc:mysql://" + IP + "/" + database, username, passw);
			stat = conn.createStatement();
			stat.executeUpdate("CREATE TABLE IF NOT EXISTS Home (Nick VARCHAR(32), Name VARCHAR(64), x SMALLINT(12), y SMALLINT(12), z SMALLINT(12), world VARCHAR(32), invited SMALLINT(2), PRIMARY KEY (Nick))");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public int update(Player player, String name, Location loc){
		try {
			return stat.executeUpdate("UPDATE Home SET x = " + loc.getBlockX() + ",y = " + loc.getBlockY() + ",z = " + loc.getBlockZ() + ",world = '" + loc.getWorld().getName() +"' WHERE Nick = '" + player.getName() + "' AND Name = '" +name+ "'");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}
	public int update(String player, String name, int x, int y, int z, String world){
		try {
			return stat.executeUpdate("UPDATE Home SET x = " + x + ",y = " + y + ",z = " + z + ",world = '" + world +"' WHERE Nick = '" + player + "' AND Name = '" +name+ "'");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	
	/**
	 * Create a home location
	 * @param player Player sets a home location
	 * @param name name of location
	 * @param loc Possition of location
	 */
	public void create(Player player, String name, Location loc){
		try{
			PreparedStatement ps = conn.prepareStatement("INSERT INTO Home(Nick, Name, x, y, z, world) VALUES(?,?,?,?,?,?,?)");
			ps.setString(1, player.getName());
			ps.setString(2, name);
			ps.setInt(3, loc.getBlockX());
			ps.setInt(4, loc.getBlockY());
			ps.setInt(5, loc.getBlockZ());
			ps.setString(6, loc.getWorld().getName());
			ps.setInt(7, 0);
			ps.execute();
			ps.close();
		}catch(SQLException e){e.printStackTrace();}
	}
	
	public void create(String player, String name, int x, int y, int z, String world){
		try{
			PreparedStatement ps = conn.prepareStatement("INSERT INTO Home(Nick, Name, x, y, z, world) VALUES(?,?,?,?,?,?,?)");
			ps.setString(1, player);
			ps.setString(2, name);
			ps.setInt(3, x);
			ps.setInt(4, y);
			ps.setInt(5, z);
			ps.setString(6, world);
			ps.setInt(7, 0);
			ps.execute();
			ps.close();
		}catch(SQLException e){e.printStackTrace();}
	}
	
	public String getStringLocation(String player, String name){
		try{
			ResultSet rs = stat.executeQuery("SELECT * FROM Home WHERE Nick = '"+player+"' AND Name = '"+name+"");
			if(rs.next() == false){return null;}
			StringBuilder sb = new StringBuilder();
			sb.append(rs.getInt("x"));
			sb.append(",");
			sb.append(rs.getInt("y"));
			sb.append(",");
			sb.append(rs.getInt("z"));
			sb.append(",");
			sb.append(rs.getString("world"));
			return sb.toString();
		}catch(SQLException e){e.printStackTrace();}
		return null;
	}
	
	public boolean delete(String player, String name){
		try{
			if(stat.executeUpdate("DELETE FROM Home WHERE Nick = '"+player+"' AND Name = '" + name + "'") == 1)
				return true;
		}catch(SQLException e){e.printStackTrace();}
		return false;
	}
	
	
	public static void main(String[] args) {
		HomeManagerSQL m = new HomeManagerSQL("192.168.1.2", "none", "none", "none", 3306);
		if(m.update("Jonas", "casa", 2,1,5,"world2") == 0){
			m.create("jonas", "casa", 1, 1, 1, "world");
		}
	}
	
}
