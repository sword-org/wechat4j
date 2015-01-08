/**
 * 
 */
package org.sword.wechat4j.token;

import java.util.Timer;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;


/**
 * Access Token 监听器
 * @author ChengNing
 * @date   2015年1月8日
 */
public class AccessTokenListener implements ServletContextListener{

    private static Logger log = Logger.getLogger(AccessTokenListener.class);
	
	private Timer timer = null;
	//accessToken有效期7200秒,提前200秒请求新的token
	private static final long accessTokenExpire = 7000; 

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		log.info("accessToken监听器启动..........");
		timer = new Timer(true);
		AccessTokenTimer accessTokenTimer = new AccessTokenTimer();
		timer.schedule(accessTokenTimer, accessTokenExpire * 1000);
		log.info("accessToken定时器注册成功，执行间隔为" + accessTokenExpire);
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		timer.cancel();
	}
	
}
