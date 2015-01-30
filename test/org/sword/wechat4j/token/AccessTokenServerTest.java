/**
 * 
 */
package org.sword.wechat4j.token;

import org.junit.Test;
import org.sword.wechat4j.token.server.AccessTokenServer;

/**
 * @author ChengNing
 * @date   2015年1月30日
 */
public class AccessTokenServerTest {
	
	@Test
	public void test(){
		AccessTokenServer accessTokenServer = new AccessTokenServer();
		accessTokenServer.defaultServer();
	}
}
