使用本包需要在公众号设置中配置JS接口安全域名

菜单：公众号设置-功能设置
选项：JS接口安全域名

在需要调用JS接口的页面引入如下JS文件，（支持https）：http://res.wx.qq.com/open/js/jweixin-1.0.0.js
请注意，如果你的页面启用了https，务必引入 https://res.wx.qq.com/open/js/jweixin-1.0.0.js ，否则将无法在iOS9.0以上系统中成功使用JSSDK
如需使用摇一摇周边功能，请引入 jweixin-1.1.0.js

备注：支持使用 AMD/CMD 标准模块加载方法加载

开发文档：http://mp.weixin.qq.com/wiki/7/aaa137b55fb2e0456bf8dd9148dd613f.html