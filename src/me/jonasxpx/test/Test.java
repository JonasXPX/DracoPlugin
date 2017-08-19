package me.jonasxpx.test;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONObject;

public class Test {

	public static String novo = "asdasd";
	public static final int MULTI = 200;
	
	public static void main(String[] args) {
		try {
			URL url = new URL("https://ender.tk/filme/data.php?getmovies=1&maxindex=10");
			URLConnection c = url.openConnection();
			c.addRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.0)");
			c.connect();
			String s = IOUtils.toString(c.getInputStream(), "UTF-8");
			JSONArray js = new JSONArray(s.replaceFirst("\\W", ""));
			for (int i = 0; i < js.length(); i++) {
				System.out.println(js.getJSONObject(i).getJSONObject("nome").getString("0"));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}