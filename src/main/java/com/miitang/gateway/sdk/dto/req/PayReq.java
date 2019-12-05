package com.miitang.gateway.sdk.dto.req;

import com.miitang.gateway.sdk.dto.res.PayRes;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 类描述: 创建订单入参<br>
 */
@Getter
@Setter
@ToString(callSuper = true)
public class PayReq extends BaseRequest{


    @Override
    public String requestUrl() {
        return "v1/trade/pay";
    }

    @Override
    public boolean needEncryption() {
        return true;
    }

    @Override
    public Class<PayRes> responseType() {
        return PayRes.class;
    }
      /**
     * 商户编号
     */
    private String merchantNo;
    /**
     * 绑定流水号
     */
    private String uniqueBindNo;
    /**
     * 短信验证码
     */
    private String smsCode;
    /**
     * 蜜堂订单号
     */
    private String uniqueOrderNo;
    /**
     * 订单金额
     */
    private String orderAmount;
    /**
     * ip
     */
    private String ip;
    /**
     * 经度
     */
    private String lng;
    /**
     * 纬度
     */
    private String lat;
}
