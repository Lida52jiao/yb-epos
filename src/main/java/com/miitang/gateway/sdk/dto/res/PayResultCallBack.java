package com.miitang.gateway.sdk.dto.res;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 类描述: <br>
 */
@Getter
@Setter
@ToString(callSuper = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PayResultCallBack extends BaseResponse{

    private String parentMerchantNo;

    private String merchantNo;

    private String requestNo;

    private String orderStatus;

    private String cashStatus;

    private String orderAmount;

    private String cashAmount;

    private String settleType;

    private String cashSuccessDate;

    private String paySuccessDate;

    private String payFee;

}
