/**
 * 
 */
package org.sword.wechat4j.token;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.sword.wechat4j.common.Config;

/**
 * accessToken中控服务器
 * @author ChengNing
 * @date   2015年1月9日
 */
public class AccessTokenServer {
	
	private static Logger logger = Logger.getLogger(AccessTokenServer.class);
	
	public IAccessTokenServer defaultServer(){
		return MemAccessTokenServer.instance();
	}
	
	public IAccessTokenServer customerServer(){
		String className = Config.instance().getAccessTokenServer();
		IAccessTokenServer customerServer = null;
		try {
			Class clazz = Class.forName(className);
			customerServer = (IAccessTokenServer)clazz.newInstance();
		} catch (Exception e) {
			logger.error("系统找不到" + className);
			logger.error("自定义accesstoken server实例化失败，" + e.getMessage());
			e.printStackTrace();
		}
		return customerServer;
	}
	
	/**
	 * 如果配置文件中配置了AccessTokenServer，那么使用客户自定义server
	 * @return
	 */
	public boolean isCustomer(){
		if(StringUtils.isBlank(Config.instance().getAccessTokenServer()))
			return false;
		return true;
	}
}
