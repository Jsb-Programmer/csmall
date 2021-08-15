package com.cskaoyan.utils;

import java.security.MessageDigest;

/**
 * @description:
 * @author: Woo
 * @create: 2021-08-14 19:58
 **/

public class MD5Utils {

    private static String getMd5(String content) throws Exception {
        content = "@" + content + "&";
        MessageDigest md5 = MessageDigest.getInstance("md5");
        byte[] contentBytes = content.getBytes();
        byte[] resultBytes = md5.digest(contentBytes);
        StringBuffer stringBuffer = new StringBuffer();
        for (byte resultByte : resultBytes) {
            int temp = resultByte & 0xff;
            String s = Integer.toHexString(temp);
            if (s.length() == 1) {
                stringBuffer.append(0);
            }
            stringBuffer.append(s);
        }
        return stringBuffer.toString();
    }

    public static String encrypt(String str) throws Exception {
        return getMd5(getMd5(getMd5(str + "#") + "$") + "@");
    }

}
