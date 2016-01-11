package org.sword.wechat4j.user;


import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.sword.wechat4j.exception.WeChatException;

public class UserTest {
	private UserManager manager;
	@Before
	public void init(){
		manager = new UserManager();
	}
	/**
	 * 获取所有的关注者列表
	 */
	@Test
	public void allSubscriber(){
		List<String> openids = manager.allSubscriber();
		for (String openid : openids) {
			System.out.println(openid);			
		}
	}
	/**
	 * 获取前10000个关注者列表
	 */
	@Test
	public void subscriberList(){
		Follwers follwers = manager.subscriberList();
		System.out.println("关注者数量:"+follwers.getCount());
	}
	/**
	 * 设置用户备注名
	 */
	@Test
	public void updateRemark(){
		try {
			manager.updateRemark("otweTt8vRASrS-zji8v4YSStH-cc", "张永帅");
			System.out.println("备注名设置成功");
		} catch (WeChatException e) {
			System.out.println("备注名设置失败");
			e.printStackTrace();
		}
	}
	/**
	 * 获取用户基本信息
	 */
	@Test
	public void getUserInfo(){
		String openId = "otweTt8vRASrS-zji8v4YSStH-cc";
		User user = manager.getUserInfo(openId);
		System.out.println(user.toString());
		user = manager.getUserInfo(openId,LanguageType.en);
		System.out.println(user.toString());
	}
	/**
	 * 创建分组
	 */
	@Test
	public void createGroup(){
		try {
			manager.createGroup("分组1");
			System.out.println("创建分组成功");
		} catch (WeChatException e) {
			System.out.println("创建分组失败");
			e.printStackTrace();
		}
		
	}
	/**
	 * 查询所有分组
	 */
	@Test
	public void getGroup(){
		List<Group> groups = manager.getGroup();
		for (Group group : groups) {
			System.out.println("分组Id:"+group.getId());			
			System.out.println("分组名称:"+group.getName());			
			System.out.println("分组人数:"+group.getCount());			
		}
	}
	/**
	 *  查询用户所在分组
	 */
	@Test
	public void getIdGroup(){
		String openId ="otweTt8vRASrS-zji8v4YSStH-cc";
		int groupId = manager.getIdGroup(openId);
		System.out.println("分组Id:"+groupId);
	}
	/**
	 * 修改分组名
	 */
	@Test
	public void updateGroup(){
		int groupId=106;
		String name = "分组4";
		try {
			manager.updateGroup(groupId, name);
			System.out.println("操作成功");
		} catch (WeChatException e) {
			System.out.println("操作失败");
			e.printStackTrace();
		}
	}
	/**
	 * 移动用户分组
	 */
	@Test
	public void membersUpdateGroup(){
		int groupId = 106;
		String openId="otweTt8vRASrS-zji8v4YSStH-cc";
		try {
			manager.membersUpdateGroup(openId, groupId);
			System.out.println("操作成功");
		} catch (WeChatException e) {
			System.out.println("操作失败");
			e.printStackTrace();
		}
	}
	/**
	 *  批量移动用户分组
	 */
	@Test
	public void membersDatchUpdateGroup(){
		String [] openIds={"otweTt8vRASrS-zji8v4YSStH-cc","otweTt4fjjPDZtGlQx_w5XfV42sc"};
		int groupId=103;
		try {
			manager.membersDatchUpdateGroup(openIds, groupId);
			System.out.println("操作成功");
		} catch (WeChatException e) {
			System.out.println("操作失败");
			e.printStackTrace();
		}
	}
	/**
	 * 删除分组
	 */
	@Test
	public void deleteGroup(){
		int groupId = 109;
		try {
			manager.deleteGroup(groupId);
			System.out.println("操作成功");
		} catch (WeChatException e) {
			System.out.println("操作失败");
			e.printStackTrace();
		}
	}
}
