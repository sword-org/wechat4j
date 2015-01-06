/**
 * 
 */
package org.sword.wechat4j.event;

/**
 * 微信事件类型，event字段的枚举
 * @author ChengNing
 * @date   2014-12-4
 */
public enum EventType {
	subscribe,             //关注
	unsubscribe,           //取消关注
	CLICK,                 //点击
	SCAN,                  //扫描
	LOCATION,              //上报地理位置
	VIEW,                  //跳转链接
	TEMPLATESENDJOBFINISH, //模板消息发送成功之后事件
	scancode_push,         //扫码推事件
	scancode_waitmsg,      //扫码推事件且弹出“消息接收中”提示框的事件
	pic_sysphoto,          //弹出系统拍照发图的事件
	pic_photo_or_album,    //弹出拍照或者相册发图的事件
	pic_weixin,            //弹出微信相册发图器的事件
	location_select;       //弹出地理位置选择器的事件
}
