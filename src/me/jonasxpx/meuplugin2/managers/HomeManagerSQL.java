package me.jonasxpx.meuplugin2.managers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import me.jonasxpx.meuplugin2.MeuPlugin;


public class HomeManagerSQL {
	
	private Connection conn;
	private Statement stat;
	
	public HomeManagerSQL(String IP, String database, String username, String passw, int port){
		try {
			System.out.println("Conectando...");
			DriverManager.setLoginTimeout(10);
			conn = DriverManager.getConnection("jdbc:mysql://" + IP + "/" + database, username, passw);
			stat = conn.createStatement();
			stat.executeUpdate("CREATE TABLE IF NOT EXISTS Home (Nick VARCHAR(32), Name VARCHAR(64), x DOUBLE(12,2), y DOUBLE(12,2), z DOUBLE(12,2),yaw FLOAT(12),pitch FLOAT(12), world VARCHAR(32), invited SMALLINT(2))");
		} catch (SQLException e) { 
			e.printStackTrace();
		}
	}
	public int update(Player player, String name, Location loc){
		try {
			return stat.executeUpdate("UPDATE Home SET x = " + loc.getX() + ",y = " + loc.getY() + ",z = " + loc.getZ() + ",yaw = "+loc.getYaw()+",pitch = "+loc.getPitch()+",world = '" + loc.getWorld().getName() +"' WHERE Nick = '" + player.getName() + "' AND Name = '" +name+ "'");
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
			PreparedStatement ps = conn.prepareStatement("INSERT INTO Home(Nick, Name, x, y, z, yaw, pitch, world, invited) VALUES(?,?,?,?,?,?,?,?,?)");
			ps.setString(1, player.getName());
			ps.setString(2, name);
			ps.setDouble(3, loc.getX());
			ps.setDouble(4, loc.getY());
			ps.setDouble(5, loc.getZ());
			ps.setFloat(6, loc.getYaw());
			ps.setFloat(7, loc.getPitch());
			ps.setString(8, loc.getWorld().getName());
			ps.setInt(9, 0);
			ps.execute();
			ps.close();
		}catch(SQLException e){e.printStackTrace();}
	}
	
	public void create(String player, String name, int x, int y, int z, String world){
		try{
			PreparedStatement ps = conn.prepareStatement("INSERT INTO Home(Nick, Name, x, y, z, world) VALUES(?,?,?,?,?,?,?)");
			ps.setString(1, player);
			ps.setString(2, name);
			ps.setDouble(3, x);
			ps.setDouble(4, y);
			ps.setDouble(5, z);
			ps.setString(6, world);
			ps.setInt(7, 0);
			ps.execute();
			ps.close();
		}catch(SQLException e){e.printStackTrace();}
	}
	
	public synchronized String getStringLocation(String player, String name){
		try{
			ResultSet rs = stat.executeQuery("SELECT * FROM Home WHERE Nick = '"+player+"' AND Name = '"+name+"'");
			if(rs.next() == false){return null;}
			StringBuilder sb = new StringBuilder();
			sb.append(rs.getDouble("x"));
			sb.append(",");
			sb.append(rs.getDouble("y"));
			sb.append(",");
			sb.append(rs.getDouble("z"));
			sb.append(",");
			sb.append(rs.getFloat("yaw"));
			sb.append(",");
			sb.append(rs.getFloat("pitch"));
			sb.append(",");
			sb.append(rs.getString("world"));
			return sb.toString();
		}catch(SQLException e){e.printStackTrace();}
		return null;
	}
	
	public String listHomes(String player){
		try{
			ResultSet rs = stat.executeQuery("SELECT Name FROM Home WHERE nick = '" + player + "'");
			StringBuilder sb = new StringBuilder();
			sb.append("§aLista: §6");
			while(rs.next() != false){
				sb.append(rs.getString(1));
				sb.append("§a , §6");
			}
			return sb.length() == 0 ? null : sb.toString();
			
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
	private void tryRecconect(){
		MeuPlugin.instance.loadDataBase();
	}
	
	
}
