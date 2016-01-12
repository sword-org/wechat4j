
package org.sword.wechat4j.csc;

import java.io.File;
import java.util.List;

import org.apache.log4j.Logger;
import org.sword.lang.HttpUtils;
import org.sword.wechat4j.exception.WeChatException;
import org.sword.wechat4j.token.TokenProxy;
import org.sword.wechat4j.util.WeChatUtil;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
/**
 * 客服管理
 * @author Zhangxs
 * @date 2015-7-7
 * @version
 */
public class CustomerServicesManager {
	private static Logger logger = Logger.getLogger(CustomerServicesManager.class);
	
	/* 创建会话*/
	private static final String CUSTOMSERVICE_KFSESSION_CREATE_POST_URL = "https://api.weixin.qq.com/customservice/kfsession/create?access_token=";
	/* 关闭会话*/
	private static final String CUSTOMSERVICE_KFSESSION_CLOSE_POST_URL = "https://api.weixin.qq.com/customservice/kfsession/close?access_token=";
	/* 获取客户的会话状态*/
	private static final String CUSTOMSERVICE_KFSESSION_GETSESSION_GET_URL = "https://api.weixin.qq.com/customservice/kfsession/getsession?access_token=";
	/* 获取客服的会话列表*/
	private static final String CUSTOMSERVICE_KFSESSION_GETSESSIONLIST_GET_URL = "https://api.weixin.qq.com/customservice/kfsession/getsessionlist?access_token=";
	/* 获取未接入会话列表*/
	private static final String CUSTOMSERVICE_KFSESSION_GETWAITCASE_GET_URL = "https://api.weixin.qq.com/customservice/kfsession/getwaitcase?access_token=";
	/* 获取客服基本信息*/
	private static final String CUSTOMSERVICE_GETKFLIST_GET_URL = "https://api.weixin.qq.com/cgi-bin/customservice/getkflist?access_token=";
	/* 获取在线客服接待信息*/
	private static final String CUSTOMSERVICE_GETONLIEKFLIST_GET_URL = "https://api.weixin.qq.com/cgi-bin/customservice/getonlinekflist?access_token=";
	/* 添加客服账号*/
	private static final String CUSTOMSERVICE_KFACCOUNT_ADD_POST_URL = "https://api.weixin.qq.com/customservice/kfaccount/add?access_token=";
	/* 设置客服信息*/
	private static final String CUSTOMSERVICE_KFACCOUNT_UPDATE_POST_URL = "https://api.weixin.qq.com/customservice/kfaccount/update?access_token=";
	/* 上传客服头像*/
	private static final String CUSTOMSERVICE_KFACCOUNT_UPLOADHEADIMG_POST_URL = "http://api.weixin.qq.com/customservice/kfaccount/uploadheadimg?access_token=";
	/* 删除客服账号*/
	private static final String CUSTOMSERVICE_KFACCOUNT_DEL_POST_URL = "https://api.weixin.qq.com/customservice/kfaccount/del?access_token=";
	// 获取客服聊天记录接口
	private static final String CUSTOMSERVICE_MSGRECORD_GETRECORD_POST_URL="https://api.weixin.qq.com/customservice/msgrecord/getrecord?access_token=";
	private static final String PARAM_FILE = "media";
	private String accessToken;
	public CustomerServicesManager() {
		this.accessToken = TokenProxy.accessToken();
	}
	/**
	 * 创建会话
	 * @param openid 客户openid
	 * @param kf_account 完整客服账号，格式为：账号前缀@公众号微信号
	 * @return
	 * @throws WeChatException 
	 */
	public void kfSessionCreate(String openId,String kfAccount) throws WeChatException{
		kfSessionCreate(openId, kfAccount, null);
	}
	/**
	 * 创建会话
	 * @param openid 客户openid
	 * @param kf_account 完整客服账号，格式为：账号前缀@公众号微信号
	 * @param text 附加信息，文本会展示在客服人员的多客服客户端
	 * @return
	 * @throws WeChatException 
	 */
	public void kfSessionCreate(String openId,String kfAccount,String text) throws WeChatException{
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("openid", openId);
		jsonObject.put("kf_account", kfAccount);
		if (text!=null){
			jsonObject.put("text", text);			
		}
		String resultStr = HttpUtils.post(CUSTOMSERVICE_KFSESSION_CREATE_POST_URL+this.accessToken,jsonObject.toJSONString());
		WeChatUtil.isSuccess(resultStr);
	}
	/**
	 * 关闭会话
	 * @param openid 客户openid
	 * @param kf_account 完整客服账号，格式为：账号前缀@公众号微信号
	 * @return
	 * @throws WeChatException 
	 */
	public void kfSessionClose(String openId,String kfAccount) throws WeChatException{
		kfSessionClose(openId, kfAccount, null);
	}
	/**
	 * 关闭会话
	 * @param openid 客户openid
	 * @param kf_account 完整客服账号，格式为：账号前缀@公众号微信号
	 * @param text 完整客服账号，格式为：账号前缀@公众号微信号
	 * @return
	 * @throws WeChatException 
	 */
	public void kfSessionClose(String openId,String kfAccount,String text) throws WeChatException{
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("openid", openId);
		jsonObject.put("kf_account", kfAccount);
		if (text!=null){
			jsonObject.put("text", text);			
		}
		String resultStr = HttpUtils.post(CUSTOMSERVICE_KFSESSION_CLOSE_POST_URL+this.accessToken,jsonObject.toJSONString());
		WeChatUtil.isSuccess(resultStr);
	}
	/**
	 * 获取客户的会话状态 
	 * @param openId
	 * @return
	 */
	public CustomerServicesSession getSession(String openId) {
		String resultStr = HttpUtils.get(CUSTOMSERVICE_KFSESSION_GETSESSION_GET_URL+this.accessToken+"&openid="+openId);
		try {
			WeChatUtil.isSuccess(resultStr);
		} catch (WeChatException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			return null;
		}
		CustomerServicesSession customerServicesSession = JSON.parseObject(resultStr, CustomerServicesSession.class);
		return customerServicesSession;
	}
	/**
	 * 获取客服的会话列表
	 * @param KfAccount
	 * @return
	 */
	public List<CustomerServicesSession> getSessionList(String kfAccount) {
		String resultStr = HttpUtils.get(CUSTOMSERVICE_KFSESSION_GETSESSIONLIST_GET_URL+this.accessToken+"&kf_account="+kfAccount);
		try {
			WeChatUtil.isSuccess(resultStr);
		} catch (WeChatException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			return null;
		}
		String sessionlist = JSON.parseObject(resultStr).getString("sessionlist");
		List<CustomerServicesSession> customerServicesSessions = JSON.parseArray(sessionlist, CustomerServicesSession.class);
		return customerServicesSessions;
	}
	/**
	 * 获取未接入会话列表
	 * @return
	 */
	public List<CustomerServicesSession> getWaitCaseList() {
		String resultStr = HttpUtils.get(CUSTOMSERVICE_KFSESSION_GETWAITCASE_GET_URL+this.accessToken);
		try {
			WeChatUtil.isSuccess(resultStr);
		} catch (WeChatException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			return null;
		}
		String waitcaselist = JSON.parseObject(resultStr).getString("waitcaselist");
		List<CustomerServicesSession> customerServicesSessions = JSON.parseArray(waitcaselist, CustomerServicesSession.class);
		return customerServicesSessions;
	}
	/**
	 * 获取所有客服账号	
	 * @return
	 */
	public List<CustomerServices> getKfList() {
		String resultStr = HttpUtils.get(CUSTOMSERVICE_GETKFLIST_GET_URL+this.accessToken);
		try {
			WeChatUtil.isSuccess(resultStr);
		} catch (WeChatException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			return null;
		}
		String kf_list = JSONObject.parseObject(resultStr).getString("kf_list");
		List<CustomerServices> list = JSON.parseArray(kf_list,  CustomerServices.class);
		return list;
	}
	 
