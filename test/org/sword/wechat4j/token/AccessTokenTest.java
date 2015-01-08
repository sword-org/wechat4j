package org.sword.wechat4j.token;

import static org.junit.Assert.*;

import org.junit.Test;

public class AccessTokenTest {

	@Test
	public void testRequest() {
		AccessToken accessToken = new AccessToken();
		accessToken.request();
		String result = accessToken.getAccessToken();
	}

}
