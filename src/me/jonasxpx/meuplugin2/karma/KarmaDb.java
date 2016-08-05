package me.jonasxpx.meuplugin2.karma;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class KarmaDb {

	private static Connection conn;
	private Statement stat;
	private static final String INSERT_K = "INSERT INTO karma VALUES(?, ?)";
	private static final String SELECT_P = "SELECT karma FROM karma WHERE username = ?";
	private static final String UPDATE_T = "UPDATE karma SET karma = ? WHERE username = ?";
	
	
	public KarmaDb(String IP, String database, String username, String passw, int port){
		try {
			System.out.println("Conectando...");
			DriverManager.setLoginTimeout(10);
			conn = DriverManager.getConnection("jdbc:mysql://" + IP + ":"+ port + "/" + database, username, passw);
			stat = conn.createStatement();
			stat.executeUpdate("CREATE TABLE IF NOT EXISTS karma (username VARCHAR(32), karma INT(4))");
		} catch (SQLException e) { 
			e.printStackTrace();
		}
	}
	
	public static void setKarma(String user, int karma){
		try {
			PreparedStatement stat = conn.prepareStatement(INSERT_K);
			stat.setString(1, user);
			stat.setInt(2, karma);
			stat.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static boolean exists(String user){
		try{
			PreparedStatement stat = conn.prepareStatement(SELECT_P);
			stat.setString(1, user);
			ResultSet r = stat.executeQuery();
			while(r.next()){
				if(r.getString(1) != null){
					return true;
				}
			}
		}catch (SQLException e){e.printStackTrace();}
		return false;
	}
	
	public static int getKarma(String user){
		try {
			PreparedStatement stat = conn.prepareStatement(SELECT_P);
			stat.setString(1, user);
			ResultSet r = stat.executeQuery();
			if(r.next()){
				return r.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	public static void updateKarma(String user, int karma){
		try{
			PreparedStatement stat = conn.prepareStatement(UPDATE_T);
			stat.setInt(1, karma);
			stat.setString(2, user);
			stat.execute();
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
}
