package org.sword.wechat4j.util;

import java.util.Random;

/**
 * 随机字符串生成器
 */
public class RandomStringGenerator {

    private static final int defaultLength = 32;
    private static final String CHARACTERS = "abcdefghijklmnopqrstuvwxyz0123456789";

    /**
     * 获取一定长度的随机字符串
     *
     * @param length 指定字符串长度
     * @return 一定长度的字符串
     */
    public static String generate(int length) {
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(CHARACTERS.length());
            sb.append(CHARACTERS.charAt(number));
        }
        return sb.toString();
    }

    /**
     * 获取默认长度的随机字符串
     *
     * @return 默认长度的字符串
     */
    public static String generate() {
        return generate(defaultLength);
    }

}
