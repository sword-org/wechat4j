/**
 * 
 */
package org.sword.wechat4j;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.sword.lang.JaxbParser;
import org.sword.lang.StreamUtils;
import org.sword.wechat4j.common.Config;
import org.sword.wechat4j.common.ValidateSignature;
import org.sword.wechat4j.event.EventType;
import org.sword.wechat4j.event.MsgType;
import org.sword.wechat4j.param.SignatureParam;
import org.sword.wechat4j.request.WechatRequest;
import org.sword.wechat4j.response.ArticleResponse;
import org.sword.wechat4j.response.ImageResponse;
import org.sword.wechat4j.response.MusicResponse;
import org.sword.wechat4j.response.TransInfoResponse;
import org.sword.wechat4j.response.VideoResponse;
import org.sword.wechat4j.response.VoiceResponse;
import org.sword.wechat4j.response.WechatResponse;




/**
 * wechat支持入口
 * 抽象方法中，on开头的是msgtype的事件，无on的是event事件
 * @author ChengNing
 * @date   2014-12-4
 */
public abstract class WechatSupport {
	
	Logger logger = Logger.getLogger(WechatSupport.class);
	
	private HttpServletRequest request;
	
	protected WechatRequest wechatRequest;
	protected WechatResponse wechatResponse;
	
	
	/**
	 * 构建微信处理
	 * @param request   微信服务发过来的http请求
	 */
	public WechatSupport(HttpServletRequest request){
		this.request = request;
		this.wechatRequest = new WechatRequest();
		this.wechatResponse = new WechatResponse();
	}

	/**
	 * wechat调用入口，进行数据接收，事件分发
	 * @return
	 */
	public String execute(){
		logger.debug("WechatSupport run");
		SignatureParam param = new SignatureParam(request);
		String signature =param.getSignature();
		String timestamp = param.getTimestamp();
		String nonce = param.getNonce();
		String echostr = param.getEchostr();
		String token = Config.instance().getToken();
		
		ValidateSignature validateSignature = new ValidateSignature(signature, 
				timestamp, nonce, token);
		if(!validateSignature.check()){
			return "error";
		}
		if(StringUtils.isNotBlank(echostr)){
     		return echostr;
		}
		//分发消息，得到响应
		String result = dispatch();
		logger.info("response data:" + result);
		return result;
	}
	
