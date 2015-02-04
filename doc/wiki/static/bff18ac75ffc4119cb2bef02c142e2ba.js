var common = {
	'string':{
		'toJson':function(s){
			if (window.JSON){
				try{
					return JSON.parse(s);
				}catch(e){
					return null;
				}
			}else{
				try{
					eval('COMMON.__json__='+s);
					return COMMON.__json__;
				}catch(e){
					return null;
				}
			}
		}
	},
	'login':{
		'resize':function(data){
			var dialog = common.dialog,
				win,
				iframe,
				height = dialog.height || 333;
			if (common.dialog){
				win = dialog.dom.win;
				iframe = win.find('iframe');
				iframe.attr('height',height);
				dialog.dom.content.css({
					'height':height
				});
				win.css({
					'height':height + 41,
					'marginTop':-((height+41)>>1)
				});
			}
		},
		'close':function(){
			
		}
	}
};
var wiki={
"loadcss":function(v,c){
			var element = document.createElement("link");
			element.href = v;
			element.rel= "stylesheet";
			element.type = "text/css";
			if(c)
			element.charset=c;
			(document.head||document.getElementsByTagName("head")[0]).appendChild(element);
	},
"loadjs":function(v,c){
		var element = document.createElement("script");
		element.src = v;
		element.type = "text/javascript";
		if(c)
		element.charset=c;
		document.body.appendChild(element); 
	},
"setCookie":function(name, value, options){
	if (typeof value != 'undefined') {
		options = options || {};
		if (value === null) {
			value = '';
			options.expires = -1;
		}
		var expires = '';
		if (options.expires && (typeof options.expires == 'number' || options.expires.toUTCString)) {
			var date;
			if (typeof options.expires == 'number') {
				date = new Date();
				date.setTime(date.getTime() + (options.expires * 24 * 60 * 60 * 1000));
			} else {
				date = options.expires;
			}
			expires = '; expires=' + date.toUTCString();
		}
		var path = options.path ? '; path=' + (options.path) : '';
		var domain = options.domain ? '; domain=' + (options.domain) : '';
		var secure = options.secure ? '; secure': '';
		document.cookie = [name, '=', encodeURIComponent(value), expires, path, domain, secure].join('');
	} else {
		var cookieValue = null;
		if (document.cookie && document.cookie != '') {
			var cookies = document.cookie.split(';');
			for (var i = 0; i < cookies.length; i++) {
				var cookie = jQuery.trim(cookies[i]);
				if (cookie.substring(0, name.length + 1) == (name + '=')) {
					cookieValue = decodeURIComponent(cookie.substring(name.length + 1));
					break;
				}
			}
		}
		return cookieValue;
	}
 },
"setLoginInfo":function(u){
	var r = {'html':'','class':''};
	if (u && u.name){
		r["html"] = [
				'<span title="'+u.nick+'('+u.name+')" class="login_name">'+u.nick+'</span>',
				'<span class="nav_arrow"></span>',
				'<a href="http://dev.t.qq.com/development/developer/" class="f12">编辑开发者信息</a>',
				'<a href="javascript:;" id="logoutBtn" class="f12" onclick="wiki.loginOut()">退出</a>'
		].join("\n");
		r["class"] = 'subnav subnav_login';
	}else if(u && !u.name && !u.nick){
		r["html"] = [
			'<span title="还未开通微博" class="login_name">还未开通微博</span>',
			'<span class="nav_arrow"></span>',
			'<a class="f12" href="http://reg.t.qq.com/index.php?pref=test.open.t.qq.com" target="_blank">开通微博</a>',
			'<a class="f12" href="javascript:;" id="logoutBtn" onclick="wiki.loginOut();">退出</a>'
		].join("\n");
		r["class"] = 'subnav subnav_login';
	}else{
		r["html"] =  '<a title="点击此处登录" class="login_btn" href="javascript:void(0);" id="loginBtn" hidefocus onclick="wiki.showLogin();" style="float:right;">登录</a>';
		r["class"] = '';
	}
	return r;
},
"showLogin":function(){
	var w = 488,
		h = 326,
		m = {"v":2},
		p = {
			"appid":46000101,
			"daid":6,
			"style":11,
			"low_login":1,
			"hide_title_bar":1,
			"hide_close_icon":1,
			"no_drop_domain":1,//不降域
			"proxy_url":location.href.replace(new RegExp(location.pathname+'.*'),'/proxy.html'),
			"self_regurl":"http://reg.t.qq.com/index.php?pref=readwall",
			"hln_logo":"http://mat1.gtimg.com/app/opent/images/websites/space.gif",
			"s_url":location.href
		},
		url = 'http://ui.ptlogin2.qq.com/cgi-bin/login?',
		show = function(dialog,url){
			dialog.showFrame({
				"text":url,
				"width":520,
				"height":374,
				"title":"登录"
			});
		};
		url += $.param(p);
		if (common.dialog){
			show(common.dialog,url);
		}else{
			$.getScript("http://mat1.gtimg.com/app/api_debuger/js/common_dialog.js",function(){
				if (typeof window.postMessage !== 'undefined' && !window.onmessage) {
					window.onmessage = function(event) {
						var msg = event || window.event; // 兼容IE8
						var data = common.string.toJson(msg.data);
						if (data && data["action"]){
							switch (data.action) {
								case 'close':
									common.login.close();
								break;
								case 'resize':
									common.login.resize(data);
								break;
							}
						}
					};
				};
				common.dialog = common._dialog();
				show(common.dialog,url);
			});
		}	
},
"login":function(d){
		var o=document.getElementById("login_status"),
			ret = d && d.ret,
			data = d && d.data,
			errCode = d && d.errCode,
			info = wiki.setLoginInfo(data);
			wiki.u = data;
			o.innerHTML = info["html"];
			o.className = info["class"];
		$("body").bind("mouseover",function(event){
			if ($(o).find(".menu").size() === 0){
				return false;
			}
			if ($.contains(o,event.target) || o === event.target){
				$(o).addClass("menuContainer");
			}else{
				$(o).removeClass("menuContainer");
			}
		});
	},
"loginOut":function(){ 
	var clearLoginInfo = function(){
		if (window.pt_logout){
			pt_logout.logout(function(n){
				wiki.setLoginInfo({});
				wiki.setCookie('uin',  null,{domain: 'qq.com',path: '/'});
				wiki.setCookie('skey', null,{domain: 'qq.com',path: '/'});
				wiki.setCookie('luin', null,{domain: 'qq.com',path: '/'});
				wiki.setCookie('lskey',null,{domain: 'qq.com',path: '/'});
				location.reload();
			});
		}
	};
	if (window.pt_logout){
		clearLoginInfo();
	}else{
		$.getScript("http://imgcache.qq.com/ptlogin/ac/v9/js/ptloginout.js",clearLoginInfo);
	}
	return false;
},
"onscroll":function(){
	if (wiki.contentHeight<(wiki.docElement.scrollTop||window.pageYOffset)){
		$(".bottomnav").css({
		"position":"absolute",
		"bottom":"auto",
		"top":$(".mainwrapper").height()+/*35-30*/65
		})
	}else{
		if (!-[1,]&&!window.XMLHttpRequest){ 
		//ie6
			$(".bottomnav").css({
			"bottom":"auto",
			"top":(document.documentElement.scrollTop+document.documentElement.clientHeight-30)	
			});
		
		}else{
		//非ie6	
		$(".bottomnav").css({
		"position":"fixed",
		"bottom":"0",
		"top":"auto"	
		})
		}
	}
},
"bindNavEvent":function(pathname){
	var path = pathname.split("/").slice(2),nav=[],curNav,getId = function(s){return "n-"+encodeURIComponent(s).replace(/%/g,".");};
	$("#mw-panel").find(".portal").css("margin-top","-1px");//.find(".body").hide();//@raphealguo
	$("#mw-panel").find("h5").click(function(){
		var t = $(this),b = t.next(".body"),p = t.parent();
		if (b.is(":visible")){
			b.slideUp("fast");
			p.removeClass("active");
		}else{
			b.find("li").find("em").addClass("none");
			b.slideDown("fast",function(){
				b.find("li").find("em").removeClass("none");
			});
			p.addClass("active");
		}
		$(this).parent().siblings(".portal").removeClass("active").find(".body").slideUp("fast");
	});
	if (path[0]){
		path[0] = decodeURIComponent(path[0]);
		nav[0] = getId(path[0]);
		nav[1] = getId(path[0]+"[热]");
		nav[2] = getId(path[0]+"[新]");
		nav[3] = getId(path[0]+"[荐]");
		
		$.each(nav,function(i,v){
			if ($("li[id="+v+"]").size()){
				curNav = $("li[id="+v+"]");
			}
		});
		
		if (curNav){
			curNav.addClass("active");
			curNav.parent().parent().show().prev("h5");
		}
		
		if ("api文档" === path[0].toLowerCase() && path.length>1){
			$('<a href="http://support.qq.com/discuss/857_1.shtml" target="_blank" style="position:absolute;top:160px;left:50%;margin-left:315px;width:130px;height:28px;line-height:28px;color:#2573A2;display:block;background:url("http://10.150.163.211/wiki/js/http://mat1.gtimg.com/app/opent/images/wiki/resource/btn_bg.gif") no-repeat;text-align:center;text-decoration:none;font-size:12px;">API接口问题反馈</a>').appendTo($("#content"));
		}
	}
},
"initDoc":function(){
	$("#bodyContent").find("a").each(function(){
		var url = $(this).attr("href");
		if (/^(http|https):\/\//.test(url)){
			$(this).attr("target","_blank");
			if ($(this).attr("href").search(/download/)>0){
				$(this).bind("click",function(event){
					var t=$(this),link=$(this).attr("href"),fileName = link.slice(link.lastIndexOf("/")+1);
					var img = new Image(1,1);
						img.src=['http://btrace.qq.com/collect',
						'?sIp=',wiki.u.ip||0,
						'&iQQ=',wiki.u.hdlogin,
						'&sBiz=','weibo.open.wiki',
						'&sOp=','/download/',
						'&iSta=',0,
						'&iTy=',1300,
						'&iFlow=',+new Date(),
						'&iFrom=',encodeURIComponent(document.reffer||''),
						'&iPubFrom=',encodeURIComponent(link),
						'&sUrl=',encodeURIComponent(location.href),
						'&iUrlType=','wiki',
						'&iPos=',0,
						'&sText=','wiki下载统计',
						'&iBak1=',0,
						'&iBak2=',0,
						'&sBak1=','',
						'&sBak2=',''
						].join('');
				});
			}
		}
		
	});
	$("#bodyContent").find(".paratable").find("a").each(function(){
		var url = $(this).attr("href");
		if (/^[^#]/.test(url)){
		$(this).attr("target","_blank");
		}
	});
	wiki.onscroll();
	$(".bottomnav").fadeIn("slow");
}
};

$(function(){
$.getScript("http://test.open.t.qq.com/controller.php?action=req_login_info&callback=wiki.login&t="+(new Date().getTime()));
wiki.docElement=document.documentElement||document.body;
wiki.bindNavEvent(location.pathname);
setTimeout(function(){
	wiki.contentHeight=65/*35+30*/+$(".mainwrapper").height()-wiki.docElement.clientHeight;
	window.onscroll=wiki.onscroll;
	wiki.initDoc();
},1000);
$("body").mouseover(function(event){
	var target = event.target,
		subnavlist = $(".subnav"),
		currentSubNav = (function(){
			for(var i=0,k=subnavlist.length;i<k;i++){
				if ($.contains(subnavlist[i],target) || target === subnavlist[i]){
					return subnavlist[i];
				}
			}
			return false;
		})();
	if (currentSubNav){
		$(currentSubNav).addClass("subnav_hover");
	}else{
		subnavlist.removeClass("subnav_hover");
	}
});
$("#header").find(".topNav").find("li:eq(5)").find("a").attr("target","_blank");//论坛用新窗口打开
});/*  |xGv00|d27908bc48ea20990ffb0146fbbf9496 */