package org.sword.wechat4j.token;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TokenProxyTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	
//	@Test
//	public void tokenTest(){
//		String accessToken = TokenProxy.accessToken();
//		System.out.println(accessToken);
//		
//	}
	
	@Test
	public void jsApiTicketTest(){
		String jsApiTicket = TokenProxy.jsApiTicket();
		System.out.println(jsApiTicket);
	}

}
