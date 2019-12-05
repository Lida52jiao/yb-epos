package com.miitang.gateway.sdk.util;

/**
 * 类描述: String工具类<br>
 */
public class StringUtil {

    public static boolean isBlank(String str){
        return str == null || "".equals(str.trim());
    }

}
