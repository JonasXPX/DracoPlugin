package me.jonasxpx.meuplugin2.managers;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.json.JSONObject;

import me.jonasxpx.meuplugin2.estastisticas.Type;

public class StatusDataBase {

	private Connection conn;
	private Statement stat;
	
	public StatusDataBase(String IP, String database, String username, String passw) {
		try{
			conn = DriverManager.getConnection("jdbc:mysql://" + IP + "/" + database, username, passw);
			stat = conn.createStatement();
			stat.executeUpdate("CREATE TABLE IF NOT EXISTS estatisticas(username VARCHAR(32), jsondata TEXT)");
			try{
				DatabaseMetaData meta = conn.getMetaData();
				for(Type key : Type.values()){
					ResultSet re = meta.getColumns(null, null, "estatisticas", key.value);
					if(!re.next()){
						stat.execute("ALTER TABLE estatisticas ADD COLUMN ("+ key.value +" DOUBLE(32,2) DEFAULT 0)");
						System.out.println("COLUMN was created: " + key.value);
					}
				}
			}catch (Exception e) {
			}
			stat.close();
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public int savePlayer(String player, String jsonData){
		player = player.toLowerCase();
		JSONObject json = new JSONObject(jsonData);
		StringBuilder sb = new StringBuilder();
		sb.append("UPDATE estatisticas SET jsondata = ?, ");
		int count = 0;
		for(String key : json.keySet()){
			count++;
			sb.append(key);
			sb.append("=");
			sb.append(json.getJSONArray(key).get(0));
			if(count != json.keySet().size())
				sb.append(", ");
		}
		sb.append(" WHERE username = ?");
		try(PreparedStatement pre = conn.prepareStatement(sb.toString())){
			pre.setString(1, jsonData);
			pre.setString(2, player);
			return pre.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}
		/*try(PreparedStatement pre = conn.prepareStatement("UPDATE estatisticas SET jsondata = ? WHERE username = ?")) {
			pre.setString(1, jsonData);
			pre.setString(2, player);
			return pre.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}*/
		return -1;
	}
	
	public String getDataPlayer(String player){
		player = player.toLowerCase();
		try(PreparedStatement pre = conn.prepareStatement("SELECT jsondata FROM estatisticas WHERE username = ?")){
			pre.setString(1, player);
			ResultSet rs = pre.executeQuery();
			if(rs.next())
				return rs.getString(1);
			else{
				try(PreparedStatement pre1 = conn.prepareStatement("INSERT INTO estatisticas VALUES(?, ?)")){
					pre1.setString(1, player);
					pre1.setString(2, "{}");
					pre1.executeUpdate();
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
