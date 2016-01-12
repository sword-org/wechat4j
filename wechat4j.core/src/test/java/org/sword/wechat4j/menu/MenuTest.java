package org.sword.wechat4j.menu;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.sword.wechat4j.event.EventType;
import org.sword.wechat4j.exception.WeChatException;
/**
 * 微信自定义菜单接口测试
 * @author Zhangxs
 * @version 2015-7-4
 */
public class MenuTest {
	private MenuManager manager;
	@Before
	public void init(){
		manager = new MenuManager();
	}
	/**
	 * 创建菜单
	 */
	@Test
	public void createMenu(){
		//单击按钮
		MenuButton btnClick= new MenuButton();
		btnClick.setName("单击按钮");
		btnClick.setType(EventType.click);
		btnClick.setKey("单击按钮");
		//跳转URL
		MenuButton btnView= new MenuButton();
		btnView.setName("跳转URL");
		btnView.setType(EventType.view);
		btnView.setUrl("http://www.baidu.com");
		//扫码推事件
		MenuButton btnScanCodePush= new MenuButton();
		btnScanCodePush.setName("扫码推事件");
		btnScanCodePush.setType(EventType.scancode_push);
		btnScanCodePush.setKey("扫码推事件");
		//扫码带提示
		MenuButton btnScanCodeWaitMsg= new MenuButton();
		btnScanCodeWaitMsg.setName("扫码带提示");
		btnScanCodeWaitMsg.setType(EventType.scancode_waitmsg);
		btnScanCodeWaitMsg.setKey("扫码带提示");
		
		//弹出系统拍照发图
		MenuButton btnPicSysPhoto= new MenuButton();
		btnPicSysPhoto.setName("拍照发图");
		btnPicSysPhoto.setType(EventType.pic_sysphoto);
		btnPicSysPhoto.setKey("拍照发图");
		//弹出拍照或者相册发图
		MenuButton btnPicPhotoOrAlbum= new MenuButton();
		btnPicPhotoOrAlbum.setName("拍照/相册发图");
		btnPicPhotoOrAlbum.setType(EventType.pic_photo_or_album);
		btnPicPhotoOrAlbum.setKey("拍照/相册发图");
		//弹出微信相册发图器
		MenuButton btnPicWeixin= new MenuButton();
		btnPicWeixin.setName("相册发图");
		btnPicWeixin.setType(EventType.pic_weixin);
		btnPicWeixin.setKey("相册发图");
		//弹出地理位置选择器
		MenuButton btnLocationSelect= new MenuButton();
		btnLocationSelect.setName("地理位置");
		btnLocationSelect.setType(EventType.location_select);
		btnLocationSelect.setKey("地理位置");
		
		//下发消息（除文本消息）
		btnLocationSelect.setType(EventType.location_select);
		MenuButton btnMediaId  = new MenuButton();
		btnMediaId.setName("");
		btnMediaId.setType(EventType.media_id);
		btnMediaId.setMediaId("");
		//跳转图文消息URL
		MenuButton btnViewLimited  = new MenuButton();
		btnViewLimited.setName("");
		btnViewLimited.setType(EventType.view_limited);
		btnViewLimited.setMediaId("");
		
		List<MenuButton> subBut1 = new ArrayList<MenuButton>();
		subBut1.add(btnScanCodePush);
		subBut1.add(btnScanCodeWaitMsg);
		subBut1.add(btnPicSysPhoto);
		subBut1.add(btnPicPhotoOrAlbum);
		subBut1.add(btnPicWeixin);
		List<MenuButton> subBut2 = new ArrayList<MenuButton>();
		subBut2.add(btnClick);
		subBut2.add(btnView);
		//二级菜单
		MenuButton subButScanCode = new MenuButton();
		subButScanCode.setName("扫码");
		subButScanCode.setSubButton(subBut1);
		MenuButton sub1 = new MenuButton();
		sub1.setName("单击");
		sub1.setSubButton(subBut2);
		
		List<MenuButton> button = new ArrayList<MenuButton>();
		button.add(sub1);
		button.add(subButScanCode);
		button.add(btnLocationSelect);
		Menu menu = new Menu();
		menu.setButton(button);
		
		try {
			manager.create(menu);
			System.out.println("菜单创建成功");
		} catch (WeChatException e) {
			System.out.println("菜单创建失败");
			e.printStackTrace();
		}
	}
	/**
	 * 查询菜单
	 */
	@Test
	public void  getMenu(){
		Menu menu = manager.getMenu();
		System.out.println(menu.getButton().size());
	}
	/**
	 * 删除菜单
	 */
	@Test
	public void delete(){
		try {
			manager.delete();
			System.out.println("菜单删除成功");
		} catch (WeChatException e) {
			System.out.println("菜单删除失败");
			e.printStackTrace();
		}
	}
}
