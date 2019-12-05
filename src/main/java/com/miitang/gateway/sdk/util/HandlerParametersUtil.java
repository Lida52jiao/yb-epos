package com.miitang.gateway.sdk.util;


import com.miitang.gateway.sdk.exception.MtSdkException;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * 类描述: 入参加密，出参解密处理<br>
 */
public class HandlerParametersUtil {

    /**
     * 对入参进行加密
     * @param data 要加密的入参
     * @param sdkKey sdk秘钥
     * @return 签名和加密后的参数
     */
    public static Map<String, String> encryption(String data, String sdkKey) {
        Map<String, String> sendParam = new HashMap<>(1<<2);
        byte[] bytes;
        try {
            bytes = AESUtil.encryptAES(data.getBytes(StandardCharsets.UTF_8),
                    sdkKey.getBytes(),
                    true, "UTF-8");
            sendParam.put("data", new String(bytes));
            sendParam.put("signData", Md5Util.MD5(SignUtil.createSignData(data, sdkKey)));
        } catch (Exception e) {
            e.printStackTrace();
            throw new MtSdkException("参数加密失败!");
        }
        return sendParam;
    }

    @SuppressWarnings("unckecked")
    public static Map<String,String> decryption(String json, String parentMerchantNo, String sdkKey){
        Map<String,String> map = null;
        try {
            map = (Map<String,String>)JsonUtil.objectMapper.readValue(json, Map.class);
        } catch (IOException e) {
            throw new MtSdkException("json 转换失败!");
        }
        String singData = map.get("signData");
        String data = map.get("data");
        String returnParentMerchantNo = map.get("parentMerchantNo");
        if (!parentMerchantNo.equals(returnParentMerchantNo)){
            throw new MtSdkException("解密失败!");
        }
        String decryRes = null;
        try {
            decryRes = decryData(URLDecoder.decode(data,"UTF-8"), sdkKey);
        } catch (UnsupportedEncodingException e) {
            throw new MtSdkException("解密失败!");
        }
        verifySing(singData, sdkKey, decryRes);
        map.put("data",decryRes);
        return map;
    }



    /**
     * 解密
     * @param data
     * @param sdkKey
     * @return
     */
    private static String decryData(String data, String sdkKey) {
        byte[] resBit;
        String decryRes;
        try {
            resBit = AESUtil.decryptAES(data.getBytes(StandardCharsets.UTF_8), sdkKey.getBytes(), true, "UTF-8");
        } catch (Exception e) {
            throw new MtSdkException("解密失败");
        }
        decryRes = new String(resBit);
        return decryRes;
    }

    /**
     * 验证签名
     * @param singData
     * @param sdkKey
     * @param decryRes
     */
    private static void verifySing(String singData, String sdkKey, String decryRes) {
        String resSign = Md5Util.MD5(SignUtil.createSignData(decryRes, sdkKey));
        if (!resSign.equals(singData)) {
            throw new MtSdkException("签名验证失败");
        }
    }

}
