/**
 * 
 */
package org.sword.wechat4j.user;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.sword.lang.HttpUtils;
import org.sword.wechat4j.token.TokenProxy;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;


/**
 * 用户管理
 * @author ChengNing
 * @date   2015年1月7日
 */
public class UserManager {
	
	private static Logger logger = Logger.getLogger(UserManager.class);
	
	private static final String USRE_GET_URL = "https://api.weixin.qq.com/cgi-bin/user/get?access_token=";
	
	
	private String accessToken;
	private String nextOpenId;
	private long total = 0;
	
	
	public UserManager(){
		this.accessToken = TokenProxy.accessToken();
	}
	
	/**
	 * 调用之后的nextOpenId
	 * @return
	 */
	public String getNextOpenId(){
		return this.nextOpenId;
	}
	
	/**
	 * 关注者总数,必须先调用一下subscriberList方法才能得到total
	 * @return
	 */
	public long getTotal(){
		return this.total;
	}
	
	/**
	 * 获取帐号的关注者列表前10000人
	 * https://api.weixin.qq.com/cgi-bin/user/get?access_token=ACCESS_TOKEN&next_openid=NEXT_OPENID
	 * @return openid 的list
	 */
	public List<Object> subscriberList(){
		return subscriberList(null);
	}
	
	/**
	 * 获取所有的关注者列表
	 * @return openid 的list
	 */
	public List<Object> allSubscriber(){
		List<Object> all = new ArrayList<Object>();
		List<Object> firstPage = subscriberList();
		all.addAll(firstPage);
		//遍历
		if(StringUtils.isNotBlank(this.nextOpenId)){
			List<Object> list = subscriberList(this.nextOpenId);
			all.addAll(list);
		}
		return all;
	}
	
	/**
	 * 获取帐号的关注者列表
	 * https://api.weixin.qq.com/cgi-bin/user/get?access_token=ACCESS_TOKEN&next_openid=NEXT_OPENID
	 * @return List<String> openid 的列表
	 */
	public List<Object> subscriberList(String nextOpenId){
		String url = USRE_GET_URL + accessToken;
		if(StringUtils.isNotBlank(nextOpenId)){
			url += "&next_openid=" + nextOpenId;
		}
		String result = HttpUtils.get(url);
		if(StringUtils.isBlank(result))
			return null;
		return parseUserResult(result);
	}
	
	/**
	 * 成功 {"total":2,"count":2,"data":{"openid":["","OPENID1","OPENID2"]},"next_openid":"NEXT_OPENID"}
	 * 失败{"errcode":40013,"errmsg":"invalid appid"}
	 * @param result  
	 * @return
	 */
	private List<Object> parseUserResult(String result){
		JSONObject json = JSONObject.parseObject(result);
		//获取错误
		if(json.containsKey("errcode")){
			logger.error("获取帐号的关注者列表错误:" + json.getString("errmsg"));
			return null;
		}
		//获取成功
		this.nextOpenId = json.getString("next_openid");
		//第一次设置total，每次的total都一样
		if(total == 0){
			this.total = Long.valueOf(json.getString("total"));
		}
		JSONArray list =json.getJSONObject("data").getJSONArray("openid");
		logger.info("获取帐号的关注者列表成功:本次获取" + list.size());
		return list;
	}
}
