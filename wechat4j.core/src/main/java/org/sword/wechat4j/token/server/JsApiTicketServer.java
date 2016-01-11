/**
 * 
 */
package org.sword.wechat4j.token.server;

import org.sword.wechat4j.common.Config;


/**
 * Ticket server适配器
 * @author ChengNing
 * @date   2015年1月29日
 */
public class JsApiTicketServer extends AbsServer implements TicketServer  {


	/**
	 * 
	 */
	public String ticket() {
		return super.token();
	}

	/**
	 * 
	 */
	@Override
	protected String getCustomerServerClass() {
		return Config.instance().getJsApiTicketServer();
	}

	/**
	 * 
	 */
	@Override
	public IServer defaultServer() {
		return JsApiTicketMemServer.instance();
	}

}
