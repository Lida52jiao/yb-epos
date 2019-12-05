package com.yjkj;

import com.miitang.gateway.sdk.MtGatewaySdk;
import com.yjkj.constant.YbConstant;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class YbSDKRunner implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        System.out.println("MtGatewaySdk.init");
        MtGatewaySdk.init(YbConstant.sdkKey,YbConstant.parentMerchantNo);
    }
}