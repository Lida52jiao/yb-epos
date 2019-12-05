package com.miitang.gateway.sdk.dto.res;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 类描述: 创建订单返回<br>
 */
@Getter
@Setter
@ToString(callSuper = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateOrderRes extends BaseResponse{
    /**
     * 业务方标识
     */
    private String bizSystemNo;

    /**
     * 平台商商户号
     */
    private String parentMerchantNo;

    /**
     * 子商户商户号
     */
    private String merchantNo;

    /**
     * 商户订单号
     */
    private String requestNo;
    /**
     * 收银台地址
     */
    private String payUrl;
    /**
     * 订单金额
     */
    private String orderAmount;
    /**
     * 订单号
     */
    private String uniqueOrderNo;

    /**
     * 绑卡流水号
     */
    private String uniqueBindNo;
}
