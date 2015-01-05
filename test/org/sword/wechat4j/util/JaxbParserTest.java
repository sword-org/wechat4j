package org.sword.wechat4j.util;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.sword.wechat4j.event.MsgType;
import org.sword.wechat4j.response.ArticleResponse;
import org.sword.wechat4j.response.WechatResponse;

public class JaxbParserTest {

	@Test
	public void testJaxbParser() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetCdataNode() {
		fail("Not yet implemented");
	}

	@Test
	public void testToXML() {
		String title = "图文消息";
		String description = "description";
		String picUrl = "picUrl";
		String url = "url";
		WechatResponse wechatResponse = new WechatResponse();
		ArticleResponse item = new ArticleResponse();
		item.setTitle(title);
		item.setDescription(description);
		item.setPicUrl(picUrl);
		item.setUrl(url);
		List<ArticleResponse> items = new ArrayList<ArticleResponse>();
		items.add(item);
		
		wechatResponse.setMsgType(MsgType.news.name());
		wechatResponse.setArticleCount(String.valueOf(items.size()));
		wechatResponse.setArticle(items);
		wechatResponse.setFromUserName("fromuser");
		wechatResponse.setToUserName("touser");
		
		String result = null;
		try {
			JaxbParser jaxbParser = new JaxbParser(WechatResponse.class);
			//设置
			jaxbParser.setCdataNode(WechatResponse.CDATA_TAG);
			result = jaxbParser.toXML(wechatResponse);
		} catch (Exception e) {
			e.printStackTrace();
		}
//		return result;
		System.out.println(result);
	}

	@Test
	public void testToObjInputStream() {
		fail("Not yet implemented");
	}

	@Test
	public void testToObjString() {
		fail("Not yet implemented");
	}

}