	/**
	 * 分发处理，得到响应
	 * @return
	 */
	private String dispatch() {
		String postDataStr = null;
		try {
			 postDataStr = StreamUtils.streamToString(request.getInputStream());
		} catch (IOException e) {
			logger.error("post data deal failed!");
			e.printStackTrace();
		}
		// 解析数据
		setPostData(postDataStr);
		// 消息分发处理
		dispatchMessage();
		// 响应事件
		String result = response();
		return result;
	}
	

	
	/**
	 * 得到post数据，对象化
	 * @param xmlStr
	 */
	private void setPostData(String xmlStr){
		logger.info("parse post data:" + xmlStr);
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
	private void dispatchMessage(){
		logger.info("distributeMessage start");
		if(StringUtils.isBlank(wechatRequest.getMsgType())){
			logger.info("msgType is null");
		}
		MsgType msgType = MsgType.valueOf(wechatRequest.getMsgType());
		logger.info("msgType is " + msgType.name());
		switch (msgType) {
		case event:
			dispatchEvent();
			break;
		case text:
			onText();
			break;
		case image:
			onImage();
			break;
		case voice:
			onVoice();
			break;
		case video:
			onVideo();
			break;
		case shortvideo:
			onShortVideo();
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
	private void dispatchEvent() {
		EventType event = EventType.valueOf(wechatRequest.getEvent());
		logger.info("dispatch event,event is " + event.name());
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
		case TEMPLATESENDJOBFINISH:
			templateMsgCallback();
			break;
		case scancode_push:
			scanCodePush();
			break;
		case scancode_waitmsg:
			scanCodeWaitMsg();
		    break;
		case pic_sysphoto:
			picSysPhoto();
		    break;
		case pic_photo_or_album:
			picPhotoOrAlbum();
		    break;
		case pic_weixin:
			picWeixin();
		    break;
		case location_select:
			locationSelect();
		    break;
		case kf_create_session:
			kfCreateSession();
			break;
		case kf_close_session:
			kfCloseSession();
			break;
		case kf_switch_session:
			kfSwitchSession();
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
	}
	
	/**
	 * 回复文本消息
	 * @param content 回复的消息内容（换行：在content中能够换行，微信客户端就支持换行显示）
	 */
	public void responseText(String content){
		responseBase();
		wechatResponse.setMsgType(MsgType.text.name());
		wechatResponse.setContent(content);
	}
	
	/**
	 * 回复图片消息
	 * @param mediaId 通过上传多媒体文件，得到的id
	 */
	public void responseImage(String mediaId){
		responseBase();
		wechatResponse.setMsgType(MsgType.image.name());
		ImageResponse image = new ImageResponse();
		image.setMediaId(mediaId);
		wechatResponse.setImage(image);
	}
	
	/**
	 * 回复语音消息
	 * @param mediaId  通过上传多媒体文件，得到的id
	 */
	public void responseVoice(String mediaId){
		responseBase();
		wechatResponse.setMsgType(MsgType.voice.name());
		VoiceResponse voice = new VoiceResponse();
		voice.setMediaId(mediaId);
		wechatResponse.setVoice(voice);
	}
	
	/**
	 * 回复视频消息
	 * @param mediaId      通过上传多媒体文件，得到的id
	 * @param title        视频消息的标题
	 * @param description  视频消息的描述
	 */
	public void responseVideo(String mediaId,String title,String description){
		VideoResponse video = new VideoResponse();
		video.setMediaId(mediaId);
		video.setTitle(title);
		video.setDescription(description);
		responseVideo(video);
	}
	
	/**
	 * 回复视频消息
	 * @param video  视频消息
	 */
	public void responseVideo(VideoResponse video){
		responseBase();
		wechatResponse.setMsgType(MsgType.video.name());
		wechatResponse.setVideo(video);
	}
	
	/**
	 * 回复音乐消息
	 * @param title         音乐标题
	 * @param description   音乐描述
	 * @param musicURL      音乐链接
	 * @param hQMusicUrl    高质量音乐链接，WIFI环境优先使用该链接播放音乐
	 * @param thumbMediaId  缩略图的媒体id，通过上传多媒体文件，得到的id
	 */
	public void responseMusic(String title,String description,
			String musicURL,String hQMusicUrl,String thumbMediaId){
		MusicResponse music = new MusicResponse();
		music.setTitle(title);
		music.setDescription(description);
		music.setMusicURL(musicURL);
		music.setHQMusicUrl(hQMusicUrl);
		music.setThumbMediaId(thumbMediaId);
		responseMusic(music);
	}
	
	/**
	 * 回复音乐消息
	 * @param music  音乐消息
	 */
	public void responseMusic(MusicResponse music){
		responseBase();
		wechatResponse.setMsgType(MsgType.music.name());
		wechatResponse.setMusic(music);
	}
	
	/**
	 * 回复图文消息，单条图文消息
	 * @param title         图文消息标题
	 * @param description   图文消息描述
	 * @param picUrl        图片链接，支持JPG、PNG格式，较好的效果为大图360*200，小图200*200
	 * @param url           点击图文消息跳转链接
	 */
	public void responseNew(String title,String description,String picUrl,String url){
		ArticleResponse item = new ArticleResponse();
		item.setTitle(title);
		item.setDescription(description);
		item.setPicUrl(picUrl);
		item.setUrl(url);
		responseNews(item);
	}
	
	/**
	 * 回复图文消息单条
	 * @param item
	 */
	public void responseNews(ArticleResponse item){
		List<ArticleResponse> items = new ArrayList<ArticleResponse>();
		items.add(item);
		responseNews(items);
	}
	
	/**
	 * 回复图文消息
	 * @param items
	 */
	public void responseNews(List<ArticleResponse> items){
		responseBase();
		wechatResponse.setMsgType(MsgType.news.name());
		wechatResponse.setArticleCount(String.valueOf(items.size()));
		wechatResponse.setArticle(items);
		
	}
	
	/**
	 * 消息转发到多客服
	 */
	public void responseCustomerService(){
		responseBase();
		wechatResponse.setMsgType(MsgType.transfer_customer_service.name());
	}
	/**
	 * 消息转发到指定客服
	 * @param kfAccount 客服账号
	 */
	public void responseCustomerService(String kfAccount){
		responseBase();
		wechatResponse.setMsgType(MsgType.transfer_customer_service.name());
		wechatResponse.setTransInfo(new TransInfoResponse(kfAccount));
		
	}
	/**
	 * 消息转发到指定客服
	 * @param kfAccount 客服
	 */
	public void responseCustomerService(TransInfoResponse transInfo){
		responseBase();
		wechatResponse.setMsgType(MsgType.transfer_customer_service.name());
		wechatResponse.setTransInfo(transInfo);
		
	}
	
	
	/**
	 * 文本消息处理Msgtype=text
	 */
    protected abstract void onText();
	/**
	 * 图像消息Msgtype=image
	 */
    protected abstract void onImage();
	/**
	 * 语音消息 Msgtype=voice
	 */
    protected abstract void onVoice();
	/**
	 * 视频 消息Msgtype=video
	 */
    protected abstract void onVideo();
    /**
     * 小视频 消息Msgtype=shortvideo
     */
    protected abstract void onShortVideo();
	/**
	 * 地理位置消息Msgtype=location
	 */
    protected abstract void onLocation();
	/**
	 * 链接消息Msgtype=link
	 */
    protected abstract void onLink();
	/**
	 * 未知消息类型的错误处理逻辑，不需要处理则空方法即可
	 */
    protected abstract void onUnknown();
	
	
	/**
	 * click点击事件处理event=location
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
	 * view 事件处理event=location
	 */
	protected abstract void view();
	/**
	 * 模板消息发送回调
	 */
	protected abstract void templateMsgCallback();
	/**
	 * 扫码推事件
	 */
	protected abstract void scanCodePush();
	/**
	 * 扫码推事件且弹出“消息接收中”提示框的事件
	 */
	protected abstract void scanCodeWaitMsg();
	/**
	 * 弹出系统拍照发图的事件
	 */
	protected abstract void picSysPhoto();
	/**
	 * 弹出拍照或者相册发图的事件
	 */
	protected abstract void picPhotoOrAlbum();
	/**
	 * 扫码推事件且弹出“消息接收中”提示框的事件
	 */
	protected abstract void picWeixin();
	/**
	 * 弹出地理位置选择器的事件
	 */
	protected abstract void locationSelect();
	/**
	 * 客服人员有接入会话
	 */
	protected abstract void kfCreateSession();
	/**
	 * 客服人员有关闭会话
	 */
	protected abstract void kfCloseSession();
	/**
	 * 客服人员有转接会话
	 */
	protected abstract void kfSwitchSession();
}
