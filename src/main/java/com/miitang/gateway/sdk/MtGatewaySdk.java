package com.miitang.gateway.sdk;

import com.miitang.gateway.sdk.dto.req.BaseRequest;
import com.miitang.gateway.sdk.exception.MtSdkException;
import com.miitang.gateway.sdk.io.ClassLoaderUtils;
import com.miitang.gateway.sdk.util.HandlerParametersUtil;
import com.miitang.gateway.sdk.util.JsonUtil;
import com.miitang.gateway.sdk.util.MapUtils;
import com.miitang.gateway.sdk.util.StringUtil;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * 类描述: mtSdk<br>
 */
public class MtGatewaySdk {

    private static final Logger log = LoggerFactory.getLogger(MtGatewaySdk.class);

    private static final int OK = 200;

    /**
     * 初始化标记
     */
    private static volatile boolean initFlag = false;

    /**
     * sdk密钥
     */
    private static String sdkKey;

    /**
     * 环境
     */
    private static String env = "PD";

    /**
     * 运营商编号
     */
    private static String parentMerchantNo;

    /**
     * baseUrl
     */
    private static Map<String, String> baseUrlMap;

    /**
     * 请求客户端
     */
    private static OkHttpClient httpPost;

    private static final String QA = "QA";

    private static final String PD = "PD";

    static {
        baseUrlMap = new HashMap();
        baseUrlMap.put(QA,"http://10.151.30.4:8989");
        baseUrlMap.put(PD,"https://open.miitang.com");
        baseUrlMap = Collections.unmodifiableMap(baseUrlMap);
        try {
            String path = null;
            Enumeration<URL> pathFirst = ClassLoaderUtils.getClassLoader()
                    .getResources("mt-gateway-sdk.properties");
            Enumeration<URL> pathSecond = ClassLoaderUtils.getClassLoader()
                    .getResources("config/mt-gateway-sdk.properties");
            if (pathFirst != null && pathFirst.hasMoreElements()){
                path = pathFirst.nextElement().getFile();
            }else if (pathSecond != null && pathSecond.hasMoreElements()){
                path = pathSecond.nextElement().getFile();
            }
            if (path != null){
                Properties properties = new Properties();
                properties.load(new FileInputStream(new File(path)));
                init(properties.getProperty("env"), properties.getProperty("sdkKey"), properties.getProperty("parentMerchantNo"));
            }
        } catch (IOException e) {
            throw new MtSdkException("初始化MT sdk配置文件失败！");
        }
        httpPost = new OkHttpClient().newBuilder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .build();
    }

    /**
     * 初始化sdk
     * @param sdkKey
     * @param parentMerchantNo
     */
    public synchronized static void init(String sdkKey, String parentMerchantNo){
        init(PD,sdkKey,parentMerchantNo);
    }

    /**
     * 初始化sdk
     * @param sdkKey
     * @param env
     */
    public synchronized static void init(String env, String sdkKey, String parentMerchantNo){
        if (MtGatewaySdk.initFlag){
            throw new MtSdkException("初始化MT sdk, 不可重复初始化！");
        }
        if (StringUtil.isBlank(sdkKey)){
            throw new MtSdkException("初始化MT sdk sdkKey不能未空！");
        }
        if (StringUtil.isBlank(parentMerchantNo)){
            throw new MtSdkException("初始化MT sdk parentMerchantNo不能未空！");
        }
        if (!StringUtil.isBlank(env)){
            MtGatewaySdk.env = env;
        }
        MtGatewaySdk.sdkKey = sdkKey;
        MtGatewaySdk.parentMerchantNo = parentMerchantNo;
        MtGatewaySdk.initFlag = true;
    }

    public static <T> T request(BaseRequest baseRequest, Class<T> clazz) throws IOException {
        if (!initFlag){
            throw new MtSdkException("MT sdk 未初始化！请先初始化sdk配置！");
        }
        String url = baseUrlMap.get(env) + "/" + baseRequest.requestUrl();
        if (log.isDebugEnabled()){
            log.debug("Mt 请求地址：{},请求报文：{}", url, baseRequest);
        }
        // 声明httpPost请求
        Map<String, String> map = null;
        if (baseRequest.needEncryption()){
            map = HandlerParametersUtil.encryption(JsonUtil.objectMapper.writeValueAsString(baseRequest), sdkKey);
        }else{
            map = new HashMap<>();
            Map<String,String> data = MapUtils.convertToAscOrderMap(baseRequest);
            data.put("parentMerchantNo",parentMerchantNo);
            map.put("data",JsonUtil.objectMapper.writeValueAsString(data));
        }
        map.put("parentMerchantNo",parentMerchantNo);
        // 声明存放参数的List集合
        FormBody.Builder formBodyBuilder = new FormBody.Builder();
        // 遍历map，设置参数到list中
        for (Map.Entry<String, String> entry : map.entrySet()) {
            formBodyBuilder.add(entry.getKey(), entry.getValue());
        }
        // 创建form表单对象
        FormBody formBody = formBodyBuilder.build();
        Request request = new Request
                .Builder()
                .post(formBody)
                .url(url)
                .build();
        try (Response response = httpPost.newCall(request).execute()) {
            //发送Post,并返回一个HttpResponse对象,如果状态码为200,就是正常返回
            if (response.code() == OK) {
                String result = response.body().string();
                if (log.isDebugEnabled()){
                    log.debug("响应内容：{}", result);
                }
                return JsonUtil.objectMapper.readValue(result,clazz);
            }
        }catch (Exception e){
           log.error("响应错误", e);
        }
        return null;
    }


    public static <T> T response(String json, Class<T> clazz) throws IOException {
        if (!initFlag){
            throw new MtSdkException("MT sdk 未初始化！请先初始化sdk配置！");
        }
        Map<String, String> data = HandlerParametersUtil.decryption(json, parentMerchantNo, sdkKey);
        return JsonUtil.objectMapper.readValue(data.get("data"), clazz);
    }

}
