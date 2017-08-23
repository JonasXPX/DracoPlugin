package me.jonasxpx.meuplugin2.managers;

import java.util.HashMap;

import me.jonasxpx.meuplugin2.MeuPlugin;

public class SimpleCache<S, V> extends HashMap<S, V>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4764262166236962554L;
	
	public void createCache(S key, V value){
		MeuPlugin.instance.getLogger().info("Cache of " + ((String)key) + " was create.");
		put(key, value);
	}
	
	public void updateCache(S key, V value){
		replace(key, value);
	}
	
	public V getCache(S key){
		return get(key);
	}
	
	
}
