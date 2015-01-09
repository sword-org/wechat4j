/**
 * 
 */
package org.sword.wechat4j.token;

/**
 * 数据库管理access token
 * 这是一个抽象类，需要由使用者自己去继承此类，并且实现自己的数据库操作
 * @author ChengNing
 * @date   2015年1月8日
 */
public abstract class DbAccessTokenServer implements IAccessTokenServer{

	@Override
	public String getAccessToken() {
		return find();
	}
	
	/**
	 * 保存或者更新accesstoken到数据库
	 * 由客户自己实现数据库插入或者更新操作
	 * @return
	 */
	public abstract boolean save();
	/**
	 * 从数据库得到accessToken
	 * 由客户自己实现数据库的查询操作
	 * @return
	 */
	public abstract String find();

}
