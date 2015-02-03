/**
 * 
 */
package org.sword.wechat4j.token.server;

import org.sword.wechat4j.token.Token;

/**
 * @author ChengNing
 * @date   2015年1月30日
 */
public abstract class CustomerServer implements IServer {
	
	public String token(){
		return find();
	}
	
	/**
	 * 保存或者更新accesstoken到数据库
	 * 由客户自己实现数据库插入或者更新操作
	 * @param token   得到的token或者ticket，需要保存
	 * @return
	 */
	public abstract boolean save(Token token);
	/**
	 * 从数据库得到accessToken
	 * 由客户自己实现数据库的查询操作
	 * @return
	 */
	protected abstract String find();

}
