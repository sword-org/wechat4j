wechat4j
========

wechat develop framework for java
微信开发框架JAVA版，最简单易用微信开发框架

##wechat4j可以用来干什么？
    wechat4j是一个帮助你开发微信应用的java包，使用它，你开发微信公众号应用只需要几秒钟的时间，完全不用关注太细节的东西。

##wechat4j快速开始
    使用wechat4j只需要两步就可以搭建微信开发环境。
1.下载wechat4j.jar包，下载地址[wechat4j下载](https://github.com/sword-org/wechat4j/releases)。
2.创建wechat4j配置文件，在src目录下（java根目录）创建wechat4j.properties文件，配置你微信公众号的相关信息。内容如下：
``
 #you wechat token
 wechat.token=lejian
 #wechat appid
 wechat.appid=appid
 #wechat app secret
 wechat.appsecret=secret
``
 你也可以在jar包的META-INF目录下找到wechat4j.properties.sample文件，复制到src目录下修改名称即可。wechat4j.properties配置文件的配置项意义参见[wechat4j配置文件解读](https://github.com/sword-org/wechat4j/wiki/wechat4j%E9%85%8D%E7%BD%AE%E6%96%87%E4%BB%B6%E8%A7%A3%E8%AF%BB)

##开发自己的微信应用
   wechat4j开发环境搭好之后，就可以开始开发自己的微信应用了。比如我有一个微信号lejian,下面就以她为例子来说明。
###创建自己公众号服务类
   1.创建自己的微信公众号服务类，需要继承wechat4j的WechatSupport类
``
public class Lejian extends WechatSupport{

	public Lejian(HttpServletRequest request, String token) {
		super(request, token);
	}

	@Override
	protected void onText() {
		this.wechatRequest.getFromUserName();
		String content = "test ok";
		responseText(content);
	}
}
``
``onText()``表示对文本消息的处理，示例中是接收到用户的消息之后，返回给用户“test ok”文本消息。
   
    2. 创建自己的微信服务地址（微信公众平台中配置的自己服务器地址）servlet类。如果是springmvc则创建对应的controller，如果是struts则创建对应的action类。servlet类示例如下：
``
        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Lejian lejian = new Lejian(request, TOKEN);
		String result = lejian.run();
		response.getOutputStream().write(result.getBytes());
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Lejian lejian = new Lejian(request, TOKEN);
		String result = lejian.run();
		response.getOutputStream().write(result.getBytes());

	}

``
















