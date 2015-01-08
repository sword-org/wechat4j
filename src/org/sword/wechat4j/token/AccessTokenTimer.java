/**
 * 
 */
package org.sword.wechat4j.token;

import java.util.TimerTask;

import org.apache.log4j.Logger;
import org.sword.wechat4j.common.Config;

/**
 * access token 定时器
 * @author ChengNing
 * @date   2015年1月8日
 */
public class AccessTokenTimer extends TimerTask{
	
	private static Logger logger = Logger.getLogger(AccessTokenTimer.class);

	@Override
	public void run() {
		logger.info("accessToken 定时任务启动，获取新的accessToken");
		//得到新的access token
		AccessToken accessToken = new AccessToken();
		//获取成功之后持久化accessToken
		if(accessToken.request()){
			String className = Config.instance().getToken();
			try {
				Class clazz = Class.forName(className);
				DbAccessTokenServer accessTokenServer = (DbAccessTokenServer)clazz.newInstance();
				accessTokenServer.save();
			} catch (Exception e) {
				logger.error("accessToken初始化失败，" + e.getMessage());
				e.printStackTrace();
			}
		}
	}

}
