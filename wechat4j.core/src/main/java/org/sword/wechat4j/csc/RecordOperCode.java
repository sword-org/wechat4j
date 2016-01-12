package org.sword.wechat4j.csc;

import java.util.HashMap;
import java.util.Map;
/**
 * 操作ID(会话状态)说明
 * @author Zhangxs
 * @date 2015-7-8 
 * @version
 */
public class RecordOperCode {
    private final static Map<Integer, String> operCodeMap = new HashMap<Integer, String>();

	static {
		 operCodeMap.put(1000,"创建未接入会话");
		 operCodeMap.put(1001,"接入会话");
		 operCodeMap.put(1002,"主动发起会话");
		 operCodeMap.put(1004,"关闭会话");
		 operCodeMap.put(1005,"抢接会话");
		 operCodeMap.put(2001,"公众号收到消息");
		 operCodeMap.put(2002,"客服发送消息");
		 operCodeMap.put(2003,"客服收到消息");
	}
	/**
	 * 根据opercode返回会话状态
	 * @param opercode
	 * @return
	 */
	public static String getSessionState(int opercode){
		if (operCodeMap.containsKey(opercode)) {
            return operCodeMap.get(opercode);
        }
        return "";
	}
}
