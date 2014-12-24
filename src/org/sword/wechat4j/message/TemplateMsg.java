/**
 * 
 */
package org.sword.wechat4j.message;

/**
 * 模板消息接口
 * @author ChengNing
 * @date   2014年12月24日
 */
public class TemplateMsg {
	
	public static final String SET_INDUSTRY_URL = "https://api.weixin.qq.com/cgi-bin/template/api_set_industry?access_token=ACCESS_TOKEN";

	public static final String ADD_TEMPLATE_URL = "https://api.weixin.qq.com/cgi-bin/template/api_add_template?access_token=ACCESS_TOKEN";
	
	public static final String SEND_MSG_URL = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=ACCESS_TOKEN";
	/**
	 * 设置所属行业
	 */
	public void setIndustry(){
		
	}
	
	/**
	 * 获得模板ID
	 */
	public void addTemplate(){
		
	}
	
	/**
	 * 发送模板消息
	 */
	public void send(){
		
	}
	
	/**
	 * 发送结果事件推送
	 */
	public void callback(){
		
	}
	
	/**
	 * 发送成功
	 */
	public void sendSuccess(){
		
	}
	
	/**
	 * 发送失败
	 */
	public void sendFailed(){
		
	}
}
