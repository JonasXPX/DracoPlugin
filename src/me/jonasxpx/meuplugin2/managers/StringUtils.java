package me.jonasxpx.meuplugin2.managers;

public class StringUtils {

	public static String parceString(String[] string){
		StringBuilder sb = new StringBuilder();
		for(String st : string)
			sb.append(st);
		return sb.toString();
	}
}
