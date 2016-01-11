package org.sword.wechat4j.common;

import static org.junit.Assert.*;

import org.junit.Test;

public class ConfigTest {

	@Test
	public void testInstance() {
		Config c = Config.instance();
		String appid = c.getAppid();
	}

}
