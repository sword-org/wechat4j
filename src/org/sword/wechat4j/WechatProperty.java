/**
 * 
 */
package org.sword.wechat4j;

/**
 * 配置文件
 * 是否需要加密实现decode方法
 * @author ChengNing
 * @date   2014-12-4
 */
public abstract class WechatProperty {

	private String url;
	private String token;
	private String appid;
	private String secret;
	
	protected abstract String decode(String text);
	
}
