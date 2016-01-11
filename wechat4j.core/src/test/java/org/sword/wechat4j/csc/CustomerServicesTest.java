package org.sword.wechat4j.csc;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Before;
import org.junit.Test;
import org.sword.lang.HttpUtils;
import org.sword.wechat4j.csc.CustomerServices;
import org.sword.wechat4j.csc.CustomerServicesManager;
import org.sword.wechat4j.csc.CustomerServicesSession;
import org.sword.wechat4j.csc.Record;
import org.sword.wechat4j.exception.WeChatException;
import org.sword.wechat4j.token.TokenProxy;

import com.alibaba.fastjson.JSONObject;

public class CustomerServicesTest {
	private CustomerServicesManager manager;
	@Before
	public void init(){
		manager = new CustomerServicesManager();
	}
	/**
	 * 创建会话
	 */
	@Test
	public void create(){
		String openId = "otweTt4fjjPDZtGlQx_w5XfV42sc";
		String kfAccount = "service01@wechat4j";
		try {
			manager.kfSessionCreate(openId, kfAccount);
			System.out.println("会话创建成功");
		} catch (WeChatException e) {
			System.out.println("会话创建失败");
			e.printStackTrace();
		}
	}
	/**
	 * 关闭会话
	 */
	@Test
	public void close(){
		String openId = "otweTt4fjjPDZtGlQx_w5XfV42sc";
		String kfAccount = "service01@wechat4j";
		try {
			manager.kfSessionClose(openId, kfAccount);
			System.out.println("会关闭建成功");
		} catch (WeChatException e) {
			System.out.println("会关闭建失败");
			e.printStackTrace();
		}
	}
	/**
	 *  获取客户的会话状态
	 */
	@Test
	public void getSession(){
		String openId = "otweTt4fjjPDZtGlQx_w5XfV42sc";
		System.out.println("客户["+openId+"]会话状态");
		CustomerServicesSession customerServicesSession = manager.getSession(openId);
		System.out.println("会话创建时间:"+customerServicesSession.getCreateTime());
		System.out.println("客服:"+customerServicesSession.getKfAccount());
	}
	/**
	 * 获取客服的会话列表
	 */
	@Test
	public void getSessionList(){
		String kfAccount = "service01@wechat4j";
		System.out.println("客服["+kfAccount+"]会话列表");
		List<CustomerServicesSession> customerServicesSessions = manager.getSessionList(kfAccount);
		for (CustomerServicesSession customerServicesSession : customerServicesSessions) {
			System.out.println("客户:"+customerServicesSession.getOpenId());
			System.out.println("会话创建时间:"+customerServicesSession.getCreateTime());
		}
	}
	/**
	 * 获取未接入会话列表
	 */
	@Test
	public void getWaitCaseList(){
		List<CustomerServicesSession> customerServicesSessions = manager.getWaitCaseList();
		System.out.println("未接入会话列表");
		for (CustomerServicesSession customerServicesSession : customerServicesSessions) {			
			System.out.println("客户:"+customerServicesSession.getOpenId());
			System.out.println("会话创建时间:"+customerServicesSession.getCreateTime());
		}
	}
	
	/**
	 * 获取客服基本信息
	 */
	@Test
	public void getKfList(){
		List<CustomerServices> customerServices = manager.getKfList();
		System.out.println("获取所有客服");
		for (CustomerServices cs : customerServices) {
			System.out.println("完整客服账号:"+cs.getKfAccount());
			System.out.println("客服头像:"+cs.getKfHeadimgurl());
			System.out.println("客服工号:"+cs.getKfId());
			System.out.println("客服昵称:"+cs.getKfNick());
		}
		
	}
	/**
	 * 获取在线客服接待信息
	 */
	@Test
	public void getOnlieKfList(){
		List<CustomerServices> customerServices = manager.getOnlieKfList();
		System.out.println("获取在线客服接待信息");
		for (CustomerServices cs : customerServices) {
			System.out.println("完整客服账号:"+cs.getKfAccount());
			System.out.println("客服状态:"+cs.getStatus());
			System.out.println("客服工号:"+cs.getKfId());
			System.out.println("客服最大自动接入数:"+cs.getAutoAccept());
			System.out.println("客服当前接待数:"+cs.getAcceptedCase());
		}
	}
	/**
	 * 添加客服账号
	 */
	@Test
	public void kfAddAccount(){
		String kfAccount="service03@wechat4j";
		String nickName ="客服03";
		String password ="service03";
		try {
			manager.kfAddAccount(kfAccount, nickName, DigestUtils.md5Hex(password).toUpperCase());
			System.out.println("添加客服成功");
		} catch (WeChatException e) {
			System.out.println("添加客服失败");
			e.printStackTrace();
		}
	}
	/**
	 * 设置客服信息
	 */
	@Test
	public void kfUpdateAccount(){
		String kfAccount="service03@wechat4j";
		String nickName ="客服";
		String password ="service";
		try {
			manager.kfUpdateAccount(kfAccount, nickName,  DigestUtils.md5Hex(password));
			System.out.println("设置客服信息成功");
		} catch (WeChatException e) {
			System.out.println("设置客服信息失败");
			e.printStackTrace();
		}
	}
	
	/**
	 * 上传客服头像
	 */
	@Test
	public void kfUploadHeadImg(){
		String kfAccount="service01@wechat4j";
		try {
			
			manager.kfUploadHeadImg(kfAccount, new File("D://wtb.jpg"));
			System.out.println("头像上传成功");
		} catch (WeChatException e) {
			System.out.println("头像上传失败");
			e.printStackTrace();
		}
		
	}
	/**
	 * 删除客服账号
	 */
	@Test
	public void kfDelAccount(){
		String kfAccount="service03@wechat4j";
		try {
			manager.kfDelAccount(kfAccount);
			System.out.println("客服删除成功");
		} catch (WeChatException e) {
			System.out.println("客服删除失败");
			e.printStackTrace();
		}
		
	}
	/**
	 * 获取客服聊天记录
	 */
	@Test
	public void getRecord(){
		long starttime=System.currentTimeMillis()/1000L-20*60*60;
		long endtime=System.currentTimeMillis()/1000L;
		int pageindex=1;
		int pagesize=50;
		List<Record> records = manager.getRecord(starttime, endtime, pageindex, pagesize);
		for (Record record : records) {
			System.out.println("用户的标识:"+record.getOpenid());
			System.out.println("操作ID:"+record.getOpercode());
			System.out.println("会话状态:"+RecordOperCode.getSessionState(record.getOpercode()));
			System.out.println("聊天记录:"+record.getText());
			System.out.println("操作时间，UNIX时间戳:"+record.getTime());
			System.out.println("客服账号:"+record.getWorker());
		}
	}

}
