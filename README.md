wechat4j
========

##What is wechat4j?
wechat develop framework for java(微信开发框架JAVA版，最简单易用微信开发框架)

##wechat4j可以用来干什么？
  wechat4j是一个帮助你开发微信应用的jar包，使用它，你开发微信公众号应用只需要几秒钟的时间，完全不用关注太细节的东西。

##wechat4j快速开始
   可以去下载wechat4j示例项目[wechat4jDemo](https://github.com/repoproject/wechat4jDemo)，然后在其基础之上修改即可。如果你要自己搭建，那么使用wechat4j只需要三步就可以搭建微信开发环境。 
 1. 创建一个web工程，导入jdk和相关的web工程jar包。 
 2. 下载wechat4j.jar包，下载地址[wechat4j下载](https://github.com/sword-org/wechat4j/releases)。 
 3. 创建wechat4j配置文件，在src目录下（java根目录）创建wechat4j.properties文件，配置你微信公众号的相关信息。内容如下：
```properties
#you wechat token
wechat.token=token
#wechat appid
wechat.appid=appid
#wechat app secret
wechat.appsecret=secret
```
你也可以在jar包的META-INF目录下找到wechat4j.properties.sample文件，复制到src目录下修改名称即可。wechat4j.properties配置文件的详细配置项意义参见[wechat4j配置文件解读](https://github.com/sword-org/wechat4j/wiki/wechat4j%E9%85%8D%E7%BD%AE%E6%96%87%E4%BB%B6%E8%A7%A3%E8%AF%BB)

通过以上步骤，你的微信工程就完全搭好了。

##wechat4j 运行环境
 wechat4j要求的最低java运行环境是jdk1.6  

 wechat4j.jar的依赖jar包
> * commons-codec.jar  1.3以上
> * commons-lang3.jar
> * log4j.jar 1.2以上
> * fastjson-1.2.0.jar
> * sword-lang-1.2 (https://github.com/sword-org/sword-lang/releases)
> * fluent-hc-4.3.6.jar（httpclient依赖）
> * httpclient-4.3.6.jar
> * httpcore-4.3.3.jar （httpclient依赖）
> * servlet-api.jar  如果你是web工程，导入支持web工程的包就会包括，例如tomcat包

你可以去集中下载这些jar包的集合[wechat4j所需jar下载](http://files.cnblogs.com/chengn/wechat4j-lib.rar),也可以去maven库或者对应jar包的项目官网下载.

##开发自己的微信应用
 wechat4j开发环境搭好之后，就可以开始开发自己的微信应用了。比如我有一个微信号的token是lejian,下面就以她为例子来说明。
###创建自己公众号服务类
创建自己的微信公众号服务类，需要继承wechat4j的WechatSupport类，然后实现其抽象方法即可，下面以文本消息处理为例子
```java
public class Lejian extends WechatSupport{
	public Lejian(HttpServletRequest request) {
		super(request);
	}

	@Override
	protected void onText() {
		this.wechatRequest.getFromUserName();
		String content = "test ok";
		responseText(content);
	}
}
```
上面代码中的``onText()``是WechatSupport的抽象方法，需要你自己的类来实现，表示对文本消息的处理，示例中是接收到用户的消息之后，返回给用户“test ok”文本消息。
   
###创建微信服务地址
创建微信服务地址（微信公众平台中配置的自己服务器地址）servlet类。如果是springmvc则创建对应的controller，如果是struts则创建对应的action类。servlet类示例如下：
```java
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Lejian lejian = new Lejian(request);
		String result = lejian.execute();
		response.getOutputStream().write(result.getBytes());
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Lejian lejian = new Lejian(request);
		String result = lejian.execute();
		response.getOutputStream().write(result.getBytes());

	}
```
通过上面两步你的微信服务就可以运行了

##如何得到微信的请求参数
继承了``WechatSupport``类之后，你可以通过``wechatRequest.getFromUserName()``类似的方法来得到微信服务器请求的参数。详细信息请阅读[微信请求参数](https://github.com/sword-org/wechat4j/wiki/%E5%BE%97%E5%88%B0%E5%BE%AE%E4%BF%A1%E8%AF%B7%E6%B1%82%E5%8F%82%E6%95%B0)
##如何设置响应参数
继承了``WechatSupport``类之后，你可以通过``wechatResponse.setFromUserName(fromUser)``类似的方法来设置给微信服务器的响应参数。详细信息请阅读[响应微信服务器参数](https://github.com/sword-org/wechat4j/wiki/%E8%AE%BE%E7%BD%AE%E5%93%8D%E5%BA%94%E5%BE%AE%E4%BF%A1%E5%8F%82%E6%95%B0)
##如何响应用户信息
以文本信息为例，响应文本信息只需要在你的``onText``方法中使用``responseText(content)``即可（参见上面的代码例子）

##wechat4j示例项目
* [wechat4jDemo](https://github.com/repoproject/wechat4jDemo)

如果你有好的demo项目，请邮件或者修改本文件然后pull request给我，我会列在上面。

##技术支持
* [wechat4j开发者文档中心](http://www.chengn.com/wechat4j/)
* [wechat4j开发文档](https://github.com/sword-org/wechat4j/wiki) 
* wechat4j技术交流QQ群  **423134346**
* 支持邮件 sword_org@163.com
* wechat4j暂无论坛，欢迎开通论坛交流版块，如果开通请邮件，我会添加到这里。


##贡献代码

1. 如果你觉得本项目不错，希望你能够点击一下右上角的star
2. 如果你希望参与改进本项目，那么请点击右上角的fork，修改之后pull request即可。如果你的贡献不错，你就会收到加入[sword](https://github.com/sword-org)开源社区的邀请。
3. 如果你发现了一个bug，请你创建一个issue来报告。
非常非常欢迎你能够参与本项目的建设，每人做出一点点贡献，对整个项目来说就是一个非常大的贡献，希望集合众人的力量，让项目走的更好，能够为更多的人服务。

###贡献者列表
* [@chengn](https://github.com/chengn)
* [@truecn](https://github.com/truecn)
* [@Zhangys-hh](https://github.com/Zhangys-hh)














