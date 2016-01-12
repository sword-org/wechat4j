package org.sword.wechat4j.user;

import com.alibaba.fastjson.annotation.JSONField;
import org.sword.lang.HttpUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.net.URLEncoder;

/**
 * 二维码
 * @author Zhangxs
 * @version 2015-7-5
 */
public class Qrcode {
    //通过ticket换取二维码
    private static final String SHOWQRCODE_POST_URL = "https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=";
	private String ticket;//	获取的二维码ticket，凭借此ticket可以在有效时间内换取二维码。
	private Integer expireSeconds;//	二维码的有效时间，以秒为单位。最大不超过1800。
	private String url;//	二维码图片解析后的地址，开发者可根据该地址自行生成需要的二维码图片
	public String getTicket() {
		return ticket;
	}
	public void setTicket(String ticket) {
		this.ticket = ticket;
	}
	@JSONField(name="expire_seconds")
	public Integer getExpireSeconds() {
		return expireSeconds;
	}
	@JSONField(name="expire_seconds")
	public void setExpireSeconds(Integer expireSeconds) {
		this.expireSeconds = expireSeconds;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}

    /**
     * 换取二维码
     * @param qrcodeFile 二维码存储路径
     */
    public void getQrcode(String qrcodeFile){
        try {
            byte[] b = HttpUtils.getFile(SHOWQRCODE_POST_URL + URLEncoder.encode(ticket, "UTF-8"));
            File file = new File(qrcodeFile);
            FileOutputStream fStream = new FileOutputStream(file);
            fStream.write(b);
            fStream.flush();
            fStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 通过二维码获取二维码URL
     * @return
     */
    public String getQrcodeUrl(){
        return SHOWQRCODE_POST_URL + ticket;
    }
	
}