	/**
	 * 获取在线客服接待信息
	 * @return
	 */
	 
	public List<CustomerServices> getOnlieKfList() {
		String resultStr = HttpUtils.get(CUSTOMSERVICE_GETONLIEKFLIST_GET_URL+this.accessToken);		
		try {
			WeChatUtil.isSuccess(resultStr);
		} catch (WeChatException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			return null;
		}
		JSONObject jsonObject = JSONObject.parseObject(resultStr);
		List<CustomerServices> list = JSON.parseArray(jsonObject.getString("kf_online_list"),  CustomerServices.class);
		return list;
	}
	
	/**
	 * 添加客服账号 
	 * @param kfAccount 完整客服账号，格式为：账号前缀@公众号微信号，账号前缀最多10个字符，必须是英文或者数字字符。如果没有公众号微信号，请前往微信公众平台设置。
	 * @param nickName 客服昵称，最长6个汉字或12个英文字符
	 * @param password 客服账号登录密码，格式为密码明文的32位加密MD5值
	 * @return
	 * @throws WeChatException 
	 */
	public void kfAddAccount(String kfAccount,String nickName,String password) throws WeChatException{
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("kf_account", kfAccount);
		jsonObject.put("nickname", nickName);
		jsonObject.put("password", password);
		String resultStr = HttpUtils.post(CUSTOMSERVICE_KFACCOUNT_ADD_POST_URL+this.accessToken,jsonObject.toJSONString());
		WeChatUtil.isSuccess(resultStr);
	}
	/**
	 * 设置客服信息
	 * @param kf_account 完整客服账号，格式为：账号前缀@公众号微信号
	 * @param nickname	客服昵称，最长6个汉字或12个英文字符
	 * @param password	客服账号登录密码，格式为密码明文的32位加密MD5值
	 * @return
	 * @throws WeChatException 
	 */
	public void kfUpdateAccount(String kfAccount,String nickName,String password) throws WeChatException {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("kf_account", kfAccount);
		jsonObject.put("nickname", nickName);
		jsonObject.put("password", password);
		String resultStr =HttpUtils.post(CUSTOMSERVICE_KFACCOUNT_UPDATE_POST_URL+this.accessToken, jsonObject.toJSONString());
		WeChatUtil.isSuccess(resultStr);
	}
	/**
	 * 上传客服头像
	 * 头像图片文件必须是jpg格式，推荐使用640*640大小的图片以达到最佳效果
	 * @param kfAccount 完整客服账号，格式为：账号前缀@公众号微信号
	 * @param file	客服头像
	 * @return
	 * @throws WeChatException 
	 */
	public void kfUploadHeadImg(String kfAccount,File file) throws WeChatException{
		String resultStr = HttpUtils.postFile(CUSTOMSERVICE_KFACCOUNT_UPLOADHEADIMG_POST_URL+this.accessToken+"&kf_account="+kfAccount,PARAM_FILE, file);
		WeChatUtil.isSuccess(resultStr);
	}

