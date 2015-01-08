/**
 * 
 */
package org.sword.wechat4j;



import org.junit.After;
import org.junit.Before;
import org.junit.Test;


/**
 * @author ChengNing
 * @date   2014年12月7日
 */
public class WechatTest {

	private String url = "http://localhost:8080/phdc/lejian?signature=de8de4d90ab29dc1edca1d80863115e5385e25aa&timestamp=1418008326&nonce=1917848460";
	

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void textTest() {
//		HttpClient client = new HttpClient();
//		PostMethod post = new PostMethod(url);
//		post.setRequestBody("<xml><Content><![CDATA[test ok]]></Content><CreateTime>1418007611</CreateTime><FromUserName><![CDATA[o493zsx3Sde0Uq_pjhhlvBfPDNgU]]></FromUserName><MsgType><![CDATA[text]]></MsgType><ToUserName><![CDATA[gh_de186888c944]]></ToUserName></xml>");
//		
//		String expected = "test ok";
//		String actual = "";
//		try {
//			client.executeMethod(post);
//			actual = post.getResponseBodyAsString();
//		} catch (Exception e) {
//			e.printStackTrace();
//		} 
//		Assert.assertEquals(expected, actual);
	}
	
	@Test
	public void linkTest(){
//		HttpClient client = new HttpClient();
//		PostMethod post = new PostMethod(url);
//		post.setRequestBody("<xml><Title><![CDATA[test ok]]></Title><Description><![CDATA[test ok]]></Description><Url><![CDATA[test ok]]></Url><MsgId>1234567890123456</MsgId><CreateTime>1418007611</CreateTime><FromUserName><![CDATA[o493zsx3Sde0Uq_pjhhlvBfPDNgU]]></FromUserName><MsgType><![CDATA[link]]></MsgType><ToUserName><![CDATA[gh_de186888c944]]></ToUserName></xml>");
//		
//		String expected = "test ok";
//		String actual = "";
//		try {
//			client.executeMethod(post);
//			actual = post.getResponseBodyAsString();
//		} catch (Exception e) {
//			e.printStackTrace();
//		} 
//		Assert.assertEquals(expected, actual);
	}
	
	@Test
	public void clickTest(){
		
	}


}
