package com.miitang.gateway.sdk.dto.res;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 类描述: 查询订单出参<br>
 */
@Getter
@Setter
@ToString(callSuper = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class QueryOrderRes extends BaseResponse {
    /**
     * 订单状态
     */
    private String orderStatus;
    /**
     * 提现状态
     */
    private String cashStatus;
    /**
     * 订单金额
     */
    private String orderAmount;
    /**
     * 提现到账金额
     */
    private String cashAmount;
    /**
     * 到账时间
     */
    private String cashSuccessDate;
    /**
     * 支付成功时间
     */
    private String paySuccessDate;
    /**
     * 交易手续费
     */
    private String payFee;
    /**
     * 订单号
     */
    private String uniqueOrderNo;
    /**
     * 支付方式
     */
    private String payWay;
    /**
     * 支付版本
     */
    private String payVersion;
}