	/**
	 * 删除客服账号
	 * @param kfAccount	完整客服账号，格式为：账号前缀@公众号微信号
	 * @return
	 * @throws WeChatException 
	 */
	public void kfDelAccount(String kfAccount) throws WeChatException{
		String resultStr = HttpUtils.post(CUSTOMSERVICE_KFACCOUNT_DEL_POST_URL+this.accessToken+"&kf_account="+kfAccount);
		WeChatUtil.isSuccess(resultStr);
	}
	/**
	 * 获取客服聊天记录
	 * @param starttime 查询开始时间，UNIX时间戳
	 * @param endtime 查询结束时间，UNIX时间戳，每次查询不能跨日查询
	 * @param pageindex 查询第几页，从1开始
	 * @param pagesize 每页大小，每页最多拉取50条
	 * @return
	 */
	public List<Record> getRecord(long starttime,long endtime, int pageindex,int pagesize) {
		JSONObject data = new JSONObject();
		data.put("endtime", endtime);
		data.put("pageindex", pageindex);
		data.put("pagesize", pagesize);
		data.put("starttime", starttime);
		String resultStr = HttpUtils.post(CUSTOMSERVICE_MSGRECORD_GETRECORD_POST_URL+this.accessToken, data.toJSONString());
		try {
			WeChatUtil.isSuccess(resultStr);
		} catch (WeChatException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			return null;
		}
		String recordlist = JSON.parseObject(resultStr).getString("recordlist");
		List<Record> records = JSON.parseArray(recordlist, Record.class);
		return records;
	}
}
