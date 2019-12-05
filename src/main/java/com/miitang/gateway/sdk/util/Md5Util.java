package com.miitang.gateway.sdk.util;

import org.apache.commons.codec.binary.Hex;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 类描述: MD5签名工具类<br>
 */
public class Md5Util {

    /**
     * MD5签名工具类
     * @param s
     * @return
     */
    public static String MD5(String s) {
        byte[] btInput = s.getBytes(Charset.forName("UTF-8"));
        // 获得MD5摘要算法的 MessageDigest 对象
        try {
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(btInput);
            // 获得密文
            byte[] md = mdInst.digest();
            return new String(Hex.encodeHex(md, false));
        } catch (NoSuchAlgorithmException e) {
            //ignore
            return "";
        }
    }

}
