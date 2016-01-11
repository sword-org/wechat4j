/**
 * 
 */
package org.sword.wechat4j.event;

/**
 * 微信事件类型，event字段的枚举<br/>
 * <b>scancode_push、scancode_waitmsg、pic_sysphoto、pic_photo_or_album、pic_weixin、location_select</b><br/>
 * 仅支持微信iPhone5.4.1以上版本,和Android5.4以上版本的微信用户<br/>
 * <b>media_id,view_limited</b><br/>
 * 仅限第三方平台旗下未微信认证（具体而言，是资质认证未通过）的订阅号准备的事件类型,没有事件推送
 * @author ChengNing
 * @author Zhangxs
 * @date 2015-7-6
 * 
 */
public enum EventType {
	subscribe,             //关注
	unsubscribe,           //取消关注
	/** 创建菜单使用 */
	click,				   
	CLICK,                 //点击
	/** 创建菜单使用  */
	view,				   
	VIEW,                  //跳转链接
	SCAN,                  //扫描
	LOCATION,              //上报地理位置
	TEMPLATESENDJOBFINISH, //模板消息发送成功之后事件
	scancode_push,         //扫码推事件
	scancode_waitmsg,      //扫码推事件且弹出“消息接收中”提示框的事件
	pic_sysphoto,          //弹出系统拍照发图的事件
	pic_photo_or_album,    //弹出拍照或者相册发图的事件
	pic_weixin,            //弹出微信相册发图器的事件
	location_select,       //弹出地理位置选择器的事件
	media_id,			   //下发消息(除文本消息)
	view_limited,		   //跳转图文消息URL 
	kf_create_session,	   //接入会话
	kf_close_session,	   //关闭会话
	kf_switch_session,	   //转接会话
}
