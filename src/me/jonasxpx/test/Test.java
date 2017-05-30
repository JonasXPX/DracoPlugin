package me.jonasxpx.test;

import org.json.JSONObject;

public class Test {

	public static String novo = "asdasd";
	public static final int MULTI = 200;
	
	public static void main(String[] args) {
		/*int level = 3852;
		double nivel = level / (MULTI);
		System.out.println(Math.floor(1.5));
		System.out.println((int)nivel + " - " + level + "-" + ((MULTI) * (nivel + 1)));*/
		String json = "{\"mobkills\":[1],\"Kills\":[0],\"dead\":[1],\"walk\":[5469]}";
		JSONObject j = new JSONObject(json);
		for(String data : j.keySet()){
			System.out.println(data + ":" + j.getJSONArray(data).get(0));
		}
	}
}
