package org.sword.wechat4j.util;

import static org.junit.Assert.*;

import org.junit.Test;

public class ConfigTest {

	@Test
	public void instanceTest() {
		String token = "";
		String expect1 = Config.instance().getToken();
		
		String expect2 = Config.instance().getToken();
		assertEquals(expect1, expect2);
	}

}
