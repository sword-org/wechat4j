/**
 * 
 */
package org.sword.wechat4j.token;


/**
 * AccessToken代理
 * 所有获取accessToken的地方都通过此代理获得
 * 获得方法String token = AccessTokenProxy.token()
 * @author ChengNing
 * @date   2015年1月9日
 */
public class AccessTokenProxy {
	
	/**
	 * 通过代理得到accessToken的串
	 * @return
	 */
	public static String token(){
		AccessTokenServer accessTokenServer = new AccessTokenServer();
		if(accessTokenServer.isCustomer())
			return accessTokenServer.customerServer().getAccessToken();
		
		return accessTokenServer.defaultServer().getAccessToken();
	}
	

	
	
}
