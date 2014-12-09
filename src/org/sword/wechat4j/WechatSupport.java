/**
 * 
 */
package org.sword.wechat4j;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.sword.wechat4j.event.EventType;
import org.sword.wechat4j.event.MsgType;
import org.sword.wechat4j.param.SignatureParam;
import org.sword.wechat4j.request.WechatRequest;
import org.sword.wechat4j.response.WechatResponse;
import org.sword.wechat4j.util.JaxbParser;



/**
 * @author ChengNing
 * @date   2014-12-4
 */
public abstract class WechatSupport {
	
	Logger logger = Logger.getLogger(WechatSupport.class);
	
	private HttpServletRequest request;
	private String postData;
	private String token;
	
	protected WechatRequest wechatRequest;
	protected WechatResponse wechatResponse;
	
	
	/**
	 * 构建微信处理
	 * @param request   微信服务发过来的http请求
	 * @param token     token
	 */
	public WechatSupport(HttpServletRequest request,String token){
		this.request = request;
		this.token = token;
		this.wechatRequest = new WechatRequest();
		this.wechatResponse = new WechatResponse();
	}

	/**
	 * wechat调用入口，进行数据接收，事件分发
	 * @return
	 */
	public String run(){
		logger.debug("WechatSupport run");
		SignatureParam param = new SignatureParam(request);
		String signature =param.getSignature();
		String timestamp = param.getTimestamp();
		String nonce = param.getNonce();
		String echostr = param.getEchostr();
		
		ValidateSignature validateSignature = new ValidateSignature(signature, 
				timestamp, nonce, this.token);
		if(!validateSignature.check()){
			return "error";
		}
		if(StringUtils.isNotBlank(echostr)){
     		return echostr;
		}
		//分发消息，得到响应
		String result = distribute();
		logger.info("response data:" + result);
		return result;
	}
	
	/**
	 * 分发处理，得到响应
	 * @return
	 */
	private String distribute() {
		String postDataStr = null;
		try {
			 postDataStr = stream2String(request.getInputStream());
		} catch (IOException e) {
			logger.error("post data deal failed!");
			e.printStackTrace();
		}
		// 解析数据
		setPostData(postDataStr);
		// 消息分发处理
		distributeMessage();
		// 响应事件
		String result = response();
		return result;
	}
	
	private String stream2String(InputStream is){
		ByteArrayOutputStream   baos   =   new   ByteArrayOutputStream(); 
        int i=-1; 
        try {
			while((i=is.read())!=-1){ 
			baos.write(i); 
			}
		} catch (IOException e) {
			e.printStackTrace();
		} 
       return   baos.toString(); 
	}
	
	/**
	 * 得到post数据，对象化
	 * @param xmlStr
	 */
	private void setPostData(String xmlStr){
		logger.info("parse post data");
		try {
			JaxbParser jaxbParser = new JaxbParser(WechatRequest.class);
			this.wechatRequest = (WechatRequest)jaxbParser.toObj(xmlStr);
		} catch (Exception e) {
			logger.error("post data parse error");
			e.printStackTrace();
		}
	}

	/**
	 * 消息事件分发
	 */
	public void distributeMessage(){
		logger.info("distributeMessage start");
		if(StringUtils.isBlank(wechatRequest.getMsgType())){
			logger.info("msgType is null");
		}
		MsgType msgType = MsgType.valueOf(wechatRequest.getMsgType());
		switch (msgType) {
		case event:
			distributeEvent();
			break;
		case text:
			onText();
			break;
		case image:
			onImage();
			break;
		case location:
			onLocation();
			break;
		case link:
			onLink();
			break;
		default:
			onUnknown();
			break;
		}
		
	}
	
	/**
	 * event事件分发
	 */
	private void distributeEvent(){
		EventType event = EventType.valueOf(wechatRequest.getEvent());
			switch (event) {
			case CLICK:
				click();
				break;
			case subscribe:
				subscribe();
				break;
			case unsubscribe:
				unSubscribe();
				break;
			case SCAN:
				scan();
				break;
			case LOCATION:
				location();
				break;
			case VIEW:
				view();
				break;
			default:
				break;
			}
		
	}
	
	/**
	 * 返回响应数据
	 * @return
	 */
	private String response(){
		String result = null;
		try {
			JaxbParser jaxbParser = new JaxbParser(WechatResponse.class);
			//设置
			jaxbParser.setCdataNode(WechatResponse.CDATA_TAG);
			result = jaxbParser.toXML(wechatResponse);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 响应数据基础构造
	 */
	private void responseBase(){
		wechatResponse.setToUserName(this.wechatRequest.getFromUserName());
		wechatResponse.setFromUserName(wechatRequest.getToUserName());
		wechatResponse.setCreateTime(wechatRequest.getCreateTime());
		wechatResponse.setMsgType(wechatRequest.getMsgType());
	}
	
	/**
	 * 文本消息响应
	 * @param content
	 */
	public void responseText(String content){
		responseBase();
		wechatResponse.setContent(content);
	}
	
	
	
	/**
	 * 文本消息处理
	 */
    protected abstract void onText();
	/**
	 * 图像
	 */
    protected abstract void onImage();
	/**
	 * location消息处理msgtype=location
	 */
    protected abstract void onLocation();
	/**
	 * link消息处理
	 */
    protected abstract void onLink();
	/**
	 * 未知消息类型的错误处理逻辑，不需要处理则空方法即可
	 */
    protected abstract void onUnknown();
	
	
	/**
	 * click点击事件处理
	 */
	protected abstract void click();
	/**
	 * subscribe关注事件处理
	 */
	protected abstract void subscribe();
	/**
	 * unSubscribe取消关注事件处理
	 */
	protected abstract void unSubscribe();
	/**
	 * scan事件处理
	 */
	protected abstract void scan();
	/**
	 * location事件处理event=location
	 */
	protected abstract void location();
	/**
	 * view 事件处理
	 */
	protected abstract void view();

}
