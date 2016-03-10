/**
 *
 */
package org.sword.wechat4j.user;

import com.alibaba.fastjson.JSONObject;
import org.apache.log4j.Logger;
import org.sword.lang.HttpUtils;
import org.sword.wechat4j.exception.WeChatException;
import org.sword.wechat4j.token.TokenProxy;
import org.sword.wechat4j.util.WeChatUtil;

/**
 * 账户管理
 *
 * @author Zhangxs
 * @version 2015-7-5
 */
public class AccountManager {
    Logger logger = Logger.getLogger(AccountManager.class);

    //长链接转短链接接口
    private static final String SHORTURL_POST_URL = "https://api.weixin.qq.com/cgi-bin/shorturl?access_token=";
    //生成带参数的二维码
    private static final String QRCODE_POST_URL = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=";

    /**
     * 长链接转短链接接口
     *
     * @param longUrl 需要转换的长链接
     * @return
     */
    public String shortUrl(String longUrl) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("action", "long2short");
        jsonObject.put("long_url", longUrl);
        String requestData = jsonObject.toString();
        logger.info("request data " + requestData);
        String resultStr = HttpUtils.post(SHORTURL_POST_URL + TokenProxy.accessToken(), requestData);
        logger.info("return data " + resultStr);
        try {
            WeChatUtil.isSuccess(resultStr);
        } catch (WeChatException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
            return null;
        }
        JSONObject resultJson = JSONObject.parseObject(resultStr);
        return resultJson.getString("short_url");
    }

    /**
     * 创建永久二维码
     *
     * @param sceneId 场景值ID,目前参数只支持1--100000
     * @return
     */
    public Qrcode createQrcodePerpetual(long sceneId) {
        return createQrcodeTicket(QrcodeType.QR_LIMIT_SCENE, null, sceneId, null);
    }

    /**
     * 创建永久二维码
     *
     * @param sceneStr 场景值ID,长度限制为1到64
     * @return
     */
    public Qrcode createQrcodePerpetualstr(String sceneStr) {
        return createQrcodeTicket(QrcodeType.QR_LIMIT_STR_SCENE, null, null, sceneStr);
    }

    /**
     * 创建临时二维码
     *
     * @param sceneId       场景值ID
     * @param expireSeconds 二维码有效时间，以秒为单位,最大不超过604800（即7天）。
     * @return
     */
    public Qrcode createQrcodeTemporary(long sceneId, int expireSeconds) {
        return createQrcodeTicket(QrcodeType.QR_SCENE, expireSeconds, sceneId, null);
    }

    private Qrcode createQrcodeTicket(QrcodeType qrcodeType, Integer expireSeconds, Long sceneId, String sceneStr) {
        JSONObject ticketJson = new JSONObject();
        ticketJson.put("action_name", qrcodeType);
        JSONObject sceneJson = new JSONObject();
        switch (qrcodeType) {
            case QR_SCENE:
                ticketJson.put("expire_seconds", expireSeconds);
                sceneJson.put("scene_id", sceneId);
                break;
            case QR_LIMIT_SCENE:
                sceneJson.put("scene_id", sceneId);
                break;
            case QR_LIMIT_STR_SCENE:
                sceneJson.put("scene_str", sceneStr);
                break;
        }
        JSONObject actionInfoJson = new JSONObject();
        actionInfoJson.put("scene", sceneJson);
        ticketJson.put("action_info", actionInfoJson);
        String requestData = ticketJson.toString();
        logger.info("request data " + requestData);
        String resultStr = HttpUtils.post(QRCODE_POST_URL + TokenProxy.accessToken(), requestData);
        logger.info("return data " + resultStr);
        try {
            WeChatUtil.isSuccess(resultStr);
        } catch (WeChatException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
            return null;
        }
        Qrcode qrcode = JSONObject.parseObject(resultStr, Qrcode.class);
        return qrcode;
    }

}
