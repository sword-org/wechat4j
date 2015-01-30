/**
 * 
 */
package org.sword.wechat4j.token.timer;

import java.util.TimerTask;

import org.apache.log4j.Logger;
import org.sword.wechat4j.token.Ticket;
import org.sword.wechat4j.token.TicketType;
import org.sword.wechat4j.token.server.CustomerServer;
import org.sword.wechat4j.token.server.JsApiTicketServer;

/**
 * @author ChengNing
 * @date 2015年1月29日
 */
public class JsApiTicketTimer extends TimerTask {

	private static Logger logger = Logger.getLogger(JsApiTicketTimer.class);
	// jsapi_ticket有效期7200秒,提前200秒请求新的token
	public static final long PERIOD = 7000 * 1000;
	public static final long DELAY = 0; // 此任务的延迟时间为0，即立即执行

	@Override
	public void run() {
		logger.info("jsapi_ticket 定时任务启动，获取新的jsapi_ticket");
		// 得到新的access token
		Ticket jsapiTicket = new Ticket(TicketType.jsapi);
		// 手动获取成功之后持久化accessToken
		if (jsapiTicket.request()) {
			JsApiTicketServer jsapiTicketServer = new JsApiTicketServer();
			CustomerServer customerServer = (CustomerServer) jsapiTicketServer
					.customerServer();
			customerServer.save(jsapiTicket);
		}
	}

}
