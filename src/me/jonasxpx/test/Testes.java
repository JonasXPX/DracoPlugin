package me.jonasxpx.test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import me.jonasxpx.meuplugin2.managers.HomeManagerSQL;

public class Testes {

	HomeManagerSQL hmsql;
	
	@Before
	public void setUp() throws Exception {
		hmsql = new HomeManagerSQL("192.168.1.2", "mc_4720", "root", "***", 3306);
	}

	@Test
	public void testUpdateStringStringIntIntIntString() {
		assertEquals(1, hmsql.update("jonas", "casa", 51, 152, 1236, "worlds"));
	}

	
	
}
