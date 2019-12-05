package com.miitang.gateway.sdk.dto.req;

import com.miitang.gateway.sdk.dto.res.CreateOrderRes;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 类描述: 创建订单入参<br>
 */
@Getter
@Setter
@ToString(callSuper = true)
public class CreateOrderInfoReq extends BaseRequest{


    @Override
    public String requestUrl() {
        return "v1/trade/createOrder";
    }

    @Override
    public boolean needEncryption() {
        return true;
    }

    @Override
    public Class<CreateOrderRes> responseType() {
        return CreateOrderRes.class;
    }

    /**
     * 子商户号
     */
    private String merchantNo;

    /**
     * 商户订单号
     */
    private String requestNo;

    /**
     * 订单金额
     */
    private String orderAmount;
    /**
     * 产品名称
     */
    private String productName;

    /**
     * 产品描述
     */
    private String productDesc;
    /**
     * 支付方式
     */
    private String payWay;
    /**
     * 回调地址
     */
    private String callBackURL;

    /**
     * 交易方式
     */
    private String salesWay;
    /**
     * 支付卡号
     */
    private String payBankCardNo;
    /**
     * 银行预留手机号
     */
    private String payBankPhoneNo;
    /**
     * 经度
     */
    private String lng;
    /**
     * 维度
     */
    private String lat;
    /**
     * 前端回调地址
     */
    private String frontCallBackUrl;

    /**
     * 支付人身份证号
     */
    private String payIdCardNo;

    /**
     * 支付人姓名
     */
    private String payIdCardName;

    /**
     * 支付卡cvv2
     */
    private String cvv2;

    /**
     * 支付卡有效期
     */
    private String expireDate;
}
