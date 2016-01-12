package org.sword.wechat4j.token;

import org.junit.Test;

public class TicketTest {
	
	@Test
	public void test(){
		String jsapiTicket = TokenProxy.jsApiTicket();
		String expi = "";
		System.out.println(jsapiTicket);
	}

}
