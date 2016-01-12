/**
 * 
 */
package org.sword.wechat4j.response;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;


/**
 * 用于回复的基本消息类型
 * @author ChengNing
 * @date   2014-12-4
 */
@XmlRootElement(name="xml")
public class WechatResponse {

	private String ToUserName;
	private String FromUserName;
	private String CreateTime;
	private String MsgType;
	private String Content;
	private String ArticleCount;
	
	private ImageResponse Image;
	private VoiceResponse Voice;
	private VideoResponse Video;
	private MusicResponse Music;
	private List<ArticleResponse> article;
	private TransInfoResponse TransInfo;
	
	
	
	public static String[] CDATA_TAG = {"ToUserName",
		"FromUserName","MsgType","Event","MsgId","Content","MediaId","Title","Description","MusicUrl","HQMusicUrl","ThumbMediaId",
		"PicUrl","Url"
		};
	

	@XmlElement(name="ToUserName")
	public String getToUserName() {
		return ToUserName;
	}
	public void setToUserName(String toUserName) {
		ToUserName = toUserName;
	}
	@XmlElement(name="FromUserName")
	public String getFromUserName() {
		return FromUserName;
	}
	public void setFromUserName(String fromUserName) {
		FromUserName = fromUserName;
	}
	@XmlElement(name="CreateTime")
	public String getCreateTime() {
		return CreateTime;
	}
	public void setCreateTime(String createTime) {
		CreateTime = createTime;
	}
	@XmlElement(name="MsgType")
	public String getMsgType() {
		return MsgType;
	}
	public void setMsgType(String msgType) {
		MsgType = msgType;
	}
	@XmlElement(name="Content")
	public String getContent() {
		return Content;
	}
	public void setContent(String content) {
		Content = content;
	}
	@XmlElement(name="ArticleCount")
	public String getArticleCount() {
		return ArticleCount;
	}
	public void setArticleCount(String articleCount) {
		ArticleCount = articleCount;
	}

	@XmlElement(name="Image")
	public ImageResponse getImage() {
		return Image;
	}
	public void setImage(ImageResponse image) {
		Image = image;
	}
	@XmlElement(name="Voice")
	public VoiceResponse getVoice() {
		return Voice;
	}
	public void setVoice(VoiceResponse voice) {
		Voice = voice;
	}
	@XmlElement(name="Video")
	public VideoResponse getVideo() {
		return Video;
	}
	public void setVideo(VideoResponse video) {
		Video = video;
	}
	@XmlElement(name="Music")
	public MusicResponse getMusic() {
		return Music;
	}
	public void setMusic(MusicResponse music) {
		Music = music;
	}
	@XmlElementWrapper(name="Articles")
	@XmlElement(name="item")
	public List<ArticleResponse> getArticle() {
		return article;
	}
	public void setArticle(List<ArticleResponse> article) {
		this.article = article;
	}
	@XmlElement(name="TransInfo")
	public TransInfoResponse getTransInfo() {
		return TransInfo;
	}
	public void setTransInfo(TransInfoResponse transInfo) {
		TransInfo = transInfo;
	}
	
	
}
