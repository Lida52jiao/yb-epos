package com.miitang.gateway.sdk.util;

import net.sf.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 类描述: 签名工具类<br>
 */
public class SignUtil {

    /**
     * 签名
     * @param data 创建签名
     * @param aesKey 密钥
     * @return
     */
    public static String createSignData(String data, String aesKey) {
        return createQueryStr(data) + "&md5key=" + aesKey;
    }

    @SuppressWarnings("unchecked")
    public static String createQueryStr(String data) {
        Map<String,Object> json = JSONObject.fromObject(data);
        return orderedEndKey(json);
    }

    public static String orderedEndKey(Map<String,Object> json) {
        List<String> keys = new ArrayList(json.keySet());
        StringBuilder sbuf = new StringBuilder("");
        String[] arrayToSort = keys.toArray(new String[0]);
        Arrays.sort(arrayToSort, String.CASE_INSENSITIVE_ORDER);
        for (int i = 0; i < arrayToSort.length; i++) {
            String key = arrayToSort[i];
            Object value = json.get(key);
            if (value != null && !"".equals(value.toString()) && !"null".equals(value.toString())) {
                sbuf.append(key).append("=").append(value.toString()).append("&");
            }
        }
        String result = sbuf.toString();
        if (result.length()>0){
            result = result.substring(0, result.length() - 1);
        }
        return result;
    }
}